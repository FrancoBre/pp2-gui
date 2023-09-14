package org.ungs.view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpinnerPanel extends JPanel{

    ShoppinatorView shoppinatorView;

    public SpinnerPanel() {
        setLayout(new BorderLayout());
        // Create a panel to hold the spinner GIF
        JPanel loadingPanel = new JPanel();
        loadingPanel.setLayout(new BorderLayout());
        ImageIcon spinnerIcon = new ImageIcon("src/main/resources/img/spinner.gif"); // Replace with your GIF path
        JLabel spinnerLabel = new JLabel(spinnerIcon);
        loadingPanel.add(spinnerLabel, BorderLayout.CENTER);

        add(loadingPanel, BorderLayout.CENTER); // Initially show the spinner GIF
    }
}
