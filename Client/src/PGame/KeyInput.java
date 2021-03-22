package PGame;

import java.awt.event.*;

/**
 * klasa KeyInput
 */

public class KeyInput implements KeyListener {

    /**
     * deklaracja obiektu GameManager
     */

    ObjectManager manager;

    /**
     * deklaracja obiektu Game
     */

    Game game;

    /**
     * metoda odpowiedzialna za dodawanie sterowania
     *
     * @param game
     * @param manager
     */

    public KeyInput(ObjectManager manager, Game game) {

        this.manager = manager;
        this.game = game;

    }

    /**
     * metoda odpowiedzialna za reakcję na wciśnięcie klawisza
     *
     * @param e
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * metoda odpowiedzialna za reakcję na wciśnięcie klawisza
     *
     * @param e
     */

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                manager.getTmpObject().flyUp(true);
                if (!game.paused)
                    manager.getTmpObject().decreaseFuel();

                break;

            case KeyEvent.VK_LEFT:
                manager.getTmpObject().flyLeft(true);
                if (!game.paused)
                    manager.getTmpObject().decreaseFuel();

                break;

            case KeyEvent.VK_RIGHT:
                manager.getTmpObject().flyRight(true);
                if (!game.paused)
                    manager.getTmpObject().decreaseFuel();

                break;

            case KeyEvent.VK_ESCAPE:

                try {
                    game.mainWindow.gameWindow.setVisible(false);
                    game.mainWindow.setVisible(true);
                    game.mainWindow.gameWindow.dispose();
                    game.mainWindow.gameWindow.exists = false;
                }
                catch (IllegalStateException i){
                    System.out.println("");
                }

                break;

            case KeyEvent.VK_P:

                if (!game.paused) {
                    game.paused = true;
                } else
                    game.paused = false;

                break;
            case KeyEvent.VK_M:

                if (!game.mainWindow.musicPaused) {
                    game.mainWindow.soundManager.pause(0);
                    game.mainWindow.musicPaused=true;

                } else {
                    game.mainWindow.soundManager.play(0);
                    game.mainWindow.musicPaused = false;

                }

                break;
        }

    }



    /**
     * Metoda odpowiedzialna za reakcję na puszczenie klawisza
     *
     * @param e
     */

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                manager.getTmpObject().flyUp(false);

                break;

            case KeyEvent.VK_LEFT:
                manager.getTmpObject().flyLeft(false);

                break;

            case KeyEvent.VK_RIGHT:
                manager.getTmpObject().flyRight(false);

                break;

            case KeyEvent.VK_ESCAPE:
                break;
        }
    }
}