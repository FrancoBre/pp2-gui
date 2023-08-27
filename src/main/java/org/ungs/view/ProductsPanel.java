package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import models.Product;

public class ProductsPanel extends JPanel {

    public ProductsPanel(List<Product> products) {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        for (Product product : products) {
            JPanel card = createCard(product);
            cardPanel.add(card);
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createCard(Product product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 200));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel nameLabel = new JLabel(product.getName());
        card.add(nameLabel, BorderLayout.NORTH);

        JLabel priceLabel = new JLabel("Price: " + product.getProductPresentation().getPrice());
        card.add(priceLabel, BorderLayout.CENTER);

        JButton postButton = new JButton("Visit Shop");
        postButton.addActionListener(e -> {
            String postUrl = product.getProductPresentation().getShopProduct().getPostUrl();
            // Open the post URL in a browser or perform relevant action
        });
        card.add(postButton, BorderLayout.SOUTH);

        return card;
    }
}
