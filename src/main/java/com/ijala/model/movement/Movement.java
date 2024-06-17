package com.ijala.model.movement;

import com.ijala.model.product.Product;

import java.sql.Date;


public class Movement {

    private int id;
    private Product product;
    private int quantity;
    private Date date;
    private String type;

    public Movement(int id, int quantity, Date date, String type, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.type = type;
        this.product = product;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
