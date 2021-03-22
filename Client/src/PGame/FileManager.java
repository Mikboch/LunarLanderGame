package PGame;

import PServer.Client;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna za odczyt z plików konfiguracyjnych
 */

public class FileManager {

    /**
     * szerokość okna głównego
     */

    public static int mainWindowWidth;

    /**
     * wysokość okna głównego
     */

    public static int mainWindowHeight;

    /**
     * szerokość okna gry
     */

    public static int gameWindowWidth;

    /**
     * wysokość okna gry
     */

    public static int gameWindowHeight;

    /**
     * szerokość okna pomocy
     */

    public static int helpWindowWidth;

    /**
     * wysokość okna pomocy
     */

    public static int helpWindowHeight;

    /**
     * szerokość okna z najlepszymi wynikami
     */

    public static int scoreboardWindowWidth;

    /**
     * wysokość okna z najlepszymi wynikami
     */

    public static int scoreboardWindowHeight;

    /**
     * szerokość okna z połączeniem do serwera
     */

    public static int modeWindowWidth;

    /**
     * wysokość okna z połączeniem do serwera
     */

    public static int modeWindowHeight;

    /**
     * szerokość okna z podaniem nazwy gracza
     */

    public static int playerNameWindowWidth;

    /**
     * wysokość okna z podaniem nazwy gracza
     */

    public static int playerNameWindowHeight;

    /**
     * tablica z wartoścuami x-owymi terenu
     */

    public static int[] terrainX;

    /**
     * tablica z wartoścuami y-owymi terenu
     */

    public static int[] terrainY;

    /**
     * położenie lądowiska nr 1 w X
     */

    public static int landing1X;

    /**
     * położenie lądowiska nr 1 w Y
     */

    public static int landing1Y;

    /**
     * długość lądowiska nr 1
     */

    public static int landing1Width;

    /**
     * wysokość lądowiska nr 1
     */

    public static int landing1Height;

    /**
     * mnożnik lądowiska nr 1
     */


    public static int landing1Multiplier;

    /**
     * położenie hit-box lądowiska nr 1 w X
     */

    public static int landingShape1X;

    /**
     * położenie hit-box lądowiska nr 1 w Y
     */

    public static int landingShape1Y;

    /**
     * szerokość hit-box lądowiska nr 1
     */

    public static int landingShape1Width;

    /**
     * wysokość hit-box lądowiska nr 1
     */

    public static int landingShape1Height;

    /**
     * położenie lądowiska nr 2 w X
     */

    public static int landing2X;

    /**
     * położenie lądowiska nr 2 w Y
     */

    public static int landing2Y;

    /**
     * długość lądowiska nr 2
     */

    public static int landing2Width;

    /**
     * wysokość lądowiska nr 1
     */

    public static int landing2Height;

    /**
     * mnożnik lądowiska nr 2
     */

    public static int landing2Multiplier;

    /**
     * położenie hit-box lądowiska nr 2 w X
     */

    public static int landingShape2X;

    /**
     * położenie hit-box lądowiska nr 2 w Y
     */

    public static int landingShape2Y;

    /**
     * szerokość hit-box lądowiska nr 1
     */

    public static int landingShape2Width;

    /**
     * wysokość hit-box lądowiska nr 1
     */

    public static int landingShape2Height;

    /**
     * położenie początkowe statku w X
     */

    public static int landerX;

    /**
     * położenie początkowe statku w Y
     */

    public static int landerY;

    /**
     * siła grawitacji
     */

    public static double gravity;

    /**
     * wysokość statku
     */

    public static int landerHeight;

    /**
     * szerokość statku
     */

    public static int landerWidth;

    /**
     * początkowa ilość paliwa
     */

    public static double fuelAmount;

    /**
     * maksymalna prędkość
     */

    public static double MaxSpeed;

    /**
     * przyśpieszenie w lewo statku
     */

    public static double accLeft;

    /**
     * przyśpieszenie w prawo statku
     */

    public static double accRight;

    /**
     * przyśpieszenie w górę statku
     */

    public static double accUp;

    /**
     * prędkość paliwa
     */

    public static int fuelVel;

