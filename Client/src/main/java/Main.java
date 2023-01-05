import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        // write your code here
        int nrLocatii = 5;
        int nrTratamente = 5;
        Client clientWorker = new Client(nrLocatii,nrTratamente);
        clientWorker.connect();
    }
}
