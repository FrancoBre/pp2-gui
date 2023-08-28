package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel {

    public TitlePanel() {
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());

        // Instructions list
        String instructions = "<html><ul>"
            + "<li>Ingrese un producto en la barra de búsqueda inferior</li>"
            + "<li>Espere unos segundos</li>"
            + "<li>Elija el producto que más le convenga</li>"
            + "<li>Listo! Hizo uso de su dinero inteligentemente</li>"
            + "</ul></html>";
        JLabel instructionsLabel = new JLabel(instructions);
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the instructions label to the frame
        add(instructionsLabel, BorderLayout.CENTER, -1); // Instructions at the top
    }
}