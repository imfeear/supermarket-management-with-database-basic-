package com.ijala.util.panel;

import java.awt.*;
import javax.swing.*;

public class SideTitlePanel {

    private JPanel sidePanel;
    private JLabel labelTitle;

    public SideTitlePanel(Dimension screenSize) {
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(46, 86, 190));
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));

        labelTitle = new JLabel("", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 54));
        labelTitle.setForeground(Color.WHITE);
        sidePanel.add(labelTitle, BorderLayout.CENTER);
    }

    public void setTitulo(String titulo) {
        labelTitle.setText("<html><div style='text-align: center;'>" + titulo.replaceAll("\n", "<br>") + "</div></html>");
    }

    public JPanel getSideTitlePanel() {
        return sidePanel;
    }
}
