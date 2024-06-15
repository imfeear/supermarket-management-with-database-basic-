package com.ijala.view.financeira;

import com.ijala.model.finance.FinanceManage;
import com.ijala.model.finance.FinanceManageDAO;
import com.ijala.util.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FinanceTablePanel extends JPanel {

    private TablePanel tablePanel;
    private FinanceManageDAO financeManageDAO;

    public FinanceTablePanel(FinanceManageDAO dao) {
        this.financeManageDAO = dao;
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
        List<FinanceManage> management = financeManageDAO.searchForEntries();
        for (FinanceManage finance : management) {
            String tipo = finance.isReceita() ? "Receita" : "Despesa"; // Usando isReceita para determinar o tipo
            String categoria = (finance.getCategoria() == null || finance.getCategoria().isEmpty()) ? "-" : finance.getCategoria();
            model.addRow(new Object[]{
                    tipo,
                    categoria,
                    finance.getData(),
                    finance.getValor()
            });
        }
    }
}
