package com.ijala.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária para obter conexão com o banco de dados do supermercado.
 * Utiliza JDBC para conectar-se a um banco de dados PostgreSQL.
 */
public class DatabaseConnection {
    // URL de conexão JDBC para o banco de dados do supermercado
    private static final String URL_SUPERMERCADO = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";

    /**
     * Obtém uma conexão com o banco de dados do supermercado.
     *
     * @return Objeto Connection que representa a conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao tentar estabelecer a conexão com o banco de dados.
     */
    public static Connection getSupermercadoConnection() throws SQLException {
        return DriverManager.getConnection(URL_SUPERMERCADO, USER, PASSWORD);
    }
}
