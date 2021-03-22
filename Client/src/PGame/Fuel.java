package PGame;

import javax.swing.*;
import java.awt.*;

/**
 * Konstruktor klasy Fuel odpowiadającej za zarządzanie paliwem
 */

public class Fuel {

    /**
     *  wektor wpółrzędnych (x,y) paliwa
     */

    public PVector coords;     //coordinates

    /**
     * wektor prędkości
     */

    public PVector vel = new PVector(FileManager.fuelVel, 0);        //velocity

    /**
     *  szerokosc paliwa
     */

    private int width = FileManager.fuelWidth;

    /**
     *  wysokosc paliwa
     */

    private int height = FileManager.fuelHeight;

    /**
     *  zmienna okreslajca czy paliwo zostało podniesione
     */

    public boolean collected = false;

    /**
     *  ikona paliwa
     */

    private ImageIcon fuelIcon;

    /**
     *  okresla czy paliwo istnieje
     */

    public boolean exists;

    /**
     *  deklaracja obiektu pomocniczego game
     */

    private Game game;


    /**
     * Konstruktor klasy fuel
     *
     * @param x    współrzędna położenia x
     * @param y    współrzędna położenia y
     * @param game referncja do obiektu gra
     */
    public Fuel(double x, double y, Game game) {
        fuelIcon = new ImageIcon("Client/data/fuel/fuel canister.png");
        fuelIcon = new ImageIcon(fuelIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        coords = new PVector(x, y);
        this.game = game;
        exists = true;
    }

    /**
     * Metoda aktulizująca połozenie kanistra
     */

    public void update() {

        if (coords.x < FileManager.gameWindowWidth)
            coords.x += vel.x;
        else {
            coords.x = 0 - width;
            double yR = Math.random() * (200);
            coords.y = yR;
        }
    }


    /**
     * Metoda wyswietlajaca obiekt
     */

    public void render(Graphics g) {

        if (exists) {

            fuelIcon.paintIcon(game, g, (int) coords.x, (int) coords.y);
        }

    }

    /**
     * Metoda zwracająca szerokość obiektu
     */

    public double getWidth() {
        return width;
    }

    /**
     * Metoda zwracająca wysokość obiektu
     */

    public double getHeight() {
        return height;
    }


}




