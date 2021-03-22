package PServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa Server
 */

public class Server {

    /**
     * Numer portu
     */
    static int port = 9999;

    /**
     * Metoda odpowiedzialna za uruchomienie servera
     */

    public void runServer() throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is up!");
        while (true) {
            Thread.sleep(10);
            Socket socket = serverSocket.accept();
            new Thread(new ServerThread(socket)).start();

        }
    }
}
