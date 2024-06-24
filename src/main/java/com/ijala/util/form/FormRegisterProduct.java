package com.ijala.util.form;

import com.ijala.controller.ProductController;
import com.ijala.model.product.Product;
import com.ijala.util.ButtonUtil;
import com.ijala.view.product.TableProductsFrame;

import javax.swing.*;
import java.awt.*;

public class FormRegisterProduct extends FormBase {
    private final JFrame mainFrame;
    private final ProductController productController;

    public FormRegisterProduct(JFrame frame, ProductController productController) {
        this.mainFrame = frame;
        this.productController = productController;
    }

    public JPanel getFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(50, 0, 50, 0)
        ));

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.Y_AXIS));
        formContent.setBackground(Color.decode("#2B2B2B"));
        formContent.add(createCustomContainer("Nome do Produto", true, "/icon/product.png"));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("ID Fornecedor", true, "/icon/supplier.png"));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("ID Categoria", true, "/icon/category.png"));
        formContent.add(Box.createVerticalStrut(20));
        formContent.add(createCustomContainer("Descrição", true, "/icon/description.png"));
        formContent.add(Box.createVerticalStrut(20));

        JPanel formSmallContent = new JPanel();
        formSmallContent.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        formSmallContent.setBackground(Color.decode("#2B2B2B"));
        formSmallContent.add(createSmallContainer("Preço", true, "/icon/price.png"));
        formSmallContent.add(createSmallContainer("Quantidade", true, "/icon/quantity.png"));
        formContent.add(formSmallContent);

        ButtonUtil button = new ButtonUtil("Cadastrar", e -> register());
        button.setPreferredSize(new Dimension(460, 50));
        button.setMaximumSize(new Dimension(460, 50));

        formContent.add(Box.createVerticalStrut(50));
        formContent.add(button);
        panel.add(formContent);

        return panel;
    }

    private void register() {
        try {
            String name = textFieldName.getText();
            String supplier = textFieldSupplier.getText();
            String category = textFieldCategory.getText();
            String description = textFieldDescription.getText();
            double price = Double.parseDouble(textFieldPrice.getText());
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            if (name.isEmpty() || supplier.isEmpty() || category.isEmpty() || price <= 0 || quantity <= 0) {
                throw new IllegalArgumentException("Os campos (Nome, Fornecedor, Categoria, Preço e Quantidade) são obrigatórios e devem conter valores válidos.");
            }

            if (description.isEmpty()) {
                description = "-";
            }

            Product product = new Product(name, description, quantity, price, Integer.parseInt(category), Integer.parseInt(supplier));
            productController.registerProduct(product);

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            mainFrame.dispose();
            TableProductsFrame tableProductsFrame = new TableProductsFrame();
            tableProductsFrame.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Preço e Quantidade devem ser números válidos.", "Aviso", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
