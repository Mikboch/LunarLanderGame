package PGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.Scanner;

/**
 * Klasa Terrain
 */

public class Terrain extends JPanel {

    /**
     * Textura terenu
     */

    ImageIcon surfaceTexture = new ImageIcon("Client/data/Textures/moon-surface.png");

    /**
     * Metoda odpowiedzialna za rysowanie terenu
     * @param g
     */

    public void paint(Graphics2D g) {

        g.setColor(Color.GRAY);
        g.fillPolygon(FileManager.terrainX,FileManager.terrainY,FileManager.terrainY.length);
        g.setColor(Color.WHITE);
        g.drawPolygon(FileManager.terrainX,FileManager.terrainY,FileManager.terrainY.length);

    }
}