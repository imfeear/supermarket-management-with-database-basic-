package com.ijala.view.product;

import com.ijala.util.BackgroundPanel;
import com.ijala.util.SideTitlePanel;
//import com.ijala.util.form.FormRegisterCategory;
import com.ijala.util.form.FormRegisterProduct;
//import com.ijala.util.form.FormRegisterSupplier;

import javax.swing.*;
import java.awt.*;

public class RegisterProductFrame extends JFrame {

    private JPanel formContainer;

    public RegisterProductFrame() {
        setTitle("Cadastro de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        // Configura o SideTitlePanel
        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Cadastro de\nProduto");
//
//        // Configura o painel de botões
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Define 2 linhas e 1 coluna com um gap de 10 pixels
//
//        JButton registerSupplierButton = new JButton("Cadastrar Fornecedor");
//        registerSupplierButton.addActionListener(e -> openRegisterSupplierForm());
//        buttonPanel.add(registerSupplierButton);
//
//        JButton registerCategoryButton = new JButton("Cadastrar Categoria");
//        registerCategoryButton.addActionListener(e -> openRegisterCategoryForm());
//        buttonPanel.add(registerCategoryButton);

//        // Adiciona o painel de botões ao sideTitlePanel
//        sideTitlePanel.getSideTitlePanel().setLayout(new GridBagLayout());
//        GridBagConstraints buttonPanelGbc = new GridBagConstraints();
//        buttonPanelGbc.gridx = 0;
//        buttonPanelGbc.gridy = 1; // Posiciona abaixo do título
//        buttonPanelGbc.insets = new Insets(20, 0, 0, 0); // Adiciona algum espaço acima dos botões
//        buttonPanelGbc.anchor = GridBagConstraints.NORTH;
//        sideTitlePanel.getSideTitlePanel().add(buttonPanel, buttonPanelGbc);

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

        FormRegisterProduct formCadastroProduto = new FormRegisterProduct(this);
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

//    private void openRegisterSupplierForm() {
//        FormRegisterSupplier registerSupplierForm = new FormRegisterSupplier();
//        registerSupplierForm.setVisible(true);
//    }
//
//    private void openRegisterCategoryForm() {
//        FormRegisterCategory registerCategoryForm = new FormRegisterCategory();
//        registerCategoryForm.setVisible(true);
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterProductFrame frame = new RegisterProductFrame();
            frame.setVisible(true);
        });
    }
}
