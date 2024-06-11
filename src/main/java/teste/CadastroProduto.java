//package org.example;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//public class CadastroProduto extends JFrame {
//    private JTextField nomeField;
//    private JTextField descricaoField;
//    private JTextField quantidadeField;
//    private JTextField precoField;
//    private JTextField categoriaField;
//    private JTextField fornecedorField;
//    private JButton salvarButton;
//    private ProdutoDAO produtoDAO;
//
//    public CadastroProduto() {
//        produtoDAO = new ProdutoDAO();
//
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//        nomeField = new JTextField(20);
//        descricaoField = new JTextField(20);
//        quantidadeField = new JTextField(20);
//        precoField = new JTextField(20);
//        categoriaField = new JTextField(20);
//        fornecedorField = new JTextField(20);
//        salvarButton = new JButton("Salvar");
//
//        add(new JLabel("Nome:"));
//        add(nomeField);
//        add(new JLabel("Descrição:"));
//        add(descricaoField);
//        add(new JLabel("Quantidade:"));
//        add(quantidadeField);
//        add(new JLabel("Preço:"));
//        add(precoField);
//        add(new JLabel("Categoria ID:"));
//        add(categoriaField);
//        add(new JLabel("Fornecedor ID:"));
//        add(fornecedorField);
//        add(salvarButton);
//
//        salvarButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Produto produto = new Produto();
//                produto.setNome(nomeField.getText());
//                produto.setDescricao(descricaoField.getText());
//                produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
//                produto.setPreco(Double.parseDouble(precoField.getText()));
//                produto.setCategoriaId(Integer.parseInt(categoriaField.getText()));
//                produto.setFornecedorId(Integer.parseInt(fornecedorField.getText()));
//
//                try {
//                    produtoDAO.adicionarProduto(produto);
//                    JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Erro ao salvar produto.");
//                }
//            }
//        });
//
//        setTitle("Cadastro de Produto");
//        pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}