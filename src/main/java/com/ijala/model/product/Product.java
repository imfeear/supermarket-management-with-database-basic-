package com.ijala.model.product;

import com.ijala.util.Formater;

/**
 * Representa um produto no sistema.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private int categoryId;
    private int supplierId;

    /**
     * Construtor para inicializar um produto.
     *
     * @param name        o nome do produto
     * @param description a descrição do produto
     * @param quantity    a quantidade do produto em estoque
     * @param price       o preço do produto
     * @param categoryId  o ID da categoria do produto
     * @param supplierId  o ID do fornecedor do produto
     */
    public Product(String name, String description, int quantity, double price, int categoryId, int supplierId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return o ID do produto
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id o ID do produto
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return o nome do produto
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     *
     * @param name o nome do produto
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return a descrição do produto
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do produto.
     *
     * @param description a descrição do produto
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém a quantidade do produto em estoque.
     *
     * @return a quantidade do produto
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade do produto em estoque.
     *
     * @param quantity a quantidade do produto
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return o preço do produto
     */
    public double getPrice() {
        return price;
    }

    /**
     * Define o preço do produto.
     *
     * @param price o preço do produto
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtém o preço formatado do produto.
     *
     * @return o preço formatado do produto
     */
    public String getPriceFormater() {
        return Formater.formatarValor(price);
    }

    /**
     * Obtém o ID da categoria do produto.
     *
     * @return o ID da categoria do produto
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Define o ID da categoria do produto.
     *
     * @param categoryId o ID da categoria do produto
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Obtém o ID do fornecedor do produto.
     *
     * @return o ID do fornecedor do produto
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * Define o ID do fornecedor do produto.
     *
     * @param supplierId o ID do fornecedor do produto
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
