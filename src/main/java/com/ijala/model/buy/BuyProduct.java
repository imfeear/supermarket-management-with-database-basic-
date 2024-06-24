package com.ijala.model.buy;

import com.ijala.model.product.Product;

public class BuyProduct {
    private Product product;
    private int quantity;

    public BuyProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
}
