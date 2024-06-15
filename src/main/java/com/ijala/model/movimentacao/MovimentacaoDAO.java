package com.ijala.model.movimentacao;

import com.ijala.database.DatabaseConnection;
import com.ijala.model.produto.Produto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        String sql = "INSERT INTO movimentacoes_estoque (produto_id , quantidade  , data  , tipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getProduto().getId());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setDate(3, movimentacao.getData());
            stmt.setString(4, movimentacao.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
