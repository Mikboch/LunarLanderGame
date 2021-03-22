package PGame;


import javax.swing.*;
import java.awt.*;

/**
 * Klasa LandingPlatform
 */

public class LandingPlatform {

    /**
     * Położenie platformy w X
     */

    private double x;

    /**
     * położenie platformy w Y
     */

    private double y;

    /**
     * Szerokość platformy
     */

    private int width;

    /**
     * Wysokość platformy
     */

    private int height;

    /**
     * Deklaracja ikony dla platformy
     */

    private ImageIcon landingPlatformIcon;

    /**
     * Deklaracja obiektu Game
     */

    private Game game;

    /**
     * Zmienna przechowująca mnośnik
     */

    public int multiplier;

    /**
     * Konstruktor LandingPlatform
     *
     * @param y
     * @param x
     * @param height
     * @param multiplier
     * @param pathIcon
     * @param width
     */

    public LandingPlatform(double x, double y, int width, int height, int multiplier, String pathIcon) {

        landingPlatformIcon = new ImageIcon(pathIcon);
        landingPlatformIcon = new ImageIcon(landingPlatformIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.multiplier = multiplier;
    }

    /**
     * Metoda odpowiedzialna za renderowanie platformy
     *
     * @param g
     */

    public void render(Graphics2D g) {

        landingPlatformIcon.paintIcon(game, g, (int) x, (int) y);

    }
}
