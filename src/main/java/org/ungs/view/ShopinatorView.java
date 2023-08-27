package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import lombok.Getter;
import org.ungs.controller.ShopinatorController;
import service.ProductSearcher;

@Getter
public class ShopinatorView extends JFrame {

    JTextField productNameField;

    ProductSearcher productSearcher;
    ShopinatorController shopinatorController;
    TitlePanel titlePanel;
    NotFoundPanel notFoundPanel;

    public ShopinatorView(ProductSearcher productSearcher) {
        this.initialize();
        this.productSearcher = productSearcher;
        this.shopinatorController = new ShopinatorController(this, productSearcher);
    }

    public void initialize() {
        setTitle("Shopinator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(55, 71, 79)); // Custom background color

        // Product name label and input field
        JLabel promptLabel = new JLabel("Ingrese el producto que desea buscar:");
        productNameField = new JTextField(20);

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

        pack();
        setLocationRelativeTo(null); // Center the window

        this.titlePanel = new TitlePanel();
        this.add(titlePanel, BorderLayout.CENTER);
    }

    public void init() {
        this.setVisible(true);
    }

}