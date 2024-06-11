package org.supermercado.views;

import org.supermercado.components.CustomComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Janela maximizada

        // Painel principal com a imagem de fundo
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("src/main/resources/images/fundo.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        // Painel centralizado de 500x500
        JPanel centralPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(600, 500));
        centralPanel.setBackground(new Color(43, 43, 43));
        centralPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0); // Sem espaçamento
        gbc.anchor = GridBagConstraints.CENTER; // Alinhar à esquerda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        // Título "Login"
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        centralPanel.add(titleLabel, gbc);

        // Campo de texto para usuário
        JPanel userPanel = CustomComponents.create("Usuário", true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        centralPanel.add(userPanel, gbc);

        // Campo de texto para senha
        JPanel passwordPanel = CustomComponents.create("Senha", true);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        centralPanel.add(passwordPanel, gbc);

        // Botão de login
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(400, 60));
        loginButton.setBackground(new Color(46, 86, 190));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        centralPanel.add(loginButton, gbc);

        // Adiciona ação ao botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de login
                Menu menu = new Menu();
                menu.setVisible(true); // Abre a tela de menu
            }
        });

        // Adiciona o painel centralizado ao painel de fundo
        backgroundPanel.add(centralPanel, new GridBagConstraints());

        add(backgroundPanel);
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
            Login menu = new Login();
            menu.setVisible(true);
        });
    }
}
