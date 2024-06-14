package com.ijala.view.financeira;

import com.ijala.model.financeira.GestaoFinanceira;
import com.ijala.model.financeira.GestaoFinanceiraDAO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JTextField despesaCategoriaField;
    private JTextField despesaQuantidadeField;
    private JTextField receitaDataField;
    private JTextField receitaQuantidadeField;
    private JTextField excluirCategoriaField;
    private JTextField excluirDataField;
    private JTextField excluirQuantidadeField;

    private GestaoFinanceiraDAO gestaoFinanceiraDAO;

    public MainPanel(GestaoFinanceiraDAO dao) {
        this.gestaoFinanceiraDAO = dao;

        setBackground(Color.decode("#2B2B2B"));
        setLayout(new GridLayout(1, 3, 100, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        despesaCategoriaField = new JTextField();
        despesaQuantidadeField = new JTextField();
        receitaDataField = new JTextField();
        receitaQuantidadeField = new JTextField();
        excluirCategoriaField = new JTextField();
        excluirDataField = new JTextField();
        excluirQuantidadeField = new JTextField();

        add(createInputPanel("Insira Despesas:", new String[]{"Categoria:", "Quantidade:"}, new JTextField[]{despesaCategoriaField, despesaQuantidadeField}, new AddDespesaAction()));
        add(createInputPanel("Insira Receita:", new String[]{"Data:", "Quantidade:"}, new JTextField[]{receitaDataField, receitaQuantidadeField}, new AddReceitaAction()));
        add(createInputPanel("Excluir:", new String[]{"Categoria (para Despesa):", "Data (yyyy-mm-dd, para Receita):", "Quantidade:"}, new JTextField[]{excluirCategoriaField, excluirDataField, excluirQuantidadeField}, new DeleteAction()));
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
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            panel.add(label, gbc);
            gbc.gridy++;
            fields[i].setPreferredSize(new Dimension(300, 40));
            fields[i].setBackground(Color.WHITE);
            fields[i].setForeground(Color.BLACK);
            panel.add(fields[i], gbc);
            gbc.gridy++;
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
        return border;
    }

    private class AddDespesaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
            gestaoFinanceira.setTipo("Despesa");
            gestaoFinanceira.setCategoria(despesaCategoriaField.getText());
            gestaoFinanceira.setData("");  // Data não necessária para despesas no exemplo
            gestaoFinanceira.setQuantidade(Double.parseDouble(despesaQuantidadeField.getText()));

            gestaoFinanceiraDAO.inserirDespesa(gestaoFinanceira);
            loadData();
        }
    }

    private class AddReceitaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
            gestaoFinanceira.setTipo("Receita");
            gestaoFinanceira.setCategoria("");  // Categoria não necessária para receitas no exemplo
            gestaoFinanceira.setData(receitaDataField.getText());
            gestaoFinanceira.setQuantidade(Double.parseDouble(receitaQuantidadeField.getText()));

            gestaoFinanceiraDAO.inserirReceita(gestaoFinanceira);
            loadData();
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
            gestaoFinanceira.setCategoria(excluirCategoriaField.getText());
            gestaoFinanceira.setData(excluirDataField.getText());
            gestaoFinanceira.setQuantidade(Double.parseDouble(excluirQuantidadeField.getText()));

            if (!gestaoFinanceira.getCategoria().isEmpty()) {
                gestaoFinanceira.setTipo("Despesa");
            } else {
                gestaoFinanceira.setTipo("Receita");
            }

            gestaoFinanceiraDAO.excluirEntrada(gestaoFinanceira);
            loadData();
        }
    }

    public void loadData() {
        // Implementar carga de dados aqui
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Exemplo de Tabela");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(1200, 400);
//
//            // Simulando DAO para exemplo
//            GestaoFinanceiraDAO dao = new GestaoFinanceiraDAO();
//
//            MainPanel mainFrame = new MainPanel(dao);
//            frame.add(mainFrame);
//
//            frame.setVisible(true);
//        });
//    }
}
