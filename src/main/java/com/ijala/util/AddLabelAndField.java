package com.ijala.util;

import javax.swing.*;
import java.awt.*;

public class AddLabelAndField {

    public static void addLabelAndField(String labelText, JTextField textField, JPanel formContainer, GridBagConstraints formGbc) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        fieldPanel.setBackground(new Color(43, 43, 43));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setBackground(new Color(43, 43, 43));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        label.setForeground(Color.WHITE);
        labelPanel.add(label);

        fieldPanel.add(labelPanel, BorderLayout.NORTH);
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        fieldPanel.add(textField, BorderLayout.CENTER);

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(10, 0, 10, 0);
        formContainer.add(fieldPanel, formGbc);
    }
}
