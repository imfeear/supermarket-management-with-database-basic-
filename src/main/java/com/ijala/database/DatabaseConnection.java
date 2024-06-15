package com.ijala.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL_SUPERMERCADO = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER = "postgres";
    private static final String PASSWORD = "jonatas";

    public static Connection getSupermercadoConnection() throws SQLException {
        return DriverManager.getConnection(URL_SUPERMERCADO, USER, PASSWORD);
    }
}