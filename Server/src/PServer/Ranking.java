package PServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa Ranking
 */

public class Ranking {

    /**
     * Metoda odpowiedzialna za wyslanie rankingu do servera
     */

    public static String sendScore() throws IOException {

        File file = new File("Server/data/config/best_scores.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<String> scores = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            scores.add(line);
        }
        scanner.close();

        String response = "";

        for (int i = 0; i < scores.size(); i++) {
            response += scores.get(i) + "-";

        }

        System.out.println("Otwarto pomyslnie");

        return response;

    }

    /**
     * Metoda odpowiedzialna za odbior wynikow z pliku konfiguracyjnego serwera
     */

    public static ArrayList loadScore() throws IOException {

        File file = new File("Server/data/config/best_scores.txt");
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

    /**
     * Metoda odpowiedzialna za dopisnaie nowego wyniku uzyskanego od klienta i zapisanie do pliku
     *
     * @param name  nazwa gracza od klienta
     * @param score punkty użytkownika od klienta
     */

    public static void saveScore(String name, int score) throws IOException {

        ArrayList<String[]> scores = loadScore();
        String[] record = {name, Integer.toString(score)};

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

        File file = new File("Server/data/config/best_scores.txt");
        FileWriter writer = new FileWriter(file);


        for (int i = 0; i < scores.size(); i++) {
            writer.write(scores.get(i)[0] + " " + scores.get(i)[1] + "\n");

        }

        writer.close();

    }

}
