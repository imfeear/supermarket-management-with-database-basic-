package com.ijala.model.product;

import com.ijala.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO produtos (nome, descricao, quantidade, preco, categoria_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getCategoryId());
            stmt.setInt(6, product.getSupplierId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Product> listProducts() {
        String sql = "SELECT * FROM produtos ORDER BY id ASC";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getInt("categoria_id"),
                        rs.getInt("fornecedor_id")
                );
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public Product searchProductById(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getDouble("preco"),
                            rs.getInt("categoria_id"),
                            rs.getInt("fornecedor_id")
                    );
                    product.setId(rs.getInt("id"));
                    return product;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Product searchProductByName(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getDouble("preco"),
                            rs.getInt("categoria_id"),
                            rs.getInt("fornecedor_id")
                    );
                    product.setId(rs.getInt("id"));
                    return product;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, quantidade = ?, preco = ?, categoria_id = ?, fornecedor_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getCategoryId());
            stmt.setInt(6, product.getSupplierId());
            stmt.setInt(7, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateQuantity(Product product) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product.getQuantity());
            stmt.setInt(2, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar coluna: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
