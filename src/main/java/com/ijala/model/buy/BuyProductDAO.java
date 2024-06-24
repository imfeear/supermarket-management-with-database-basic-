package com.ijala.model.buy;

import com.ijala.database.DatabaseConnection;
import com.ijala.model.stock.StockDAO;
import com.ijala.service.ProductService;

import java.sql.*;
import java.text.SimpleDateFormat;

public class BuyProductDAO {
    private Connection connection;

    public BuyProductDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void makeBuy(BuyProduct buyProduct) {
        try {
            StockDAO stockDAO = new StockDAO(new ProductService());

            connection.setAutoCommit(false); // Inicia a transação

            // Atualizar a quantidade no estoque
            String updateQuery = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, buyProduct.getQuantity());
                updateStatement.setInt(2, buyProduct.getProduct().getId());
                updateStatement.executeUpdate();
            }

            // Registrar a compra
            String insertQuery = "INSERT INTO movimentacoes_estoque (produto_id, quantidade, data, tipo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, buyProduct.getProduct().getId());
                insertStatement.setInt(2, buyProduct.getQuantity());
                insertStatement.setDate(3, new Date(System.currentTimeMillis()));
                insertStatement.setString(4, "entrada");
                insertStatement.executeUpdate();
            }

            // Atualizar ou adicionar no estoque
            stockDAO.updateOrAddStock(buyProduct.getProduct(), buyProduct.getQuantity(), "entrada");

            // Atualizar a data de entrada no estoque
            String updateStockExitQuery = "UPDATE estoque SET entrada = ? WHERE produto_id = ?";
            try (PreparedStatement updateStockExitStatement = connection.prepareStatement(updateStockExitQuery)) {
                updateStockExitStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                updateStockExitStatement.setInt(2, buyProduct.getProduct().getId());
                updateStockExitStatement.executeUpdate();
            }

            connection.commit(); // Finaliza a transação
        } catch (Exception e) {
            try {
                connection.rollback(); // Reverte a transação em caso de erro
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Restaura o modo de auto commit
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
