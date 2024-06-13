package com.ijala.view.financeira;

import com.ijala.model.financeira.GestaoFinanceira;
import com.ijala.model.financeira.GestaoFinanceiraDAO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class GestaoFinanceiraPanel extends JFrame {

    private JTextField despesaCategoriaField;
    private JTextField despesaQuantidadeField;
    private JTextField receitaDataField;
    private JTextField receitaQuantidadeField;
    private JTextField excluirCategoriaField;
    private JTextField excluirDataField;
    private JTextField excluirQuantidadeField;
    private JTextArea displayArea;

    private JLabel totalRendaMensalLabel;
    private JLabel totalDespesasMensaisLabel;
    private JLabel saldoTotalCaixaLabel;

    private GestaoFinanceiraDAO gestaoFinanceiraDAO;

    public GestaoFinanceiraPanel() {
        setTitle("Gestão Financeira");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        gestaoFinanceiraDAO = new GestaoFinanceiraDAO();
        loadData();
    }

    private void initComponents() {
        // Random values for the labels
        Random rand = new Random();
        double totalRendaMensal = 5000 + (10000 - 5000) * rand.nextDouble();
        double totalDespesasMensais = 3000 + (7000 - 3000) * rand.nextDouble();
        double saldoTotalCaixa = totalRendaMensal - totalDespesasMensais;

        totalRendaMensalLabel = new JLabel(String.format("R$ %.2f", totalRendaMensal));
        totalDespesasMensaisLabel = new JLabel(String.format("R$ %.2f", totalDespesasMensais));
        saldoTotalCaixaLabel = new JLabel(String.format("R$ %.2f", saldoTotalCaixa));

        // Set font color to white
        totalRendaMensalLabel.setForeground(Color.WHITE);
        totalDespesasMensaisLabel.setForeground(Color.WHITE);
        saldoTotalCaixaLabel.setForeground(Color.WHITE);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20)); // Center alignment with horizontal gaps
        headerPanel.setBackground(Color.decode("#2B2B2B"));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 0, 10)); // Add margin
        headerPanel.add(createLabeledPanel("Total Renda Mensal:", totalRendaMensalLabel));
        headerPanel.add(createLabeledPanel("Total Despesas Mensais:", totalDespesasMensaisLabel));
        headerPanel.add(createLabeledPanel("Saldo Total do Caixa:", saldoTotalCaixaLabel));

        totalRendaMensalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalDespesasMensaisLabel.setFont(new Font("Arial", Font.BOLD, 20));
        saldoTotalCaixaLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Tabela de Receita e Despesas
        JLabel tabelaLabel = new JLabel("Tabela de Receita e Despesas");
        tabelaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tabelaLabel.setForeground(Color.WHITE);

        JPanel tabelaPanel = new JPanel();
        tabelaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tabelaPanel.setBackground(Color.decode("#2E56BE"));
        tabelaPanel.add(tabelaLabel);

        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        displayArea.setBackground(Color.decode("#2B2B2B"));
        displayArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setColumnHeaderView(tabelaPanel);
        scrollPane.getViewport().setBackground(Color.decode("#2B2B2B")); // Set the viewport background

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 60, 20)); // Increase horizontal and vertical gaps
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60)); // Add margin around the main panel
        mainPanel.setBackground(Color.decode("#2B2B2B"));

        mainPanel.add(createInputPanel("Insira Despesas:", new String[]{"Categoria:", "Quantidade:"}, new JTextField[]{despesaCategoriaField = new JTextField(), despesaQuantidadeField = new JTextField()}, new AddDespesaAction()));
        mainPanel.add(createInputPanel("Insira Receita:", new String[]{"Data:", "Quantidade:"}, new JTextField[]{receitaDataField = new JTextField(), receitaQuantidadeField = new JTextField()}, new AddReceitaAction()));
        mainPanel.add(createInputPanel("Excluir:", new String[]{"Categoria (para Despesa):", "Data (yyyy-mm-dd, para Receita):", "Quantidade:"}, new JTextField[]{excluirCategoriaField = new JTextField(), excluirDataField = new JTextField(), excluirQuantidadeField = new JTextField()}, new DeleteAction()));

        // Layout Setup
        setLayout(new BorderLayout(40, 40)); // Increase horizontal and vertical gaps
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.decode("#2B2B2B"));
    }

    private JPanel createLabeledPanel(String labelText, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 100)); // Set fixed size
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 1), // Border com 1 pixel de largura e cor branca
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margem interna
        ));

        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        valueLabel.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInputPanel(String title, String[] labels, JTextField[] fields, ActionListener actionListener) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), title));
        panel.setBackground(Color.decode("#2B2B2B"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 20, 5); // Reduce bottom padding to decrease space between labels and inputs
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 15)); // Increase font size to 15
            panel.add(label, gbc);
            gbc.gridy++;
            fields[i].setPreferredSize(new Dimension(300, 40)); // Set input size to 300x40
            fields[i].setBackground(Color.WHITE); // Set background to white
            fields[i].setForeground(Color.BLACK); // Set text color to black for better visibility on white background
            panel.add(fields[i], gbc);
            gbc.gridy++;
        }

        JButton button = new JButton("Adicionar");
        if (title.equals("Excluir:")) button.setText("Deletar");
        button.setPreferredSize(new Dimension(200, 50)); // Set button size to 200x50
        button.setBackground(Color.decode("#2E56BE"));
        button.setForeground(Color.WHITE);
        if (title.equals("Excluir:")) button.setBackground(Color.RED);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        gbc.gridy++;
        gbc.insets = new Insets(20, 5, 5, 5); // Increase top padding for the button
        panel.add(button, gbc);

        return panel;
    }

    private void loadData() {
        displayArea.setText("");
        List<GestaoFinanceira> gestaoFinanceiras = gestaoFinanceiraDAO.buscarEntradas();
        for (GestaoFinanceira gestaoFinanceira : gestaoFinanceiras) {
            displayArea.append("Categoria: " + gestaoFinanceira.getCategoria() +
                    ", Data: " + gestaoFinanceira.getData() +
                    ", Quantidade: " + gestaoFinanceira.getQuantidade() + "\n");
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestaoFinanceiraPanel().setVisible(true);
        });
    }
}
