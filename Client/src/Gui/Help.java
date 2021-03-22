package Gui;

import PGame.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Klasa odpowiedzialna za wyświetleni okna z informacjami pomocniczymi
 */

public class Help extends JFrame {

    /**
     * Pole tekstowe
     */

    private JTextArea jt;

    /**
     * Linijka pomocnicza do wczytywania tekstu
     */

    private String line;

    /**
     * Konstruktor klasy Help
     * @param width szerokość okna
     * @param height wysokość okna
     * @param title tytuł okna
     */

    public Help(int width, int height, String title) {

        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setLayout(null);
        setResizable(false);

        Color background = new Color(255, 255, 255);
        setBackground(background);

        try {


            FileReader fileReader = new FileReader("Client/data/help/help.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            jt = new JTextArea("");
            jt.setSize(730, 600);
            jt.setLineWrap(true);
            jt.setWrapStyleWord(true);
            jt.setEditable(false);

            Font font = new Font("Arial", Font.PLAIN, 20);
//            System.out.println(jt.getFont().getName());
            jt.setFont(font);


                jt.read(bufferedReader,"Ok");



            fileReader.close();

        } catch (FileNotFoundException el) {
            System.out.println("Błąd, nie ma takiego pliku");
        } catch (IOException el) {
            System.out.println("Błąd, odczytu");
        }


        add(jt);
    }
}
