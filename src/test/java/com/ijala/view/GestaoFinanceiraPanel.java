//package test.view;
//
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import java.awt.*;
//import java.util.Random;
//
//public class GestaoFinanceiraPanel extends JFrame {
//
//    private JLabel totalRendaMensalLabel;
//    private JLabel totalDespesasMensaisLabel;
//    private JLabel saldoTotalCaixaLabel;
//    private JTextArea displayArea;
//
//    private JTextField despesaCategoriaField;
//    private JTextField despesaQuantidadeField;
//    private JTextField receitaDataField;
//    private JTextField receitaQuantidadeField;
//    private JTextField excluirCategoriaField;
//    private JTextField excluirDataField;
//    private JTextField excluirQuantidadeField;
//
//    private JButton addDespesaButton;
//    private JButton addReceitaButton;
//    private JButton deleteButton;
//
//    public GestaoFinanceiraPanel() {
//        setTitle("Gestão Financeira");
//        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a tela
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        initComponents();
//    }
//
//    private void initComponents() {
//        // Random values for the labels
//        Random rand = new Random();
//        double totalRendaMensal = 5000 + (10000 - 5000) * rand.nextDouble();
//        double totalDespesasMensais = 3000 + (7000 - 3000) * rand.nextDouble();
//        double saldoTotalCaixa = totalRendaMensal - totalDespesasMensais;
//
//        totalRendaMensalLabel = new JLabel(String.format("R$ %.2f", totalRendaMensal));
//        totalDespesasMensaisLabel = new JLabel(String.format("R$ %.2f", totalDespesasMensais));
//        saldoTotalCaixaLabel = new JLabel(String.format("R$ %.2f", saldoTotalCaixa));
//
//        // Set font color to white
//        totalRendaMensalLabel.setForeground(Color.WHITE);
//        totalDespesasMensaisLabel.setForeground(Color.WHITE);
//        saldoTotalCaixaLabel.setForeground(Color.WHITE);
//
//        // Header panel
//        JPanel headerPanel = new JPanel();
//        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20)); // Center alignment with horizontal gaps
//        headerPanel.setBackground(Color.decode("#2B2B2B"));
//        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 0, 10)); // Add margin
//        headerPanel.add(createLabeledPanel("Total Renda Mensal:", totalRendaMensalLabel));
//        headerPanel.add(createLabeledPanel("Total Despesas Mensais:", totalDespesasMensaisLabel));
//        headerPanel.add(createLabeledPanel("Saldo Total do Caixa:", saldoTotalCaixaLabel));
//
//        // Set font
//        totalRendaMensalLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        totalDespesasMensaisLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        saldoTotalCaixaLabel.setFont(new Font("Arial", Font.BOLD, 20));
//
//        // Tabela de Receita e Despesas
//        JLabel tabelaLabel = new JLabel("Tabela de Receita e Despesas");
//        tabelaLabel.setFont(new Font("Arial", Font.BOLD, 14));
//        tabelaLabel.setForeground(Color.WHITE);
//
//        JPanel tabelaPanel = new JPanel();
//        tabelaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        tabelaPanel.setBackground(Color.decode("#4B2B2B"));
//        tabelaPanel.add(tabelaLabel);
//
//        displayArea = new JTextArea(10, 50);
//        displayArea.setEditable(false);
//        displayArea.setBackground(Color.decode("#2B2B2B"));
//        displayArea.setForeground(Color.WHITE);
//
//        JScrollPane scrollPane = new JScrollPane(displayArea);
//        scrollPane.setColumnHeaderView(tabelaPanel);
//        scrollPane.getViewport().setBackground(Color.decode("#2B2B2B")); // Set the viewport background
//
//        // Main Panel
//        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 60, 20)); // Increase horizontal and vertical gaps
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60)); // Add margin around the main panel
//        mainPanel.setBackground(Color.decode("#2B2B2B"));
//
//        // Components for adding, updating, and deleting entries
//        JPanel addPanel = new JPanel(new GridLayout(4, 1, 10, 10));
//        addPanel.setBackground(Color.decode("#2B2B2B"));
//        addPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Gerenciar Entradas"));
//
//        despesaCategoriaField = new JTextField(10);
//        despesaQuantidadeField = new JTextField(10);
//        receitaDataField = new JTextField(10);
//        receitaQuantidadeField = new JTextField(10);
//        excluirCategoriaField = new JTextField(10);
//        excluirDataField = new JTextField(10);
//        excluirQuantidadeField = new JTextField(10);
//
//        addDespesaButton = new JButton("Adicionar Despesa");
//        addReceitaButton = new JButton("Adicionar Receita");
//        deleteButton = new JButton("Excluir Entrada");
//
//        addPanel.add(new JLabel("Categoria Despesa:"));
//        addPanel.add(despesaCategoriaField);
//        addPanel.add(new JLabel("Quantidade Despesa:"));
//        addPanel.add(despesaQuantidadeField);
//        addPanel.add(new JLabel("Data Receita:"));
//        addPanel.add(receitaDataField);
//        addPanel.add(new JLabel("Quantidade Receita:"));
//        addPanel.add(receitaQuantidadeField);
//        addPanel.add(new JLabel("Categoria Excluir:"));
//        addPanel.add(excluirCategoriaField);
//        addPanel.add(new JLabel("Data Excluir:"));
//        addPanel.add(excluirDataField);
//        addPanel.add(new JLabel("Quantidade Excluir:"));
//        addPanel.add(excluirQuantidadeField);
//        addPanel.add(addDespesaButton);
//        addPanel.add(addReceitaButton);
//        addPanel.add(deleteButton);
//
//        mainPanel.add(addPanel);
//        mainPanel.add(scrollPane);
//
//        // Layout Setup
//        setLayout(new BorderLayout(40, 40)); // Increase horizontal and vertical gaps
//        add(headerPanel, BorderLayout.NORTH);
//        add(mainPanel, BorderLayout.CENTER);
//
//        getContentPane().setBackground(Color.decode("#2B2B2B"));
//    }
//
//    private JPanel createLabeledPanel(String labelText, JLabel valueLabel) {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setPreferredSize(new Dimension(300, 100)); // Set fixed size
//        panel.setBackground(Color.decode("#2B2B2B"));
//        panel.setBorder(BorderFactory.createCompoundBorder(
//                new LineBorder(Color.WHITE, 1), // Border com 1 pixel de largura e cor branca
//                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margem interna
//        ));
//
//        JLabel label = new JLabel(labelText, JLabel.CENTER);
//        label.setForeground(Color.WHITE);
//        valueLabel.setHorizontalAlignment(JLabel.CENTER);
//        valueLabel.setForeground(Color.WHITE);
//        panel.add(label, BorderLayout.NORTH);
//        panel.add(valueLabel, BorderLayout.CENTER);
//
//        return panel;
//    }
//
//    // Getters for components in GestaoFinanceiraPanel
//    public JTextArea getDisplayArea() {
//        return displayArea;
//    }
//
//    public JTextField getDespesaCategoriaField() {
//        return despesaCategoriaField;
//    }
//
//    public JTextField getDespesaQuantidadeField() {
//        return despesaQuantidadeField;
//    }
//
//    public JTextField getReceitaDataField() {
//        return receitaDataField;
//    }
//
//    public JTextField getReceitaQuantidadeField() {
//        return receitaQuantidadeField;
//    }
//
//    public JTextField getExcluirCategoriaField() {
//        return excluirCategoriaField;
//    }
//
//    public JTextField getExcluirDataField() {
//        return excluirDataField;
//    }
//
//    public JTextField getExcluirQuantidadeField() {
//        return excluirQuantidadeField;
//    }
//
//    public JButton getAddDespesaButton() {
//        return addDespesaButton;
//    }
//
//    public JButton getAddReceitaButton() {
//        return addReceitaButton;
//    }
//
//    public JButton getDeleteButton() {
//        return deleteButton;
//    }
//}
