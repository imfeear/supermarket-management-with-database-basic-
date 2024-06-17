package com.ijala.model.sell;

import com.ijala.model.product.Product;

import java.sql.Timestamp;

public class SellProduct {
    private Product product;
    private int quantity;
    private Timestamp sellDate;

    public SellProduct(Product product, int quantity, Timestamp sellDate) {
        this.product = product;
        this.quantity = quantity;
        this.sellDate = sellDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getSellDate() {
        return sellDate;
    }

    public void setSellDate(Timestamp sellDate) {
        this.sellDate = sellDate;
    }
}
