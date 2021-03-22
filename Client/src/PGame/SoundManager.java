package PGame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa SoundManager
 */

public class SoundManager {

    /**
     * Lista przechowująca Clipy
     */

    ArrayList<Clip> clipArray = new ArrayList<>();

    /**
     * Tablica przechowująca ścieżki do utworów
     */

    String[] soundsPaths ={"Client/data/music/space.wav","Client/data/music/test.wav"};

    /**
     * Przechowuje informcaje o liczbie utworów
     */

    int numberOfSounds = 2;

    /**
     * Metoda zarządzająca utworami
     */

    public SoundManager() {
        for (int i = 0; i < numberOfSounds; i++) {
            try {
                File soundPath = new File(soundsPaths[i]);

                if (soundPath.exists()) {

                    clipArray.add(AudioSystem.getClip());

                }
            } catch ( LineUnavailableException ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * Metoda odpowiedzialna za uruchomienie utworu
     */

    public void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(soundsPaths[0]));
        clipArray.get(0).open(audioIS);
        FloatControl gainControl = (FloatControl) clipArray.get(0).getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30);
        clipArray.get(0).start();
        clipArray.get(0).loop(Clip.LOOP_CONTINUOUSLY);

        }

    /**
     * Metoda odpowiedzialna za uruchomienie dźwięku
     */

    public void playSound(int index) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(soundsPaths[index]));
        clipArray.get(index).open(audioIS);
        clipArray.get(index).start();
        if(!clipArray.get(index).isRunning())
            clipArray.get(index).close();

    }

    /**
     * Metoda odpowiedzialna za pause muzyki
     *
     * @param index numer utworu
     */

    public void pause(int index){
        clipArray.get(index).stop();

    }

    /**
     * Metoda odpowiedzialna za uruchomienie muzyki
     *
     * @param index numer utworu
     */

    public void play(int index){
        clipArray.get(index).start();
    }

    /**
     * Metoda odpowiedzialna za zatrzymanie muzyki
     *
     * @param index numer utworu
     */

    public void stop(int index){

        clipArray.get(index).stop();
        clipArray.get(index).close();

    }

}
