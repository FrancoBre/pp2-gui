package org.ungs.main;

import javax.swing.SwingUtilities;
import org.ungs.view.ShopinatorView;
import service.ProductSearcher;

public class Main {

    private final ProductSearcher productSearcher;
    private final ShopinatorView shopinatorView;

    public Main() {
        this.productSearcher = new ProductSearcher(null);
        this.shopinatorView = new ShopinatorView(productSearcher);
    }

    private void init() {
        SwingUtilities.invokeLater(() -> this.shopinatorView.init());
    }

    public static void main(String[] args) {
        new Main().init();
    }
}