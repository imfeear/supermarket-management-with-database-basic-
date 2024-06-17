package com.ijala.view.stock;

import com.ijala.controller.MovementController;
import com.ijala.model.movement.MovementDAO;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.stock.Stock;
import com.ijala.model.stock.StockDAO;
import com.ijala.util.ButtonUtil;
import com.ijala.util.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class StockManageFrame extends JFrame {
    private MovementController movementController;
    private TablePanel tablePanel;
    private StockDAO stockDAO;
    private MovementDAO movementDAO;

    public StockManageFrame() {
        super("Estoque");
        this.movementController = new MovementController();
        ProductDAO productDAO = new ProductDAO(); // Crie uma instância de ProductDAO
        stockDAO = new StockDAO();
        movementDAO = new MovementDAO(productDAO); // Passe productDAO como argumento para MovementDAO
        initComponents();
        loadStock();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43));
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 50));

        JLabel titleLabel = new JLabel("Gestão de Estoque");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        ButtonUtil movementButton = new ButtonUtil("Histórico de Movimentação", e -> {
            new StockMovementFrame().setVisible(true);
            this.dispose();
        });

        ButtonUtil levelStockButton = new ButtonUtil("Níveis de Estoque", e -> {
            new StockLevelsFrame().setVisible(true);
            this.dispose();
        });

        customButton(movementButton);
        customButton(levelStockButton);

        buttonPanel.add(movementButton);
        buttonPanel.add(levelStockButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"COD.", "Produto ID", "Produto", "Estoque inicial", "Entrada", "Saída", "Estoque final"};
        int[] columnWidths = {25, 50, 300, 150, 150, 150, 150};

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
        model.setRowCount(0);
        List<Stock> stocks = stockDAO.listProductInStock();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Stock stock : stocks) {
            String entradaFormatada = dateFormat.format(stock.getEntry());
            String saidaFormatada = stock.getExit() != null ? dateFormat.format(stock.getExit()) : "Sem saída";
            model.addRow(new Object[]{
                    stock.getId(),
                    stock.getProduct().getId(),
                    stock.getProduct().getName(),
                    stock.getInitialStock(),
                    entradaFormatada,
                    saidaFormatada,
                    stock.getFinalStock()
            });
        }
    }

    private void customButton(ButtonUtil button) {
        button.setPreferredSize(new Dimension(300, 50));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockManageFrame stockManage = new StockManageFrame();
            stockManage.setVisible(true);
        });
    }
}
