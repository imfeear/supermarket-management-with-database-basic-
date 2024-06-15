package com.ijala.view;

import com.ijala.view.estoque.StockManageFrame;
import com.ijala.view.produto.TabelaProdutos;
import com.ijala.view.financeira.FinanceManageFrame;
import com.ijala.view.venda.VenderProduto;
import com.ijala.util.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("src/main/resources/image/fundo.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(120, 250, 120, 250);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        JButton GestaoEstoqueButton = createButton("Gestão de Estoque", "src/main/resources/image/gestao_estoque.png");
        JButton GestaoCompraButton = createButton("Gestão de Compras", "src/main/resources/image/gestao_compras.png");
        JButton GestaoVendaButton = createButton("Gestão de Vendas", "src/main/resources/image/gestao_vendas.png");
        JButton GestaoFinanceiraButton = createButton("Gestão Financeira", "src/main/resources/image/gestao_financeira.png");

        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(GestaoEstoqueButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        backgroundPanel.add(GestaoCompraButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(GestaoVendaButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(GestaoFinanceiraButton, gbc);

        GestaoEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose(); // Fecha a tela de menu
                StockManageFrame stock = new StockManageFrame();
                stock.setVisible(true);
            }
        });

        GestaoCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaProdutos tabelaProdutos = new TabelaProdutos();
                tabelaProdutos.setVisible(true);
            }
        });

        GestaoVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VenderProduto venderProduto = new VenderProduto();
                venderProduto.setVisible(true);
            }
        });

        GestaoFinanceiraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose(); // Fecha a tela de menu
                FinanceManageFrame financeManage = new FinanceManageFrame();
                financeManage.setVisible(true);
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
            MenuFrame menu = new MenuFrame();
            menu.setVisible(true);
        });
    }
}
