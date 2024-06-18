package com.ijala.view.sell;

import com.ijala.controller.MovementController;
import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;
import com.ijala.model.sell.SellProduct;
import com.ijala.model.sell.SellProductDAO;
import com.ijala.util.ButtonPanel;
import com.ijala.util.form.FormCustomContent;
import com.ijala.util.form.FormCustomSmallContent;
import com.ijala.view.stock.StockManageFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class SellProductFrame extends JFrame {
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldQuantity;
    private JTextField productIdField;
    private MovementController movementController;
    private Product product; // Atributo para armazenar o produto

    public SellProductFrame(Product product, MovementController movementController) {
        this.product = product;
        this.movementController = movementController;
        initializeUI();
        setProductDetails();
    }

    private void initializeUI() {
        setTitle("Vender Produto");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        textFieldName = createTextField(20, false);
        textFieldPrice = createTextField(20, false);
        textFieldQuantity = createTextField(20, true);
        productIdField = createTextField(20, false);

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

        formPanel.add(FormCustomContent.create("ID do Produto", productIdField, true, "/icon/key.png"));
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(FormCustomContent.create("Nome do Produto", textFieldName, true, "/icon/product.png"));

        JPanel smallContainersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormCustomSmallContent.create("Preço", textFieldPrice, true, "/icon/price.png"));
        formPanel.add(Box.createVerticalStrut(20));
        smallContainersPanel.add(FormCustomSmallContent.create("Quantidade", textFieldQuantity, true, "/icon/quantity.png"));
        formPanel.add(smallContainersPanel);

        JPanel buttonPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelContainer.setBackground(Color.decode("#2B2B2B"));
        ButtonPanel buttonPanel = new ButtonPanel("Vender", e -> sellProduct());

        buttonPanelContainer.add(buttonPanel);
        formPanel.add(buttonPanelContainer);

        return formPanel;
    }

    private void sellProduct() {
        try {
            String quantityText = textFieldQuantity.getText().trim();
            String priceText = textFieldPrice.getText().trim();

            if (quantityText.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);
            double totalPrice = quantity * price;

            if (quantity <= 0 || price <= 0) {
                JOptionPane.showMessageDialog(null, "Quantidade e preço devem ser números positivos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (product.getQuantity() < quantity) {
                JOptionPane.showMessageDialog(null, "Quantidade insuficiente em estoque para vender.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SellProduct sellProduct = new SellProduct(product, quantity, new Timestamp(System.currentTimeMillis()));
            new SellProductDAO().makeSale(sellProduct);

            product.setQuantity(product.getQuantity() - quantity);
            new ProductDAO().updateQuantity(product);
            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!\nValor Total: R$ " + String.format("%.2f", totalPrice), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
            StockManageFrame stockManageFrame = new StockManageFrame();
            stockManageFrame.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite números válidos para quantidade e preço.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void setProductDetails() {
        productIdField.setText(String.valueOf(product.getId()));
        textFieldName.setText(product.getName());
        textFieldPrice.setText(String.valueOf(product.getPrice()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovementController movementController = new MovementController();
            SellProductFrame sellProductFrame = new SellProductFrame(null, movementController);
            sellProductFrame.setVisible(true);
        });
    }
}
