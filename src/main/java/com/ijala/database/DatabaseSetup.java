package com.ijala.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Conectar ao banco de dados supermercado
            connection = DatabaseConnection.getSupermercadoConnection();
            connection.setAutoCommit(false);  // Inicia a transação
            statement = connection.createStatement();
            String[] sqlStatements = readFile("src/main/resources/supermercado.sql").split(";");

            // Executar cada instrução SQL separadamente
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    statement.executeUpdate(sql.trim());
                }
            }

            connection.commit();  // Confirma a transação
            System.out.println("Banco de dados configurado com sucesso.");
        } catch (SQLException | IOException e) {
            System.err.println("Erro ao configurar o banco de dados: " + e.getMessage());
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();  // Reverte a transação em caso de erro
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }
}
