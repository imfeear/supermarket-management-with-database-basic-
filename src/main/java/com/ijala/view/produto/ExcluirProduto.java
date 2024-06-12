package com.ijala.view.produto;

import javax.swing.*;

import com.ijala.model.produto.ProdutoDAO;

public class ExcluirProduto extends JFrame {
    private ProdutoDAO produtoDAO;

    public ExcluirProduto() {
        super("Excluir Produto");
        produtoDAO = new ProdutoDAO();
        initComponents();
    }

    private void initComponents() {
    }
}
