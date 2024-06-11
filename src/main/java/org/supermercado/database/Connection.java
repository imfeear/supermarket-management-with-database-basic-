package org.supermercado.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
