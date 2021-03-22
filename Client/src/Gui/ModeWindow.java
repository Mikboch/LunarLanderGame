package Gui;

import PGame.FileManager;
import PServer.Client;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wybranie trybu gry- offline/online oraz podanie parametrów servera
 */


public class ModeWindow extends JFrame implements ActionListener {

    /**
     * Przycisk do gry bez servera
     */
    private JButton bOffline;

    /**
     * Przycisk do gry z połączeniem z serverem
     */
    private JButton bOnline;

    /**
     * Przycisk wyjścia
     */
    private JButton bExit;

    /**
     * Pole tekstowe przyjmujące nr portu
     */
    JTextField port = new JTextField(Integer.toString(FileManager.port));

    /**
     * Pole tekstowe przyjmujące nr IP
     */
    JTextField ipAdress = new JTextField(FileManager.ipAddress);

    /**
     * Ikona przycisku wyjścia z programu
     */
    private ImageIcon exitIcon;

    /**
     * Ikona przycisku do gry przez serwer
     */
    private ImageIcon onlineIcon;

    /**
     * Ikona przycisku do gry bez serwera
     */
    private ImageIcon offlineIcon;

    /**
     * Konstruktor okna z wyborem trybu gry
     */

    public ModeWindow() {

        exitIcon = new ImageIcon("Client/data/icons/exit.png");
        onlineIcon = new ImageIcon("Client/data/icons/online.png");
        offlineIcon = new ImageIcon("Client/data/icons/offline.png");

        setSize(FileManager.modeWindowWidth, FileManager.modeWindowHeight);
        setLocationRelativeTo(null);
        setTitle("Mode");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        bOffline = new JButton(offlineIcon);
        bOffline.setBounds(35, 60, 128, 128);
        add(bOffline);
        bOffline.addActionListener(this);
        bOffline.setBorder(BorderFactory.createEmptyBorder());
        bOffline.setBackground(Color.BLACK);


        bOnline = new JButton(onlineIcon);
        bOnline.setBounds(180, 60, 128, 128);
        add(bOnline);
        bOnline.addActionListener(this);
        bOnline.setBorder(BorderFactory.createEmptyBorder());
        bOnline.setBackground(Color.BLACK);

        bExit = new JButton(exitIcon);
        bExit.setBounds(325, 60, 128, 128);
        add(bExit);
        bExit.addActionListener(this);
        bExit.setBorder(BorderFactory.createEmptyBorder());
        bExit.setBackground(Color.BLACK);



        port.setBounds(140, 400, 200, 20);
        add(port);

        JLabel portText = new JLabel();
        portText.setBounds(140, 380, 200, 20);
        portText.setText("Port:");
        portText.setForeground(Color.WHITE);
        add(portText);


        ipAdress.setBounds(140, 470, 200, 20);
        add(ipAdress);

        JLabel ipText = new JLabel();
        ipText.setBounds(140, 450, 200, 20);
        ipText.setText("Adres IP:");
        ipText.setForeground(Color.WHITE);
        add(ipText);

//        connectionLabel.setBounds(400, 400, 200, 20);
//        connectionLabel.setForeground(Color.WHITE);
//        add(connectionLabel);


        JLabel background;
        ImageIcon picture = new ImageIcon("Client/data/background/modeWindow.jpg");
        background = new JLabel("", picture, JLabel.CENTER);
        background.setBounds(0, 0, 800, 600);
        add(background);
       // getContentPane().setBackground(Color.BLACK);

    }

    /**
     * Metoda odpowiedzialna za obsługę zdarzeń- naciśnięć myszy
     *
     * @param e zdarzenie nacisnięcia myszy
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == bExit) {

            setBackground(Color.BLACK);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            dispose();

        } else if (src == bOffline) {

            setVisible(false);

            MainWindow window = null;
            IntroWindow intro = null;
            try {
                window = new MainWindow();
                window.setVisible(true);
                intro = new IntroWindow();
                intro.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }


        } else if (src == bOnline) {
            MainWindow window = null;
            IntroWindow intro = null;
            try {

                FileManager.savePortAndIP(Integer.parseInt(port.getText()), ipAdress.getText());
                FileManager.loadWindowConfigFromServer();
                FileManager.loadLanderParametersFromServer();
                System.out.println(port.getText());
                System.out.println(ipAdress.getText());

            } catch (IOException f) {
                System.out.println("Connection could not be established");
                revalidate();

            }
            if (Client.checkIfOnline()) {
                setVisible(false);

                try {
                    window = new MainWindow();
                    System.out.println("Connection established");
                    window.setVisible(true);
                    intro = new IntroWindow();
                    intro.setVisible(true);


                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }

            }

        }
    }
}
