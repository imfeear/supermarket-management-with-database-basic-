package com.ijala.view.estoque;

import com.ijala.model.stock.Stock;
import com.ijala.model.stock.StockDAO;
import com.ijala.util.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StockManageFrame extends JFrame {
    private TablePanel tablePanel;
    private StockDAO stockDAO;

    public StockManageFrame() {
        super("Estoque");
        stockDAO = new StockDAO();
        initComponents();
        loadStock();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 50));

        JLabel titleLabel = new JLabel("Gestão de Estoque");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B

        JButton niveisEstoqueButton = new JButton("Níveis de Estoque");
        JButton reposicaoButton = new JButton("Alerta de Reposição");
        JButton movimentacaoButton = new JButton("Histórico de Movimentação");

        customButton(niveisEstoqueButton);
        customButton(reposicaoButton);
        customButton(movimentacaoButton);

        buttonPanel.add(niveisEstoqueButton);
        buttonPanel.add(reposicaoButton);
        buttonPanel.add(movimentacaoButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"Produto", "Estoque inicial", "Entrada", "Saída", "Estoque final"};
        int[] columnWidths = {300, 150, 150, 150, 150};

        tablePanel = new TablePanel(columnNames, columnWidths);
        JScrollPane scrollPane = tablePanel.getScrollPane();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(topPanel, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void loadStock() {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // Limpa os dados da tabela

        List<Stock> management = stockDAO.listStock();
        for (Stock stock : management) {
            model.addRow(new Object[]{
                    stock.getProduto().getId(),
                    stock.getEstoque_inicial(),
                    stock.getEntrada(),
                    stock.getSaida(),
                    stock.getEstoque_final()
            });
        }
    }

    private void customButton(JButton botao) {
        botao.setPreferredSize(new Dimension(300, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(46, 86, 190)); // #2E56BE
        botao.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockManageFrame stockManage = new StockManageFrame();
            stockManage.setVisible(true);
        });
    }
}
