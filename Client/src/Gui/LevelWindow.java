package Gui;

import PGame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiadająca za wyświetlenie okna po pomyślnym przejściu poziomu.
 */

public class LevelWindow extends JFrame implements ActionListener {

    /**
     * Przycisk przejścia na kolejny poziom.
     */
    private JButton bnextLevel;

    /**
     * Ikona przycisku przechodzenia na kolejny poziom.
     */
    private ImageIcon levelWindowIcon;

    /**
     * Pole przechowujące referencje na obiekt game.
     */
    Game game;

    /**
     * Konstruktor tworzący okno przechodzenia na kolejny poziom.
     *
     * @param width szerokość okna
     * @param height wysokość okna
     * @param title nazwa okna
     * @param game przekazanie referencji na obiekt game
     */
    public LevelWindow(int width, int height, String title,Game game) {

        levelWindowIcon = new ImageIcon("Client/data/icons/next.png");

        this.game = game;

        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setLayout(null);
        setResizable(false);
        setVisible(true);


        bnextLevel = new JButton(levelWindowIcon);
        bnextLevel.setBounds(400, 200, 128, 128);
        add(bnextLevel);
        bnextLevel.setBorder(BorderFactory.createEmptyBorder());
        bnextLevel.addActionListener(this);
        bnextLevel.setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



        JLabel tlo;
        ImageIcon obrazek = new ImageIcon("Client/data/background/success1.png");
        tlo = new JLabel("", obrazek, JLabel.CENTER);
        tlo.setBounds(0, 0, 450,210);
        add(tlo);

        this.getContentPane().setBackground(Color.BLACK);
        game.paused=true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();
        if (src == bnextLevel) {

            game.paused= false;

            this.dispose();

        }
    }
}