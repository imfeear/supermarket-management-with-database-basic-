package com.ijala.view.financeira;

import com.ijala.model.finance.FinanceManageDAO;

import javax.swing.*;
import java.awt.*;

public class FinanceManageFrame extends JFrame {

    private HeaderPanel headerPanel;
    private MainPanel mainPanel;
    private FinanceTablePanel tabelaPanel;

    private FinanceManageDAO financeManageDAO;

    public FinanceManageFrame() {
        setTitle("Gestão Financeira");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        financeManageDAO = new FinanceManageDAO();

        headerPanel = new HeaderPanel();
        mainPanel = new MainPanel(financeManageDAO);
        tabelaPanel = new FinanceTablePanel(financeManageDAO);

        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(tabelaPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.decode("#2B2B2B"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FinanceManageFrame().setVisible(true);
        });
    }
}
