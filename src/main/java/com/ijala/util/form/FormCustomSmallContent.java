package com.ijala.util.form;

import javax.swing.*;
import java.awt.*;

public class FormCustomSmallContent {
    public static JPanel create(String labelText, JTextField textField,boolean isLargeField) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(220, 80));
        panel.setMaximumSize(new Dimension(220, 80));
        panel.setLayout(null);
        panel.setBackground(Color.decode("#2B2B2B"));

        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/image/icone-teste.png");
                g.drawImage(icon.getImage(), (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
            }
        };
        iconPanel.setBounds(0, 0, 80, 80);
        iconPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel formContent = new JPanel();
        formContent.setLayout(null);
        formContent.setBounds(80, 0, 140, 80); // Largura ajustada para 140
        formContent.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 120, 40); // Largura ajustada para 120
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        textField.setBounds(0, 30, 120, 40); // Largura ajustada para 120
        textField.setBackground(Color.WHITE);

        formContent.add(label);
        formContent.add(textField);

        panel.add(iconPanel);
        panel.add(formContent);

        return panel;
    }
}
