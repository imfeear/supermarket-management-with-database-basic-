package com.ijala.view.financeira;

import com.ijala.model.finance.FinanceManage;
import com.ijala.model.finance.FinanceManageDAO;
import com.ijala.util.AddLabelAndField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JTextField despesaCategoriaField;
    private JTextField despesaValorField;
    private JTextField receitaDataField;
    private JTextField receitaValorField;
    private JTextField excluirCategoriaField;
    private JTextField excluirDataField;
    private JTextField excluirValorField;

    private FinanceManageDAO financeManageDAO;

    public MainPanel(FinanceManageDAO dao) {
        this.financeManageDAO = dao;

        setBackground(Color.decode("#2B2B2B"));
        setLayout(new BorderLayout());

        // Painel Central
        JPanel centralPanel = new JPanel(new GridLayout(1, 3, 100, 0));
        centralPanel.setPreferredSize(new Dimension(500, 400));
        centralPanel.setBackground(Color.decode("#2B2B2B"));
        centralPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        despesaCategoriaField = new JTextField();
        despesaValorField = new JTextField();
        receitaDataField = new JTextField();
        receitaValorField = new JTextField();
        excluirCategoriaField = new JTextField();
        excluirDataField = new JTextField();
        excluirValorField = new JTextField();

        centralPanel.add(createInputPanel("Insira Despesas:", new String[]{"Categoria:", "Valor:"}, new JTextField[]{despesaCategoriaField, despesaValorField}, new AddDespesaAction()));
        centralPanel.add(createInputPanel("Insira Receita:", new String[]{"Data:", "Valor:"}, new JTextField[]{receitaDataField, receitaValorField}, new AddReceitaAction()));
        centralPanel.add(createInputPanel("Excluir:", new String[]{"Categoria (para Despesa):", "Data (yyyy-mm-dd, para Receita):", "Valor:"}, new JTextField[]{excluirCategoriaField, excluirDataField, excluirValorField}, new DeleteAction()));

        centralPanel.setMinimumSize(new Dimension(400, 300));
        centralPanel.setMaximumSize(new Dimension(400, 500));

        add(centralPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel(String title, String[] labels, JTextField[] fields, ActionListener actionListener) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(createTitledBorder(title));
        panel.setBackground(Color.decode("#2B2B2B"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 20, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            AddLabelAndField.addLabelAndField(labels[i], fields[i], panel, gbc);
        }

        JButton button = new JButton("Adicionar");
        if (title.equals("Excluir:")) button.setText("Deletar");
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.decode("#2E56BE"));
        button.setForeground(Color.WHITE);
        if (title.equals("Excluir:")) button.setBackground(Color.RED);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        gbc.gridy++;
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(button, gbc);

        return panel;
    }

    private static TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), title);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        return border;
    }

    private class AddDespesaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FinanceManage financeManage = new FinanceManage();
                financeManage.setTipo("Despesa");
                financeManage.setCategoria(despesaCategoriaField.getText());
                financeManage.setData("");  // Data não necessária para despesas no exemplo
                financeManage.setValor(Double.parseDouble(despesaValorField.getText()));

                financeManageDAO.addExpense(financeManage);
                JOptionPane.showMessageDialog(MainPanel.this, "Despesa inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainPanel.this, "Erro ao inserir despesa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AddReceitaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FinanceManage financeManage = new FinanceManage();
                financeManage.setTipo("Receita");
                financeManage.setCategoria("");  // Categoria não necessária para receitas no exemplo
                financeManage.setData(receitaDataField.getText());
                financeManage.setValor(Double.parseDouble(receitaValorField.getText()));

                financeManageDAO.addRecipe(financeManage);
                JOptionPane.showMessageDialog(MainPanel.this, "Receita inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainPanel.this, "Erro ao inserir receita: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String categoria = excluirCategoriaField.getText();
                String data = excluirDataField.getText();
                Double valor = Double.parseDouble(excluirValorField.getText());

                FinanceManage financeManage = new FinanceManage();
                financeManage.setCategoria(categoria);
                financeManage.setData(data);
                financeManage.setValor(valor);

                if (!categoria.isEmpty()) {
                    financeManage.setTipo("Despesa");
                } else if (!data.isEmpty()) {
                    financeManage.setTipo("Receita");
                }

                int rowsAffected = financeManageDAO.deleteEntries(financeManage);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(MainPanel.this, financeManage.getTipo() + " excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainPanel.this, "Nenhuma entrada foi encontrada com os dados informados", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                loadData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainPanel.this, "Por favor, insira um número válido para a valor.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(MainPanel.this, "Erro ao excluir entrada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadData() {
        // Implemente o carregamento de dados aqui
    }
}
