package com.ijala.util.form;

import javax.swing.*;
import java.awt.*;

public abstract class FormBase {
    protected JTextField textFieldName;
    protected JTextField textFieldSupplier;
    protected JTextField textFieldCategory;
    protected JTextField textFieldDescription;
    protected JTextField textFieldPrice;
    protected JTextField textFieldQuantity;

    protected JPanel createCustomContainer(String labelText, boolean isLargeField, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 80));
        panel.setMaximumSize(new Dimension(500, 80));
        panel.setLayout(null);
        panel.setBackground(Color.decode("#2B2B2B"));

        JPanel iconPanel = createIconPanel(imagePath);
        iconPanel.setBounds(0, 0, 80, 80);

        JPanel formContent = new JPanel();
        formContent.setLayout(null);
        formContent.setBounds(80, 0, isLargeField ? 420 : 140, 80);
        formContent.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, isLargeField ? 400 : 120, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, isLargeField ? 400 : 120, 40);
        textField.setBackground(Color.WHITE);

        formContent.add(label);
        formContent.add(textField);

        panel.add(iconPanel);
        panel.add(formContent);

        assignTextField(textField, labelText);

        return panel;
    }

    protected JPanel createSmallContainer(String labelText, boolean isLargeField, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(220, 80));
        panel.setLayout(null);
        panel.setBackground(Color.decode("#2B2B2B"));

        JPanel iconPanel = createIconPanel(imagePath);
        iconPanel.setBounds(0, 0, 80, 80);

        JPanel formContent = new JPanel();
        formContent.setLayout(null);
        formContent.setBounds(80, 0, 140, 80);
        formContent.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 120, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 120, 40);
        textField.setBackground(Color.WHITE);

        formContent.add(label);
        formContent.add(textField);

        panel.add(iconPanel);
        panel.add(formContent);

        assignTextField(textField, labelText);

        return panel;
    }

    protected JPanel createIconPanel(String imagePath) {
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath));
                    g.drawImage(image, (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        iconPanel.setBackground(Color.decode("#2B2B2B"));
        return iconPanel;
    }

    private void assignTextField(JTextField textField, String labelText) {
        switch (labelText) {
            case "Nome do Produto" -> textFieldName = textField;
            case "ID Fornecedor" -> textFieldSupplier = textField;
            case "ID Categoria" -> textFieldCategory = textField;
            case "Descrição" -> textFieldDescription = textField;
            case "Preço" -> textFieldPrice = textField;
            case "Quantidade" -> textFieldQuantity = textField;
        }
    }
}
