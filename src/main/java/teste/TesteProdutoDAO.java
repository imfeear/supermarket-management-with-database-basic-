//package teste;
//
//import org.example.Produto;
//import org.example.ProdutoDAO;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class TesteProdutoDAO {
//    public static void main(String[] args) {
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//
//        try {
//            // Adicionar um novo produto
//            Produto novoProduto = new Produto("Produto Teste", "Descrição Teste", 10, 99.99, 1, 1);
//            produtoDAO.adicionarProduto(novoProduto);
//            System.out.println("Produto adicionado com sucesso!");
//
//            // Listar produtos
//            List<Produto> produtos = produtoDAO.listarProdutos();
//            produtos.forEach(produto -> System.out.println(produto.getNome()));
//
//            // Atualizar um produto
//            if (!produtos.isEmpty()) {
//                Produto produtoParaAtualizar = produtos.get(0);
//                produtoParaAtualizar.setNome("Produto Atualizado");
//                produtoDAO.atualizarProduto(produtoParaAtualizar);
//                System.out.println("Produto atualizado com sucesso!");
//            }
//
//            // Deletar um produto
//            if (!produtos.isEmpty()) {
//                Produto produtoParaDeletar = produtos.get(0);
//                produtoDAO.deletarProduto(produtoParaDeletar.getId());
//                System.out.println("Produto deletado com sucesso!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
