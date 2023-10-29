package org.ungs.controller;

import entities.Product;
import entities.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.ungs.view.NotFoundPanel;
import org.ungs.view.ProductsPanel;
import org.ungs.view.ShoppinatorView;
import org.ungs.view.SpinnerPanel;
import shoppinator.core.Shoppinator;

public class ShoppinatorController {

    private ShoppinatorView shoppinatorView;
    private Shoppinator shoppinator;

    private List<Product> productList;

    public ShoppinatorController(ShoppinatorView shoppinatorView, Shoppinator shoppinator) {
        this.shoppinatorView = shoppinatorView;
        this.shoppinator = shoppinator;
        this.productList = new ArrayList<>();

        // Add ActionListener to the input field
        shoppinatorView.getProductNameField().addActionListener(new SearchActionListener());
    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = shoppinatorView.getProductNameField().getText();

            if (shoppinatorView.getProductsPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getProductsPanel());
            }

            SpinnerPanel spinnerPanel = new SpinnerPanel();
            shoppinatorView.setSpinnerPanel(spinnerPanel);
            shoppinatorView.add(shoppinatorView.getSpinnerPanel());
            shoppinatorView.revalidate();

            try {
                shoppinator.search(productName);
            } catch (Exception ex) {
                NotFoundPanel notFoundPanel = new NotFoundPanel(ex.getMessage());
                shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
                shoppinatorView.add(notFoundPanel);
                shoppinatorView.revalidate();
            }
        }
    }

    public void updateProductsPanel(List<Result> productList) {
        if (!productList.isEmpty()) {

            if(shoppinatorView.getProductsPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getProductsPanel());
            }

            ProductsPanel productsPanel = new ProductsPanel(productList);
            shoppinatorView.setProductsPanel(productsPanel);

            if(shoppinatorView.getSpinnerPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
            }
            shoppinatorView.add(productsPanel);
            shoppinatorView.revalidate();
        } else {
            NotFoundPanel notFoundPanel = new NotFoundPanel("No se encontraron productos para tu búsqueda");
            shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
            shoppinatorView.add(notFoundPanel);
            shoppinatorView.revalidate();
        }
    }

}
