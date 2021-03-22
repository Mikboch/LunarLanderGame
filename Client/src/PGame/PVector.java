package PGame;

/**
 * Klasa PVector
 */

public class PVector {

    /**
     * Pole x
     */

    public double x;

    /**
     * Pole y
     */

    public double y;

    /**
     * Konstruktor PVector
     *
     * @param x
     * @param y
     */

    public PVector(double x, double y) {

        this.x = x;
        this.y = y;

    }

    /**
     * Metoda odpowiedzialna za dodawanie wektorów
     *
     * @param v dodatkowy wekto
     */

    public PVector add(PVector v) {

        x += v.x;
        y += v.y;

        return this.copy();

    }

    /**
     * Metoda odpowiedzialna za mnożenie wektorów
     *
     * @param k dodatkowy wekto
     */

    public PVector mult(double k) {

        x *= k;
        y *= k;

        return this.copy();

    }

    /**
     * Metoda odpowiedzialna za kopiowanie wektora
     */

    public PVector copy() {

        return new PVector(x, y);
    }
}
