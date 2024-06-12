package com.ijala.util;

import javax.swing.*;
import java.awt.*;

public abstract class FormBase {
    protected JTextField textFieldNome;
    protected JTextField textFieldFornecedor;
    protected JTextField textFieldCategoria;
    protected JTextField textFieldDescricao;
    protected JTextField textFieldPreco;
    protected JTextField textFieldQuantidade;

    protected JPanel createCustomContainer(String labelText, boolean isLargeField) {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(500, 80));
        container.setMinimumSize(new Dimension(500, 80));
        container.setMaximumSize(new Dimension(500, 80));
        container.setLayout(null);
        container.setBackground(Color.decode("#2B2B2B"));

        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/image/icone_categoria.png");
                g.drawImage(icon.getImage(), (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
            }
        };
        leftPanel.setBounds(0, 0, 80, 80);
        leftPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(80, 0, 420, 80);
        rightPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 400, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 400, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        if (labelText.equals("Nome do Produto")) {
            textFieldNome = textField;
        } else if (labelText.equals("Fornecedor")) {
            textFieldFornecedor = textField;
        } else if (labelText.equals("Categoria")) {
            textFieldCategoria = textField;
        } else if (labelText.equals("Descrição")) {
            textFieldDescricao = textField;
        }

        return container;
    }

    protected JPanel createSmallContainer(String labelText, boolean isLargeField) {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(220, 80));
        container.setMinimumSize(new Dimension(220, 80));
        container.setMaximumSize(new Dimension(220, 80));
        container.setLayout(null);
        container.setBackground(Color.decode("#2B2B2B"));

        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/image/icone_categoria.png");
                g.drawImage(icon.getImage(), (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
            }
        };
        leftPanel.setBounds(0, 0, 80, 80);
        leftPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(80, 0, 140, 80);
        rightPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 120, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 120, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        if (labelText.equals("Preço")) {
            textFieldPreco = textField;
        } else if (labelText.equals("Quantidade")) {
            textFieldQuantidade = textField;
        }

        return container;
    }
}

