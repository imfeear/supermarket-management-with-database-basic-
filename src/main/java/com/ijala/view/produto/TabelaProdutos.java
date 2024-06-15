package com.ijala.view.produto;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.TablePanel;
import com.ijala.view.compra.ComprarProduto;
import com.ijala.view.compra.SearchId;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TabelaProdutos extends JFrame {
    private TablePanel customTable;
    private ProdutoDAO produtoDAO;

    public TabelaProdutos() {
        super("Produtos");
        produtoDAO = new ProdutoDAO();
        initComponents();
        carregarProdutos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 50));

        JLabel titleLabel = new JLabel("Produtos");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton comprarButton = new JButton("Comprar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton excluirButton = new JButton("Excluir");

        customButton(cadastrarButton);
        customButton(comprarButton);
        customButton(atualizarButton);
        customButton(excluirButton);

        cadastrarButton.addActionListener(e -> new CadastrarProduto().setVisible(true));
        comprarButton.addActionListener(e -> new SearchId().setVisible(true));
        atualizarButton.addActionListener(e -> new AtualizarProduto().setVisible(true));
//        excluirButton.addActionListener(e -> new deletarProduto());

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(comprarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(excluirButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        String[] columnNames = {"ID", "Nome", "Descrição", "Quantidade", "Preço", "Categoria ID", "Fornecedor ID"};
        int[] columnWidths = {80, 300, 500, 150, 150, 150, 150};

        customTable = new TablePanel(columnNames, columnWidths);
        JScrollPane scrollPane = customTable.getScrollPane();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(topPanel, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void carregarProdutos() {
        DefaultTableModel model = customTable.getModel();
        model.setRowCount(0); // Limpa os dados da tabela

        List<Produto> produtos = produtoDAO.listarProdutos();
        for (Produto produto : produtos) {
            model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getQuantidade(),
                    produto.getPreco(),
                    produto.getCategoriaId(),
                    produto.getFornecedorId()
            });
        }
    }

    private void customButton(JButton botao) {
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(46, 86, 190)); // #2E56BE
        botao.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TabelaProdutos telaProdutos = new TabelaProdutos();
            telaProdutos.setVisible(true);
        });
    }
}
