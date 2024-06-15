package com.ijala.controller;

import com.ijala.model.movimentacao.Movimentacao;
import com.ijala.model.movimentacao.MovimentacaoDAO;
import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

import java.sql.Date;

public class MovimentacaoController {
    ProdutoDAO produtoDAO = new ProdutoDAO();
    MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

    public void Comprar(int id, int quantidade) {
        try {
            Produto produto = produtoDAO.buscarProdutoPorId(id);

            if (produto.getQuantidade() < quantidade) {
                throw new Exception("Quantidade indisponível");
            }

            produto.setQuantidade(produto.getQuantidade() + quantidade);
            produtoDAO.atualizarQuantidade(produto);

            var movimentacao = new Movimentacao();
            movimentacao.setQuantidade(quantidade);
            movimentacao.setData(new Date(System.currentTimeMillis()));
            movimentacao.setTipo("entrada");
            movimentacao.setProduto(produto);
            movimentacaoDAO.adicionarMovimentacao(movimentacao);


        }catch (Exception e) {
            new Exception("Quantidade indisponívelg");
        }

    }

    public void Vender() {

    }



}
