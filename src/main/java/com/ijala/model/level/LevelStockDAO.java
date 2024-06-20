package com.ijala.model.level;

import com.ijala.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade LevelStock.
 */
public class LevelStockDAO {
    private Connection connection;

    /**
     * Construtor para inicializar a conexão com o banco de dados.
     */
    public LevelStockDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recupera uma lista de LevelStock do banco de dados.
     *
     * @return Uma lista de objetos LevelStock.
     */
    public List<LevelStock> getLevelStocks() {
        List<LevelStock> levelStocks = new ArrayList<>();
        String sql = "SELECT p.id AS product_id, p.nome AS product_name, e.entrada AS entry_date, e.saida AS exit_date, e.estoque_final AS quantity, p.preco AS unit_price " +
                "FROM estoque e " +
                "JOIN produtos p ON e.produto_id = p.id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double unitPrice = rs.getDouble("unit_price");

                Timestamp entryDate = rs.getTimestamp("entry_date");
                Timestamp exitDate = rs.getTimestamp("exit_date");

                // Verifica se entryDate ou exitDate são null antes de calcular timeInStockMillis
                long timeInStockMillis = 0;
                if (entryDate != null && exitDate != null) {
                    timeInStockMillis = exitDate.getTime() - entryDate.getTime();
                }

                long timeInStockDays = timeInStockMillis / (1000 * 60 * 60 * 24);

                LevelStock levelStock = new LevelStock(productId, productName, timeInStockDays, quantity, unitPrice);
                levelStocks.add(levelStock);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar nível de estoque: " + e.getMessage());
            e.printStackTrace();
        }
        return levelStocks;
    }

    /**
     * Atualiza o nível de estoque de um produto.
     *
     * @param productId      ID do produto.
     * @param quantityChange Mudança na quantidade do produto.
     */
    public void updateStockLevel(int productId, int quantityChange) {
        String sql = "UPDATE estoque SET quantidade = quantidade + ? WHERE produto_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantityChange);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar nível de estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
