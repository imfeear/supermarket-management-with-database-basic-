package com.ijala.view.finance;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class FinanceHeaderPanel extends JPanel {

    private JLabel totalMonthlyIncomeLabel;
    private JLabel totalExpensesMonthlyLabel;
    private JLabel balanceTotalCaixaLabel;

    public FinanceHeaderPanel() {
        initComponents();

        setBackground(Color.decode("#2B2B2B"));
        setBorder(BorderFactory.createEmptyBorder(40, 10, 5, 10));
        setLayout(new GridBagLayout()); // Usando GridBagLayout para centralizar o painel
    }

    private void initComponents() {
        // Valores aleatórios
        Random rand = new Random();
        double totalMonthlyIncome = 5000 + (10000 - 5000) * rand.nextDouble();
        double totalExpensesMonthly = 3000 + (totalMonthlyIncome - 3000) * rand.nextDouble();
        double balanceTotalCaixa = Math.max(totalMonthlyIncome - totalExpensesMonthly, 0); // Garante que balanceTotalCaixa não seja negativo

        ImageIcon incomeIcon = new ImageIcon(FinanceHeaderPanel.class.getResource("/icon/money.png"));
        ImageIcon expensesIcon = new ImageIcon(FinanceHeaderPanel.class.getResource("/icon/money.png"));
        ImageIcon balanceIcon = new ImageIcon(FinanceHeaderPanel.class.getResource("/icon/money.png"));

        totalMonthlyIncomeLabel = new JLabel(String.format("R$ %.2f", totalMonthlyIncome));
        totalExpensesMonthlyLabel = new JLabel(String.format("R$ %.2f", totalExpensesMonthly));
        balanceTotalCaixaLabel = new JLabel(String.format("R$ %.2f", balanceTotalCaixa));

        totalMonthlyIncomeLabel.setForeground(Color.decode("#00BA1E"));
        totalExpensesMonthlyLabel.setForeground(Color.decode("#00BA1E"));
        balanceTotalCaixaLabel.setForeground(Color.decode("#00BA1E"));

        totalMonthlyIncomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalExpensesMonthlyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceTotalCaixaLabel.setFont(new Font("Arial", Font.BOLD, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);

        JPanel panel1 = createLabeledPanel("Total Renda Mensal:", totalMonthlyIncomeLabel, incomeIcon);
        add(createMarginPanel(panel1));

        JPanel panel2 = createLabeledPanel("Total Despesas Mensais:", totalExpensesMonthlyLabel, expensesIcon);
        add(createMarginPanel(panel2));

        JPanel panel3 = createLabeledPanel("Saldo Total do Caixa:", balanceTotalCaixaLabel, balanceIcon);
        add(createMarginPanel(panel3));
    }

    private JPanel createLabeledPanel(String labelText, JLabel valueLabel, ImageIcon icon) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(350, 90));
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(20, 10, 10, 10)
        ));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.decode("#2B2B2B"));
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(new JLabel(icon));
        labelPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        labelPanel.add(valueLabel);
        labelPanel.add(Box.createHorizontalGlue());

        panel.add(label);
        panel.add(Box.createVerticalGlue());
        panel.add(labelPanel);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createMarginPanel(JPanel contentPanel) {
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.add(contentPanel, BorderLayout.NORTH);
        marginPanel.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 90));
        marginPanel.setBackground(Color.decode("#2B2B2B"));
        return marginPanel;
    }
}