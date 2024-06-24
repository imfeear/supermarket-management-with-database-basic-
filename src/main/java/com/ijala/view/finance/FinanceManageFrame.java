package com.ijala.view.finance;

import com.ijala.controller.FinanceController;
import com.ijala.model.finance.FinanceDAO;
import com.ijala.service.FinanceService;

import javax.swing.*;
import java.awt.*;

public class FinanceManageFrame extends JFrame {

    public FinanceManageFrame() {
        setTitle("Gestão Financeira");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        FinanceDAO financeDAO = new FinanceDAO();
        FinanceService financeService = new FinanceService(financeDAO);
        FinanceController financeController = new FinanceController(financeService);

        FinanceHeaderPanel financeHeaderPanel = new FinanceHeaderPanel();
        FinanceMainPanel financeMainPanel = new FinanceMainPanel(financeController);
        FinanceTablePanel financeTablePanel = new FinanceTablePanel(financeDAO);

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
