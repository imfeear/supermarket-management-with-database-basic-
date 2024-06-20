package com.ijala.controller;

import com.ijala.model.movement.Movement;
import com.ijala.model.movement.MovementDAO;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.stock.StockDAO;
import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class MovementController {
    private ProductDAO productDAO;
    private MovementDAO movementDAO;
    private StockDAO stockDAO;
    private Connection connection;

    public MovementController() {
        this.productDAO = new ProductDAO();
        this.movementDAO = new MovementDAO(productDAO);
        this.stockDAO = new StockDAO(productDAO);
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void buy(int id, int quantity) throws Exception {
        try {
            connection.setAutoCommit(false); // Inicia a transação
            Product product = productDAO.searchProductById(id);

            if (product == null) {
                throw new Exception("Produto não encontrado com o ID: " + id);
            }
            if (quantity <= 0) {
                throw new Exception("Quantidade inválida: " + quantity);
            }

            product.setQuantity(product.getQuantity() + quantity);
            productDAO.updateQuantity(product);

            // Registra a movimentação de entrada no estoque
            Movement movement = new Movement(id, quantity, new Date(System.currentTimeMillis()), "entrada", product);
            movementDAO.addMovement(movement);

            // Atualiza ou adiciona o estoque com a nova entrada
            stockDAO.updateOrAddStock(product, quantity, "entrada");

            connection.commit(); // Finaliza a transação

        } catch (Exception e) {
            try {
                connection.rollback(); // Desfaz a transação em caso de erro
            } catch (SQLException rollbackException) {
                System.err.println("Erro ao desfazer transação: " + rollbackException.getMessage());
                rollbackException.printStackTrace();
            }
            throw new Exception("Erro ao realizar compra: " + e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true); // Restaura o comportamento padrão de autocommit
            } catch (SQLException autoCommitException) {
                System.err.println("Erro ao restaurar autocommit: " + autoCommitException.getMessage());
                autoCommitException.printStackTrace();
            }
        }
    }

    public void sell(int id, int quantity) throws Exception {
        try {
            connection.setAutoCommit(false); // Inicia a transação
            Product product = productDAO.searchProductById(id);
            if (product == null) {
                throw new Exception("Produto não encontrado com o ID: " + id);
            }
            if (quantity <= 0) {
                throw new Exception("Quantidade inválida: " + quantity);
            }
            if (product.getQuantity() < quantity) {
                throw new Exception("Quantidade insuficiente em estoque para vender: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - quantity);
            productDAO.updateQuantity(product);

            // Registra a movimentação de saída no estoque
            Movement movement = new Movement(id, quantity, new Date(System.currentTimeMillis()), "saida", product);
            movementDAO.addMovement(movement);

            // Atualiza o estoque
            stockDAO.updateOrAddStock(product, quantity, "saida");

            connection.commit(); // Finaliza a transação

        } catch (Exception e) {
            try {
                connection.rollback(); // Desfaz a transação em caso de erro
            } catch (SQLException rollbackException) {
                System.err.println("Erro ao desfazer transação: " + rollbackException.getMessage());
                rollbackException.printStackTrace();
            }
            throw new Exception("Erro ao realizar venda: " + e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true); // Restaura o comportamento padrão de autocommit
            } catch (SQLException autoCommitException) {
                System.err.println("Erro ao restaurar autocommit: " + autoCommitException.getMessage());
                autoCommitException.printStackTrace();
            }
        }
    }
}
