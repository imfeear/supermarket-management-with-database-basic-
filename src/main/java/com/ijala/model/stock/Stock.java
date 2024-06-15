package com.ijala.model.stock;

import com.ijala.model.produto.Produto;

import java.util.Date;

public class Stock {
    private int id;
    private Produto produto;
    private int estoque_inicial;
    private Date entrada;
    private Date saida;
    private int estoque_final;

    public Stock(Produto produto, int estoque_inicial, Date entrada, Date saida, int estoque_final) {
        this.produto = produto;
        this.estoque_inicial = estoque_inicial;
        this.entrada = entrada;
        this.saida = saida;
        this.estoque_final = estoque_final;
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

    public int getEstoque_inicial() {
        return estoque_inicial;
    }

    public void setEstoque_inicial(int estoque_inicial) {
        this.estoque_inicial = estoque_inicial;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public int getEstoque_final() {
        return estoque_final;
    }

    public void setEstoque_final(int estoque_final) {
        this.estoque_final = estoque_final;
    }
}
