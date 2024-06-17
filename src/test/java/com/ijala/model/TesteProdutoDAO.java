package com.ijala.model;

import com.ijala.model.product.Product;
import com.ijala.model.product.ProductDAO;

import java.util.List;

public class TesteProdutoDAO {
    public static void main(String[] args) {
        ProductDAO produtoDAO = new ProductDAO();

        try {
            // Adicionar produto
            Product novoProduto = new Product("Produto Teste", "Descrição Teste", 10, 99.99, 1, 1);
            produtoDAO.addProduct(novoProduto);
            System.out.println("Produto adicionado com sucesso!");

            // Listar produtos
            List<Product> produtos = produtoDAO.listProducts();
            produtos.forEach(produto -> System.out.println(produto.getName()));

            // Atualizar produto
            if (!produtos.isEmpty()) {
                Product produtoParaAtualizar = produtos.get(0);
                produtoParaAtualizar.setName("Produto Atualizado");
                produtoDAO.updateProduct(produtoParaAtualizar);
                System.out.println("Produto atualizado com sucesso!");
            }

            // Deletar produto
            if (!produtos.isEmpty()) {
                Product produtoParaDeletar = produtos.get(0);
                produtoDAO.deleteProduct(produtoParaDeletar.getId());
                System.out.println("Produto deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro SQL ao executar a operação: " + e.getMessage());
        }
    }
}
