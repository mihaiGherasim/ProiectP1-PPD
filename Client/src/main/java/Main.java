
public class Main {
    public static void main(String[] args) {
        int nrLocatii = 5;
        int nrTratamente = 5;
        Client clientWorker = new Client(nrLocatii,nrTratamente);
        clientWorker.connect();
    }
}
