
import domain.Programare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;


public class Client {
    private static final String host = "localhost";
    private static final int port = 55555;
    private  Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Random random = new Random();
    private int nrLocatii;
    private int nrTratamente;
    private boolean keepGoing = true;

     Client(int nrLocatii,int nrTratamente){
        this.nrLocatii = nrLocatii;
        this.nrTratamente = nrTratamente;
    }

    public void connect() {
        try {
            while(keepGoing) {
                connection = new Socket(host, port);
                outputStream = new ObjectOutputStream(connection.getOutputStream());
                outputStream.flush();
                inputStream = new ObjectInputStream(connection.getInputStream());
                startWork();
            }

            System.out.println("Client stopped by server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startWork(){
        sendProgramare();
    }

    private void sendProgramare() {
        Programare programare = Programare.genereazaProgramare(nrLocatii,nrTratamente);
        try {
            outputStream.writeObject(programare);
            outputStream.flush();
            String response = (String) inputStream.readObject();
            if(response.equals("stop")){
              keepGoing = false;
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