package Gui;

import PGame.FileManager;
import PServer.Client;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wyświetlanie najlepszych wyników graczy
 */

public class ScoreboardWindow extends JFrame {

    /**
     * Pole służące do wyświetlenia wyników
     */
    private JTextArea jt;


    /**
     * Konstruktor klasy ScoreBoardWindow
     *
     * @param width  szerokość okna
     * @param height wysokość okna
     * @param title  tytuł okna
     */

    public ScoreboardWindow(int width, int height, String title) throws IOException {

        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setLayout(null);


        Color background = new Color(255, 255, 255);
        setBackground(background);

        try {
            FileReader fileReader;
            if (Client.isOnline) {
                FileManager.loadScoreFromServer();
                fileReader = new FileReader("Client/data/config/best_online_scores.txt");

            } else {
                fileReader = new FileReader("Client/data/config/best_scores.txt");
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            jt = new JTextArea("");
            jt.setSize(500, 500);
            jt.setLineWrap(true);
            jt.setWrapStyleWord(true);
            jt.setEditable(false);

            Font font = new Font("Arial", Font.PLAIN, 20);
//            System.out.println(jt.getFont().getName());
            jt.setFont(font);


//               textLine = bufferedReader.readLine();
            jt.read(bufferedReader, "OK");


            fileReader.close();

        } catch (FileNotFoundException el) {
            System.out.println("Błąd, nie ma takiego pliku");
        } catch (IOException el) {
            System.out.println("Błąd, odczytu");
        }


        add(jt);


    }

}
