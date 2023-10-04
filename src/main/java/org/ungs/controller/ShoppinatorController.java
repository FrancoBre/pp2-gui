package org.ungs.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import org.ungs.view.NotFoundPanel;
import org.ungs.view.ProductsPanel;
import org.ungs.view.ShoppinatorView;
import org.ungs.view.SpinnerPanel;
import shoppinator.core.Shoppinator;
import shoppinator.core.model.Product;

public class ShoppinatorController {

    private ShoppinatorView shoppinatorView;
    private Shoppinator shoppinator;

    private List<Product> productList;
    private String[] selectedShops;

    public ShoppinatorController(ShoppinatorView shoppinatorView, Shoppinator shoppinator) {
        this.shoppinatorView = shoppinatorView;
        this.shoppinator = shoppinator;
        this.productList = new ArrayList<>();
        this.shoppinatorView.setProductsPanel(new ProductsPanel(productList));
        this.shoppinatorView.getProductsPanel().setVisible(true);

        SearchActionListener actionListener = new SearchActionListener();
        shoppinatorView.getProductNameField().addActionListener(actionListener);
        shoppinatorView.getMaxPriceField().addActionListener(actionListener);
        shoppinatorView.getMinPriceField().addActionListener(actionListener);

        RefreshMouseAdapter refreshMouseAdapter = new RefreshMouseAdapter();
        shoppinatorView.getRefreshButton().addMouseListener(refreshMouseAdapter);

        CheckboxActionListener checkboxActionListener = new CheckboxActionListener();
        shoppinatorView.getFravegaCheckbox().addActionListener(checkboxActionListener);
        shoppinatorView.getGarbarinoCheckbox().addActionListener(checkboxActionListener);
    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!areSearchParamsValid()) {
                return;
            } else {
                unPaintSearchFieldsRed();
            }

            if (shoppinatorView.getProductsPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getProductsPanel());
            }

            SpinnerPanel spinnerPanel = new SpinnerPanel();
            shoppinatorView.setSpinnerPanel(spinnerPanel);
            shoppinatorView.add(shoppinatorView.getSpinnerPanel());
            shoppinatorView.revalidate();

            shoppinator.search(getSearchParams());
        }
    }

    private class RefreshMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            System.out.println("refrescate! aaah qué rica seven up");
            //shoppinator.refresh(getSearchParams());
        }
    }

    private class CheckboxActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            shoppinatorView.getCheckboxesNotSelected().setVisible(false);

            if(shoppinatorView.getFravegaCheckbox().isSelected() && shoppinatorView.getGarbarinoCheckbox().isSelected()) {
                selectedShops = new String[] {"fravega", "garbarino"};
            } else if(shoppinatorView.getFravegaCheckbox().isSelected()) {
                selectedShops = new String[] {"fravega"};
            } else if(shoppinatorView.getGarbarinoCheckbox().isSelected()) {
                selectedShops = new String[] {"garbarino"};
            } else {
                selectedShops = new String[] {};
            }
        }
    }

    public void updateProductsPanel(List<Product> productList) {
        if (!productList.isEmpty()) {

            if (shoppinatorView.getProductsPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getProductsPanel());
            }

            ProductsPanel productsPanel = new ProductsPanel(productList);
            shoppinatorView.setProductsPanel(productsPanel);

            if (shoppinatorView.getSpinnerPanel() != null) {
                shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
            }

            shoppinatorView.add(productsPanel);
            shoppinatorView.revalidate();
        } else {
            NotFoundPanel notFoundPanel = new NotFoundPanel();
            shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
            shoppinatorView.add(notFoundPanel);
            shoppinatorView.revalidate();
        }
    }

    protected boolean areSearchParamsValid() {
        return isProductSearchParamValid() && arePriceSearchParamsValid() && areCheckboxesSelected();
    }

    private boolean areCheckboxesSelected() {
        if (shoppinatorView.getFravegaCheckbox().isSelected() || shoppinatorView.getGarbarinoCheckbox().isSelected()) {
            return true;
        } else {
            shoppinatorView.getCheckboxesNotSelected().setVisible(true);
            return false;
        }
    }

    private boolean arePriceSearchParamsValid() {
        String minPrice = shoppinatorView.getMinPriceField().getText();
        String maxPrice = shoppinatorView.getMaxPriceField().getText();

        minPrice = minPrice.replace(",", "");
        maxPrice = maxPrice.replace(",", "");

        if (!minPrice.isEmpty() && !maxPrice.isEmpty()) {
            if (Double.parseDouble(minPrice) > Double.parseDouble(maxPrice)) {
                paintPriceFieldsRed();
                return false;
            }
        }

        return true;
    }

    private boolean isProductSearchParamValid() {
        String productName = shoppinatorView.getProductNameField().getText();
        if (productName.isEmpty()) {
            paintProductFieldRed();
            return false;
        }

        return true;
    }

    private void paintPriceFieldsRed() {
        shoppinatorView.getMinPriceField().setBackground(java.awt.Color.RED);
        shoppinatorView.getMaxPriceField().setBackground(java.awt.Color.RED);
    }

    private void paintProductFieldRed() {
        shoppinatorView.getProductNameField().setBackground(java.awt.Color.RED);
    }

    protected void unPaintSearchFieldsRed() {
        if (shoppinatorView.getProductNameField().getBackground().equals(Color.RED)
            || shoppinatorView.getMinPriceField().getBackground().equals(Color.RED)
            || shoppinatorView.getMaxPriceField().getBackground().equals(Color.RED)) {
            shoppinatorView.getProductNameField().setBackground(new Color(33, 150, 243));
            shoppinatorView.getMinPriceField().setBackground(new Color(33, 150, 243));
            shoppinatorView.getMaxPriceField().setBackground(new Color(33, 150, 243));
        }
    }

    protected String[] getSearchParams() {
        return new String[]{
            shoppinatorView.getProductNameField().getText(),
            shoppinatorView.getMinPriceField().getText(),
            shoppinatorView.getMaxPriceField().getText()
        };
    }
}
