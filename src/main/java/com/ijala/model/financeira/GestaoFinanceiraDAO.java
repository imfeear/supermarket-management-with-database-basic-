package com.ijala.model.financeira;

import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestaoFinanceiraDAO {
    private Connection connection;

    public GestaoFinanceiraDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void inserirReceita(GestaoFinanceira finance) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Receitas (data, quantidade) VALUES (?, ?)");
            statement.setDate(1, java.sql.Date.valueOf(finance.getData()));
            statement.setDouble(2, finance.getQuantidade());
            statement.executeUpdate();
            System.out.println("Receita inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir receita");
            e.printStackTrace();
        }
    }

    public void inserirDespesa(GestaoFinanceira finance) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Despesas (categoria, quantidade) VALUES (?, ?)");
            statement.setString(1, finance.getCategoria());
            statement.setDouble(2, finance.getQuantidade());
            statement.executeUpdate();
            System.out.println("Despesa inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir despesa");
            e.printStackTrace();
        }
    }

    public List<GestaoFinanceira> buscarEntradas() {
        List<GestaoFinanceira> finances = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT 'Receita' as tipo, '' as categoria, data, quantidade FROM Receitas UNION ALL SELECT 'Despesa', categoria, NULL as data, quantidade FROM Despesas");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GestaoFinanceira finance = new GestaoFinanceira();
                finance.setTipo(resultSet.getString("tipo"));
                finance.setCategoria(resultSet.getString("categoria"));
                finance.setData(resultSet.getString("data") != null ? resultSet.getString("data") : "");
                finance.setQuantidade(resultSet.getDouble("quantidade"));
                finances.add(finance);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar entradas");
            e.printStackTrace();
        }
        return finances;
    }

    public void excluirEntrada(GestaoFinanceira finance) {
        try {
            String sql;
            PreparedStatement statement;
            if ("Receita".equals(finance.getTipo())) {
                sql = "DELETE FROM Receitas WHERE data = ? AND quantidade = ?";
                statement = connection.prepareStatement(sql);
                statement.setDate(1, java.sql.Date.valueOf(finance.getData()));
                statement.setDouble(2, finance.getQuantidade());
            } else {
                sql = "DELETE FROM Despesas WHERE categoria = ? AND quantidade = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, finance.getCategoria());
                statement.setDouble(2, finance.getQuantidade());
            }
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
                System.out.println(finance.getTipo() + " excluída com sucesso");
            else
                System.out.println("Nenhuma entrada foi encontrada com os dados informados");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir entrada");
            e.printStackTrace();
        }
    }

    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
