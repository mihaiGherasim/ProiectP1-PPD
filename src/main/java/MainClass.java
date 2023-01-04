import client.Client;
import server.Server;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        int nrLocatii = 10;
        int nrTratamente = 5;
        int nrClienti = 1;
        Server server = new Server();
        server.startServer();
        Client client = new Client(server, nrLocatii, nrTratamente);
        client.run();
    }
}
