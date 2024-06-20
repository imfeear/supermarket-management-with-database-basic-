package com.ijala.view.finance;

import com.ijala.model.finance.Finance;
import com.ijala.model.finance.FinanceDAO;
import com.ijala.util.panel.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FinanceTablePanel extends JPanel {

    private TablePanel tablePanel;
    private FinanceDAO financeDAO;

    public FinanceTablePanel(FinanceDAO dao) {
        this.financeDAO = dao;
        setBackground(Color.decode("#2B2B2B"));
        setPreferredSize(new Dimension(1000, 350));
        setMinimumSize(new Dimension(1000, 300));
        setMaximumSize(new Dimension(1000, 350));
        setLayout(new BorderLayout());

        String[] columnNames = {"Tipo", "Categoria", "Data", "Valor"};
        int[] columnWidths = {150, 150, 100, 100};
        tablePanel = new TablePanel(columnNames, columnWidths);

        add(tablePanel.getScrollPane(), BorderLayout.CENTER);

        loadData();
    }

    public void loadData() {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // Limpa a tabela antes de carregar novos dados
        List<Finance> finances = financeDAO.searchForEntries();
        for (Finance finance : finances) {
            String tipo = finance.isRecipe() ? "Receita" : "Despesa"; // Usando isReceita para determinar o tipo
            String categoria = (finance.getCategory() == null || finance.getCategory().isEmpty()) ? "-" : finance.getCategory();
            model.addRow(new Object[]{
                    tipo,
                    categoria,
                    finance.getDate(),
                    finance.getValue()
            });
        }
    }
}
