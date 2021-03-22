package PGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Klasa Lander
 */

public class Lander {

    /**
     * Deklaracja obiektu PVectot
     */

    public PVector coords;     //coordinates

    /**
     * Deklaracja obiektu PVectot
     */

    public PVector vel;        //velocity

    /**
     * Deklaracja obiektu PVectot
     */

    public PVector acc;        //acceleration

    /**
     * Zmienna przyjmująca szerokość statku
     */

    private double width = FileManager.landerWidth;

    /**
     * Zmienna przyjmująca wysokość statku
     */

    private double height = FileManager.landerHeight;

    /**
     * Zmienna przyjmująca wartość grawitację
     */

    public double gravity = FileManager.gravity;

    /**
     * Zmienna przyjmująca wartość maksymalnej prędkości
     */

    public final double MAX_SPEED = FileManager.MaxSpeed;

    /**
     * Flaga mówiąca nam czy statek leci w górę
     */

    private boolean boostingUp;

    /**
     * Flaga mówiąca nam czy statek leci w prawo
     */

    private boolean boostingRight;

    /**
     * Flaga mówiąca nam czy statek leci w lewo
     */

    private boolean boostingLeft;

    /**
     * Delkaracja ikony
     */

    private ImageIcon landerIcon;

    /**
     * Flaga mówiąca nam czy statek wylądował
     */

    public boolean hasLanded = false;

    /**
     * Flaga mówiąca nam czy statek się rozbił
     */

    public boolean hasCrashed = false;

    /**
     * Zmienna przchowująca ilość zdobytych punktów
     */

    public int points = 0;

    /**
     * Zmienna przchowująca początkową ilość paliwa
     */

    private double fuel = FileManager.fuelAmount;

    /**
     * Deklaracja obiektu Game
     */

    private Game game;

    /**
     * Konstruktor Lander
     *
     * @param game
     * @param x    położanie obiektu w X
     * @param y    położanie obiektu w Y
     */

    public Lander(double x, double y, Game game) {

        coords = new PVector(x, y);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
        landerIcon = new ImageIcon("Client/data/lander/space lander.png");
        landerIcon = new ImageIcon(landerIcon.getImage().getScaledInstance(FileManager.landerWidth, FileManager.landerHeight, Image.SCALE_SMOOTH));
        this.game = game;

    }

    /**
     * Metoda odpowiedzialna za uaktualnianie położenia statku
     */

    public void update() {

        applyForce(new PVector(0, gravity));

        if (boostingUp && fuel > 0) {

            if (vel.y > MAX_SPEED * -1) {
                PVector f = new PVector(0, -FileManager.accUp);
                applyForce(f);
            }

        }

        if (boostingLeft && fuel > 0) {

            if (vel.x > MAX_SPEED * -2) {
                PVector f = new PVector(-FileManager.accLeft, 0);
                applyForce(f);
            }

        }

        if (boostingRight && fuel > 0) {

            if (vel.x < MAX_SPEED * 2) {
                PVector f = new PVector(FileManager.accRight, 0);
                applyForce(f);
            }

        }

        // Ograniczenie prędkości
        vel.add(acc);
        coords.add(vel);

        // aby przyśpieszenie się nie kumulowało
        acc.mult(0);

    }

    /**
     * Metoda odpowiedzialna za zmianę przyśpieszenia
     *
     * @param force
     */

    public void applyForce(PVector force) {

        acc.add(force);

    }

    /**
     * Metoda odpowiedzialna za nałożenie textury statku
     *
     * @param g
     */

    public void render(Graphics g) {

        landerIcon.paintIcon(game, g, (int) coords.x, (int) coords.y);

    }

    /**
     * Metoda odpowiedzialna za lot statku do góry
     *
     * @param flying
     */

    public void flyUp(boolean flying) {

        boostingUp = flying;

    }

    /**
     * Metoda odpowiedzialna za lot statku do góry
     *
     * @param flying
     */

    public void flyLeft(boolean flying) {

        boostingLeft = flying;

    }

    /**
     * Metoda odpowiedzialna za lot statku do góry
     *
     * @param flying
     */

    public void flyRight(boolean flying) {

        boostingRight = flying;

    }

    /**
     * Getter szerokośi statku
     *
     * @return width
     */

    public double getWidth() {

        return width;
    }

    /**
     * Getter wysokość statku
     *
     * @return height
     */

    public double getHeight() {
        return height;

    }

    /**
     * Getter ilości paliwa statku
     *
     * @return fuel
     */

    public double getFuel() {

        return fuel;
    }

    /**
     * Metoda odpowiedzialna za dodanie paliwa
     *
     * @param amount
     * @return
     */

    public void addFuel(double amount) {
        fuel += amount;
        return;
    }

    /**
     * Metoda odpowiedzialna za utratę paliwa
     */

    public void decreaseFuel() {

        fuel -= 1;
    }
}
