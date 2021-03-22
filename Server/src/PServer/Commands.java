package PServer;

import java.io.IOException;

/**
 * Klasa odpowiedzialna za sterowanie akcjami serwera, poprzez odbiór odpowienich komend- rządań
 */
public class Commands {

    /**
     * Funkcja odpowiedzialna za wykonywanie zadań zleconych przez klienta
     *
     * @param command żądanie wysłane przez klienta
     * @return odpowiedź serwera
     */

    public static String serverAction(String command) throws IOException {

        String serverMessage;
        String name;
        int score;

        String[] commands = command.split("-");
        switch (commands[0]) {

            case "getConfig":
                serverMessage = FileManager.loadConfig();
                break;

            case "getMaps":
                serverMessage = FileManager.loadLevel(Integer.parseInt(commands[1]));
                break;

            case "getLander":
                serverMessage = FileManager.loadLander();
                break;

            case "getRanking":
                serverMessage = Ranking.sendScore();
                break;

            case "saveScore":
                serverMessage = "Score saved";
                name = commands[1];
                score = Integer.parseInt(commands[2]);
                Ranking.saveScore(name, score);
                break;

            default:
                serverMessage = "Error - Invalid Command";

        }
        return serverMessage;
    }
}