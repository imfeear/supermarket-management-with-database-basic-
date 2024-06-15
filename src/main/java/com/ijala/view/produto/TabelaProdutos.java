package com.ijala.view.produto;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.view.compra.ComprarProduto;
import com.ijala.view.compra.SearchId;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class TabelaProdutos extends JFrame {
    private JTable table;
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
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titleLabel = new JLabel("Produtos");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

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

        // Cria um modelo de tabela personalizado
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        model.addColumn("Quantidade");
        model.addColumn("Preço");
        model.addColumn("Categoria ID");
        model.addColumn("Fornecedor ID");

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);

        // Define a largura das colunas
        TableColumn column;
        int[] columnWidths = {80, 300, 500, 150, 150, 150, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        // Personaliza o cabeçalho da tabela
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(46, 86, 190)); // #2E56BE
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                return label;
            }
        };
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Centralizar as células da tabela
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(43, 43, 43));
        scrollPane.setBackground(new Color(43, 43, 43));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Ajustar para preencher horizontalmente
        gbc.weighty = 1.0; // Ajustar para preencher verticalmente
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
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa os dados da tabela
        // Obtém a lista de produtos do banco de dados e os adiciona à tabela
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
        botao.setPreferredSize(new Dimension(200, 40));
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
