package com.ijala.util.form;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.util.panel.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class FormUpdate extends FormBase {

    private Product product;
    private ProductController productController;
    private Window parentWindow;

    private JTextField textFieldName;
    private JTextField textFieldSupplier;
    private JTextField textFieldCategory;
    private JTextField textFieldDescription;
    private JTextField textFieldPrice;
    private JTextField textFieldQuantity;

    public FormUpdate(Product product, ProductController productController, Window parentWindow) {
        this.product = product;
        this.productController = productController;
        this.parentWindow = parentWindow;
        initializeTextFields();
    }

    private void initializeTextFields() {
        textFieldName = new JTextField();
        textFieldSupplier = new JTextField();
        textFieldCategory = new JTextField();
        textFieldDescription = new JTextField();
        textFieldPrice = new JTextField();
        textFieldQuantity = new JTextField();

        // Preencher os campos com os dados atuais do produto
        textFieldName.setText(product.getName());
        textFieldSupplier.setText(String.valueOf(product.getSupplierId()));
        textFieldCategory.setText(String.valueOf(product.getCategoryId()));
        textFieldDescription.setText(product.getDescription());
        textFieldPrice.setText(String.valueOf(product.getPrice()));
        textFieldQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public JPanel getFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 700)); // Define o tamanho preferencial do painel
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.add(Box.createVerticalStrut(40));
        panel.add(FormContent.create("Nome do Produto", textFieldName, true, "/icon/product.png"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(FormContent.create("Fornecedor", textFieldSupplier, true, "/icon/supplier.png"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(FormContent.create("Categoria", textFieldCategory, true, "/icon/category.png"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(FormContent.create("Descrição", textFieldDescription, true, "/icon/description.png"));

        JPanel smallContainersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(FormSmallContent.create("Preço", textFieldPrice, true, "/icon/price.png"));
        panel.add(Box.createVerticalStrut(20));
        smallContainersPanel.add(FormSmallContent.create("Quantidade", textFieldQuantity, true, "/icon/quantity.png"));
        panel.add(smallContainersPanel);

        ButtonPanel buttonPanel = new ButtonPanel("Atualizar", e -> updateProduct());
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalStrut(40));

        return panel;
    }

    private void updateProduct() {
        try {
            // Atualizar os dados do produto com base nos campos de texto
            product.setName(textFieldName.getText());
            product.setSupplierId(Integer.parseInt(textFieldSupplier.getText()));
            product.setCategoryId(Integer.parseInt(textFieldCategory.getText()));
            product.setDescription(textFieldDescription.getText());
            product.setPrice(Double.parseDouble(textFieldPrice.getText()));
            product.setQuantity(Integer.parseInt(textFieldQuantity.getText()));

            // Chamar o método de atualização do ProductController
            productController.updateProduct(product);

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fechar a janela pai após a atualização
            parentWindow.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Preço e Quantidade devem ser números válidos.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
        }
    }
}
