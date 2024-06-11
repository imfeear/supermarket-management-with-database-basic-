package org.supermercado.views;

import javax.swing.*;

import org.supermercado.ProdutoDAO;

public class ExcluirProduto extends JFrame {
    private ProdutoDAO produtoDAO;

    public ExcluirProduto() {
        super("Excluir Produto");
        produtoDAO = new ProdutoDAO();
        initComponents();
    }

    private void initComponents() {
        // Adicione os componentes necessários para a tela de exclusão aqui
    }
}
