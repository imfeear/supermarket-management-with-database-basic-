package com.ijala.model.finance;

public class FinanceManage {
    private String tipo;
    private String categoria;
    private String data;
    private Double valor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    // Método isReceita para verificar se é uma receita
    public boolean isReceita() {
        return "Receita".equalsIgnoreCase(tipo);
    }
}
