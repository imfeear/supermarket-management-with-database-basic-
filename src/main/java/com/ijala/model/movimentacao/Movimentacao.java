package com.ijala.model.movimentacao;

import com.ijala.model.produto.Produto;
import java.util.Date;

public class Movimentacao {

    private int id;
    private Produto produto;
    private String qualidade;
    private Date data;
    private String tipo;

    public Movimentacao(int id, Produto produto, String qualidade, Date data, String tipo) {
        this.id = id;
        this.produto = produto;
        this.qualidade = qualidade;
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

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
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
