package com.ijala.controller;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(Produto produto) {
        produtoDAO.adicionarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarProdutoPorId(id);
    }

    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizarProduto(produto);
    }

    public void deletarProduto(int id) {
        produtoDAO.deletarProduto(id);
    }
}
