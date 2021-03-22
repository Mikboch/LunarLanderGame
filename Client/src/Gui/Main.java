package Gui;

import PGame.FileManager;

import javax.swing.*;
import java.io.*;

/**
 * Klasa Main, z której zaczyna się kompilacja całego programu
 */

public class Main extends JFrame {

    /**
     * Konstruktor klasy Main
     */

    public static void main(String[] args) throws IOException {

        FileManager.loadConfig();
        FileManager.loadLander();
        ModeWindow modeWindow= new ModeWindow();
        modeWindow.setVisible(true);

    }
}

