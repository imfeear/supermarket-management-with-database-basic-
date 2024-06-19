package com.ijala.view;

import com.ijala.view.stock.StockManageFrame;
import com.ijala.view.product.TableProductsFrame;
import com.ijala.view.finance.FinanceManageFrame;
import com.ijala.util.panel.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Classe que representa o menu principal do sistema de gerenciamento de supermercado.
 * Permite acesso às funcionalidades principais do sistema, como gestão de estoque,
 * listagem de produtos cadastrados e gestão financeira.
 */
public class MenuFrame extends JFrame {

    /**
     * Construtor da classe MenuFrame que configura e exibe o menu principal do sistema.
     * Este menu oferece botões para acessar as funcionalidades de gestão de estoque,
     * listagem de produtos cadastrados e gestão financeira.
     */
    public MenuFrame() {
        setTitle("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Deseja sair do Sistema?", "Confirmação",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(MenuFrame.class.getResource("/image/background.png")).getImage());
        GridBagLayout layout = new GridBagLayout();
        backgroundPanel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(50, 50, 50, 50);

        JButton stockManageButton = createButton("Gestão de Estoque", "/image/stock-manage.png");
        JButton tableProductsButton = createButton("Produtos Cadastrados", "/image/registered-products.png");
        JButton financeManageButton = createButton("Gestão Financeira", "/image/finance-manage.png");

        backgroundPanel.add(stockManageButton, constraints);
        constraints.gridx = 1;
        backgroundPanel.add(tableProductsButton, constraints);
        constraints.gridx = 2;
        backgroundPanel.add(financeManageButton, constraints);

        stockManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockManageFrame stock = new StockManageFrame();
                stock.setVisible(true);
            }
        });

        tableProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableProductsFrame tabelaProdutos = new TableProductsFrame();
                tabelaProdutos.setVisible(true);
            }
        });

        financeManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinanceManageFrame financeManage = new FinanceManageFrame();
                financeManage.setVisible(true);
            }
        });

        add(backgroundPanel, BorderLayout.CENTER);
    }

    /**
     * Método privado para criar um JButton personalizado com ícone e texto.
     * Utiliza um ícone especificado por um caminho e um texto para exibir no botão.
     *
     * @param text     Texto a ser exibido no botão.
     * @param iconPath Caminho para o ícone a ser exibido no botão.
     * @return JButton configurado com o ícone e texto especificados.
     */
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton();

        // Construa o ImageIcon usando a URL do recurso
        URL iconUrl = MenuFrame.class.getResource(iconPath);
        if (iconUrl != null) {
            ImageIcon icon = new ImageIcon(iconUrl);
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Icon not found: " + iconPath);
        }

        button.setText("<html><div style='text-align: center;'><br>" + text + "<br>&nbsp;</div></html>");

        button.setPreferredSize(new Dimension(450, 350));
        button.setMinimumSize(new Dimension(450, 350));
        button.setMaximumSize(new Dimension(450, 350));

        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBackground(new Color(46, 86, 190));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setFocusPainted(false);

        return button;
    }

    /**
     * Método principal para iniciar a aplicação.
     * Cria uma instância da MenuFrame (menu principal) e a exibe.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
        });
    }
}
