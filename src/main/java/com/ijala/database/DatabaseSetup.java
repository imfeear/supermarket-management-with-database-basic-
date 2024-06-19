package com.ijala.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por configurar o banco de dados do supermercado utilizando um arquivo SQL.
 * A classe lê o arquivo SQL, executa as instruções contidas nele e realiza o commit das alterações.
 */
public class DatabaseSetup {

    /**
     * Método principal para configurar o banco de dados do supermercado.
     * Carrega um arquivo SQL, executa suas instruções e realiza o commit das alterações no banco de dados.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getSupermercadoConnection();
            connection.setAutoCommit(false);

            // Carregar o arquivo SQL usando getResourceAsStream
            InputStream inputStream = DatabaseSetup.class.getClassLoader().getResourceAsStream("supermercado.sql");

            if (inputStream == null) {
                throw new IllegalArgumentException("Arquivo não encontrado: supermercado.sql");
            }

            // Ler o arquivo linha por linha
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // Remover BOM, se presente
                if (sqlBuilder.length() == 0 && line.length() > 0 && line.charAt(0) == '\uFEFF') {
                    line = line.substring(1); // Remove o BOM
                }

                // Ignorar comentários SQL e linhas vazias
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }

                sqlBuilder.append(line).append("\n");
            }

            // Separar instruções SQL por ';'
            String[] sqlStatements = sqlBuilder.toString().split(";");

            // Executar cada instrução SQL
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    preparedStatement = connection.prepareStatement(sql.trim());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
            }

            connection.commit();
            System.out.println("Banco de dados configurado com sucesso.");

        } catch (SQLException | IOException e) {
            System.err.println("Erro ao configurar o banco de dados: " + e.getMessage());
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
