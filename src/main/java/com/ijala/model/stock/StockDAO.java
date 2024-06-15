package com.ijala.model.stock;

import com.ijala.database.DatabaseConnection;
import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

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

    public void addInStock(Stock estoque) {
        String sql = "INSERT INTO estoque (produto_id, estoque_inicial, entrada, saida, estoque_final) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getProduto().getId());
            stmt.setInt(2, estoque.getEstoque_inicial());
            stmt.setTimestamp(3, new Timestamp(estoque.getEntrada().getTime()));
            stmt.setTimestamp(4, new Timestamp(estoque.getSaida().getTime()));
            stmt.setInt(5, estoque.getEstoque_final());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar no estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Stock> listStock() {
        String sql = "SELECT * FROM estoque";
        List<Stock> estoques = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new ProdutoDAO().buscarProdutoPorId(rs.getInt("produto_id")); // Supondo que você tenha um ProdutoDAO para buscar produtos
                Stock estoque = new Stock(
                        produto,
                        rs.getInt("estoque_inicial"),
                        rs.getTimestamp("entrada"),
                        rs.getTimestamp("saida"),
                        rs.getInt("estoque_final")
                );
                estoque.setId(rs.getInt("id"));
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar estoques: " + e.getMessage());
            e.printStackTrace();
        }
        return estoques;
    }

    public void deleteStock(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
