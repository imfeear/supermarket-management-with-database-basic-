package com.ijala.model.finance;

import com.ijala.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de acesso ao banco de dados para transações financeiras.
 */
public class FinanceDAO {
    private Connection connection;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
    public FinanceDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adiciona uma receita ao banco de dados.
     *
     * @param finance Objeto Finance contendo os dados da receita.
     */
    public void addRecipe(Finance finance) {
        String sql = "INSERT INTO receitas (data, valor) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(finance.getDate()));
            statement.setDouble(2, finance.getValue());
            statement.executeUpdate();
            System.out.println("Receita inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir receita");
            e.printStackTrace();
        }
    }

    /**
     * Adiciona uma despesa ao banco de dados.
     *
     * @param finance Objeto Finance contendo os dados da despesa.
     */
    public void addExpense(Finance finance) {
        String sql = "INSERT INTO despesas (categoria, valor) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, finance.getCategory());
            statement.setDouble(2, finance.getValue());
            statement.executeUpdate();
            System.out.println("Despesa inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir despesa");
            e.printStackTrace();
        }
    }

    /**
     * Busca todas as receitas e despesas no banco de dados.
     *
     * @return Lista de objetos Finance contendo as receitas e despesas.
     */
    public List<Finance> searchForEntries() {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT 'Receita' as tipo, '' as categoria, data, valor FROM Receitas UNION ALL SELECT 'Despesa', categoria, NULL as data, valor FROM Despesas";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Finance finance = new Finance();
                finance.setType(resultSet.getString("tipo"));
                finance.setCategory(resultSet.getString("categoria"));
                finance.setDate(resultSet.getString("data") != null ? resultSet.getString("data") : "-");
                finance.setValue(resultSet.getDouble("valor"));
                finances.add(finance);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar entradas");
            e.printStackTrace();
        }
        return finances;
    }

    /**
     * Exclui uma receita ou despesa do banco de dados.
     *
     * @param finance Objeto Finance contendo os dados da transação a ser excluída.
     * @return Número de linhas afetadas pela exclusão.
     */
    public int deleteEntries(Finance finance) {
        try {
            String sql;
            PreparedStatement statement;
            if ("Receita".equals(finance.getType())) {
                sql = "DELETE FROM receitas WHERE data = ? AND valor = ?";
                statement = connection.prepareStatement(sql);
                statement.setDate(1, java.sql.Date.valueOf(finance.getDate()));
                statement.setDouble(2, finance.getValue());
            } else {
                sql = "DELETE FROM despesas WHERE categoria = ? AND valor = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, finance.getCategory());
                statement.setDouble(2, finance.getValue());
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir entrada");
            e.printStackTrace();
            return 0;
        }
    }
}
