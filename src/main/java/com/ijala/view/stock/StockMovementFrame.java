package com.ijala.view.stock;

import com.ijala.model.movement.Movement;
import com.ijala.model.movement.MovementDAO;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.util.ButtonUtil;
import com.ijala.util.panel.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

public class StockMovementFrame extends JFrame {
    private TablePanel tablePanel;
    private MovementDAO movementDAO;

    public StockMovementFrame() {
        super("Histórico de Movimentação");

        ProductDAO productDAO = new ProductDAO(); // Inicialização do ProductDAO
        this.movementDAO = new MovementDAO(productDAO); // Passagem do ProductDAO para MovementDAO
        initComponents();
        loadStock();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 50));

        JLabel titleLabel = new JLabel("Histórico de Movimentação");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        ButtonUtil stockButton = new ButtonUtil("Gestão de Estoque",e -> {
            new StockManageFrame().setVisible(true);
            this.dispose();
        });
        ButtonUtil levelStockButton = new ButtonUtil("Níveis de Estoque",e -> {
            new StockLevelsFrame().setVisible(true);
            this.dispose();
        });

        customButton(stockButton);
        customButton(levelStockButton);

        buttonPanel.add(stockButton);
        buttonPanel.add(levelStockButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"Produto", "Tipo", "Data", "Quantidade"};
        int[] columnWidths = {300, 150, 150, 150};

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
        List<Movement> movements = movementDAO.getAllMovements();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Movement movement : movements) {
            String dateFormated = dateFormat.format(movement.getDate());
            Object[] row = new Object[4];
            row[0] = movement.getProduct().getName();
            row[1] = movement.getType();
            try {
                row[2] = dateFormated;
            } catch (NullPointerException e) {
                row[2] = null;
            }
            row[3] = movement.getQuantity();
            model.addRow(row);
        }
    }

    private void customButton(ButtonUtil button) {
        button.setPreferredSize(new Dimension(300, 50));
    }

    public void addMovement(int productId, int quantity, String type) {
        Product product = new ProductDAO().searchProductById(productId);
        if (product != null) {
            Movement movement = new Movement(0, quantity, new Date(System.currentTimeMillis()), type, product);
            movementDAO.addMovement(movement);
            loadStock(); // Atualiza a tabela de movimentações na UI
        } else {
            System.out.println("Produto " + productId + " não encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockMovementFrame stockMovement = new StockMovementFrame();
            stockMovement.setVisible(true);
        });
    }
}
