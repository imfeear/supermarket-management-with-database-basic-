package com.ijala.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InitialFrame extends JFrame {
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

        LoginFrame login = new LoginFrame();
        login.setVisible(true);
    }
}
