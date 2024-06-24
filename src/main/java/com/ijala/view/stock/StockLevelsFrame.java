package com.ijala.view.stock;

import com.ijala.model.level.LevelStock;
import com.ijala.model.level.LevelStockDAO;
import com.ijala.util.ButtonUtil;
import com.ijala.util.panel.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StockLevelsFrame extends JFrame {
    private TablePanel tablePanel;
    private final LevelStockDAO levelStockDAO;

    public StockLevelsFrame() {
        super("Níveis de Estoque");
        this.levelStockDAO = new LevelStockDAO();
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

        JLabel titleLabel = new JLabel("Níveis de Estoque");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        ButtonUtil stockButton = new ButtonUtil("Gestão de Estoque", e -> {
            new StockManageFrame().setVisible(true);
            this.dispose();
        });
        ButtonUtil movementButton = new ButtonUtil("Histórico de Movimentação",e -> {
            new StockMovementFrame().setVisible(true);
            this.dispose();
        });

        customButton(stockButton);
        customButton(movementButton);

        buttonPanel.add(stockButton);
        buttonPanel.add(movementButton);

        buttonPanel.add(stockButton);
        buttonPanel.add(movementButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"Produto", "Tempo de Estoque (dias)", "Quantidade", "Valor Unitário", "Valor Total", "Classificação"};
        int[] columnWidths = {300, 150, 100, 120, 120, 50};

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

    private void customButton(ButtonUtil button) {
        button.setPreferredSize(new Dimension(300, 50));
    }

    public void loadStock() {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // Limpa os dados da tabela

        List<LevelStock> levelStocks = levelStockDAO.getLevelStocks();
        for (LevelStock levelStock : levelStocks) {
            model.addRow(new Object[]{
                    levelStock.getProductName(),
                    levelStock.getTimeInStockDays(),
                    levelStock.getQuantity(),
                    levelStock.getUnitPrice(),
                    levelStock.getTotalPrice(),
                    levelStock.getClassification()
            });
        }

        tablePanel.getTable().getColumnModel().getColumn(5).setCellRenderer(new ClassificationCellRenderer());
    }

    private static class ClassificationCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String classification = (String) value;

            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(Font.BOLD));
            setText(getText().toUpperCase());
            setForeground(Color.BLACK);

            switch (classification) {
                case "D" -> setBackground(Color.decode("#EC6767"));
                case "C" -> setBackground(Color.decode("#ECDF67"));
                case "B" -> setBackground(Color.decode("#67C5EC"));
                case "A" -> setBackground(Color.decode("#46EC69"));
                default -> setBackground(table.getBackground());
            }
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockLevelsFrame stockLevelsFrame = new StockLevelsFrame();
            stockLevelsFrame.setVisible(true);
        });
    }
}