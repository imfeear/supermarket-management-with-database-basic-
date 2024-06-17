package com.ijala.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonUtil extends JButton {
    public ButtonUtil(String text, ActionListener actionListener) {
        super(text);
        this.addActionListener(actionListener);
        this.setPreferredSize(new Dimension(200, 50));
        this.setMaximumSize(new Dimension(200, 50));
        this.setBackground(Color.decode("#2E56BE"));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFocusPainted(false);
    }
}
