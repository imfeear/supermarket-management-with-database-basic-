package com.ijala.model.finance;

public class Finance {
    private String type;
    private String category;
    private String date;
    private Double value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    // Método isRecipe para verificar se é uma receita
    public boolean isRecipe() {
        return "Receita".equalsIgnoreCase(type);
    }
}
