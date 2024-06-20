package com.ijala.util.panel;

import com.ijala.controller.CategoryController;
import com.ijala.model.category.Category;
import com.ijala.util.ButtonUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoryTablePanel extends JPanel {
    private TablePanel tablePanel;
    private CategoryController categoryController;

    public CategoryTablePanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 40));

        JLabel titleLabel = new JLabel("Categorias");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        categoryController = new CategoryController();
        String[] columnNames = {"ID", "Nome"};
        int[] columnWidths = {50, 200};
        tablePanel = new TablePanel(columnNames, columnWidths);

        ButtonUtil addButton = new ButtonUtil("Cadastrar", e -> addCategory());
        ButtonUtil deleteButton = new ButtonUtil("Excluir", e -> deleteSelectedCategory());
        deleteButton.setBackground(Color.RED);

        customButton(addButton);
        customButton(deleteButton);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        add(tablePanel.getScrollPane(), BorderLayout.CENTER);

        loadCategories();
    }

    private void customButton(ButtonUtil button) {
        button.setPreferredSize(new Dimension(180, 38));
    }

    private void loadCategories() {
        List<Category> categories = categoryController.getAllCategories();
        if (categories != null) {
            DefaultTableModel model = tablePanel.getModel();
            model.setRowCount(0); // Clear existing data
            for (Category category : categories) {
                model.addRow(new Object[]{category.getId(), category.getName()});
            }
        }
    }

    private void addCategory() {
        String name = JOptionPane.showInputDialog(this, "Digite o nome da categoria:", "Cadastrar Categoria", JOptionPane.PLAIN_MESSAGE);
        if (name != null && !name.trim().isEmpty()) {
            categoryController.addCategory(name);
            loadCategories();
        } else {
            JOptionPane.showMessageDialog(this, "Nome da categoria não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedCategory() {
        int selectedRow = tablePanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tablePanel.getModel().getValueAt(selectedRow, 0);
            categoryController.deleteCategory(id);
            loadCategories();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
}
