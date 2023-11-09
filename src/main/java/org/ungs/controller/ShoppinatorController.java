package org.ungs.controller;

import entities.Result;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JCheckBox;
import lombok.extern.slf4j.Slf4j;
import org.ungs.view.NotFoundPanel;
import org.ungs.view.ProductsPanel;
import org.ungs.view.ShoppinatorView;
import org.ungs.view.SpinnerPanel;
import shoppinator.core.Shoppinator;

@Slf4j
public class ShoppinatorController {

    private ShoppinatorView shoppinatorView;
    private Shoppinator shoppinator;

    private List<Result> productList;
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
        for (JCheckBox checkbox : shoppinatorView.getCheckBoxes()) {
            checkbox.addActionListener(checkboxActionListener);
        }
        //shoppinatorView.getCheckboxesPanel().addActionListener(checkboxActionListener);
        //shoppinatorView.getGarbarinoCheckbox().addActionListener(checkboxActionListener);
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
            shoppinatorView.getProductList().clear();

            try {
                shoppinator.search(getSearchParams());
            } catch (Exception ex) {
                NotFoundPanel notFoundPanel = new NotFoundPanel("Ocurrió un error durante la búsqueda");
                shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
                shoppinatorView.add(notFoundPanel);
                shoppinatorView.revalidate();
                log.error(ex.getMessage());
            }
        }
    }

    private class RefreshMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            writeFileToDisk();
        }
    }

    private void writeFileToDisk() {
        String fileName = "plugins/easter-egg.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            log.error("Error creating a file to trigger easter egg " + e.getMessage());
        }
    }

    private class CheckboxActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedShops = getSelectedShops();
            shoppinator.setShops(new HashSet<>(List.of(selectedShops)));
        }
    }

    public String[] getSelectedShops() {
        List<String> shops = new ArrayList<>();
        for (JCheckBox checkbox : shoppinatorView.getCheckBoxes()) {
            if (checkbox.isSelected()) {
                shops.add(checkbox.getText());
            }
        }
        return shops.toArray(new String[0]);
    }

    public void updateProductsPanel(List<Result> productList) {
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
            NotFoundPanel notFoundPanel = new NotFoundPanel("No se encontraron productos para tu búsqueda");
            shoppinatorView.remove(shoppinatorView.getSpinnerPanel());
            shoppinatorView.add(notFoundPanel);
            shoppinatorView.revalidate();
        }
    }

    protected boolean areSearchParamsValid() {
        return isProductSearchParamValid() && arePriceSearchParamsValid();
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

    protected String getSearchParams() {
        StringBuilder params = new StringBuilder();

        params.append(shoppinatorView.getProductNameField().getText());

        if (!shoppinatorView.getMinPriceField().getText().isEmpty()) {
            params.append(" -").append(shoppinatorView.getMinPriceField().getText().replace(",", ""));
        }
        if (!shoppinatorView.getMaxPriceField().getText().isEmpty()) {
            params.append(" +").append(shoppinatorView.getMaxPriceField().getText().replace(",", ""));
        }

        if (selectedShops != null) {
            for (String shopName : selectedShops) {
                params.append(" #").append(shopName);
            }
        }

        return params.toString();
    }

}
