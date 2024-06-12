package test.view;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class TesteEstoque extends JFrame {
    private JTable table;
    private ProdutoDAO produtoDAO;

    public TesteEstoque() {
        super("Estoque");
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

        JLabel titleLabel = new JLabel("Gerenciamento do Estoque");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(43, 43, 43)); // #2B2B2B

        JButton controleButton = new JButton("Controle de Produtos");
        JButton niveisEstoqueButton = new JButton("Níveis de Estoque");
        JButton reposicaoButton = new JButton("Alerta de Reposição");
        JButton movimentacaoButton = new JButton("Histórico de Movimentação");

        customButton(controleButton);
        customButton(niveisEstoqueButton);
        customButton(reposicaoButton);
        customButton(movimentacaoButton);

        buttonPanel.add(controleButton);
        buttonPanel.add(niveisEstoqueButton);
        buttonPanel.add(reposicaoButton);
        buttonPanel.add(movimentacaoButton);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Produto");
        model.addColumn("Estoque inicial");
        model.addColumn("Entrada");
        model.addColumn("Saída");
        model.addColumn("Estoque final");

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);

        TableColumn column;
        int[] columnWidths = {300, 150, 150, 150, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

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

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(43, 43, 43)); // Cor de fundo do viewport
        scrollPane.setBackground(new Color(43, 43, 43)); // Cor de fundo do scrollpane
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // Defina a largura preferida aqui

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(43, 43, 43)); // Cor de fundo do painel intermediário
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor da tabela

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza a tabela horizontalmente
        mainPanel.add(tablePanel, gbc);

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
        botao.setPreferredSize(new Dimension(300, 40));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(46, 86, 190)); // #2E56BE
        botao.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TesteEstoque estoque = new TesteEstoque();
            estoque.setVisible(true);
        });
    }
}
