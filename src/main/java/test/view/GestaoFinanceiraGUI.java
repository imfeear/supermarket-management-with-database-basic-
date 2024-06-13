//package test.view;
//
//import com.ijala.model.financeira.GestaoFinanceira;
//import com.ijala.model.financeira.GestaoFinanceiraDAO;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class GestaoFinanceiraGUI {
//
//    private GestaoFinanceiraPanel view;
//    private GestaoFinanceiraDAO dao;
//
//    public GestaoFinanceiraGUI(GestaoFinanceiraPanel view) {
//        this.view = view;
//        this.dao = new GestaoFinanceiraDAO();
//        initData();
//        initListeners();
//    }
//
//    private void initData() {
//        // Load initial data
//        List<GestaoFinanceira> entries = dao.buscarEntradas();
//        updateDisplay(entries);
//    }
//
//    private void initListeners() {
//        view.getAddDespesaButton().addActionListener(new AddDespesaListener());
//        view.getAddReceitaButton().addActionListener(new AddReceitaListener());
//        view.getDeleteButton().addActionListener(new DeleteListener());
//    }
//
//    private void updateDisplay(List<GestaoFinanceira> entries) {
//        StringBuilder sb = new StringBuilder();
//        for (GestaoFinanceira entry : entries) {
//            sb.append("Categoria: ").append(entry.getCategoria()).append(", ")
//                    .append("Data: ").append(entry.getData()).append(", ")
//                    .append("Quantidade: ").append(entry.getQuantidade()).append("\n");
//        }
//        view.getDisplayArea().setText(sb.toString());
//    }
//
//    private class AddDespesaListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
//            gestaoFinanceira.setTipo("Despesa");
//            gestaoFinanceira.setCategoria(view.getDespesaCategoriaField().getText());
//            gestaoFinanceira.setData("");  // Data não necessária para despesas no exemplo
//            gestaoFinanceira.setQuantidade(Double.parseDouble(view.getDespesaQuantidadeField().getText()));
//
//            dao.inserirDespesa(gestaoFinanceira);
//            updateDisplay(dao.buscarEntradas());
//        }
//    }
//
//    private class AddReceitaListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
//            gestaoFinanceira.setTipo("Receita");
//            gestaoFinanceira.setCategoria("");  // Categoria não necessária para receitas no exemplo
//            gestaoFinanceira.setData(view.getReceitaDataField().getText());
//            gestaoFinanceira.setQuantidade(Double.parseDouble(view.getReceitaQuantidadeField().getText()));
//
//            dao.inserirReceita(gestaoFinanceira);
//            updateDisplay(dao.buscarEntradas());
//        }
//    }
//
//    private class DeleteListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
//            gestaoFinanceira.setCategoria(view.getExcluirCategoriaField().getText());
//            gestaoFinanceira.setData(view.getExcluirDataField().getText());
//            gestaoFinanceira.setQuantidade(Double.parseDouble(view.getExcluirQuantidadeField().getText()));
//
//            if (!gestaoFinanceira.getCategoria().isEmpty()) {
//                gestaoFinanceira.setTipo("Despesa");
//            } else {
//                gestaoFinanceira.setTipo("Receita");
//            }
//
//            dao.excluirEntrada(gestaoFinanceira);
//            updateDisplay(dao.buscarEntradas());
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            GestaoFinanceiraPanel view = new GestaoFinanceiraPanel();
//            GestaoFinanceiraGUI controller = new GestaoFinanceiraGUI(view);
//            view.setVisible(true);
//        });
//    }
//}
