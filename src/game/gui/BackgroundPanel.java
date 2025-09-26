package game.gui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // Încarcă imaginea
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenează imaginea pe întreaga dimensiune a panoului
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}