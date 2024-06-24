package com.ijala.model.stock;

import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private Connection connection;
    private final ProductService productService;

    public StockDAO(ProductService productService) {
        this.productService = productService;
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para inicializar o estoque com a quantidade do produto cadastrada
    public void initializeStock(int productId, int initialQuantity) throws SQLException {
        String sql = "INSERT INTO estoque (produto_id, estoque_inicial, entrada, saida, estoque_final) VALUES (?, ?, ?, NULL, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, initialQuantity);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(4, initialQuantity); // Inicializa o estoque final com a mesma quantidade inicial

            stmt.executeUpdate();
        }
    }

    public void addInStock(Stock stock) {
        String sql = "INSERT INTO estoque (produto_id, estoque_inicial, entrada, saida, estoque_final) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stock.getProduct().getId());
            stmt.setInt(2, stock.getInitialStock());
            stmt.setTimestamp(3, new Timestamp(stock.getEntry().getTime()));

            // Verifica se há uma data de saída definida
            if (stock.getExit() != null) {
                stmt.setTimestamp(4, new Timestamp(stock.getExit().getTime()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP); // Define a data de saída como nula
            }

            stmt.setInt(5, stock.getFinalStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar no estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Stock> listProductInStock() {
        List<Stock> stockList = new ArrayList<>();
        String sql = "SELECT s.id as stockId, p.id as productId, p.nome, s.estoque_inicial, s.entrada, s.saida, s.estoque_final " +
                "FROM estoque s " +
                "JOIN produtos p ON s.produto_id = p.id " +
                "ORDER BY s.id ASC"; // Ordena pelo ID do estoque em ordem crescente

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int stockId = rs.getInt("stockId");
                int productId = rs.getInt("productId");
                String productName = rs.getString("nome");
                int initialStock = rs.getInt("estoque_inicial");
                Timestamp entry = rs.getTimestamp("entrada");
                Timestamp exit = rs.getTimestamp("saida");
                int finalStock = rs.getInt("estoque_final");

                // Recuperar o produto associado ao estoque
                Product product = productService.searchProductById(productId);

                // Criar o objeto Stock
                Stock stock = new Stock(product, initialStock, entry, exit, finalStock);
                stock.setId(stockId); // Define o ID do estoque

                stockList.add(stock);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos em estoque: " + e.getMessage());
            e.printStackTrace();
        }
        return stockList;
    }

    public Stock getStockByProductId(int productId) {
        String sql = "SELECT * FROM estoque WHERE produto_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int stockId = rs.getInt("id");
                    String productName = rs.getString("nome");
                    int initialStock = rs.getInt("estoque_inicial");
                    Timestamp entry = rs.getTimestamp("entrada");
                    Timestamp exit = rs.getTimestamp("saida");
                    int finalStock = rs.getInt("estoque_final");

                    // Recuperar o produto associado ao estoque
                    Product product = productService.searchProductById(productId);

                    // Criar o objeto Stock
                    Stock stock = new Stock(product, initialStock, entry, exit, finalStock);
                    stock.setId(stockId); // Define o ID do estoque

                    return stock;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar estoque por ID do produto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

//    public void updateStock(Stock oldStock, Stock newStock) {
//        // Lógica para atualizar os valores do estoque existente com base no novo estoque
//        oldStock.setInitialStock(newStock.getInitialStock());
//        oldStock.setEntry(newStock.getEntry());
//        oldStock.setExit(newStock.getExit());
//        oldStock.setFinalStock(newStock.getFinalStock());
//
//        String sql = "UPDATE estoque SET estoque_inicial = ?, entrada = ?, saida = ?, estoque_final = ? WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, oldStock.getInitialStock());
//            stmt.setTimestamp(2, new Timestamp(oldStock.getEntry().getTime()));
//            stmt.setTimestamp(3, oldStock.getExit() != null ? new Timestamp(oldStock.getExit().getTime()) : null);
//            stmt.setInt(4, oldStock.getFinalStock());
//            stmt.setInt(5, oldStock.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            System.err.println("Erro ao atualizar o estoque: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void updateStock(Stock stock) {
        String sql = "UPDATE estoque SET estoque_inicial = ?, entrada = ?, saida = ?, estoque_final = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stock.getInitialStock());
            stmt.setTimestamp(2, new Timestamp(stock.getEntry().getTime()));
            stmt.setTimestamp(3, stock.getExit() != null ? new Timestamp(stock.getExit().getTime()) : null);
            stmt.setInt(4, stock.getFinalStock());
            stmt.setInt(5, stock.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o estoque: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void updateOrAddStock(Product product, int quantity, String type) {
        Stock oldStock = getStockByProductId(product.getId());

        if (oldStock != null) {
            int newFinalStock = type.equals("entrada") ? oldStock.getFinalStock() + quantity : oldStock.getFinalStock() - quantity;
            if (newFinalStock < 0) {
                throw new IllegalArgumentException("O estoque final não pode ser negativo.");
            }

            oldStock.setEntry(new Date(System.currentTimeMillis())); // Atualiza a data de entrada
            oldStock.setFinalStock(newFinalStock); // Atualiza a quantidade final no estoque

            updateStock(oldStock); // Atualiza o estoque existente
        } else {
            if (quantity < 0) {
                throw new IllegalArgumentException("A quantidade inicial não pode ser negativa.");
            }

            Stock newStock = new Stock(
                    product,
                    quantity,
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis()),
                    quantity
            );
            addInStock(newStock); // Adiciona um novo registro de estoque
        }
    }

}
