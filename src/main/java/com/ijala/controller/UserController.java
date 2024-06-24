package com.ijala.controller;

import com.ijala.model.user.User;
import com.ijala.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean registerUser(String name, String email, String password) {
        try {
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
            }
            return userService.userRegister(name, email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password) {
        try {
            if (email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
            }
            return userService.userLogin(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
