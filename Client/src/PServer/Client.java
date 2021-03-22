package PServer;

import PGame.FileManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Klasa Client
 */

public class Client {

    /**
     * Definicja Socketu
     */

    static Socket socket;

    /**
     * Flaga informująca czy rozgrywka jest online
     */

    public static boolean isOnline = false;

    /**
     * Flaga informująca czy połączono się z serwerem
     */

    public static boolean connected = false;

    /**
     * Metoda odpowiedzialna za połączenie się z serwerem
     */

    private static Socket checkConnection() throws Exception {
        Socket soc = new Socket();

        soc.connect(new InetSocketAddress(FileManager.ipAddress, FileManager.port), 500);

        return soc;

    }

    /**
     * Ustanawia polaczenie z serwerem poprzez utworzenie obiektu klasy socket i wysylanie zadania do serwera
     *
     * @param command Tresc zadania który mu podajemy
     * @return linia tekstu będąca odpowiedzia serwera
     */
    private static String connectWithServer(String command) throws IOException {

        try {

            socket = checkConnection();

            System.out.println("Client socket");
        } catch (Exception e) {
            System.out.println("Server offline");
            return " ";
        }
//        socket = new Socket(FileManager.ipAddres, FileManager.port);

        connected = true;
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        printWriter.println(command);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();

    }

    /**
     * Wysyla do serwera zadanie o wyslanie danych konfiguracyjnych mapy o danym indeksie
     *
     * @param index numer poziomu ktory chcemmy uzyskac
     * @return linia tekstu będąca odpowiedzia od serwera
     */

    public static String getMaps(int index) throws IOException {

        String response = connectWithServer("getMaps" + "-" + index);
        socket.close();
        connected=false;
        return response;

    }

    /**
     * Prosi o liste wyników z serwera
     *
     * @return linia tekstu zawierajaca kolejne nazwy graczy wraz z wynikami
     */
    public static String getRanking() throws IOException {

        String response = connectWithServer("getRanking");
        socket.close();
        connected=false;
        return response;

    }

    /**
     * Wysyla do serwera prosbe o wyslanie danych konfiguracyjnych landera
     *
     * @return linia tekstu z odpowiedzia serwera
     */
    public static String getLander() throws IOException {
        String response = connectWithServer("getLander");
        socket.close();
        connected=false;
        return response;

    }

    /**
     * Wysyla do serwera prosbe o wyslanie danych konfiguracyjnych
     *
     * @return linia tekstu z odpowiedzia serwera
     */

    public static String getConfig() throws IOException {

        String response = connectWithServer("getConfig");
        socket.close();
        connected=false;
        return response;

    }

    /**
     * Sprawdzamy czy program moze połaczyc sie z serwerem
     *
     * @return boolean, jesli true - nie mozna ustanowic polaczenia, jesli false - udalo się ustanowic
     * polaczenie
     */

    public static boolean checkIfOnline() {
        try {
            isOnline = true;
            socket = new Socket(FileManager.ipAddress, FileManager.port);
        } catch (IOException e) {
            System.out.println("Server offline");
            isOnline = false;
        }
        return isOnline;
    }

    /**
     * Wysyla do serwera nowy wynik uzytkownika
     *
     * @param name  nazwa gracza
     * @param score uzyskany wynik
     * @return linia tekstu z odpowiedzia serwera
     */

    public static String sendScoreToServer(String name, int score) throws IOException {
        String response = connectWithServer("saveScore" + "-" + name + "-" + score);
        socket.close();
        connected=false;
        return response;

    }

}
