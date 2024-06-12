package test.model;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

import java.util.List;

public class TesteProdutoDAO {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            // Adicionar produto
            Produto novoProduto = new Produto("Produto Teste", "Descrição Teste", 10, 99.99, 1, 1);
            produtoDAO.adicionarProduto(novoProduto);
            System.out.println("Produto adicionado com sucesso!");

            // Listar produtos
            List<Produto> produtos = produtoDAO.listarProdutos();
            produtos.forEach(produto -> System.out.println(produto.getNome()));

            // Atualizar produto
            if (!produtos.isEmpty()) {
                Produto produtoParaAtualizar = produtos.get(0);
                produtoParaAtualizar.setNome("Produto Atualizado");
                produtoDAO.atualizarProduto(produtoParaAtualizar);
                System.out.println("Produto atualizado com sucesso!");
            }

            // Deletar produto
            if (!produtos.isEmpty()) {
                Produto produtoParaDeletar = produtos.get(0);
                produtoDAO.deletarProduto(produtoParaDeletar.getId());
                System.out.println("Produto deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro SQL ao executar a operação: " + e.getMessage());
        }
    }
}
