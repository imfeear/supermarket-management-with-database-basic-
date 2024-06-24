package com.ijala.model.sell;

import com.ijala.database.DatabaseConnection;

import java.sql.*;

public class SellProductDAO {
    private Connection connection;

    public SellProductDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean verifyProduct(SellProduct sellProduct) {
        String query = "SELECT id, quantidade FROM produtos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sellProduct.getProduct().getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int quantityInStock = resultSet.getInt("quantidade");
                    return quantityInStock >= sellProduct.getQuantity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void makeSale(SellProduct sellProduct) {
        try {
            if (!verifyProduct(sellProduct)) {
                throw new Exception("Produto não encontrado ou quantidade insuficiente no estoque.");
            }

            connection.setAutoCommit(false); // Inicia a transação

            // Atualizar a quantidade no estoque
            String updateQuery = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, sellProduct.getQuantity());
                updateStatement.setInt(2, sellProduct.getProduct().getId());
                updateStatement.executeUpdate();
            }

            // Registrar a venda
            String insertQuery = "INSERT INTO movimentacoes_estoque (produto_id, quantidade, data, tipo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, sellProduct.getProduct().getId());
                insertStatement.setInt(2, sellProduct.getQuantity());
                insertStatement.setDate(3, new Date(System.currentTimeMillis()));
                insertStatement.setString(4, "saida");
                insertStatement.executeUpdate();
            }

            // Atualizar a data de saída no estoque
            String updateStockExitQuery = "UPDATE estoque SET saida = ? WHERE produto_id = ?";
            try (PreparedStatement updateStockExitStatement = connection.prepareStatement(updateStockExitQuery)) {
                updateStockExitStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                updateStockExitStatement.setInt(2, sellProduct.getProduct().getId());
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