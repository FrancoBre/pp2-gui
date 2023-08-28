package org.ungs.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import models.Product;
import org.ungs.utils.ShopinatorUtil;
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
            //List<Product> products = productSearcher.searchProducts(productName);

            List<Product> products;
            if(productName.equals("vacio")) {
                products = Collections.emptyList();
            } else {
                products = ShopinatorUtil.createMockProductList();
            }

            if (!products.isEmpty()) {
                shopinatorView.remove(shopinatorView.getTitlePanel());

                if (shopinatorView.getProductsPanel() != null) {
                    shopinatorView.remove(shopinatorView.getProductsPanel());
                }

                ProductsPanel productsPanel = new ProductsPanel(products);
                shopinatorView.setProductsPanel(productsPanel);
                shopinatorView.add(productsPanel);
                shopinatorView.revalidate();
            } else {
                NotFoundPanel notFoundPanel = new NotFoundPanel();
                shopinatorView.remove(shopinatorView.getTitlePanel());
                shopinatorView.add(notFoundPanel);
                shopinatorView.revalidate();
            }
        }
    }
}
