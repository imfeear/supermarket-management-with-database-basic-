package org.supermercado.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {

    public static void main(String[] args) {
        java.sql.Connection connection = null;
        Statement statement = null;

        try {
            connection = Connection.getConnection();
            statement = connection.createStatement();
            String sql = readFile("src/main/resources/supermercado.sql");
            statement.executeUpdate(sql);
            System.out.println("Banco de dados configurado com sucesso.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
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
