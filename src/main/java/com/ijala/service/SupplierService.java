package com.ijala.service;

import com.ijala.model.supplier.Supplier;
import com.ijala.model.supplier.SupplierDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SupplierService {
    private final SupplierDAO supplierDAO;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
    }

    public void addSupplier(String name, String contact) {
        Supplier supplier = new Supplier(0, name, contact);
        try {
            supplierDAO.addSupplier(supplier);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSupplier(int id) {
        try {
            supplierDAO.deleteSupplier(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Supplier> getAllSuppliers() {
        try {
            return supplierDAO.getAllSuppliers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar fornecedores: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Supplier getSupplierById(int id) {
        try {
            return supplierDAO.getSupplierById(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar fornecedor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
