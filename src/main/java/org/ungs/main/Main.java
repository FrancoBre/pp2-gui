package org.ungs.main;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import org.ungs.view.ShoppinatorView;
import shoppinator.core.Shoppinator;

public class Main {

    private final Shoppinator shoppinator;
    private final ShoppinatorView shoppinatorView;

    public Main() throws FileNotFoundException {
        this.shoppinator = new Shoppinator("plugins/");
        this.shoppinatorView = new ShoppinatorView(shoppinator);
    }

    private void init() {
        SwingUtilities.invokeLater(this.shoppinatorView::init);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().init();
    }
}