    /**
     * wysokość paliwa
     */

    public static int fuelHeight;

    /**
     * szerokość paliwa
     */

    public static int fuelWidth;

    /**
     * początkowe położenie paliwa w X
     */

    public static int fuelX;

    /**
     * początkowe położenie paliwa w Y
     */

    public static int fuelY;

    /**
     * ilość buforowania
     */

    public static int numBuffers;

    /**
     * ilość jednostek bonusowego paliwa
     */

    public static int fuelBonus;

    /**
     * liczba leveli
     */

    public static int numberOfLevel;

    /**
     * Ip adres serwera
     */

    public static String ipAddress;

    /**
     * port servera
     */
    public static int port;

    /**
     * Zapisuje do zmiennych dane podane przez uzytkownika w momencie laczenia sie z serwerem
     *
     * @param port      numer portu
     * @param ipAddress adres ip serwera
     */
    public static void savePortAndIP(int port, String ipAddress) {
        FileManager.port = port;
        FileManager.ipAddress = ipAddress;
    }

    /**
     * Wczytuje wspolrzedne z pliku konfiguracyjnego serwera i zapisuje je do odpowednich tablic
     *
     * @param levelIndex numer poziomu ktory chcemy wczytac z pliku konfiguracyjnego serwera
     */
    public static void loadLevelFromServer(int levelIndex) throws IOException {

        //if (Client.connected) {
            String response = Client.getMaps(levelIndex);
            String[] configs = response.split("-");


            //Liczba poziomów
            numberOfLevel = Integer.parseInt(configs[0]);

            // Mapa
            terrainX = Arrays.stream(configs[1].split(" ")).mapToInt(Integer::parseInt).toArray();
            terrainY = Arrays.stream(configs[2].split(" ")).mapToInt(Integer::parseInt).toArray();

            // Pierwsze lądowisko
            landing1X = Integer.parseInt(configs[3]);
            landing1Y = Integer.parseInt(configs[4]);
            landing1Width = Integer.parseInt(configs[5]);
            landing1Height = Integer.parseInt(configs[6]);
            landing1Multiplier = Integer.parseInt(configs[7]);
            landingShape1X = Integer.parseInt(configs[8]);
            landingShape1Y = Integer.parseInt(configs[9]);
            landingShape1Height = Integer.parseInt(configs[10]);
            landingShape1Width = Integer.parseInt(configs[11]);


            // Drugielądowisko
            landing2X = Integer.parseInt(configs[12]);
            landing2Y = Integer.parseInt(configs[13]);
            landing2Width = Integer.parseInt(configs[14]);
            landing2Height = Integer.parseInt(configs[15]);
            landing2Multiplier = Integer.parseInt(configs[16]);
            landingShape2X = Integer.parseInt(configs[17]);
            landingShape2Y = Integer.parseInt(configs[18]);
            landingShape2Height = Integer.parseInt(configs[19]);
            landingShape2Width = Integer.parseInt(configs[20]);

            //Parametry Landera
            landerX = Integer.parseInt(configs[21]);
            landerY = Integer.parseInt(configs[22]);

            //Paliwo
            fuelAmount = Integer.parseInt(configs[23]);
            fuelVel = Integer.parseInt(configs[24]);
            fuelHeight = Integer.parseInt(configs[25]);
            fuelWidth = Integer.parseInt(configs[26]);
            fuelX = Integer.parseInt(configs[27]);
            fuelY = Integer.parseInt(configs[28]);

        //}

    }


    /**
     * Wczytuje dane dotyczące paramtetrów gracza z plikow konfiguracyjnych serwera i zapisuje je do odpowiednich pol w klasie
     */
    public static void loadLanderParametersFromServer() throws IOException {

        //if (Client.connected) {
            String response = Client.getLander();
            double[] config;
            config = Arrays.stream(response.split(" ")).mapToDouble(Double::parseDouble).toArray();
            gravity = config[0];

            landerHeight = (int) config[1];
            landerWidth = (int) config[2];

            MaxSpeed = config[3];

            accLeft = config[4];
            accRight = config[5];
            accUp = config[6];
        //}
    }

