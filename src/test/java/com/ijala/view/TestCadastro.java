package com.ijala.view;

import com.ijala.model.produto.Produto;
import com.ijala.model.produto.ProdutoDAO;
import com.ijala.util.SideTitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCadastro extends JFrame {

    private JTextField textFieldNome;
    private JTextField textFieldFornecedor;
    private JTextField textFieldCategoria;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JTextField textFieldQuantidade;

    public TestCadastro() {
        setTitle("Cadastrar Produtos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("Label.font", labelFont);

        SideTitlePanel panelTitleConfig = new SideTitlePanel(screenSize);
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
        form.add(createCustomContainer("Nome do Produto", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Fornecedor", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Categoria", true));
        form.add(Box.createVerticalStrut(20));
        form.add(createCustomContainer("Descrição", true));
        form.add(Box.createVerticalStrut(20));

        JPanel smallContainersPanel = new JPanel();
        smallContainersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        smallContainersPanel.setBackground(Color.decode("#2B2B2B"));
        smallContainersPanel.add(createSmallContainer("Preço", true));
        smallContainersPanel.add(createSmallContainer("Quantidade", true));
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
                    String nome = textFieldNome.getText();
                    String fornecedor = textFieldFornecedor.getText();
                    String categoria = textFieldCategoria.getText();
                    String descricao = textFieldDescricao.getText();
                    double preco = Double.parseDouble(textFieldPreco.getText());
                    int quantidade = Integer.parseInt(textFieldQuantidade.getText());

                    if (nome.isEmpty() || fornecedor.isEmpty() || categoria.isEmpty() || descricao.isEmpty() || preco <= 0 || quantidade <= 0) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
                    }

                    Produto produto = new Produto(nome, descricao, quantidade, preco, Integer.parseInt(categoria), Integer.parseInt(fornecedor));
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

    private JPanel createCustomContainer(String labelText, boolean isLargeField) {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(500, 80));
        container.setMinimumSize(new Dimension(500, 80));
        container.setMaximumSize(new Dimension(500, 80));
        container.setLayout(null);
        container.setBackground(Color.decode("#2B2B2B"));

        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/image/icone_categoria.png");
                g.drawImage(icon.getImage(), (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
            }
        };
        leftPanel.setBounds(0, 0, 80, 80);
        leftPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(80, 0, 420, 80);
        rightPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 400, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 400, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        if (labelText.equals("Nome do Produto")) {
            textFieldNome = textField;
        } else if (labelText.equals("Fornecedor")) {
            textFieldFornecedor = textField;
        } else if (labelText.equals("Categoria")) {
            textFieldCategoria = textField;
        } else if (labelText.equals("Descrição")) {
            textFieldDescricao = textField;
        }

        return container;
    }

    private JPanel createSmallContainer(String labelText, boolean isLargeField) {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(220, 80));
        container.setMinimumSize(new Dimension(220, 80));
        container.setMaximumSize(new Dimension(220, 80));
        container.setLayout(null);
        container.setBackground(Color.decode("#2B2B2B"));

        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/image/icone_categoria.png");
                g.drawImage(icon.getImage(), (getWidth() - 40) / 2, (getHeight() - 40) / 2, 40, 40, null);
            }
        };
        leftPanel.setBounds(0, 0, 80, 80);
        leftPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(80, 0, 140, 80);
        rightPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel(labelText);
        label.setBounds(0, 0, 120, 40);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JTextField textField = new JTextField();
        textField.setBounds(0, 30, 120, 40);
        textField.setBackground(Color.WHITE);

        rightPanel.add(label);
        rightPanel.add(textField);

        container.add(leftPanel);
        container.add(rightPanel);

        if (labelText.equals("Preço")) {
            textFieldPreco = textField;
        } else if (labelText.equals("Quantidade")) {
            textFieldQuantidade = textField;
        }

        return container;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestCadastro ex = new TestCadastro();
            ex.setVisible(true);
        });
    }
}