package com.ijala.view.finance;

import com.ijala.model.finance.FinanceDAO;

import javax.swing.*;
import java.awt.*;

public class FinanceManageFrame extends JFrame {

    private FinanceHeaderPanel financeHeaderPanel;
    private FinanceMainPanel financeMainPanel;
    private FinanceTablePanel financeTablePanel;

    private FinanceDAO financeDAO;

    public FinanceManageFrame() {
        setTitle("Gestão Financeira");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        financeDAO = new FinanceDAO();

        financeHeaderPanel = new FinanceHeaderPanel();
        financeMainPanel = new FinanceMainPanel(financeDAO);
        financeTablePanel = new FinanceTablePanel(financeDAO);

        setLayout(new BorderLayout());
        add(financeHeaderPanel, BorderLayout.NORTH);
        add(financeMainPanel, BorderLayout.CENTER);
        add(financeTablePanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.decode("#2B2B2B"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FinanceManageFrame().setVisible(true);
        });
    }
}
