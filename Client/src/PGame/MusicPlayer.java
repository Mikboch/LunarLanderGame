package PGame;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Klasa MusicPlayer
 */
public class MusicPlayer implements Runnable {

    /**
     * lista przechowująca muzykę
     */

    private ArrayList<String> musicFiles;

    /**
     * index aktualnej muzyki
     */

    private int currentSongIndex;

    /**
     * deklaracja obiektu Clip
     */

    private Clip clip;

    /**
     * konstruktor MusicPlayer
     */

    public MusicPlayer(String... files) {
        musicFiles = new ArrayList<String>();
        for (String file : files) {
            musicFiles.add("Client/data/music/" + file + ".wav");
        }
    }

    /**
     * metoda odpoweidzialna za dodanie muzyki
     *
     * @param fileName
     */

    private void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30);
            clip.start();
            clip.loop(100);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metoda odpoweidzialna za włączenie muzyki
     */

    @Override
    public void run() {
        playSound(musicFiles.get(currentSongIndex));
    }


    /**
     * metoda odpoweidzialna za wyłączenie muzyki
     */

    public void stopMusic() {

        clip.stop();
        clip.close();
    }
}
