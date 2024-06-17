package com.ijala.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton cancelButton;
    private JButton actionButton;

    public ButtonPanel(String actionButtonText, ActionListener buttonAction) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode("#2B2B2B"));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        cancelButton = new JButton("Cancelar");
        cancelButton.setPreferredSize(new Dimension(200, 50)); // Define o tamanho preferencial
        cancelButton.setMaximumSize(new Dimension(200, 50)); // Define o tamanho máximo
        cancelButton.setBackground(Color.decode("#FF0000"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> closePanel());

        actionButton = new JButton(actionButtonText);
        actionButton.setPreferredSize(new Dimension(200, 50)); // Define o tamanho preferencial
        actionButton.setMaximumSize(new Dimension(200, 50)); // Define o tamanho máximo
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.setBackground(Color.decode("#2E56BE"));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFont(new Font("Arial", Font.BOLD, 14));
        actionButton.addActionListener(buttonAction);

        add(cancelButton);
        add(Box.createHorizontalStrut(60));
        add(actionButton);
    }

    private void closePanel() {
        // Trying to find the parent JFrame and dispose it
        Container parent = SwingUtilities.getWindowAncestor(this);
        if (parent instanceof JFrame) {
            ((JFrame) parent).dispose();
        } else if (parent instanceof JDialog) {
            ((JDialog) parent).dispose();
        } else {
            Container parentContainer = this.getParent();
            if (parentContainer != null) {
                parentContainer.remove(this);
                parentContainer.revalidate();
                parentContainer.repaint();
            }
        }
    }
}
