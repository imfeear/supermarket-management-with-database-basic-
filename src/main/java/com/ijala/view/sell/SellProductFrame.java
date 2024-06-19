package com.ijala.view.sell;

import com.ijala.controller.MovementController;
import com.ijala.model.product.Product;
import com.ijala.service.ProductService;
import com.ijala.util.form.FormContent;
import com.ijala.util.form.FormSmallContent;
import com.ijala.util.panel.ButtonPanel;
import com.ijala.view.stock.StockManageFrame;

import javax.swing.*;
import java.awt.*;

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

        formPanel.add(FormContent.create("ID do Produto", productIdField, true, "/icon/key.png"));
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(FormContent.create("Nome do Produto", textFieldName, true, "/icon/product.png"));

        JPanel smallContainersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormSmallContent.create("Preço", textFieldPrice, true, "/icon/price.png"));
        formPanel.add(Box.createVerticalStrut(20));
        smallContainersPanel.add(FormSmallContent.create("Quantidade", textFieldQuantity, true, "/icon/quantity.png"));
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

            movementController.sell(product.getId(), quantity);

            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!\nValor Total: R$ " + String.format("%.2f", totalPrice), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();

            // Atualiza o StockManageFrame para refletir a nova quantidade no estoque
            for (Window window : Window.getWindows()) {
                if (window instanceof StockManageFrame) {
                    StockManageFrame stockManageFrame = (StockManageFrame) window;
                    stockManageFrame.loadStock(); // Atualiza os dados do estoque
                    stockManageFrame.setVisible(true); // Torna o StockManageFrame visível
                    break;
                }
            }

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
