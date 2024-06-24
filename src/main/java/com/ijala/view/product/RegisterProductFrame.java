package com.ijala.view.product;

import com.ijala.controller.ProductController;
import com.ijala.service.ProductService;
import com.ijala.util.*;
import com.ijala.util.form.FormRegisterProduct;
import com.ijala.util.panel.BackgroundPanel;
import com.ijala.util.panel.CategoryTablePanel;
import com.ijala.util.panel.SideTitlePanel;
import com.ijala.util.panel.SupplierTablePanel;

import javax.swing.*;
import java.awt.*;

public class RegisterProductFrame extends JFrame {

    private JPanel formContainer;

    public RegisterProductFrame(ProductController productController) {
        setTitle("Cadastro de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        // Configura o SideTitlePanel
        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Cadastro de\nProduto");

        // Configura o painel de botões
        JPanel consultPanel = new JPanel();
        consultPanel.setBackground(new Color(46, 86, 190));
        consultPanel.setLayout(new GridLayout(2, 1, 10, 20));

        ButtonUtil registerSupplierButton = new ButtonUtil("Consultar Fornecedores", e -> openSupplierTable());
        consultPanel.add(registerSupplierButton);

        ButtonUtil registerCategoryButton = new ButtonUtil("Consultar Categorias", e -> openCategoryTable());
        consultPanel.add(registerCategoryButton);

        customButton(registerCategoryButton);
        customButton(registerSupplierButton);

        // Adiciona o painel de botões ao sideTitlePanel
        sideTitlePanel.getSideTitlePanel().setLayout(new GridBagLayout());
        GridBagConstraints buttonPanelGbc = new GridBagConstraints();
        buttonPanelGbc.gridx = 0;
        buttonPanelGbc.gridy = 1;
        buttonPanelGbc.insets = new Insets(80, 0, 0, 0);
        buttonPanelGbc.anchor = GridBagConstraints.NORTH;
        sideTitlePanel.getSideTitlePanel().add(consultPanel, buttonPanelGbc);

        // Configura o painel de fundo
        ImageIcon imageBackground = new ImageIcon(RegisterProductFrame.class.getResource("/image/background.png"));
        BackgroundPanel backgroundPanel = new BackgroundPanel(imageBackground.getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setPreferredSize(new Dimension(screenSize.width * 2 / 3, screenSize.height));

        formContainer = new JPanel();
        formContainer.setBackground(new Color(43, 43, 43));
        formContainer.setLayout(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints formContainerGbc = new GridBagConstraints();
        formContainerGbc.gridx = 0;
        formContainerGbc.gridy = 0;
        formContainerGbc.anchor = GridBagConstraints.CENTER;

        FormRegisterProduct formCadastroProduto = new FormRegisterProduct(this, productController);
        JPanel formPanel = formCadastroProduto.getFormPanel();
        formContainer.add(formPanel, formContainerGbc);

        GridBagConstraints backgroundPanelGbc = new GridBagConstraints();
        backgroundPanelGbc.gridx = 1;
        backgroundPanelGbc.gridy = 0;
        backgroundPanelGbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(formContainer, backgroundPanelGbc);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideTitlePanel.getSideTitlePanel(), backgroundPanel);
        splitPane.setDividerLocation(screenSize.width / 3);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void customButton(ButtonUtil button) {
        button.setBackground(Color.decode("#2B2B2B"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(350,80));
    }

    private void openSupplierTable() {
        JFrame supplierFrame = new JFrame("Tabela de Fornecedores");
        SupplierTablePanel supplierTablePanel = new SupplierTablePanel();
        supplierFrame.add(supplierTablePanel);
        supplierFrame.setSize(800, 600);
        supplierFrame.setLocationRelativeTo(null);
        supplierFrame.setVisible(true);
    }

    private void openCategoryTable() {
        JFrame categoryFrame = new JFrame("Tabela de Categorias");
        CategoryTablePanel categoryTablePanel = new CategoryTablePanel();
        categoryFrame.add(categoryTablePanel);
        categoryFrame.setSize(800, 600);
        categoryFrame.setLocationRelativeTo(null);
        categoryFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterProductFrame frame = new RegisterProductFrame(new ProductController(new ProductService()));
            frame.setVisible(true);
        });
    }
}
