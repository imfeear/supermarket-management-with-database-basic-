package org.supermercado.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Janela maximizada

        // Painel principal com a imagem de fundo
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("src/main/resources/images/fundo.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(120, 250, 120, 250); // Margens entre os botões (aumentadas)
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        // Adicionando botões ao painel
        JButton button1 = createButton("Gestão de Estoque", "src/main/resources/images/gestao_estoque.png");
        JButton button2 = createButton("Gestão de Compras", "src/main/resources/images/gestao_compras.png");
        JButton button3 = createButton("Gestão de Vendas", "src/main/resources/images/gestao_vendas.png");
        JButton button4 = createButton("Gestão Financeira", "src/main/resources/images/gestao_financeira.png");

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
                tabelaProdutos.setVisible(true); // Abre a tela de cadastro de produto
            }
        });

        add(backgroundPanel);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setPreferredSize(new Dimension(250, 100)); // Tamanho dos botões (aumentado)
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBackground(new Color(46, 86, 190)); // Cor de fundo azul
        button.setForeground(Color.WHITE); // Texto branco
        button.setFont(new Font("Arial", Font.BOLD, 24)); // Fonte maior e em negrito
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    // Painel com a imagem de fundo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });
    }
}
