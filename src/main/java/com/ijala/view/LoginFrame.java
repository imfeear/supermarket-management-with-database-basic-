package com.ijala.view;

import com.ijala.controller.UserController;
import com.ijala.service.UserService;
import com.ijala.model.user.User;
import com.ijala.util.AddLabelAndField;
import com.ijala.util.panel.BackgroundPanel;
import com.ijala.util.ButtonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;
    private UserController userController;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(LoginFrame.class.getResource("/image/background.png")).getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel formContainer = new JPanel();
        formContainer.setBackground(new Color(43, 43, 43));
        formContainer.setPreferredSize(new Dimension(500, 600));
        formContainer.setLayout(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        ));

        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.gridx = 0;
        formGbc.gridy = 0;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        titlePanel.setBackground(new Color(43, 43, 43));

        ImageIcon logo = new ImageIcon(LoginFrame.class.getResource("/icon/log-in.png"));
        JLabel labelLogo = new JLabel(logo);
        titlePanel.add(labelLogo);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(0, 0, 40, 0);
        formContainer.add(titlePanel, formGbc);
        formGbc.gridy++;

        // Adicionando label e campo de texto para o Email com imagem
        ImageIcon emailIcon = new ImageIcon(LoginFrame.class.getResource("/icon/email.png"));
        AddLabelAndField.addLabelAndField("Email:", emailIcon, textFieldEmail = new JTextField(), formContainer, formGbc);

        // Adicionando label e campo de texto para a Senha com imagem
        ImageIcon senhaIcon = new ImageIcon(LoginFrame.class.getResource("/icon/password.png"));
        AddLabelAndField.addLabelAndField("Senha:", senhaIcon, passwordFieldSenha = new JPasswordField(), formContainer, formGbc);

        ButtonUtil buttonUserLogin = new ButtonUtil("Login", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(40, 0, 10, 0);
        formContainer.add(buttonUserLogin, formGbc);

        ButtonUtil buttonUserRegister = new ButtonUtil("Cadastre-se", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserRegisterFrame userRegister = new UserRegisterFrame();
                userRegister.setVisible(true);
            }
        });
        buttonUserRegister.setBackground(Color.decode("#0A1AAD"));

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(20, 0, 0, 0);
        formContainer.add(buttonUserRegister, formGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(formContainer, gbc);

        add(backgroundPanel);
        setVisible(true);

        // Inicialização do UserController
        UserService userService = new UserService();
        userController = new UserController(userService);
    }

    private void loginUser() {
        try {
            String email = textFieldEmail.getText().trim();
            String password = new String(passwordFieldSenha.getPassword());

            User user = userController.loginUser(email, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido. Bem-vindo(a) " + user.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Fecha a tela de login
                MenuFrame menu = new MenuFrame();
                menu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Email ou senha incorretos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao logar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal para iniciar a aplicação.
     * Cria uma instância da LoginFrame (tela de login) e a exibe.
     *
     * @param args Argumentos da linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
