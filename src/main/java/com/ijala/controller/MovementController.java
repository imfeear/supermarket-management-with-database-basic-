package com.ijala.controller;

import com.ijala.model.movement.Movement;
import com.ijala.model.movement.MovementDAO;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

import java.sql.Date;

public class MovementController {
    private ProductDAO productDAO;
    private MovementDAO movementDAO;

    public MovementController() {
        this.productDAO = new ProductDAO();
        this.movementDAO = new MovementDAO(productDAO);
    }

    public void buy(int id, int quantity) throws Exception {
        try {
            System.out.println("ID do Produto Recebido para Compra: " + id);

            Product product = productDAO.searchProductById(id);

            System.out.println("Estado do Objeto Product após Busca: " + product);

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

        } catch (Exception e) {
            throw new Exception("Erro ao realizar compra: ", e);
        }
    }

    public void sell(int id, int quantity) throws Exception {
        try {
            Product product = productDAO.searchProductById(id);
            if (product == null) {
                throw new Exception("Produto não encontrado com o ID: " + id);
            }
            if (quantity <= 0) {
                throw new Exception("Quantidade inválida: " + quantity);
            }
            if (product.getQuantity() < quantity) {
                throw new Exception("Quantidade insuficiente em estoque para comprar: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - quantity);
            productDAO.updateQuantity(product);

            // Registra a movimentação de entrada no estoque
            Movement movement = new Movement(id, quantity, new Date(System.currentTimeMillis()), "Saída", product);
            movementDAO.addMovement(movement);

        } catch (Exception e) {
            throw new Exception("Erro ao realizar venda", e);
        }
    }

}
