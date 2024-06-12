package teste;

import javax.swing.*;
import java.awt.*;

public class ContainerForm {

    public static JPanel createCustomContainer(String labelText, boolean isLargeField, JTextField textField) {
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
                ImageIcon icon = new ImageIcon("src/main/resources/images/icone_categoria.png");
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

        textField.setBounds(0, 30, 400, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        return container;
    }

    public static JPanel createSmallContainer(String labelText, boolean isLargeField, JTextField textField) {
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
                ImageIcon icon = new ImageIcon("src/main/resources/images/icone_categoria.png");
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

        textField.setBounds(0, 30, 120, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        return container;
    }
}