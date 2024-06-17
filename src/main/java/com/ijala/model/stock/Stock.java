package com.ijala.model.stock;

import com.ijala.model.product.Product;

import java.util.Date;

public class Stock {
    private int id;
    private Product product;
    private int initialStock;
    private Date entry;
    private Date exit;
    private int finalStock;

    public Stock(Product product, int initialStock, Date entry, Date exit, int finalStock) {
        this.product = product;
        this.initialStock = initialStock;
        this.entry = entry;
        this.exit = exit;
        this.finalStock = finalStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getExit() {
        return exit;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }

    public int getFinalStock() {
        return finalStock;
    }

    public void setFinalStock(int finalStock) {
        this.finalStock = finalStock;
    }
}