    /**
     * Wczytuje dane dotyczące rozmiarów okien z plików konfiguracyjncyh serwera
     */

    public static void loadWindowConfigFromServer() throws IOException {

       // if (Client.connected) {

            String response = Client.getConfig();
            int[] config;
            config = Arrays.stream(response.split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(config);

            mainWindowWidth = config[0];
            mainWindowHeight = config[1];

            gameWindowWidth = config[2];
            gameWindowHeight = config[3];

            helpWindowWidth = config[4];
            helpWindowHeight = config[5];

            scoreboardWindowWidth = config[6];
            scoreboardWindowHeight = config[7];

            modeWindowWidth = config[8];
            modeWindowHeight = config[9];

            playerNameWindowWidth = config[10];
            playerNameWindowHeight = config[11];


            numBuffers = config[12];
            fuelBonus = config[13];
       // }

    }


    /**
     * funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego parametrów okien
     */

    public static void loadConfig() throws IOException {
        InputStream propertiesFile = new FileInputStream("Client/data/config/config.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);

        port= Integer.parseInt(properties.getProperty("port"));
        ipAddress= properties.getProperty("ipAddress");

        mainWindowWidth = Integer.parseInt(properties.getProperty("mainWindowWidth"));
        mainWindowHeight = Integer.parseInt(properties.getProperty("mainWindowHeight"));

        gameWindowWidth = Integer.parseInt(properties.getProperty("gameWindowWidth"));
        gameWindowHeight = Integer.parseInt(properties.getProperty("gameWindowHeight"));

        helpWindowWidth = Integer.parseInt(properties.getProperty("helpWindowWidth"));
        helpWindowHeight = Integer.parseInt(properties.getProperty("helpWindowHeight"));

        scoreboardWindowWidth = Integer.parseInt(properties.getProperty("scoreboardWindowWidth"));
        scoreboardWindowHeight = Integer.parseInt(properties.getProperty("scoreboardWindowHeight"));

        modeWindowWidth = Integer.parseInt(properties.getProperty("modeWindowWidth"));
        modeWindowHeight = Integer.parseInt(properties.getProperty("modeWindowHeight"));

        playerNameWindowWidth = Integer.parseInt(properties.getProperty("playerNameWindowWidth"));
        playerNameWindowHeight = Integer.parseInt(properties.getProperty("playerNameWindowHeight"));


        numBuffers = Integer.parseInt(properties.getProperty("numBuffers"));
        fuelBonus = Integer.parseInt(properties.getProperty("fuelBonus"));


        propertiesFile.close();
    }


    /**
     * funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego mapy
     */

    static void loadLevel(int index) throws IOException {

        InputStream propertiesFile = new FileInputStream("Client/data/config/maps.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);

        //Liczba poziomów
        numberOfLevel = Integer.parseInt(properties.getProperty("numberOfLevel"));

        // Mapa
        terrainX = Arrays.stream(properties.getProperty("terrainX" + index).split(" ")).mapToInt(Integer::parseInt).toArray();
        terrainY = Arrays.stream(properties.getProperty("terrainY" + index).split(" ")).mapToInt(Integer::parseInt).toArray();

        // Pierwsze lądowisko
        landing1X = Integer.parseInt(properties.getProperty("landing1X" + index));
        landing1Y = Integer.parseInt(properties.getProperty("landing1Y" + index));
        landing1Width = Integer.parseInt(properties.getProperty("landing1Width" + index));
        landing1Height = Integer.parseInt(properties.getProperty("landing1Height" + index));
        landing1Multiplier = Integer.parseInt(properties.getProperty("landing1Multiplier" + index));
        landingShape1X = Integer.parseInt(properties.getProperty("landingShape1X" + index));
        landingShape1Y = Integer.parseInt(properties.getProperty("landingShape1Y" + index));
        landingShape1Height = Integer.parseInt(properties.getProperty("landingShape1Height" + index));
        landingShape1Width = Integer.parseInt(properties.getProperty("landingShape1Width" + index));


        // Drugielądowisko
        landing2X = Integer.parseInt(properties.getProperty("landing2X" + index));
        landing2Y = Integer.parseInt(properties.getProperty("landing2Y" + index));
        landing2Width = Integer.parseInt(properties.getProperty("landing2Width" + index));
        landing2Height = Integer.parseInt(properties.getProperty("landing2Height" + index));
        landing2Multiplier = Integer.parseInt(properties.getProperty("landing2Multiplier" + index));
        landingShape2X = Integer.parseInt(properties.getProperty("landingShape2X" + index));
        landingShape2Y = Integer.parseInt(properties.getProperty("landingShape2Y" + index));
        landingShape2Height = Integer.parseInt(properties.getProperty("landingShape2Height" + index));
        landingShape2Width = Integer.parseInt(properties.getProperty("landingShape2Width" + index));

        //Parametry Landera
        landerX = Integer.parseInt(properties.getProperty("landerX" + index));
        landerY = Integer.parseInt(properties.getProperty("landerY" + index));

        //Paliwo
        fuelAmount = Integer.parseInt(properties.getProperty("fuelAmount" + index));
        fuelVel = Integer.parseInt(properties.getProperty("fuelVel" + index));
        fuelHeight = Integer.parseInt(properties.getProperty("fuelHeight" + index));
        fuelWidth = Integer.parseInt(properties.getProperty("fuelWidth" + index));
        fuelX = Integer.parseInt(properties.getProperty("fuelX" + index));
        fuelY = Integer.parseInt(properties.getProperty("fuelY" + index));


        propertiesFile.close();
    }

    public static void loadScoreFromServer() throws IOException {


        File file = new File("Client/data/config/best_online_scores.txt");
        FileWriter writer = new FileWriter(file);

        String response = Client.getRanking();
        String[] configs = response.split("-");
        System.out.println(configs.toString());


        for (int i = 0; i < configs.length; i++) {
            writer.write(configs[i] + " " + "\n");
            //System.out.println(i);
        }

        writer.close();
    }


    /**
     * funkcja odpowiedzialna za odczyt z pliku konfiguracyjnego parametrów statku
     */

    public static void loadLander() throws IOException {

        InputStream propertiesFile = new FileInputStream("Client/data/config/lander.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);


        gravity = Float.parseFloat(properties.getProperty("gravity"));

        landerHeight = Integer.parseInt(properties.getProperty("landerHeight"));
        landerWidth = Integer.parseInt(properties.getProperty("landerWidth"));

        MaxSpeed = Float.parseFloat(properties.getProperty("MaxSpeed"));

        accLeft = Float.parseFloat(properties.getProperty("accLeft"));
        accRight = Float.parseFloat(properties.getProperty("accRight"));
        accUp = Float.parseFloat(properties.getProperty("accUp"));


        propertiesFile.close();
    }

    /**
     *
     */
    public static ArrayList loadScore() throws IOException {


        File file = new File("Client/data/config/best_scores.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<String> scores = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scores.add(line);
        }
        scanner.close();

        ArrayList<String[]> splittedScores = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) {
            String[] tmp = scores.get(i).split(" ");
            splittedScores.add(tmp);
        }

        System.out.println("Otwarto pomyslnie");

        return splittedScores;


    }


    public static void saveScore(String name, int score) throws IOException {

        ArrayList<String[]> scores = loadScore();
        String[] record = {name, Integer.toString(score)};
        //System.out.println(scores.size());

        int previousSize = scores.size();

        for (int i = 0; i < previousSize; i++) {


            if (previousSize < 10 && Integer.parseInt(scores.get(i)[1]) < score) {
                scores.add(i, record);
                break;

            } else if (previousSize < 10 && i == previousSize - 1 && Integer.parseInt(scores.get(previousSize - 1)[1]) >= score) {
                scores.add(record);

            } else if (previousSize == 10 && Integer.parseInt(scores.get(i)[1]) < score) {
                scores.add(i, record);
                break;

            }

        }

        if (scores.size() > 10)
            scores.remove(10);

        File file = new File("Client/data/config/best_scores.txt");
        FileWriter writer = new FileWriter(file);


        for (int i = 0; i < scores.size(); i++) {
            writer.write(scores.get(i)[0] + " " + scores.get(i)[1] + "\n");
            
        }

        writer.close();

    }
}
