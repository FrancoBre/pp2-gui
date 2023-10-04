package org.ungs.view;

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
import shoppinator.core.model.Product;

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

    public ShoppinatorView(Shoppinator shoppinator) {
        this.initialize();
        this.shoppinator = shoppinator;
        this.shoppinatorController = new ShoppinatorController(this, shoppinator);
        addObservers();
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
        ImageIcon refreshIcon = new ImageIcon("src/main/resources/img/refresh.png");
        Image scaledImage = refreshIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(scaledImage);
        refreshButton.setIcon(refreshIcon);
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

        JPanel checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new FlowLayout());
        checkboxesPanel.setBackground(new Color(55, 71, 79));
        checkboxesNotSelected = new JLabel(new ImageIcon("src/main/resources/img/arrow.png"));

        fravegaCheckbox = new JCheckBox("Fravega");
        garbarinoCheckbox = new JCheckBox("Garbarino");

        fravegaCheckbox.setForeground(Color.WHITE);
        garbarinoCheckbox.setForeground(Color.WHITE);

        JPanel imageAndCheckboxesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imageAndCheckboxesPanel.setBackground(new Color(55, 71, 79));

        imageAndCheckboxesPanel.add(checkboxesNotSelected);
        imageAndCheckboxesPanel.add(fravegaCheckbox);
        imageAndCheckboxesPanel.add(garbarinoCheckbox);

        checkboxesNotSelected.setVisible(false);

        inputPanel.add(imageAndCheckboxesPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public void init() {
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object productList) {
        List<Product> products = (List<Product>) productList;
        shoppinatorController.updateProductsPanel(products);
    }

    private void addObservers() {
        shoppinator.addObserver(this);

        this.update(null, shoppinator.getProducts());
    }
}