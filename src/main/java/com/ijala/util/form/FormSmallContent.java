package com.ijala.util.form;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FormSmallContent {

    public static JPanel create(String labelText, JTextField textField, boolean isLargeField, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(220, 80));
        panel.setMaximumSize(new Dimension(220, 80));
        panel.setLayout(null);
        panel.setBackground(Color.decode("#2B2B2B"));

        JPanel iconPanel = createIconPanel(imagePath);
        iconPanel.setBounds(0, 0, 80, 80);
        iconPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel formContent = new JPanel();
        formContent.setLayout(null);
        formContent.setBounds(80, 0, 140, 80);
        formContent.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 120, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        textField.setBounds(0, 30, 120, 40);
        textField.setBackground(Color.WHITE);

        formContent.add(label);
        formContent.add(textField);

        panel.add(iconPanel);
        panel.add(formContent);

        return panel;
    }

    private static JPanel createIconPanel(String imagePath) {
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(getClass().getResource(imagePath));
                    g.drawImage(image, (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        iconPanel.setBackground(Color.decode("#2B2B2B"));
        return iconPanel;
    }
}