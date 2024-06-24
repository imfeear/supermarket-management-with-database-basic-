package com.ijala.service;

import com.ijala.model.finance.Finance;
import com.ijala.model.finance.FinanceDAO;

import java.util.List;

public class FinanceService {

    private final FinanceDAO financeDAO;

    public FinanceService(FinanceDAO financeDAO) {
        this.financeDAO = financeDAO;
    }

    public void addExpense(Finance finance) {
        financeDAO.addExpense(finance);
    }

    public void addRecipe(Finance finance) {
        financeDAO.addRecipe(finance);
    }

    public List<Finance> getAllEntries() {
        return financeDAO.searchForEntries();
    }

    public int deleteEntry(Finance finance) {
        return financeDAO.deleteEntries(finance);
    }
}
