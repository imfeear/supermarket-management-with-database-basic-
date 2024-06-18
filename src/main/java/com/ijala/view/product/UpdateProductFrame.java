package com.ijala.view.product;

import com.ijala.model.product.ProductDAO;
import com.ijala.util.ButtonUtil;
import com.ijala.util.form.FormUpdate;
import com.ijala.model.product.Product;

import javax.swing.*;
import java.awt.*;

public class UpdateProductFrame extends JFrame {
    private JTextField idField;
    private JPanel mainPanel;

    public UpdateProductFrame() {
        super("Atualizar produto");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300); // Definir o tamanho inicial da janela
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#2B2B2B"));

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.decode("#2B2B2B"));

        JPanel searchPanel = createSearchPanel();
        mainPanel.add(searchPanel, "searchPanel");

        add(mainPanel);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(Color.decode("#2B2B2B"));

        JLabel label = new JLabel("Insira o ID do Produto:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        idField = new JTextField();
        idField.setMaximumSize(new Dimension(200, 40));
        idField.setAlignmentX(Component.CENTER_ALIGNMENT);
        idField.add(Box.createVerticalStrut(20));

        ButtonUtil searchButton = new ButtonUtil("Buscar", e -> update());

        searchPanel.add(Box.createVerticalStrut(50));
        searchPanel.add(label);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(idField);
        searchPanel.add(Box.createVerticalStrut(40));
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void showFormUpdate(Product produto) {
        FormUpdate formUpdate = new FormUpdate(produto, this);
        JPanel formPanel = formUpdate.getFormPanel();

        // Remover o painel de busca antes de adicionar o formulário de atualização
        mainPanel.add(formPanel, "formPanel");

        setSize(600, 700);
        setLocationRelativeTo(null);

        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "formPanel");
    }

    private void update(){
        String idText = idField.getText();
        try {
            int id = Integer.parseInt(idText);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.searchProductById(id);
            if (product != null) {
                showFormUpdate(product);
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UpdateProductFrame updateProductFrame = new UpdateProductFrame();
            updateProductFrame.setVisible(true);
        });
    }
}
