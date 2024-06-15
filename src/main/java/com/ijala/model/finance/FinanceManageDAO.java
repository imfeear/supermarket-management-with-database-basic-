package com.ijala.model.finance;

import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceManageDAO {
    private Connection connection;

    public FinanceManageDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addRecipe(FinanceManage finance) {
        String sql = "INSERT INTO receitas (data, valor) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(finance.getData()));
            statement.setDouble(2, finance.getValor());
            statement.executeUpdate();
            System.out.println("Receita inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir receita");
            e.printStackTrace();
        }
    }

    public void addExpense(FinanceManage finance) {
        String sql = "INSERT INTO despesas (categoria, valor) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, finance.getCategoria());
            statement.setDouble(2, finance.getValor());
            statement.executeUpdate();
            System.out.println("Despesa inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir despesa");
            e.printStackTrace();
        }
    }

    public List<FinanceManage> searchForEntries() {
        List<FinanceManage> finances = new ArrayList<>();
        String sql = "SELECT 'Receita' as tipo, '' as categoria, data, valor FROM Receitas UNION ALL SELECT 'Despesa', categoria, NULL as data, valor FROM Despesas";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                FinanceManage finance = new FinanceManage();
                finance.setTipo(resultSet.getString("tipo"));
                finance.setCategoria(resultSet.getString("categoria"));
                finance.setData(resultSet.getString("data") != null ? resultSet.getString("data") : "-");
                finance.setValor(resultSet.getDouble("valor"));
                finances.add(finance);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar entradas");
            e.printStackTrace();
        }
        return finances;
    }

    public int deleteEntries(FinanceManage finance) {
        try {
            String sql;
            PreparedStatement statement;
            if ("Receita".equals(finance.getTipo())) {
                sql = "DELETE FROM receitas WHERE data = ? AND valor = ?";
                statement = connection.prepareStatement(sql);
                statement.setDate(1, java.sql.Date.valueOf(finance.getData()));
                statement.setDouble(2, finance.getValor());
            } else {
                sql = "DELETE FROM despesas WHERE categoria = ? AND valor = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, finance.getCategoria());
                statement.setDouble(2, finance.getValor());
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir entrada");
            e.printStackTrace();
            return 0;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
