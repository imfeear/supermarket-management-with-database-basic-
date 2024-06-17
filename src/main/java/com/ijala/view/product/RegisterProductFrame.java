package com.ijala.view.product;

import com.ijala.util.BackgroundPanel;
import com.ijala.util.SideTitlePanel;
import com.ijala.util.form.FormRegisterProduct;

import javax.swing.*;
import java.awt.*;

public class RegisterProductFrame extends JFrame {

    private JPanel formContainer;

    public RegisterProductFrame() {
        setTitle("Cadastro de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Cadastro de\nProduto");

        ImageIcon imageBackground = new ImageIcon(RegisterProductFrame.class.getResource("/image/image.png"));
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
        formContainerGbc.insets = new Insets(10, 10, 10, 10);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterProductFrame frame = new RegisterProductFrame();
            frame.setVisible(true);
        });
    }
}
