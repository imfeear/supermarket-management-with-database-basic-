package org.supermercado.views;

import org.supermercado.components.CustomComponents;
import org.supermercado.components.SmallCustomComponents;
import org.supermercado.components.PanelTitle;
import org.supermercado.Produto;
import org.supermercado.ProdutoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarProduto extends JFrame {

    // Declaração dos campos de texto como membros da classe
    private JTextField textFieldNome;
    private JTextField textFieldFornecedor;
    private JTextField textFieldCategoria;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidade;

    public CadastrarProduto() {
        setTitle("Cadastrar Produtos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("Label.font", labelFont);

        PanelTitle panelTitleConfig = new PanelTitle(screenSize);
        JPanel panelTitle = panelTitleConfig.getPanelLeft();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel containerForm = new JPanel();
        containerForm.setLayout(new BoxLayout(containerForm, BoxLayout.Y_AXIS));
        containerForm.setBackground(Color.decode("#2B2B2B"));
        containerForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#2B2B2B"));

//        // Initialize the text fields
//        textFieldNome = new JTextField(20);
//        textFieldFornecedor = new JTextField(20);
//        textFieldCategoria = new JTextField(20);
//        textFieldDescricao = new JTextField(20);
//        textFieldPreco = new JTextField(10);
//        textFieldQuantidade = new JTextField(10);

        // Add labels and text fields to the form
        form.add(CustomComponents.create("Nome do Produto", true));
//        form.add(textFieldNome);
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Fornecedor", true));
//        form.add(textFieldFornecedor);
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Categoria", true));
//        form.add(textFieldCategoria);
        form.add(Box.createVerticalStrut(20));
        form.add(CustomComponents.create("Descrição", true));
//        form.add(textFieldDescricao);
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(SmallCustomComponents.create("Preço", true));
//        smallContainersPanel.add(textFieldPreco);
        smallContainersPanel.add(SmallCustomComponents.create("Quantidade", true));
//        smallContainersPanel.add(textFieldQuantidade);
        form.add(Box.createVerticalStrut(0));
        form.add(smallContainersPanel);

        JButton button = new JButton("Cadastrar");
        button.setPreferredSize(new Dimension(460, 50));
        button.setMaximumSize(new Dimension(460, 50));
        button.setMinimumSize(new Dimension(460, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.decode("#2E56BE"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Capturando os valores inseridos pelo usuário
                    String nome = textFieldNome.getText();
                    String fornecedor = textFieldFornecedor.getText();
                    String categoria = textFieldCategoria.getText();
                    String descricao = textFieldDescricao.getText();
                    double preco = Double.parseDouble(textFieldPreco.getText());
                    int quantidade = Integer.parseInt(textFieldQuantidade.getText());

                    // Validando os valores inseridos
                    if (nome.isEmpty() || fornecedor.isEmpty() || categoria.isEmpty() || descricao.isEmpty() || preco <= 0 || quantidade <= 0) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
                    }

                    // Criando um objeto Produto com os valores inseridos
                    Produto produto = new Produto(nome, descricao, quantidade, preco, Integer.parseInt(categoria), Integer.parseInt(fornecedor));

                    // Adicionando o produto ao banco de dados usando ProdutoDAO
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.adicionarProduto(produto);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Preço e Quantidade devem ser números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
                }
            }
        });

        form.add(Box.createVerticalStrut(50));
        form.add(button);
        containerForm.add(form);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(containerForm, gbc);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelTitle, mainPanel);
        splitPane.setDividerLocation(screenSize.width / 3);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        add(splitPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastrarProduto ex = new CadastrarProduto();
            ex.setVisible(true);
        });
    }
}
