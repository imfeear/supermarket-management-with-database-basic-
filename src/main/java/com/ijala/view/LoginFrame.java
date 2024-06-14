package com.ijala.view;

import com.ijala.model.user.User;
import com.ijala.model.user.UserDAO;
import com.ijala.util.BackgroundPanel;
import com.ijala.util.AddLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JPanel formContainer;
    private GridBagConstraints formGbc;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;
    private JButton buttonRegister;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("src/main/resources/image/fundo.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());

        formContainer = new JPanel();
        formContainer.setBackground(new Color(43, 43, 43));
        formContainer.setPreferredSize(new Dimension(500, 600));
        formContainer.setLayout(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        ));
        formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.gridx = 0;
        formGbc.gridy = 0;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        titlePanel.setBackground(new Color(43, 43, 43));

        ImageIcon logo = new ImageIcon("src/main/resources/icon/log-in-regular-72.png");
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

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(10, 0, 10, 0);
        AddLabelAndField.addLabelAndField("Email:", textFieldEmail = new JTextField(), formContainer, formGbc);

        formGbc.gridy++;
        AddLabelAndField.addLabelAndField("Senha:", passwordFieldSenha = new JPasswordField(), formContainer, formGbc);

        buttonLogin = new JButton("Login");
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonLogin.setPreferredSize(new Dimension(300, 50));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBackground(new Color(46, 86, 190));
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = textFieldEmail.getText().trim();
                    String senha = new String(passwordFieldSenha.getPassword());

                    if (email.isEmpty() || senha.isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
                    }

                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.userLogin(email, senha);

                    if (user != null) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido. Bem-vindo(a) " + user.getNome() + "!");
                        dispose(); // Fecha a tela de login
                        MenuFrame menu = new MenuFrame();
                        menu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao logar usuário: " + ex.getMessage());
                }
            }
        });
        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(40, 0, 10, 0);
        formContainer.add(buttonLogin, formGbc);

        buttonRegister = new JButton("Cadastre-se");
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 14));
        buttonRegister.setPreferredSize(new Dimension(300, 50));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBackground(Color.decode("#0A1AAD"));
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de login
                UserRegisterFrame userRegister = new UserRegisterFrame();
                userRegister.setVisible(true);
            }
        });

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(20, 0, 0, 0);
        formContainer.add(buttonRegister, formGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(formContainer, gbc);

        add(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
    }
}
