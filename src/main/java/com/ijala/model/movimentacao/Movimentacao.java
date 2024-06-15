package com.ijala.model.movimentacao;

import com.ijala.model.produto.Produto;

import java.sql.Date;


public class Movimentacao {

    private int id;
    private Produto produto;
    private int quantidade;
    private Date data;
    private String tipo;

    public Movimentacao(int id, Produto produto, int quantidade, Date data, String tipo) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
    }

    public Movimentacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
