package com.ijala.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Classe que representa a tela inicial (splash screen) do sistema de gerenciamento de supermercado.
 * Esta tela é exibida por um curto período antes de redirecionar para a tela de login.
 */
public class InitialFrame extends JFrame {

    /**
     * Construtor da classe InitialFrame que configura e exibe a tela inicial do sistema.
     * Esta tela contém o nome do sistema, logotipo da empresa e informações de direitos autorais.
     */
    public InitialFrame() {
        setSize(1000, 800);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.decode("#E9FBFF"));

        JLabel label = new JLabel("SISTEMA DE GERENCIAMENTO DE SUPERMERCADO", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.decode("#1B60C9"));
        label.setBorder(new EmptyBorder(80, 0, 0, 0));

        JLabel company = new JLabel("Copyrigth © 2024 - IJALA", SwingConstants.CENTER);
        company.setFont(new Font("Arial", Font.BOLD, 15));
        company.setForeground(Color.BLACK);
        company.setBorder(new EmptyBorder(0, 0, 20, 0));

        ImageIcon logo = new ImageIcon(InitialFrame.class.getResource("/image/logo-ijala.jpeg"));
        JLabel logoLabel = new JLabel(logo, SwingConstants.CENTER);
        logoLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        content.add(label, BorderLayout.NORTH);
        content.add(logoLabel, BorderLayout.CENTER);
        content.add(company, BorderLayout.SOUTH);

        add(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Método principal para iniciar a aplicação.
     * Cria uma instância da InitialFrame (tela inicial), exibe uma splash screen por 10 segundos
     * e depois abre a tela de login.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        // Cria a Splash Screen
        InitialFrame initialPanel = new InitialFrame();
        initialPanel.setVisible(true);

        // Mantém a splash screen visível por 10 segundos
        try {
            Thread.sleep(10000); // 10000 milissegundos = 10 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initialPanel.setVisible(false);
        initialPanel.dispose();

        // Abre a tela de login
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
    }
}
