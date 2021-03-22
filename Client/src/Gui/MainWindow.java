package Gui;
//package PGame;

import PGame.FileManager;
import PGame.Game;
import PGame.SoundManager;
import PServer.Client;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Klasa odpowiedzialna za wyświetlenie głównego okna tytułowego
 */

public class MainWindow extends JFrame implements ActionListener {

    /**
     * Deklaracja okna z nazwą gracza
     */
    PlayerNameWindow playerNameWindow = null;

    /**
     * Utworzenie okna z zasadami gry
     */
    Help help = new Help(FileManager.helpWindowWidth, FileManager.helpWindowHeight, "Help");

    /**
     * Stworzenie odtwarzacza muzyki
     */
    public SoundManager soundManager= new SoundManager();

    /**
     * Flaga mówiąca, czy muzyka została zatrzymana
     */
    public boolean musicPaused=false;

    /**
     * Utworzenie okna z tabelą wyników
     */
    ScoreboardWindow scoreboardWindow;

    /**
     * Przycisk do włączenia nowej gry
     */
    private JButton bNewGame;

    /**
     * Przycisk wyjścia
     */
    private JButton bExit;

    /**
     * Przycisk okna pomocy
     */
    private JButton bHelp;

    /**
     * Przycisk do otworzenia okna z najlepszymi wynikami
     */
    private JButton bBestScore;

    /**
     * Ikona przycisku wyjścia z gry
     */
    private ImageIcon exitIcon;

    /**
     * Ikona przycisku do rozpoczęcia nowej gry
     */
    private ImageIcon newGameIcon;

    /**
     * Ikona przycisku do rozpoczęcia okna pomocy
     */
    private ImageIcon helpIcon;

    /**
     * Ikona przycisku do otwarcia okna z najlepszymi wynikami
     */
    private ImageIcon bestScoreIcon;

    /**
     *  Zmienna odpowiedzialna za działanie guzików w JOption - potwierdzenie zamknięcia okna.
     */
    private int act;

    /**
     *  Obiekt klasy GameWindow - okno z grą
     */
     public GameWindow gameWindow;

    /**
     * Pole przechowujące nazwe gracza.
     */
    public String playerName;



    /**
     * Konstruktor klasy MainWindow
     */

    public MainWindow() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        //this.modeWindow=modeWindow;

        exitIcon = new ImageIcon("Client/data/icons/exit.png");
        newGameIcon = new ImageIcon("Client/data/icons/New game.png");
        helpIcon = new ImageIcon("Client/data/icons/help.png");
        bestScoreIcon = new ImageIcon("Client/data/icons/the best.png");


        setSize(FileManager.mainWindowWidth, FileManager.mainWindowHeight);
        setLocationRelativeTo(null);
        setTitle("Lunar Lander");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        bNewGame = new JButton(newGameIcon);
        bNewGame.setBounds(500, 10, 128, 128);
        add(bNewGame);
        bNewGame.setBorder(BorderFactory.createEmptyBorder());
        bNewGame.addActionListener(this);
        bNewGame.setBackground(Color.BLACK);

        bHelp = new JButton(helpIcon);
        bHelp.setBounds(628, 10, 128, 128);
        add(bHelp);
        bHelp.setBorder(BorderFactory.createEmptyBorder());
        bHelp.addActionListener(this);
        bHelp.setBackground(Color.BLACK);

        bBestScore = new JButton(bestScoreIcon);
        bBestScore.setBounds(500, 138, 128, 128);
        add(bBestScore);
        bBestScore.setBorder(BorderFactory.createEmptyBorder());
        bBestScore.addActionListener(this);
        bBestScore.setBackground(Color.BLACK);

        bExit = new JButton(exitIcon);
        bExit.setBounds(628, 138, 128, 128);
        add(bExit);
        bExit.setBorder(BorderFactory.createEmptyBorder());
        bExit.addActionListener(this);
        bExit.setBackground(Color.BLACK);

        JLabel tlo;
        ImageIcon obrazek = new ImageIcon("Client/data/background/Space.jpg");
        tlo = new JLabel("", obrazek, JLabel.CENTER);
        tlo.setBounds(0, 0, 800, 600);
        add(tlo);

        soundManager.playMusic();

    }


    /**
     * Metoda odpowiedzialna za sterowanie przyciaskami okna głównego
     *
     * @param e zdarzenie, jakim jest przyciśnięcie myszki
     */

    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();
        if (src == bNewGame ) {

            playerNameWindow = new PlayerNameWindow(FileManager.playerNameWindowWidth, FileManager.playerNameWindowHeight, "Lunar Lander");
            playerNameWindow.bAcceptName.addActionListener(this);

            playerNameWindow.setPlayerName("");
            playerNameWindow.setVisible(true);



        } else if (playerNameWindow != null && src == playerNameWindow.bAcceptName) {
            //zapis zmiennej
            playerNameWindow.setPlayerName(playerNameWindow.playerNameField.getText());
            playerName = playerNameWindow.getPlayerName();
            playerNameWindow.dispose();

            if (playerNameWindow.getPlayerName() != null && !playerNameWindow.getPlayerName().equals("")) {
                gameWindow = new GameWindow(FileManager.gameWindowWidth, FileManager.gameWindowHeight, "Lunar Lander", new Game(this));
                this.dispose();

            }

            playerNameWindow = null;
        } else if (src == bExit) {

            act = JOptionPane.showConfirmDialog(null, "Do you want to leave", "Exit", JOptionPane.YES_NO_OPTION);
            if (act == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "See you soon");

                soundManager.stop(0);

                setDefaultCloseOperation(EXIT_ON_CLOSE);
                System.exit(0);


            }
        } else if (src == bHelp) {

            help.setVisible(true);

        } else if (src == bBestScore) {
            try {
                scoreboardWindow=new ScoreboardWindow(FileManager.scoreboardWindowWidth, FileManager.scoreboardWindowHeight, "Scoreboard");
                scoreboardWindow.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            }
        }

    }


