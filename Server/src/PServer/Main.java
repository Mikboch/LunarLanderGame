package PServer;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Klasa Main
 */

public class Main {

    /**
     * Konstruktor klasy main
     */

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = new Server();
        System.out.println("IP adress: " + InetAddress.getLocalHost());
        System.out.println("Port: " + Server.port);
        server.runServer();

    }
}
