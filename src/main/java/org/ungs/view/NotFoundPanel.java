package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NotFoundPanel extends JPanel {

    public NotFoundPanel() {
        setPreferredSize(new Dimension(400, 300));

        JLabel notFoundLabel = new JLabel(new ImageIcon("src/main/resources/img/not-found.png"));
        notFoundLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        notFoundLabel.setPreferredSize(new Dimension(200, 209)); // Set a smaller size
        add(notFoundLabel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("No se encontraron productos para tu búsqueda");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
    }
}
