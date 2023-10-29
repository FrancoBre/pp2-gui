package org.ungs.main;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import org.ungs.view.ShoppinatorView;
import service.factory.ShoppinatorFactory;
import shoppinator.core.Shoppinator;

public class Main {

    private final Shoppinator shoppinator;
    private final ShoppinatorView shoppinatorView;

    public Main() throws FileNotFoundException {
        ShoppinatorFactory shoppinatorFactory = new ShoppinatorFactory();
        this.shoppinator = shoppinatorFactory.create("plugins/");
        this.shoppinatorView = new ShoppinatorView(shoppinator);
    }

    private void init() {
        SwingUtilities.invokeLater(this.shoppinatorView::init);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().init();
    }
}