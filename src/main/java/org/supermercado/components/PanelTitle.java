package org.supermercado.components;

import java.awt.*;
import javax.swing.*;

public class PanelTitle {

    private JPanel panelLeft;
    private JLabel labelTitulo;

    public PanelTitle(Dimension screenSize) {
        panelLeft = new JPanel();
        panelLeft.setBackground(new Color(46, 86, 190));
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));

        labelTitulo = new JLabel("<html><div style='text-align: center;'>Cadastro de<br>Produto</div></html>");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 54));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelLeft.add(labelTitulo, BorderLayout.CENTER);
    }

    public JPanel getPanelLeft() {
        return panelLeft;
    }
}

