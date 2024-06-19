package com.ijala.model.supplier;

import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO fornecedores (nome, contato) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.executeUpdate();
        }
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                String contact = rs.getString("contato");
                suppliers.add(new Supplier(id, name, contact));
            }
        }
        return suppliers;
    }

    public Supplier getSupplierById(int id) throws SQLException {
        String sql = "SELECT * FROM fornecedores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("nome");
                    String contact = rs.getString("contato");
                    return new Supplier(id, name, contact);
                }
            }
        }
        return null;
    }

    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE fornecedores SET nome = ?, contato = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.setInt(3, supplier.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteSupplier(int id) throws SQLException {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
