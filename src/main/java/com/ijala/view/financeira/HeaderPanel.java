package com.ijala.view.financeira;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class HeaderPanel extends JPanel {

    private JLabel totalRendaMensalLabel;
    private JLabel totalDespesasMensaisLabel;
    private JLabel saldoTotalCaixaLabel;

    public HeaderPanel() {
        initComponents();

        setBackground(Color.decode("#2B2B2B"));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));
    }

    private void initComponents() {
        // Valores aleatórios
        Random rand = new Random();
        double totalRendaMensal = 5000 + (10000 - 5000) * rand.nextDouble();
        double totalDespesasMensais = 3000 + (7000 - 3000) * rand.nextDouble();
        double saldoTotalCaixa = totalRendaMensal - totalDespesasMensais;

        totalRendaMensalLabel = new JLabel(String.format("R$ %.2f", totalRendaMensal));
        totalDespesasMensaisLabel = new JLabel(String.format("R$ %.2f", totalDespesasMensais));
        saldoTotalCaixaLabel = new JLabel(String.format("R$ %.2f", saldoTotalCaixa));

        totalRendaMensalLabel.setForeground(Color.WHITE);
        totalDespesasMensaisLabel.setForeground(Color.WHITE);
        saldoTotalCaixaLabel.setForeground(Color.WHITE);

        totalRendaMensalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalDespesasMensaisLabel.setFont(new Font("Arial", Font.BOLD, 20));
        saldoTotalCaixaLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(createLabeledPanel("Total Renda Mensal:", totalRendaMensalLabel));
        add(createLabeledPanel("Total Despesas Mensais:", totalDespesasMensaisLabel));
        add(createLabeledPanel("Saldo Total do Caixa:", saldoTotalCaixaLabel));
    }

    private JPanel createLabeledPanel(String labelText, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 100));
        panel.setBackground(Color.decode("#2B2B2B"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }
}
