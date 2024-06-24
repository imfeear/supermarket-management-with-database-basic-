package com.ijala.controller;

import com.ijala.model.finance.Finance;
import com.ijala.service.FinanceService;

import javax.swing.*;
import java.util.List;

public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    public void addExpense(String category, String value) {
        try {
            Finance finance = new Finance();
            finance.setType("Despesa");
            finance.setCategory(category);
            finance.setDate(""); // Data não necessária para despesas
            finance.setValue(Double.parseDouble(value));

            financeService.addExpense(finance);
            JOptionPane.showMessageDialog(null, "Despesa inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir despesa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addRecipe(String date, String value) {
        try {
            Finance finance = new Finance();
            finance.setType("Receita");
            finance.setCategory(""); // Categoria não necessária para receitas
            finance.setDate(date);
            finance.setValue(Double.parseDouble(value));

            financeService.addRecipe(finance);
            JOptionPane.showMessageDialog(null, "Receita inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir receita: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteEntry(String category, String date, String value) {
        try {
            Finance finance = new Finance();
            finance.setCategory(category);
            finance.setDate(date);
            finance.setValue(Double.parseDouble(value));

            if (!category.isEmpty()) {
                finance.setType("Despesa");
            } else if (!date.isEmpty()) {
                finance.setType("Receita");
            }

            int rowsAffected = financeService.deleteEntry(finance);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, finance.getType() + " excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma entrada foi encontrada com os dados informados", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o valor.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir entrada: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Finance> getAllEntries() {
        return financeService.getAllEntries();
    }
}
