package org.ungs.main;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import org.ungs.utils.ShoppinatorUtil;
import org.ungs.view.ShoppinatorView;
import shoppinator.core.Shoppinator;

public class Main {

    private final Shoppinator shoppinator;
    private final ShoppinatorView shoppinatorView;

    public Main() throws FileNotFoundException {
        logAsciiArt();

        this.shoppinator = new Shoppinator();
        shoppinator.init(ShoppinatorUtil.DEFAULT_PATH);
        this.shoppinatorView = new ShoppinatorView(shoppinator);
    }

    private void init() {
        SwingUtilities.invokeLater(this.shoppinatorView::init);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().init();
    }

    public static void logAsciiArt() {
        System.out.println();
        System.out.println("\u001B[31m        ______   __    __   ______   _______   _______  ______  __    __   ______  ________   ______   _______");
        System.out.println("\u001B[33m       /      \\ |  \\  |  \\ /      \\ |       \\ |       \\|      \\|  \\  |  \\ /      \\|        \\ /      \\ |       \\");
        System.out.println("\u001B[32m      |  $$$$$$\\| $$  | $$|  $$$$$$\\| $$$$$$$\\| $$$$$$$\\\\$$$$$$| $$\\ | $$|  $$$$$$\\\\$$$$$$$$|  $$$$$$\\| $$$$$$$\\");
        System.out.println("\u001B[36m      | $$___\\$$| $$__| $$| $$  | $$| $$__/ $$| $$__/ $$ | $$  | $$$\\| $$| $$__| $$  | $$   | $$  | $$| $$__| $$");
        System.out.println("\u001B[34m       \\$$    \\ | $$    $$| $$  | $$| $$    $$| $$    $$ | $$  | $$$$\\ $$| $$    $$  | $$   | $$  | $$| $$    $$");
        System.out.println("\u001B[35m      _\\$$$$$$\\| $$$$$$$$| $$  | $$| $$$$$$$\\| $$$$$$$  | $$  | $$\\$$ $$| $$$$$$$$  | $$   | $$  | $$| $$$$$$$\\");
        System.out.println("\u001B[34m     |  \\__| $$| $$  | $$| $$__/ $$| $$      | $$      _| $$_ | $$ \\$$$$| $$  | $$  | $$   | $$__/ $$| $$  | $$");
        System.out.println("\u001B[36m      \\$$    $$| $$  | $$ \\$$    $$| $$      | $$     |   $$ \\| $$  \\$$$| $$  | $$  | $$    \\$$    $$| $$  | $$");
        System.out.println("\u001B[32m       \\$$$$$$  \\$$   \\$$  \\$$$$$$  \\$$       \\$$      \\$$$$$$ \\$$   \\$$ \\$$   \\$$   \\$$     \\$$$$$$  \\$$   \\$$");
        System.out.println("\u001B[30m");
    }
}