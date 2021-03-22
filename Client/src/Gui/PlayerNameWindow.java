package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiedzialna za wyświetlanie okna, w którym wprowadzamy nazwę gracza.
 */


public class PlayerNameWindow extends JFrame implements ActionListener {

    /**
     * Pole do wprowadzenia imienia gracza
     */
    public JTextField playerNameField;

    /**
     *  Etykieta do wyświetlenia polecenia dla grąjącego
     */
    private JLabel playerNameLabel;

    /**
     * Zmienna przechowująca imie gracza
     */
    public static String playerName;

    /**
     *  Przycisk do potwierdzenia imienia
     */
    public JButton bAcceptName;


    /**
     * Konstruktor klasy PlayerNameWindow
     *
     * @param width  szerokość okna
     * @param height wysokość okna
     * @param title  tytuł okna
     */

    public PlayerNameWindow(int width, int height, String title) {

        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setLayout(null);
        setResizable(false);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        playerNameLabel = new JLabel();
        playerNameLabel.setBounds(60, 20, 180, 20);
        playerNameLabel.setText("Write your name");
        playerNameLabel.setForeground(Color.WHITE);
        add(playerNameLabel);

        playerNameField = new JTextField();
        playerNameField.setBounds(60, 60, 150, 20);
        add(playerNameField);

        bAcceptName = new JButton("Accept");
        bAcceptName.setBounds(60, 90, 150, 20);
        add(bAcceptName);
        bAcceptName.addActionListener(this);


        JLabel tlo;
        ImageIcon obrazek = new ImageIcon("Client/data/background/nazwa gracza.jpg");
        tlo = new JLabel("", obrazek, JLabel.CENTER);
        tlo.setBounds(0, 0, 840, 840);
        add(tlo);

    }


    /**
     * Metoda odpowiedzialna za obsługę zdarzeń- naciśnięć myszy
     *
     * @param e zdarzenie nacisnięcia myszy
     */

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    /**
     * Wczytuje imie gracza
     *
     * @return imie gracza
     */

    public String getPlayerName() {

        return playerName;
    }

    /**
     * Ustawia imie gracza
     *
     * @param n imie gracza
     */
    public void setPlayerName(String n) {

        playerName = n;
    }

}
