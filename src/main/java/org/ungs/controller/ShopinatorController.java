package org.ungs.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Product;
import org.ungs.view.NotFoundPanel;
import org.ungs.view.ProductsPanel;
import org.ungs.view.ShopinatorView;
import org.ungs.view.TitlePanel;
import service.ProductSearcher;

public class ShopinatorController {

    private ShopinatorView shopinatorView;
    private TitlePanel titlePanel;
    private ProductSearcher productSearcher;

    public ShopinatorController(ShopinatorView shopinatorView, ProductSearcher productSearcher) {
        this.shopinatorView = shopinatorView;
        this.productSearcher = productSearcher;

        // Add ActionListener to the input field
        shopinatorView.getProductNameField().addActionListener(new SearchActionListener());
    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = shopinatorView.getProductNameField().getText();
            List<Product> products = productSearcher.searchProducts(productName);

            if (!products.isEmpty()) {
                ProductsPanel productsPanel = new ProductsPanel(products);
                productsPanel.setVisible(true);
            } else {
                NotFoundPanel notFoundPanel = new NotFoundPanel();
                shopinatorView.remove(shopinatorView.getTitlePanel());
                shopinatorView.add(notFoundPanel);
                shopinatorView.revalidate();
            }
        }
    }
}
