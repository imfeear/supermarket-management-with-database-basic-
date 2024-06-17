package com.ijala.view.finance;

import com.ijala.model.finance.Finance;
import com.ijala.model.finance.FinanceDAO;
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

    private FinanceDAO financeDAO;
    public FinanceMainPanel(FinanceDAO dao) {
        this.financeDAO = dao;

        setBackground(Color.decode("#2B2B2B"));
        setLayout(new BorderLayout());

        ImageIcon expenseIcon = new ImageIcon("src/main/resources/icon/receipt-solid-72.png");
        ImageIcon recipeIcon = new ImageIcon("src/main/resources/icon/receipt-solid-72.png");

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 125, 0));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBackground(Color.decode("#2B2B2B"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        expenseCategoryField = new JTextField();
        expenseValueField = new JTextField();
        recipeDateField = new JTextField();
        recipeValueField = new JTextField();
        deleteCategoryField = new JTextField();
        deleteDateField = new JTextField();
        deleteValueField = new JTextField();

        mainPanel.add(createInputPanel("Insira Despesas:",
                new String[]{"Categoria:", "Valor:"},
                new JTextField[]{expenseCategoryField, expenseValueField},
                new AddExpenseAction(), null, null, expenseIcon
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
            try {
                Finance finance = new Finance();
                finance.setType("Despesa");
                finance.setCategory(expenseCategoryField.getText());
                finance.setDate("");  // Data não necessária para despesas
                finance.setValue(Double.parseDouble(expenseValueField.getText()));

                financeDAO.addExpense(finance);
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Despesa inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadData();  // Recarrega os dados após adicionar uma despesa
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Erro ao inserir despesa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AddRecipeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Finance finance = new Finance();
                finance.setType("Receita");
                finance.setCategory("");  // Categoria não necessária para receitas
                finance.setDate(recipeDateField.getText());
                finance.setValue(Double.parseDouble(recipeValueField.getText()));

                financeDAO.addRecipe(finance);
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Receita inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadData();  // Recarrega os dados após adicionar uma receita
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Erro ao inserir receita: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String category = deleteCategoryField.getText();
                String date = deleteDateField.getText();
                Double value = Double.parseDouble(deleteValueField.getText());

                Finance finance = new Finance();
                finance.setCategory(category);
                finance.setDate(date);
                finance.setValue(value);

                if (!category.isEmpty()) {
                    finance.setType("Despesa");
                } else if (!date.isEmpty()) {
                    finance.setType("Receita");
                }

                int rowsAffected = financeDAO.deleteEntries(finance);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(FinanceMainPanel.this, finance.getType() + " excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FinanceMainPanel.this, "Nenhuma entrada foi encontrada com os dados informados", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                loadData();  // Recarrega os dados após excluir uma entrada
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Por favor, insira um número válido para a valor.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FinanceMainPanel.this, "Erro ao excluir entrada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadData() {
        // Recarregar o formulário
    }
}
