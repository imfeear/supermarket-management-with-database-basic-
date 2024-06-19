package com.ijala.controller;

import com.ijala.model.category.Category;
import com.ijala.model.category.CategoryDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class CategoryController {
    private CategoryDAO categoryDAO;

    public CategoryController() {
        categoryDAO = new CategoryDAO();
    }

    public void addCategory(String name) {
        Category category = new Category(0, name);
        try {
            categoryDAO.addCategory(category);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar categoria: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteCategory(int id) {
        try {
            categoryDAO.deleteCategory(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Category> getAllCategories() {
        try {
            return categoryDAO.getAllCategories();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar categorias: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Category getCategoryById(int id) {
        try {
            return categoryDAO.getCategoryById(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar categoria: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
