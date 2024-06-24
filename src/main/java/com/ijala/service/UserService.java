package com.ijala.service;

import com.ijala.model.user.User;
import com.ijala.model.user.UserDAO;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean userRegister (String name, String email, String password) {
        return userDAO.userRegister(name, email, password);
    }

    public User userLogin(String email, String password) {
        return userDAO.userLogin(email, password);
    }
}
