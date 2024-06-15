package com.ijala.view;

import com.ijala.model.finance.FinanceManageDAO;
import com.ijala.view.financeira.FinanceTablePanel;
import com.ijala.view.financeira.HeaderPanel;
import com.ijala.view.financeira.MainPanel;

import javax.swing.*;
import java.awt.*;

public class FinanceTestFrame extends JFrame {

    private HeaderPanel headerPanel;
    private MainPanel mainPanel;
    private FinanceTableTest tabelaPanel;

    private FinanceManageDAO financeManageDAO;

    public FinanceTestFrame() {
        setTitle("Gestão Financeira");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        financeManageDAO = new FinanceManageDAO();

        headerPanel = new HeaderPanel();
        mainPanel = new MainPanel(financeManageDAO);
        tabelaPanel = new FinanceTableTest(financeManageDAO);

        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(tabelaPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.decode("#2B2B2B"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FinanceTestFrame().setVisible(true);
        });
    }
}
