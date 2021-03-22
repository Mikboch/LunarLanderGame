package PServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa ServerThread
 */

public class ServerThread implements Runnable {

    /**
     * Definicja socketu
     */

    private Socket socket;

    /**
     * Konstruktor ServerThread
     * @param  socket
     */

    public ServerThread(Socket socket){

        this.socket = socket;

    }

    /**
     * Metoda odbiera prosby klienta i na nie odpowiada
     */

    @Override
    public void run(){
        try {
        while(true) {
                Thread.sleep(10);

                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);

                // Konwersja ze strumienia na stringa
                String fromClient = bufferedReader.readLine();
                if(fromClient != null) {

                    System.out.println("From client: " + fromClient);
                    String serverResponse = Commands.serverAction(fromClient);
                    printWriter.println(serverResponse);
                    printWriter.flush();
                    System.out.println("Server respond: " + serverResponse);
                    break;

                }
            }
        }catch (IOException | InterruptedException e) {
            System.out.println("Connection lost");
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}
