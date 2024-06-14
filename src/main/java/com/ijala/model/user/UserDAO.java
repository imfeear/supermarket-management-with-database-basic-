package com.ijala.model.user;

import com.ijala.database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean userRegister(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User userLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("senha");

                if (BCrypt.checkpw(senha, storedHash)) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            storedHash
                    );
                    return user;
                }
            }
            return null;

        } catch (SQLException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}