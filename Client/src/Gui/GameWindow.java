package Gui;

import PGame.Game;
import PGame.KeyInput;
import PGame.Terrain;


import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpwiedzialna za wyśwuetlanie okna z grą
 */

public class GameWindow extends JFrame {

    /**
     * Flaga mówiąca, czy okno zostało utworzone.
     */

    public boolean exists=false;

    /**
     * Konstruktor Klasy GameWindow
     *
     * @param w     szerokość okna
     * @param h     wysokość okna
     * @param title tytuł okna
     * @param game  referencja do obiektu Game
     */


    public GameWindow(int w, int h, String title, Game game) {



        super (title);
        game.setSize(w, h);

        exists=true;

        add(game);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        game.start();


    }

}
