package com.ijala.model.finance;

/**
 * Classe que representa uma transação financeira.
 */
public class Finance {
    private String type;
    private String category;
    private String date;
    private Double value;

    /**
     * Obtém o tipo da transação financeira.
     *
     * @return o tipo da transação.
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo da transação financeira.
     *
     * @param type o tipo da transação.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtém a categoria da transação financeira.
     *
     * @return a categoria da transação.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Define a categoria da transação financeira.
     *
     * @param category a categoria da transação.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtém a data da transação financeira.
     *
     * @return a data da transação.
     */
    public String getDate() {
        return date;
    }

    /**
     * Define a data da transação financeira.
     *
     * @param date a data da transação.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtém o valor da transação financeira.
     *
     * @return o valor da transação.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Define o valor da transação financeira.
     *
     * @param value o valor da transação.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Verifica se a transação é uma receita.
     *
     * @return true se a transação for uma receita, false caso contrário.
     */
    public boolean isRecipe() {
        return "Receita".equalsIgnoreCase(type);
    }
}
