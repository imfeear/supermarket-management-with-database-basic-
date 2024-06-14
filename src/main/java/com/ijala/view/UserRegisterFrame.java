package com.ijala.view;

import com.ijala.model.user.UserDAO;
import com.ijala.util.AddLabelAndField;
import com.ijala.util.BackgroundPanel;
import com.ijala.util.SideTitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegisterFrame extends JFrame {

    private JPanel formContainer;
    private GridBagConstraints formGbc;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;
    private JButton buttonRegister;

    public UserRegisterFrame() {
        setTitle("Cadastre-se");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        SideTitlePanel sideTitlePanel = new SideTitlePanel(screenSize);
        sideTitlePanel.setTitulo("Cadastro de\nUsuário");

        ImageIcon imageBackground = new ImageIcon("src/main/resources/image/fundo.png");
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

        ImageIcon logo = new ImageIcon("src/main/resources/icon/user-rectangle-solid-108.png");
        JLabel labelLogo = new JLabel(logo);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(0, 0, 20, 0);
        formContainer.add(labelLogo, formGbc);
        formGbc.gridy++;
        AddLabelAndField.addLabelAndField("Nome:", textFieldNome = new JTextField(), formContainer, formGbc);

        formGbc.gridy++;
        AddLabelAndField.addLabelAndField("Email:", textFieldEmail = new JTextField(), formContainer, formGbc);

        formGbc.gridy++;
        AddLabelAndField.addLabelAndField("Senha:", passwordFieldSenha = new JPasswordField(), formContainer, formGbc);

        buttonRegister = new JButton("Cadastre-se");
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 14));
        buttonRegister.setPreferredSize(new Dimension(300, 50));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBackground(new Color(46, 86, 190));
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textFieldNome.getText();
                    String email = textFieldEmail.getText().trim();
                    String senha = new String(passwordFieldSenha.getPassword());

                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
                    }

                    UserDAO userDAO = new UserDAO();
                    boolean isRegistered = userDAO.userRegister(nome, email, senha);

                    if (isRegistered) {
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                        dispose(); // Fecha a tela de cadastro
                        LoginFrame login = new LoginFrame();
                        login.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage());
                }
            }
        });

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.gridy++;
        formGbc.insets = new Insets(40, 0, 0, 0);
        formContainer.add(buttonRegister, formGbc);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(formContainer, gbc);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideTitlePanel.getSideTitlePanel(), backgroundPanel);
        splitPane.setDividerLocation(screenSize.width / 3);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegisterFrame::new);
    }
}
