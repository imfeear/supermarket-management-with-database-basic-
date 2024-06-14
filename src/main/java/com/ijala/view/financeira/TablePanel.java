package com.ijala.view.financeira;

import com.ijala.model.financeira.GestaoFinanceira;
import com.ijala.model.financeira.GestaoFinanceiraDAO;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private JTextArea displayArea;
    private GestaoFinanceiraDAO gestaoFinanceiraDAO;

    public TablePanel(GestaoFinanceiraDAO dao) {
        this.gestaoFinanceiraDAO = dao;
        setBackground(Color.decode("#2B2B2B"));
        setLayout(new BorderLayout());

        JLabel tabelaLabel = new JLabel("Tabela de Receita e Despesas");
        tabelaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tabelaLabel.setForeground(Color.WHITE);

        JPanel tabelaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabelaPanel.setBackground(Color.decode("#2E56BE"));
        tabelaPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Espaço interno dentro do tabelaPanel
        tabelaPanel.add(tabelaLabel);

        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        displayArea.setBackground(Color.decode("#2B2B2B"));
        displayArea.setForeground(Color.WHITE);

        Font textFont = new Font("Arial", Font.PLAIN, 16);
        displayArea.setFont(textFont);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBackground(Color.decode("#2B2B2B"));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 60, 20, 60),
                BorderFactory.createLineBorder(Color.WHITE, 1)
        ));

        scrollPane.setColumnHeaderView(tabelaPanel);

        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    public void loadData() {
        displayArea.setText("");
        java.util.List<GestaoFinanceira> gestaoFinanceiras = gestaoFinanceiraDAO.buscarEntradas();
        for (GestaoFinanceira gestaoFinanceira : gestaoFinanceiras) {
            displayArea.append("Categoria: " + gestaoFinanceira.getCategoria() +
                    ", Data: " + gestaoFinanceira.getData() +
                    ", Quantidade: " + gestaoFinanceira.getQuantidade() + "\n");
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Exemplo de Tabela");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//
//            // Simulando DAO para exemplo
//            GestaoFinanceiraDAO dao = new GestaoFinanceiraDAO();
//
//            TabelaPanel tabelaFrame = new TabelaPanel(dao);
//            frame.add(tabelaFrame);
//
//            frame.setVisible(true);
//        });
//    }
}
