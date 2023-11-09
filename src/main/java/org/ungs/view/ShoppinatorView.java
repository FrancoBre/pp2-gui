package org.ungs.view;

import entities.Result;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import lombok.Getter;
import lombok.Setter;
import org.ungs.controller.ShoppinatorController;
import shoppinator.core.Shoppinator;

@Getter
@Setter
public class ShoppinatorView extends JFrame implements Observer {

    JTextField productNameField;
    JFormattedTextField minPriceField;
    JFormattedTextField maxPriceField;
    JButton refreshButton;
    JCheckBox fravegaCheckbox;
    JCheckBox garbarinoCheckbox;
    JLabel checkboxesNotSelected;

    Shoppinator shoppinator;
    ShoppinatorController shoppinatorController;
    NotFoundPanel notFoundPanel;
    ProductsPanel productsPanel;
    SpinnerPanel spinnerPanel;
    JPanel checkboxesPanel;
    List<JCheckBox> checkBoxes;
    List<Result> productList;

    public ShoppinatorView(Shoppinator shoppinator) {
        this.shoppinator = shoppinator;
        this.initialize();
        this.shoppinatorController = new ShoppinatorController(this, shoppinator);
        shoppinator.addObserver(this);
        this.productList = new ArrayList<>();
    }

    public void initialize() {
        setTitle("Shoppinator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the header panel

        GridBagConstraints logoConstraints = new GridBagConstraints();
        logoConstraints.gridx = 0;
        logoConstraints.gridy = 0;
        logoConstraints.weightx = 1.0; // Expand horizontally
        logoConstraints.insets = new Insets(10, 10, 10, 10); // Padding
        logoConstraints.anchor = GridBagConstraints.CENTER; // Center alignment

        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/img/logo.png"));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        headerPanel.add(logoLabel, logoConstraints);

        GridBagConstraints refreshConstraints = new GridBagConstraints();
        refreshConstraints.gridx = 1;
        refreshConstraints.gridy = 0;
        refreshConstraints.insets = new Insets(10, 10, 10, 10); // Padding
        refreshConstraints.anchor = GridBagConstraints.LINE_START; // Align to the left

        refreshButton = new JButton();
        ImageIcon refreshIcon = new ImageIcon("src/main/resources/img/red-button.png");
        Image scaledImage = refreshIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(scaledImage);
        refreshButton.setIcon(refreshIcon);
        refreshButton.setOpaque(false);
        refreshButton.setContentAreaFilled(false);
        refreshButton.setBorderPainted(false);
        headerPanel.add(refreshButton, refreshConstraints);

        add(headerPanel, BorderLayout.PAGE_START);

        ImageIcon icon = new ImageIcon("src/main/resources/img/icon.png");
        setIconImage(icon.getImage());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(new Color(55, 71, 79));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBackground(new Color(55, 71, 79));

        JLabel promptLabel = new JLabel("Ingrese el producto que desea buscar:");
        productNameField = new JTextField(20);
        productNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        productNameField.setPreferredSize(new Dimension(300, 30));
        productNameField.setBackground(new Color(33, 150, 243));
        productNameField.setForeground(Color.WHITE);

        promptLabel.setForeground(Color.WHITE);
        searchPanel.add(promptLabel);
        searchPanel.add(productNameField);

        inputPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout());
        pricePanel.setBackground(new Color(55, 71, 79));

        JLabel minPriceLabel = new JLabel("Precio mínimo:");
        minPriceLabel.setForeground(Color.WHITE);
        minPriceField = new JFormattedTextField(NumberFormat.getNumberInstance());
        minPriceField.setColumns(10);
        minPriceField.setBackground(new Color(33, 150, 243));
        minPriceField.setForeground(Color.WHITE);

        JLabel maxPriceLabel = new JLabel("Precio máximo:");
        maxPriceLabel.setForeground(Color.WHITE);
        maxPriceField = new JFormattedTextField(NumberFormat.getNumberInstance());
        maxPriceField.setColumns(10);
        maxPriceField.setForeground(Color.WHITE);
        maxPriceField.setBackground(new Color(33, 150, 243));

        pricePanel.add(minPriceLabel);
        pricePanel.add(minPriceField);
        pricePanel.add(maxPriceLabel);
        pricePanel.add(maxPriceField);

        inputPanel.add(pricePanel, BorderLayout.CENTER);

        checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new FlowLayout());
        checkboxesPanel.setBackground(new Color(55, 71, 79));

        checkBoxes = new ArrayList<>();
        // every shop name is a checkbox, with an identifier that's the shop name
        for (String shopName : shoppinator.getShopNames()) {
            JCheckBox shopCheckbox = new JCheckBox(shopName);
            shopCheckbox.setForeground(Color.WHITE);
            checkboxesPanel.add(shopCheckbox);
            checkBoxes.add(shopCheckbox);
        }

        inputPanel.add(checkboxesPanel, BorderLayout.SOUTH);

        JPanel imageAndCheckboxesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imageAndCheckboxesPanel.setBackground(new Color(55, 71, 79));

        imageAndCheckboxesPanel.add(checkboxesPanel);

        inputPanel.add(imageAndCheckboxesPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public void init() {
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object products) {
        productList.addAll((List<Result>) products);
        shoppinatorController.updateProductsPanel(productList);
    }

}