package com.ijala.model.product;
import com.ijala.util.Formater;

public class Product {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private int categoryId;
    private int supplierId;

    public Product(String name, String description, int quantity, double price, int categoryId, int supplierId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceFormater() {
        return Formater.formatarValor(price);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
