
import domain.Programare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Client {
    private static final String host = "localhost";
    private static final int port = 55555;
    private Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Random random = new Random();
    private int nrLocatii;
    private int nrTratamente;
    private boolean keepGoing = true;
    private Timer requestTimer;

    Client(int nrLocatii, int nrTratamente) {
        this.nrLocatii = nrLocatii;
        this.nrTratamente = nrTratamente;
    }

    public void connect() {
     startWork();
    }

    private void startWork() {
        requestTimer = new Timer();
        requestTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //  System.out.println("Running: " + new java.util.Date());
                try {
                    connection = new Socket(host, port);
                    outputStream = new ObjectOutputStream(connection.getOutputStream());
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputStream = new ObjectInputStream(connection.getInputStream());
                    sendProgramare();
                } catch (SocketException ex) {
                    System.out.println("Stop client");
                    requestTimer.cancel();
                    try {
                        inputStream.close();
                        outputStream.close();
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    requestTimer.cancel();
                }
            }
        }, 0, 2000);

    }

    private void sendProgramare() {
        Programare programare = Programare.genereazaProgramare(nrLocatii, nrTratamente);
        try {
            outputStream.writeObject(programare);
            outputStream.flush();
            String response = (String) inputStream.readObject();
            if (response.equals("stop")) {
                System.out.println("I get stop");
                keepGoing = false;
                requestTimer.cancel();
            }
            System.out.println("Received from server: " + response + "\n");
            inputStream.close();
            outputStream.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}