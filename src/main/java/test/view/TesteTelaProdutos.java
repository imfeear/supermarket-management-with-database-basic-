package test.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TesteTelaProdutos {

    public static void main(String[] args) {
        // Cria o JFrame (janela principal)
        JFrame frame = new JFrame("Tabela de Produtos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Define o fundo da janela como preto
        frame.getContentPane().setBackground(Color.BLACK);

        // Cria um painel para conter a tabela
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        // Definição das colunas da tabela
        String[] colunas = {"ID", "Nome", "Preço", "Quantidade"};

        // Dados dos produtos (exemplo)
        Object[][] dados = {
                {1, "Produto A", 10.50, 20},
                {2, "Produto B", 22.30, 15},
                {3, "Produto C", 15.00, 10},
                {4, "Produto D", 8.75, 50}
        };

        // Cria o modelo da tabela
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

        // Cria a tabela
        JTable tabela = new JTable(modelo);

        // Define a cor do fundo da tabela como preto e o texto em branco
        tabela.setBackground(Color.BLACK);
        tabela.setForeground(Color.WHITE);
        tabela.setGridColor(Color.WHITE);
        tabela.setSelectionBackground(Color.DARK_GRAY);
        tabela.setSelectionForeground(Color.WHITE);

        // Adiciona a tabela a um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Adiciona o JScrollPane ao painel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o painel ao frame
        frame.add(panel);

        // Torna a janela visível
        frame.setVisible(true);
    }
}

