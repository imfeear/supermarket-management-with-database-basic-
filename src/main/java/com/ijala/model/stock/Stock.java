package com.ijala.model.stock;

import com.ijala.model.product.Product;

import java.util.Date;

/**
 * Classe Stock para representar o estoque de um produto.
 */
public class Stock {
    private int id;
    private Product product;
    private int initialStock;
    private Date entry;
    private Date exit;
    private int finalStock;

    /**
     * Construtor para inicializar o estoque de um produto.
     *
     * @param product       O produto associado ao estoque.
     * @param initialStock  A quantidade inicial de estoque.
     * @param entry         A data de entrada do estoque.
     * @param exit          A data de saída do estoque.
     * @param finalStock    A quantidade final de estoque.
     */
    public Stock(Product product, int initialStock, Date entry, Date exit, int finalStock) {
        this.product = product;
        this.initialStock = initialStock;
        this.entry = entry;
        this.exit = exit;
        this.finalStock = finalStock;
    }

    /**
     * Obtém o ID do estoque.
     *
     * @return O ID do estoque.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do estoque.
     *
     * @param id O ID do estoque.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o produto associado ao estoque.
     *
     * @return O produto associado ao estoque.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Define o produto associado ao estoque.
     *
     * @param product O produto a ser associado ao estoque.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtém a quantidade inicial de estoque.
     *
     * @return A quantidade inicial de estoque.
     */
    public int getInitialStock() {
        return initialStock;
    }

    /**
     * Define a quantidade inicial de estoque.
     *
     * @param initialStock A quantidade inicial de estoque.
     */
    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    /**
     * Obtém a data de entrada do estoque.
     *
     * @return A data de entrada do estoque.
     */
    public Date getEntry() {
        return entry;
    }

    /**
     * Define a data de entrada do estoque.
     *
     * @param entry A data de entrada do estoque.
     */
    public void setEntry(Date entry) {
        this.entry = entry;
    }

    /**
     * Obtém a data de saída do estoque.
     *
     * @return A data de saída do estoque.
     */
    public Date getExit() {
        return exit;
    }

    /**
     * Define a data de saída do estoque.
     *
     * @param exit A data de saída do estoque.
     */
    public void setExit(Date exit) {
        this.exit = exit;
    }

    /**
     * Obtém a quantidade final de estoque.
     *
     * @return A quantidade final de estoque.
     */
    public int getFinalStock() {
        return finalStock;
    }

    /**
     * Define a quantidade final de estoque.
     *
     * @param finalStock A quantidade final de estoque.
     */
    public void setFinalStock(int finalStock) {
        this.finalStock = finalStock;
    }
}
