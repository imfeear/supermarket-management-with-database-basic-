package com.ijala.view;

import com.ijala.model.user.UserDAO;
import com.ijala.util.AddLabelAndField;
import com.ijala.util.BackgroundPanel;
import com.ijala.util.ButtonUtil;
import com.ijala.util.SideTitlePanel;

import javax.swing.*;
import java.awt.*;

public class UserRegisterFrame extends JFrame {

    private JPanel formContainer;
    private GridBagConstraints formGbc;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JPasswordField passwordFieldSenha;

    public UserRegisterFrame() {
        setTitle("Cadastre-se");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        ImageIcon userIcon = new ImageIcon("src/main/resources/icon/user-regular-24.png");
        AddLabelAndField.addLabelAndField("Nome:", userIcon, textFieldNome = new JTextField(), formContainer, formGbc);
        formGbc.gridy++;
        ImageIcon emailIcon = new ImageIcon("src/main/resources/icon/envelope-regular-24.png");
        AddLabelAndField.addLabelAndField("Email:", emailIcon, textFieldEmail = new JTextField(), formContainer, formGbc);
        formGbc.gridy++;
        ImageIcon passIcon = new ImageIcon("src/main/resources/icon/lock-open-regular-24.png");
        AddLabelAndField.addLabelAndField("Senha:", passIcon, passwordFieldSenha = new JPasswordField(), formContainer, formGbc);

        ButtonUtil buttonRegister = new ButtonUtil("Cadastre-se", e -> register());
        buttonRegister.setPreferredSize(new Dimension(300, 50));

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

    private void register(){
        try {
            String name = textFieldNome.getText();
            String email = textFieldEmail.getText().trim();
            String password = new String(passwordFieldSenha.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios e devem conter valores válidos.");
            }

            UserDAO userDAO = new UserDAO();
            boolean isRegistered = userDAO.userRegister(name, email, password);

            if (isRegistered) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Fecha a tela de cadastro
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegisterFrame::new);
    }
}