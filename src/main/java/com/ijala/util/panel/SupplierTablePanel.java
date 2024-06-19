package com.ijala.util.panel;

import com.ijala.controller.SupplierController;
import com.ijala.model.supplier.Supplier;
import com.ijala.util.ButtonUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierTablePanel extends JPanel {
    private TablePanel tablePanel;
    private SupplierController supplierController;

    public SupplierTablePanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 40));

        JLabel titleLabel = new JLabel("Fornecedores");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        buttonPanel.setBackground(new Color(43, 43, 43));

        supplierController = new SupplierController();
        String[] columnNames = {"ID", "Nome", "Contato"};
        int[] columnWidths = {25, 200, 200};
        tablePanel = new TablePanel(columnNames, columnWidths);

        ButtonUtil addButton = new ButtonUtil("Cadastrar", e -> addSupplier());
        ButtonUtil deleteButton = new ButtonUtil("Excluir", e -> deleteSelectedSupplier());
        deleteButton.setBackground(Color.RED);

        customButton(addButton);
        customButton(deleteButton);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        add(tablePanel.getScrollPane(), BorderLayout.CENTER);

        loadSuppliers();
    }

    private void customButton(ButtonUtil button) {
        button.setPreferredSize(new Dimension(180, 38));
    }

    private void loadSuppliers() {
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        if (suppliers != null) {
            DefaultTableModel model = tablePanel.getModel();
            model.setRowCount(0); // Clear existing data
            for (Supplier supplier : suppliers) {
                model.addRow(new Object[]{supplier.getId(), supplier.getName(), supplier.getContact()});
            }
        }
    }

    private void addSupplier() {
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        Object[] message = {
                "Nome:", nameField,
                "Contato:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Cadastrar Fornecedor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String contact = contactField.getText();
            if (!name.trim().isEmpty() && !contact.trim().isEmpty()) {
                supplierController.addSupplier(name, contact);
                loadSuppliers();
            } else {
                JOptionPane.showMessageDialog(this, "Nome e contato não podem ser vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteSelectedSupplier() {
        int selectedRow = tablePanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tablePanel.getModel().getValueAt(selectedRow, 0);
            supplierController.deleteSupplier(id);
            loadSuppliers();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
}
