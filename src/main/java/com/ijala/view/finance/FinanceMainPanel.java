package com.ijala.view.finance;

import com.ijala.controller.FinanceController;
import com.ijala.util.AddLabelAndField;
import com.ijala.util.ButtonUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinanceMainPanel extends JPanel {

    private JTextField expenseCategoryField;
    private JTextField expenseValueField;

    private JTextField recipeDateField;
    private JTextField recipeValueField;

    private JTextField deleteCategoryField;
    private JTextField deleteDateField;
    private JTextField deleteValueField;

    private final FinanceController financeController;

    public FinanceMainPanel(FinanceController financeController) {
        this.financeController = financeController;
        setBackground(Color.decode("#2B2B2B"));
        setLayout(new BorderLayout());

        ImageIcon recipeIcon = new ImageIcon(FinanceMainPanel.class.getResource("/icon/receipt.png"));

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 125, 0));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBackground(Color.decode("#2B2B2B"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JTextField expenseCategoryField = new JTextField();
        JTextField expenseValueField = new JTextField();

        JTextField recipeDateField = new JTextField();
        JTextField recipeValueField = new JTextField();

        JTextField deleteCategoryField = new JTextField();
        JTextField deleteDateField = new JTextField();
        JTextField deleteValueField = new JTextField();

        mainPanel.add(createInputPanel("Insira Despesas:",
                new String[]{"Categoria:", "Valor:"},
                new JTextField[]{expenseCategoryField, expenseValueField},
                new AddExpenseAction(), null, null, recipeIcon
        ));
        mainPanel.add(createInputPanel("Insira Receita:",
                new String[]{"Data:", "Valor:"},
                new JTextField[]{recipeDateField, recipeValueField},
                null, new AddRecipeAction(), null, recipeIcon
        ));
        mainPanel.add(createInputPanel("Excluir:",
                new String[]{"Categoria (para Despesa):", "Data (yyyy-mm-dd, para Receita):", "Valor:"},
                new JTextField[]{deleteCategoryField, deleteDateField, deleteValueField},
                null, null, new DeleteAction(), null
        ));

        mainPanel.setMinimumSize(new Dimension(350, 300));
        mainPanel.setMaximumSize(new Dimension(400, 500));

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel(String title, String[] labels, JTextField[] fields, ActionListener expenseAction, ActionListener recipeAction, ActionListener deleteAction, ImageIcon icon) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(createTitledBorder(title));
        panel.setBackground(Color.decode("#2B2B2B"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        if (icon != null) {
            JLabel imageLabel = new JLabel(icon);
            panel.add(imageLabel, gbc);
            gbc.gridy++;
        }

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy++;
            AddLabelAndField.addLabelAndField(labels[i], new ImageIcon(), fields[i], panel, gbc);
        }

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);

        if (expenseAction != null) {
            ButtonUtil addExpense = new ButtonUtil("Adicionar", expenseAction);
            panel.add(addExpense, gbc);
            gbc.gridy++;
        }

        if (recipeAction != null) {
            ButtonUtil addRecipe = new ButtonUtil("Adicionar", recipeAction);
            panel.add(addRecipe, gbc);
            gbc.gridy++;
        }

        if (deleteAction != null) {
            gbc.gridy++;
            ButtonUtil delete = new ButtonUtil("Remover", deleteAction);
            delete.setBackground(Color.RED);
            panel.add(delete, gbc);
        }

        return panel;
    }

    private static TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), title);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 18));
        return border;
    }

    private class AddExpenseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            financeController.addExpense(expenseCategoryField.getText(), expenseValueField.getText());
            loadData();
        }
    }

    private class AddRecipeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            financeController.addRecipe(recipeDateField.getText(), recipeValueField.getText());
            loadData();
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            financeController.deleteEntry(deleteCategoryField.getText(), deleteDateField.getText(), deleteValueField.getText());
            loadData();
        }
    }

    public void loadData() {
        // Recarregar o formulário
    }
}
