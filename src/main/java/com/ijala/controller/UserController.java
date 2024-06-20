package com.ijala.controller;

import com.ijala.model.user.User;
import com.ijala.model.user.UserDAO;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public User loginUser(String email, String password) {
        return userDAO.userLogin(email, password);
    }

    public boolean registerUser(String name, String email, String password) {
        return userDAO.userRegister(name, email, password);
    }
}
