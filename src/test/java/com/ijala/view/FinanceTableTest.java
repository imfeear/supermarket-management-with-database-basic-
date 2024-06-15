package com.ijala.view;

import com.ijala.model.finance.FinanceManage;
import com.ijala.model.finance.FinanceManageDAO;
import com.ijala.util.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FinanceTableTest extends JPanel {

    private TablePanel tablePanel;
    private FinanceManageDAO financeManageDAO;

    public FinanceTableTest(FinanceManageDAO dao) {
        this.financeManageDAO = dao;
        setBackground(Color.decode("#2B2B2B"));
        setLayout(new BorderLayout());

        JLabel tabelaLabel = new JLabel("Tabela de Receita e Despesas");
        tabelaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tabelaLabel.setForeground(Color.WHITE);

        JPanel tabelaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabelaPanel.setBackground(Color.decode("#2E56BE"));
        tabelaPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Espaço interno dentro do tabelaPanel
        tabelaPanel.add(tabelaLabel);

        String[] columnNames = {"Tipo", "Categoria", "Data", "Quantidade"};
        int[] columnWidths = {100, 150, 150, 100};

        tablePanel = new TablePanel(columnNames, columnWidths);

        JScrollPane scrollPane = tablePanel.getScrollPane();
        scrollPane.setBackground(Color.decode("#2B2B2B"));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 60, 20, 60),
                BorderFactory.createLineBorder(Color.WHITE, 1)
        ));

        scrollPane.setColumnHeaderView(tabelaPanel);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    public void loadData() {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // Limpa a tabela antes de carregar novos dados
        List<FinanceManage> management = financeManageDAO.searchForEntries();
        for (FinanceManage finance : management) {
            String tipo = finance.isReceita() ? "Receita" : "Despesa"; // Usando isReceita para determinar o tipo
            String categoria = (finance.getCategoria() == null || finance.getCategoria().isEmpty()) ? "-" : finance.getCategoria();
            model.addRow(new Object[]{
                    tipo,
                    categoria,
                    finance.getData(),
                    finance.getQuantidade()
            });
        }
    }
}
