//package org.supermercado.views;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.Vector;
//
//public class TelaEstoque extends JFrame {
//
//    private JTable tabela;
//    private DefaultTableModel modelo;
//
//    public TelaEstoque() {
//        setTitle("Estoque");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(800, 600);
//
//        // Criando a tabela
//        modelo = new DefaultTableModel() {
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                return String.class; // Todas as colunas serão do tipo String
//            }
//        };
//
//        modelo.addColumn("ID Estoque");
//        modelo.addColumn("Produto");
//        modelo.addColumn("ID Produto");
//        modelo.addColumn("Estoque Inicial");
//        modelo.addColumn("Entrada");
//        modelo.addColumn("Saída");
//        modelo.addColumn("Estoque Final");
//
//        tabela = new JTable(modelo);
//        JScrollPane scrollPane = new JScrollPane(tabela);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//    }
//
//    public void addRowToTable(Produto produto) {
//        Vector<String> row = new Vector<>();
//        // Adicione os dados do produto à linha
//        row.add(String.valueOf(produto.getIdEstoque()));
//        row.add(produto.getNome());
//        row.add(String.valueOf(produto.getIdProduto()));
//        row.add(String.valueOf(produto.getEstoqueInicial()));
//        row.add(produto.getEntrada());
//        row.add(produto.getSaida());
//        row.add(String.valueOf(produto.getEstoqueFinal()));
//
//        modelo.addRow(row);
//    }
//}
