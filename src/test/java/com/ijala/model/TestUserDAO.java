//package com.ijala.model;
//
//import com.ijala.model.user.User;
//import com.ijala.model.user.UserDAO;
//
//public class TestUserDAO {
//    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        // Cadastro de usuário
//        User user = new User(0, "Vitoria Barbosa", "vbarbosa@gmail.com", "1234");
//        boolean registered = userDAO.userRegister(user);
//
//        if (registered) {
//            System.out.println("Usuário cadastrado com sucesso.");
//        } else {
//            System.out.println("Falha ao cadastrar usuário.");
//        }
//
//        // Tentativa de login
//        String email = "vbarbosa@gmail.com";
//        String senha = "1234";
//        User loginUser = userDAO.userLogin(email, senha);
//
//        if (loginUser != null) {
//            System.out.println("Login bem-sucedido. Bem-vindo, " + loginUser.getNome());
//        } else {
//            System.out.println("Email ou senha incorretos.");
//        }
//    }
//}
