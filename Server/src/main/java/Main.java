import utils.Server;

public class Main {
    private static int NUMBER_OF_THREADS = 5;
    private static int port = 55555;
    public static void main(String[] args) {
        Server server =  new Server(port,NUMBER_OF_THREADS);
        server.start();

    }
}
