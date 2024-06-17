package com.ijala.model.level;

public class LevelStock {
    private int productId;
    private String productName;
    private long timeInStockDays;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String classification;

    public LevelStock(int productId, String productName, long timeInStockDays, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.timeInStockDays = timeInStockDays;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice * quantity;
        this.classification = classify(quantity);
    }

    private String classify(int quantity) {
        if (quantity < 50) {
            return "C";
        } else if (quantity >= 50 && quantity <= 200) {
            return "B";
        } else {
            return "A";
        }
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getTimeInStockDays() {
        return timeInStockDays;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getClassification() {
        return classification;
    }
}
