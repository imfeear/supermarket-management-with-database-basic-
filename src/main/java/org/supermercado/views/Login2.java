package org.supermercado.views;

import javax.swing.*;
import java.awt.*;

public class Login2 extends JFrame {

    // Declaração de componentes da interface
    private JPanel panelLeft;
    private JLabel labelTitulo;

    private JPanel panelRight;
    private JPanel panelFormWrapper;
    private GridBagConstraints formGbc;
    private JTextField textFieldUsuario;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;

    // Construtor da classe
    public Login2() {
        // Configurações da janela principal
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definição do tamanho da janela baseado no tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        // Configuração do painel esquerdo
        panelLeft = new JPanel();
        panelLeft.setBackground(new Color(46, 86, 190));
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));

        // Configuração do título no painel esquerdo
        labelTitulo = new JLabel("Login", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 54));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelLeft.add(labelTitulo, BorderLayout.CENTER);

        // Configuração do painel direito
        panelRight = new JPanel();
        panelRight.setBackground(new Color(43, 43, 43));
        panelRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Configuração do painel que contém o formulário
        panelFormWrapper = new JPanel();
        panelFormWrapper.setBackground(new Color(43, 43, 43));
        panelFormWrapper.setPreferredSize(new Dimension(500, 500));
        panelFormWrapper.setLayout(new GridBagLayout());
        panelFormWrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.gridx = 0;
        formGbc.gridy = 0;

        // Adição dos campos de entrada no formulário
        addLabelAndField("Usuário:", textFieldUsuario = new JTextField());
        addLabelAndField("Senha:", passwordFieldSenha = new JPasswordField());

        // Configuração do botão de login
        buttonLogin = new JButton("Login");
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 24));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBackground(new Color(46, 86, 190));
        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(20, 0, 0, 0);
        panelFormWrapper.add(buttonLogin, formGbc);

        // Adição do formulário ao painel direito
        panelRight.add(panelFormWrapper, gbc);

        // Configuração do layout principal com SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
        splitPane.setDividerLocation(panelLeft.getPreferredSize().width);
        splitPane.setEnabled(false);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para adicionar labels e campos de texto no formulário
    private void addLabelAndField(String labelText, JTextField textField) {
        // Cria e configura painel de label
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        labelPanel.setBackground(new Color(43, 43, 43));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        labelPanel.add(label);

        formGbc.gridwidth = 2;
        formGbc.gridx = 0;
        panelFormWrapper.add(labelPanel, formGbc);
        formGbc.gridy++;

        // Configura campo de texto
        formGbc.gridwidth = 2;
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelFormWrapper.add(textField, formGbc);
        formGbc.gridy++;
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login2::new);
    }

    // Painel com a imagem de fundo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
