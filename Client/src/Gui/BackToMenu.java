package Gui;

import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Klasa, która opisuje okno wyświetlające się po rozbiciu się gracza, bądź przejściu gry.
 * Pozwala na powrót do menu głównego.
 */

public class BackToMenu extends JFrame implements ActionListener {

    /**
     * Obiekt JButton pozwalający na powrót do menu.
     */
    JButton menuButton;

    /**
     * Przechowuje referencje na główne okno programu
     */

    MainWindow mainWindow;

    /**
     * Konstruktor okna pozwalającego na powrót do menu
     *
     * @param width szerokośc okna
     * @param height wysokość okna
     * @param title nazwa okna
     * @param mainWindow przekazanie referencji na główne okno
     * @param background ścieżka do pliku z tłem okna
     */
    public BackToMenu(int width, int height, String title, MainWindow mainWindow, String background) throws MalformedURLException {

        this.mainWindow = mainWindow;


        setSize(width, height);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);


        menuButton = new JButton("Exit");
        menuButton.setBounds(520, 0, 100, 30);
        add(menuButton);
        menuButton.addActionListener(this);

        Icon icon = new ImageIcon(background);
        JLabel label = new JLabel(icon);
        label.setSize(640, 360);
        this.getContentPane().add(label);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == menuButton) {

            mainWindow.gameWindow.setVisible(false);
            mainWindow.setVisible(true);
            mainWindow.gameWindow.dispose();
            mainWindow.gameWindow.exists = false;

            this.dispose();
        }

    }
}
