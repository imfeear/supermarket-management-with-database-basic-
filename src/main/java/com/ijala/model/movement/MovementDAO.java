package com.ijala.model.movement;

import com.ijala.database.DatabaseConnection;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

public class MovementDAO {
    private Connection connection;
    private ProductDAO productDAO;

    public MovementDAO(ProductDAO productDAO) {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
            this.productDAO = productDAO;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Movement> getAllMovements() {
        List<Movement> movements = new ArrayList<>();
        String sql = "SELECT * FROM movimentacoes_estoque ORDER BY data DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int productId = rs.getInt("produto_id");
                int quantity = rs.getInt("quantidade");
                Date date = rs.getDate("data");
                String type = rs.getString("tipo");

                Product product = productDAO.searchProductById(productId);

                Movement movement = new Movement(productId, quantity, date, type, product);
                movements.add(movement);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações de estoque: " + e.getMessage());
            e.printStackTrace();
        }
        return movements;
    }

    public void addMovement(Movement movement) {
        String sql = "INSERT INTO movimentacoes_estoque (produto_id, quantidade, data, tipo) VALUES (?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Inicia a transação

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, movement.getId());
                stmt.setInt(2, movement.getQuantity());
                stmt.setDate(3, movement.getDate());
                stmt.setString(4, movement.getType());
                stmt.executeUpdate();

                // Atualiza a quantidade do produto
                Product product = movement.getProduct();
                product.setQuantity(product.getQuantity() + movement.getQuantity());
                productDAO.updateQuantity(product);

                connection.commit(); // Confirma a transação
            } catch (SQLException e) {
                connection.rollback(); // Desfaz a transação em caso de erro
                System.err.println("Erro ao adicionar movimentação de estoque: " + e.getMessage());
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true); // Restaura o comportamento padrão
            }
        } catch (SQLException e) {
            System.err.println("Erro ao iniciar transação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
