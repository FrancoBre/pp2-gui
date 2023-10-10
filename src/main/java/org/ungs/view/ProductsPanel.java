package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import org.ungs.utils.ShoppinatorUtil;
import shoppinator.core.model.Product;

public class ProductsPanel extends JPanel {

    public ProductsPanel(List<Product> products) {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Load and create cards
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

        for (Product product : products) {
            JPanel card = createCard(product);
            cardPanel.add(card);
            cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Replace spinner GIF with the card panel
        SwingUtilities.invokeLater(() -> {
            JScrollPane scrollPane = new JScrollPane(cardPanel);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

    }

    private JPanel createCard(Product product) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 100));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        card.setBackground(Color.DARK_GRAY); // Set background color
        card.setLayout(new GridBagLayout()); // Use GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align components to top-left
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Add image to the left
        JLabel imageLabel = new JLabel();
        BufferedImage image = ShoppinatorUtil.fetchImageFromUrl(product.getProductPresentation().getProductImageUrl());
        imageLabel.setIcon(new ImageIcon(image));
        gbc.gridx = 0;
        gbc.gridy = 0; // Use gridy value
        gbc.gridheight = 2; // Span two rows
        card.add(imageLabel, gbc);

        // Create a panel for title and price
        JPanel titlePricePanel = new JPanel();
        titlePricePanel.setLayout(new BorderLayout());
        titlePricePanel.setBackground(Color.DARK_GRAY); // Set background color

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setForeground(Color.WHITE); // Set text color
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size
        titlePricePanel.add(nameLabel, BorderLayout.NORTH);

        JLabel priceLabel = new JLabel("Precio: " + product.getProductPresentation().getPrice());
        priceLabel.setForeground(Color.WHITE); // Set text color
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font and size
        titlePricePanel.add(priceLabel, BorderLayout.CENTER);

        // Use a JLabel with vertical scrolling for the title
        JLabel title = new JLabel("<html><body>" + product.getName() + "</body></html>");
        title.setForeground(Color.WHITE);
        title.setVerticalAlignment(JLabel.TOP);
        title.setVerticalTextPosition(JLabel.TOP);
        title.setOpaque(false); // Make it transparent
        title.setPreferredSize(new Dimension(200, 50)); // Adjust the size as needed

        // Add the title to the card
        gbc.gridx = 1;
        gbc.gridy = 0; // Use gridy value
        gbc.gridheight = 1; // Reset grid height
        card.add(titlePricePanel, gbc);

        // Add button at the bottom
        JButton postButton = new JButton("Visitar tienda");
        postButton.addActionListener(e -> {
            //String postUrl = product.getProductPresentation().getShopProduct().getPostUrl();
            String postUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            ShoppinatorUtil.openWebBrowser(postUrl);
        });
        gbc.gridx = 1;
        gbc.gridy = 1; // Use gridy value
        card.add(postButton, gbc);

        return card;
    }

}
