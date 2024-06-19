package com.ijala.util.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TablePanel {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public TablePanel(String[] columnNames, int[] columnWidths) {
        createTable(columnNames, columnWidths);
    }

    private void createTable(String[] columnNames, int[] columnWidths) {
        model = new DefaultTableModel();
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);

        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(46, 86, 190));
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                return label;
            }
        };

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(43, 43, 43));
        scrollPane.setBackground(new Color(43, 43, 43));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 60, 20, 60),
                BorderFactory.createLineBorder(Color.WHITE, 1)
        ));
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void removeSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
