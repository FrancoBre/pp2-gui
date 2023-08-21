package org.ungs;

import javax.swing.SwingUtilities;
import org.ungs.view.TitleView;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TitleView titleView = new TitleView();
            titleView.setVisible(true);
        });
    }
}