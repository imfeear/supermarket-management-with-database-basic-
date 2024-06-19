package com.ijala.model.movement;

import com.ijala.model.product.Product;

import java.sql.Date;

/**
 * Representa uma movimentação de produto no sistema.
 */
public class Movement {

    private int id;
    private Product product;
    private int quantity;
    private Date date;
    private String type;

    /**
     * Construtor para criar uma instância de Movement.
     *
     * @param id       o ID da movimentação
     * @param quantity a quantidade de produtos movimentados
     * @param date     a data da movimentação
     * @param type     o tipo de movimentação (ex: "entrada" ou "saída")
     * @param product  o produto relacionado à movimentação
     */
    public Movement(int id, int quantity, Date date, String type, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.type = type;
        this.product = product;
    }

    /**
     * Obtém o ID da movimentação.
     *
     * @return o ID da movimentação
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da movimentação.
     *
     * @param id o ID da movimentação
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o produto relacionado à movimentação.
     *
     * @return o produto relacionado à movimentação
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Define o produto relacionado à movimentação.
     *
     * @param product o produto relacionado à movimentação
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtém a quantidade de produtos movimentados.
     *
     * @return a quantidade de produtos movimentados
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade de produtos movimentados.
     *
     * @param quantity a quantidade de produtos movimentados
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtém a data da movimentação.
     *
     * @return a data da movimentação
     */
    public Date getDate() {
        return date;
    }

    /**
     * Define a data da movimentação.
     *
     * @param date a data da movimentação
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtém o tipo de movimentação.
     *
     * @return o tipo de movimentação (ex: "entrada" ou "saída")
     */
    public String getType() {
        return type;
    }

    /**
     * Define o tipo de movimentação.
     *
     * @param type o tipo de movimentação (ex: "entrada" ou "saída")
     */
    public void setType(String type) {
        this.type = type;
    }
}
