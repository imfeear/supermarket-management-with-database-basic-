package com.ijala.model.venda;

import com.ijala.model.venda.ProdutoVenda;
import java.sql.*;

public class ProdutoVendaDAO {

    private Connection connect() {
        // Conexão com o banco de dados
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/supermercado", "postgres", "jonatas");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verificarProduto(ProdutoVenda produto) {
        try (Connection connection = connect()) {
            String query = "SELECT id, quantidade FROM produtos WHERE nome = ? AND fornecedor_id = ? AND categoria_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, produto.getNome());
            statement.setInt(2, Integer.parseInt(produto.getFornecedor()));
            statement.setInt(3, Integer.parseInt(produto.getCategoria()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int quantidadeNoEstoque = resultSet.getInt("quantidade");
                produto.setId(resultSet.getInt("id"));  // Atribuir o id do produto ao objeto ProdutoVenda
                return quantidadeNoEstoque >= produto.getQuantidade();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void realizarVenda(ProdutoVenda produto) {
        try (Connection connection = connect()) {
            // Verifique se o produto existe e se a quantidade é suficiente
            if (!verificarProduto(produto)) {
                throw new Exception("Produto não encontrado ou quantidade insuficiente no estoque.");
            }

            // Atualizar a quantidade no estoque
            String updateQuery = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, produto.getQuantidade());
            updateStatement.setInt(2, produto.getid());
            updateStatement.executeUpdate();

            // Registrar a venda
            String insertQuery = "INSERT INTO movimentacoes_estoque (produto_id, quantidade, data, tipo) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, produto.getid());
            insertStatement.setInt(2, produto.getQuantidade());
            insertStatement.setDate(3, new Date(System.currentTimeMillis()));
            insertStatement.setString(4, "saida");
            insertStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
