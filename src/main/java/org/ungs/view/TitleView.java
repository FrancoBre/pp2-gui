package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class TitleView extends JFrame {

    public TitleView() {
        setTitle("Shopinator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/img/logo.png"));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(logoLabel, BorderLayout.PAGE_START, 0);

        ImageIcon icono = new ImageIcon("src/main/resources/img/icon.png");
        setIconImage(icono.getImage());

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(55, 71, 79)); // Custom background color

        // Product name label and input field
        JLabel promptLabel = new JLabel("Ingrese el producto que desea buscar:");
        JTextField productNameField = new JTextField(20);
        productNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        productNameField.setPreferredSize(new Dimension(300, 30));
        productNameField.setBackground(new Color(33, 150, 243)); // Custom input field color
        productNameField.setForeground(Color.WHITE); // Custom text color

        // Add components to the input panel
        promptLabel.setForeground(Color.WHITE); // Custom text color
        inputPanel.add(promptLabel);
        inputPanel.add(productNameField);

        // Add the input panel to the frame
        add(inputPanel, BorderLayout.SOUTH);

        // Instructions list
        String instructions = "<html><ul>"
            + "<li>Ingrese un producto en la barra de búsqueda inferior</li>"
            + "<li>Espere unos segundos</li>"
            + "<li>Elija el producto que más le convenga</li>"
            + "<li>Listo! Hizo uso de su dinero inteligentemente</li>"
            + "</ul></html>";
        JLabel instructionsLabel = new JLabel(instructions);
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the instructions label to the frame
        add(instructionsLabel, BorderLayout.CENTER, -1); // Instructions at the top

        pack();
        setLocationRelativeTo(null); // Center the window

        setVisible(true);
    }
}
