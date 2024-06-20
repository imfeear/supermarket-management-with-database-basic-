package com.ijala.view;

import com.ijala.controller.UserController;
import com.ijala.util.AddLabelAndField;
import com.ijala.util.panel.BackgroundPanel;
import com.ijala.util.ButtonUtil;
import com.ijala.util.panel.SideTitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserRegisterFrame extends JFrame {

    private JPanel formContainer;
    private GridBagConstraints formGbc;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;
    private UserController userController;

    public UserRegisterFrame() {
        setTitle("Cadastre-se");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Cadastro de\nUsuário");

        ImageIcon imageBackground = new ImageIcon(UserRegisterFrame.class.getResource("/image/background.png"));
        BackgroundPanel backgroundPanel = new BackgroundPanel(imageBackground.getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setPreferredSize(new Dimension(screenSize.width * 2 / 3, screenSize.height));

        formContainer = new JPanel();
        formContainer.setBackground(new Color(43, 43, 43));
        formContainer.setPreferredSize(new Dimension(500, 600));
        formContainer.setLayout(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.gridx = 0;
        formGbc.gridy = 0;

        ImageIcon logo = new ImageIcon(UserRegisterFrame.class.getResource("/icon/user-register.png"));
        JLabel labelLogo = new JLabel(logo);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(0, 0, 20, 0);
        formContainer.add(labelLogo, formGbc);

        formGbc.gridy++;
        ImageIcon userIcon = new ImageIcon(UserRegisterFrame.class.getResource("/icon/user.png"));
        AddLabelAndField.addLabelAndField("Nome:", userIcon, textFieldNome = new JTextField(), formContainer, formGbc);
        formGbc.gridy++;
        ImageIcon emailIcon = new ImageIcon(UserRegisterFrame.class.getResource("/icon/email.png"));
        AddLabelAndField.addLabelAndField("Email:", emailIcon, textFieldEmail = new JTextField(), formContainer, formGbc);
        formGbc.gridy++;
        ImageIcon passIcon = new ImageIcon(UserRegisterFrame.class.getResource("/icon/password.png"));
        AddLabelAndField.addLabelAndField("Senha:", passIcon, passwordFieldSenha = new JPasswordField(), formContainer, formGbc);

        ButtonUtil buttonRegister = new ButtonUtil("Cadastre-se", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        buttonRegister.setPreferredSize(new Dimension(300, 50));

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(40, 0, 0, 0);
        formContainer.add(buttonRegister, formGbc);

        backgroundPanel.add(formContainer);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideTitlePanel.getSideTitlePanel(), backgroundPanel);
        splitPane.setDividerLocation(screenSize.width / 3);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);

        // Inicialização do UserController
        userController = new UserController();
    }

    private void register() {
        try {
            String name = textFieldNome.getText();
            String email = textFieldEmail.getText().trim();
            String password = new String(passwordFieldSenha.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
            }

            boolean isRegistered = userController.registerUser(name, email, password);

            if (isRegistered) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal para iniciar a aplicação.
     * Cria uma instância da UserRegisterFrame (tela de cadastro de usuário) e a exibe.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegisterFrame::new);
    }
}
