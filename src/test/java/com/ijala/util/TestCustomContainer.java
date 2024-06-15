package com.ijala.util;

import com.ijala.util.component.FormCustomContent;
import com.ijala.util.component.FormCustomSmallContent;

import javax.swing.*;
import java.awt.*;

public class TestCustomContainer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Custom Container");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Usando GridLayout com 1 coluna e espaçamento de 10 entre os componentes
            mainPanel.setBackground(Color.decode("#2B2B2B"));

            // Test creating large container
            JPanel largeContainer = FormCustomContent.create("Large Container", new JTextField(),true);
            mainPanel.add(largeContainer);

            // Usando um JPanel com FlowLayout para os smallContainers permitindo dois componentes por linha
            JPanel smallContainersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Layout FlowLayout com alinhamento à esquerda e espaçamento de 10
            smallContainersPanel.setBackground(Color.decode("#2B2B2B"));

            // Test creating small container
            JPanel smallContainer1 = FormCustomSmallContent.create("Small Container 1",new JTextField(), false);
            smallContainersPanel.add(smallContainer1);

            JPanel smallContainer2 = FormCustomSmallContent.create("Small Container 2",new JTextField(), false);
            smallContainersPanel.add(smallContainer2);

            mainPanel.add(smallContainersPanel); // Adicionando o painel de contêineres pequenos ao painel principal

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
