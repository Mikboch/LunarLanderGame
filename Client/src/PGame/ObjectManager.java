package PGame;


import java.awt.*;

//Lander manager

/**
 * klasa  GameManager
 */

public class ObjectManager {

    /**
     * delkaracja obiektu lander
     */

    private Lander tmpObject;

    /**
     * delkaracja obiektu fuel
     */

    private Fuel tmpFuel;


    //metoda ktora sluzy wprowadzaniu zmian w parametrach obiektow

    /**
     * metoda odpowiedzialna za wprowadzanie zmian w parametrach obiektow
     */

    public void update() {

        this.tmpObject.update();
        this.tmpFuel.update();

    }

    /**
     * Metoda służąca do wyswietlania zmian wporowadzonych w obiektach
     *
     * @param g
     */

    public void render(Graphics g) {

        this.tmpObject.render(g);
        this.tmpFuel.render(g);

    }

    /**
     * metoda służąca do dodawania obiektów
     *
     * @param fuel
     * @param lander
     */

    public void addObject(Lander lander, Fuel fuel) {

        tmpObject = lander;
        tmpFuel = fuel;

    }

    /**
     * getter TmpObject
     *
     * @return tmpObject
     */

    public Lander getTmpObject() {
        return tmpObject;
    }

    /**
     * getter TmpObject
     *
     * @return tmpFuel
     */

    public Fuel getTmpFuel() {
        return tmpFuel;
    }

}
