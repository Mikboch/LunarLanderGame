package PServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Klasa FileManager
 */

public class FileManager {

    /**
     * Funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego
     *
     * @return string z rozmiarami okien
     */

    public static String loadConfig() throws IOException {
        InputStream propertiesFile = new FileInputStream("Server/data/config/config.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response;

        response = (properties.getProperty("mainWindowWidth")) + " " +
                (properties.getProperty("mainWindowHeight")) + " " +

                (properties.getProperty("gameWindowWidth")) + " " +
                (properties.getProperty("gameWindowHeight")) + " " +

                (properties.getProperty("helpWindowWidth")) + " " +
                (properties.getProperty("helpWindowHeight")) + " " +

                (properties.getProperty("scoreboardWindowWidth")) + " " +
                (properties.getProperty("scoreboardWindowHeight")) + " " +

                (properties.getProperty("modeWindowWidth")) + " " +
                (properties.getProperty("modeWindowHeight")) + " " +

                (properties.getProperty("playerNameWindowWidth")) + " " +
                (properties.getProperty("playerNameWindowHeight")) + " " +

                (properties.getProperty("numBuffers")) + " " +
                (properties.getProperty("fuelBonus"));


        propertiesFile.close();
        return response;
    }

    /**
     * Funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego mapy
     *
     * @param index index danego levela
     * @return zwraca string z numerami levela
     */

    static String loadLevel(int index) throws IOException {

        InputStream propertiesFile = new FileInputStream("Server/data/config/maps.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);

        String response;

        int[] terrainX = Arrays.stream(properties.getProperty("terrainX" + index).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] terrainY = Arrays.stream(properties.getProperty("terrainY" + index).split(" ")).mapToInt(Integer::parseInt).toArray();


        //Liczba poziomów
        response = (properties.getProperty("numberOfLevel")) + "-" +
                // Mapa
                Arrays.toString(terrainX) + "-" +
                Arrays.toString(terrainY) + "-" +
                // Pierwsze lądowisko
                (properties.getProperty("landing1X" + index)) + "-" +
                (properties.getProperty("landing1Y" + index)) + "-" +
                (properties.getProperty("landing1Width" + index)) + "-" +
                (properties.getProperty("landing1Height" + index)) + "-" +
                (properties.getProperty("landing1Multiplier" + index)) + "-" +
                (properties.getProperty("landingShape1X" + index)) + "-" +
                (properties.getProperty("landingShape1Y" + index)) + "-" +
                (properties.getProperty("landingShape1Height" + index)) + "-" +
                (properties.getProperty("landingShape1Width" + index)) + "-" +

                // Drugielądowisko
                (properties.getProperty("landing2X" + index)) + "-" +
                (properties.getProperty("landing2Y" + index)) + "-" +
                (properties.getProperty("landing2Width" + index)) + "-" +
                (properties.getProperty("landing2Height" + index)) + "-" +
                (properties.getProperty("landing2Multiplier" + index)) + "-" +
                (properties.getProperty("landingShape2X" + index)) + "-" +
                (properties.getProperty("landingShape2Y" + index)) + "-" +
                (properties.getProperty("landingShape2Height" + index)) + "-" +
                (properties.getProperty("landingShape2Width" + index)) + "-" +

                //Parametry Landera
                (properties.getProperty("landerX" + index)) + "-" +
                (properties.getProperty("landerY" + index)) + "-" +

                //Paliwo
                (properties.getProperty("fuelAmount" + index)) + "-" +
                (properties.getProperty("fuelVel" + index)) + "-" +
                (properties.getProperty("fuelHeight" + index)) + "-" +
                (properties.getProperty("fuelWidth" + index)) + "-" +
                (properties.getProperty("fuelX" + index)) + "-" +
                (properties.getProperty("fuelY" + index));


        propertiesFile.close();
        return response.replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    /**
     * Funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego mapy
     *
     * @return zwraca stringa z danymi landera
     */

    public static String loadLander() throws IOException {
        InputStream propertiesFile = new FileInputStream("Server/data/config/lander.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response;

        response = (properties.getProperty("gravity")) + " " +
                (properties.getProperty("landerHeight")) + " " +
                (properties.getProperty("landerWidth")) + " " +
                (properties.getProperty("MaxSpeed")) + " " +
                (properties.getProperty("accLeft")) + " " +
                (properties.getProperty("accRight")) + " " +
                (properties.getProperty("accUp"));


        propertiesFile.close();
        return response;
    }
}
