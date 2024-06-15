package com.ijala.view.venda;

import com.ijala.model.venda.ProdutoVenda;
import com.ijala.model.venda.ProdutoVendaDAO; // Importação corrigida

import com.ijala.util.component.FormSellProduct;
import com.ijala.util.SideTitlePanel;

import javax.swing.*;
import java.awt.*;

public class VenderProduto extends JFrame {

    private FormSellProduct formSellProduct;
    private ProdutoVendaDAO produtoVendaDAO;

    public VenderProduto() {
        setTitle("Vender Produtos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Font labelFont = new Font("Arial", Font.BOLD, 15);
        UIManager.put("Label.font", labelFont);

        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Venda de\nProduto");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));

        formSellProduct = new FormSellProduct();
        produtoVendaDAO = new ProdutoVendaDAO(); // Importação corrigida

        JPanel formPanel = formSellProduct.getFormPanel();
        mainPanel.add(formPanel);

        formSellProduct.getVenderButton().addActionListener(e -> venderProduto());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideTitlePanel.getSideTitlePanel(), mainPanel);
        splitPane.setDividerLocation(screenSize.width / 3);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void venderProduto() {
        try {
            ProdutoVenda produto = new ProdutoVenda(
                    formSellProduct.getNomeProduto(),
                    formSellProduct.getFornecedor(),
                    formSellProduct.getCategoria(),
                    formSellProduct.getDescricao(),
                    formSellProduct.getPreco(),
                    formSellProduct.getQuantidade()
            );

            if (produto.getNome().isEmpty() || produto.getFornecedor().isEmpty() || produto.getCategoria().isEmpty() || produto.getDescricao().isEmpty() || produto.getPreco() <= 0 || produto.getQuantidade() <= 0) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente.");
                return;
            }

            // Verificar no banco de dados
            if (produtoVendaDAO.verificarProduto(produto)) {
                // Realizar a venda
                produtoVendaDAO.realizarVenda(produto);
                JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado no banco de dados ou quantidade insuficiente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao processar a venda.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VenderProduto ex = new VenderProduto();
            ex.setVisible(true);
        });
    }
}
