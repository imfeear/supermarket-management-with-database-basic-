package com.ijala.view.buy;

import com.ijala.controller.MovementController;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.stock.Stock;
import com.ijala.model.stock.StockDAO;
import com.ijala.util.ButtonPanel;
import com.ijala.util.form.FormCustomContent;
import com.ijala.util.form.FormCustomSmallContent;
import com.ijala.util.ButtonUtil;
import com.ijala.view.stock.StockManageFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class BuyProductFrame extends JFrame {
    private JTextField textFieldName;
    private JTextField textFieldSupplier;
    private JTextField textFieldPrice;
    private JTextField textFieldQuantity;
    private JLabel totalValueLabel;
    private int productId;
    private MovementController movementController = new MovementController();

    public BuyProductFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Comprar Produto");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        textFieldName = createTextField(20, false);
        textFieldSupplier = createTextField(20, true);
        textFieldPrice = createTextField(20, false);
        textFieldQuantity = createTextField(20, true);

        JPanel form = createFormPanel();
        getContentPane().add(form, BorderLayout.CENTER);
    }

    private JTextField createTextField(int columns, boolean editable) {
        JTextField textField = new JTextField(columns);
        textField.setEditable(editable);
        return textField;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.decode("#2B2B2B"));
        formPanel.add(Box.createVerticalStrut(40));
        formPanel.add(FormCustomContent.create("Nome do Produto", textFieldName, true,"/icon/product.png"));
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(FormCustomContent.create("ID Fornecedor", textFieldSupplier, true,"/icon/supplier.png"));

        JPanel smallContainersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormCustomSmallContent.create("Preço", textFieldPrice, true,"/icon/price.png"));
        smallContainersPanel.add(FormCustomSmallContent.create("Quantidade", textFieldQuantity, true,"/icon/quantity.png"));
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(smallContainersPanel);

        totalValueLabel = new JLabel();
        totalValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalValueLabel.setForeground(Color.WHITE);

        ButtonUtil calcularButton = new ButtonUtil("Valor Total", e -> calcularValorTotal());
        calcularButton.setBackground(Color.BLACK);
        formPanel.add(calcularButton);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(totalValueLabel);
        formPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelContainer.setBackground(Color.decode("#2B2B2B"));
        ButtonPanel buttonPanel = new ButtonPanel("Comprar", e -> buyProduct());
        formPanel.add(Box.createVerticalStrut(20));

        buttonPanelContainer.add(buttonPanel);
        formPanel.add(buttonPanelContainer);

        return formPanel;
    }

    private void calcularValorTotal() {
        try {
            if (textFieldPrice.getText().isEmpty() || textFieldQuantity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha os campos. Preço e quantidade.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            double preco = Double.parseDouble(textFieldPrice.getText());
            int quantidade = Integer.parseInt(textFieldQuantity.getText());
            double valorTotal = quantidade * preco;
            totalValueLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para preço e quantidade.", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buyProduct() {
        try {
            if (textFieldQuantity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha o campo quantidade.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int quantidade = Integer.parseInt(textFieldQuantity.getText());
            movementController.buy(productId, quantidade);

            Product product = new ProductDAO().searchProductById(productId);
            if (product != null) {
                Stock stock = new Stock(product, quantidade, new Timestamp(System.currentTimeMillis()), null, quantidade);
                new StockDAO().addInStock(stock);

                JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Troca de Tela
                this.dispose();
                StockManageFrame stockManageFrame = new StockManageFrame();
                stockManageFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido para quantidade.","Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void setProductDetails(int productId, String name, String supplier, String price) {
        this.productId = productId;
        textFieldName.setText(name);
        textFieldSupplier.setText(supplier);
        textFieldPrice.setText(price);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BuyProductFrame buyProductFrame = new BuyProductFrame();
            buyProductFrame.setVisible(true);
        });
    }
}
