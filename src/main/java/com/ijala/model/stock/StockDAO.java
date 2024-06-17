package com.ijala.model.stock;

import com.ijala.database.DatabaseConnection;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private Connection connection;

    public StockDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addInStock(Stock stock) { // Alterado para público
        String sql = "INSERT INTO estoque (produto_id, estoque_inicial, entrada, estoque_final, saida) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stock.getProduct().getId());
            stmt.setInt(2, stock.getInitialStock());
            stmt.setTimestamp(3, new Timestamp(stock.getEntry().getTime()));
            stmt.setInt(4, stock.getFinalStock());
            stmt.setNull(5, Types.TIMESTAMP); // Define a data de saída como nula
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar no estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Stock> listProductInStock() {
        String sql = "SELECT * FROM estoque ORDER BY id ASC";
        List<Stock> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new ProductDAO().searchProductById(rs.getInt("produto_id"));
                Timestamp saida = rs.getTimestamp("saida");
                Stock stock = new Stock(
                        product,
                        rs.getInt("estoque_inicial"),
                        rs.getTimestamp("entrada"),
                        saida,
                        rs.getInt("estoque_final")
                );
                stock.setId(rs.getInt("id"));
                products.add(stock);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar estoques: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public void deleteProductInStock(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Stock getStockByProductId(int productId) {
        String sql = "SELECT * FROM estoque WHERE produto_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new ProductDAO().searchProductById(productId);
                    Timestamp saida = rs.getTimestamp("saida");
                    Stock stock = new Stock(
                            product,
                            rs.getInt("estoque_inicial"),
                            rs.getTimestamp("entrada"),
                            saida,
                            rs.getInt("estoque_final")
                    );
                    stock.setId(rs.getInt("id"));
                    return stock;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar estoque por ID do produto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void addOrUpdateStock(Stock stock) {
        Stock existingStock = getStockByProductId(stock.getProduct().getId());

        if (existingStock != null) {
            // Se o estoque já existe, atualize-o
            updateStock(existingStock, stock);
        } else {
            // Se não existe, adicione um novo registro
            addInStock(stock);
        }
    }

    private void updateStock(Stock oldStock, Stock newStock) {
        // Lógica para atualizar os valores do estoque existente com base no novo estoque
        oldStock.setInitialStock(newStock.getInitialStock());
        oldStock.setEntry(newStock.getEntry());
        oldStock.setExit(newStock.getExit());
        oldStock.setFinalStock(newStock.getFinalStock());

        String sql = "UPDATE estoque SET estoque_inicial = ?, entrada = ?, saida = ?, estoque_final = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, oldStock.getInitialStock());
            stmt.setTimestamp(2, new Timestamp(oldStock.getEntry().getTime()));
            stmt.setTimestamp(3, oldStock.getExit() != null ? new Timestamp(oldStock.getExit().getTime()) : null);
            stmt.setInt(4, oldStock.getFinalStock());
            stmt.setInt(5, oldStock.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
