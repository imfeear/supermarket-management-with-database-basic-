package com.ijala.model.category;

import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                categories.add(new Category(id, name));
            }
        }
        return categories;
    }

    public Category getCategoryById(int id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("nome");
                    return new Category(id, name);
                }
            }
        }
        return null;
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
