package org.ungs.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class CarouselPanel extends JPanel implements ActionListener {

    ImageIcon[] imageIcons;
    JLabel label;
    JButton prevButton;
    JButton nextButton;
    int i;
    ShoppinatorView shoppinatorView;

    public CarouselPanel(ShoppinatorView shoppinatorView) {
        setLayout(new BorderLayout());
        setSize(800, 700);
        setVisible(true);
        JPanel panel = new JPanel(new FlowLayout());
        prevButton = new JButton("<<");
        nextButton = new JButton(">>");
        panel.add(prevButton);
        panel.add(nextButton);
        add(panel, BorderLayout.SOUTH);
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        imageIcons = new ImageIcon[3];
        imageIcons[0] = new ImageIcon("src/main/resources/img/carousel/tv.png");
        imageIcons[1] = new ImageIcon("src/main/resources/img/carousel/laptop.png");
        imageIcons[2] = new ImageIcon("src/main/resources/img/carousel/microwave.jpeg");
        label = new JLabel("", SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor
        add(label, BorderLayout.CENTER);
        label.setIcon(imageIcons[0]);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (i >= 0 && i < imageIcons.length) {
                    switch (i) {
                        case 0:
                            shoppinatorView.getProductNameField().setText("TV Samsung 50 pulgadas 4K");
                            break;
                        case 1:
                            shoppinatorView.getProductNameField().setText("Macbook Air M2 2020");
                            break;
                        case 2:
                            shoppinatorView.getProductNameField().setText("Microondas Philips 30L");
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBorder(null); // Remove border on exit
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prevButton) {
            if (i == 0) {
                i = imageIcons.length - 1;
                label.setIcon(imageIcons[i]);
            } else {
                i = i - 1;
                label.setIcon(imageIcons[i]);
            }
        }
        if (e.getSource() == nextButton) {
            if (i == imageIcons.length - 1) {
                i = 0;
                label.setIcon(imageIcons[i]);
            } else {
                i = i + 1;
                label.setIcon(imageIcons[i]);
            }
        }

    }
}
