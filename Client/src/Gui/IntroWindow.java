package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa okna wyświetlającego animację z nazwiskami twórców
 */

public class IntroWindow extends JFrame implements ActionListener {

    /**
     * Przycisk zamykający okno
     */
    JButton menuButton;

    /**
     * Konstruktor okna z animacją
     */
    public IntroWindow() {


        setSize(640, 360);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        menuButton = new JButton("Close");
        menuButton.setBounds(520, 0, 100, 30);
        add(menuButton);
        menuButton.addActionListener(this);

        Icon icon = new ImageIcon("Client/data/background/Present3.gif");
        JLabel label = new JLabel(icon);
        label.setSize(640, 360);
        this.getContentPane().add(label);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == menuButton) {


            this.dispose();
        }
    }
}
