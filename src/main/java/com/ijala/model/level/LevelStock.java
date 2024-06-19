package com.ijala.model.level;

/**
 * Classe que representa o nível de estoque de um produto.
 */
public class LevelStock {
    private int productId;
    private String productName;
    private long timeInStockDays;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String classification;

    /**
     * Construtor para inicializar um objeto LevelStock.
     *
     * @param productId      ID do produto.
     * @param productName    Nome do produto.
     * @param timeInStockDays Tempo em dias que o produto está em estoque.
     * @param quantity       Quantidade do produto em estoque.
     * @param unitPrice      Preço unitário do produto.
     */
    public LevelStock(int productId, String productName, long timeInStockDays, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.timeInStockDays = timeInStockDays;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice * quantity;
        this.classification = classify(quantity);
    }

    /**
     * Classifica o produto com base na quantidade em estoque.
     *
     * @param quantity Quantidade do produto em estoque.
     * @return A classificação do produto ("A", "B" ou "C").
     */
    private String classify(int quantity) {
        if (quantity < 50) {
            return "D";
        } else if (quantity >= 50 && quantity <= 199) {
            return "C";
        } else if (quantity >= 200 && quantity <= 499) {
            return "B";
        } return "A";
    }

    /**
     * Obtém o ID do produto.
     *
     * @return ID do produto.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return Nome do produto.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Obtém o tempo em dias que o produto está em estoque.
     *
     * @return Tempo em dias que o produto está em estoque.
     */
    public long getTimeInStockDays() {
        return timeInStockDays;
    }

    /**
     * Obtém a quantidade do produto em estoque.
     *
     * @return Quantidade do produto em estoque.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Obtém o preço unitário do produto.
     *
     * @return Preço unitário do produto.
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Obtém o preço total do produto (preço unitário multiplicado pela quantidade).
     *
     * @return Preço total do produto.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Obtém a classificação do produto.
     *
     * @return Classificação do produto ("A", "B" ou "C").
     */
    public String getClassification() {
        return classification;
    }
}
