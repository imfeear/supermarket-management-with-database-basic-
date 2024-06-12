package com.ijala.view;

import com.ijala.view.produto.TabelaProdutos;
import com.ijala.util.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JFrame {

    public MenuPanel() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Janela maximizada

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("src/main/resources/image/fundo.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(120, 250, 120, 250);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        JButton button1 = createButton("Gestão de Estoque", "src/main/resources/image/gestao_estoque.png");
        JButton button2 = createButton("Gestão de Compras", "src/main/resources/image/gestao_compras.png");
        JButton button3 = createButton("Gestão de Vendas", "src/main/resources/image/gestao_vendas.png");
        JButton button4 = createButton("Gestão Financeira", "src/main/resources/image/gestao_financeira.png");

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgroundPanel.add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(button3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(button4, gbc);

        // Adiciona ação ao botão de Gestão de Estoque
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose(); // Fecha a tela de menu
                TabelaProdutos tabelaProdutos = new TabelaProdutos();
                tabelaProdutos.setVisible(true);
            }
        });

        add(backgroundPanel);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setMaximumSize(new Dimension(250, 100));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBackground(new Color(46, 86, 190));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPanel menu = new MenuPanel();
            menu.setVisible(true);
        });
    }
}
