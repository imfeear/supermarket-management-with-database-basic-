package com.ijala.model.venda;

public class ProdutoVenda {
    private String nome;
    private String fornecedor;
    private String categoria;
    private String descricao;
    private double preco;
    private int quantidade;
    private int id;

    public ProdutoVenda(String nome, String fornecedor, String categoria, String descricao, double preco, int quantidade) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters omitidos para brevidade

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getid() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
