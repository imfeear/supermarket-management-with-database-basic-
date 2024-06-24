package com.ijala.model.user;

import com.ijala.database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) para manipulação de usuários no banco de dados.
 */
public class UserDAO {
    private Connection connection;

    /**
     * Construtor padrão que inicializa a conexão com o banco de dados.
     */
    public UserDAO() {
        try {
            this.connection = DatabaseConnection.getSupermercadoConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Registra um novo usuário no banco de dados.
     *
     * @param nome   Nome do usuário.
     * @param email  E-mail do usuário.
     * @param senha  Senha do usuário (será hashada antes de ser armazenada).
     * @return true se o usuário foi registrado com sucesso, false caso contrário.
     */
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

    /**
     * Realiza o login de um usuário com base no e-mail e senha fornecidos.
     *
     * @param email     E-mail do usuário para login.
     * @param password  Senha não criptografada para verificação.
     * @return Um objeto User se o login for bem-sucedido, null se não encontrar o usuário ou senha incorreta.
     */
    public User userLogin(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("senha");

                if (BCrypt.checkpw(password, storedHash)) {
                    User user = new User(
//                            rs.getInt("id"),
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
