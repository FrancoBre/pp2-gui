package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

    Shoppinator shoppinator;
    ShoppinatorController shoppinatorController;
    TitlePanel titlePanel;
    NotFoundPanel notFoundPanel;
    ProductsPanel productsPanel;
    SpinnerPanel spinnerPanel;

    public ShoppinatorView(Shoppinator shoppinator) {
        this.initialize();
        this.shoppinator = shoppinator;
        this.shoppinatorController = new ShoppinatorController(this, shoppinator);
        addObservers();
    }

    public void init(Shoppinator shoppinator) {
        this.shoppinator = shoppinator;
        initialize();
        this.shoppinatorController = new ShoppinatorController(this, shoppinator);
    }

    public void initialize() {
        setTitle("Shoppinator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/img/logo.png"));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(logoLabel, BorderLayout.PAGE_START, 0);

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