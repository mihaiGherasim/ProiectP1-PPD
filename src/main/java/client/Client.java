package client;

import domain.Programare;
import server.Server;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Client{

    private Server server;
    private int nrLocatii;
    private int nrTratamente;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Client(Server server, int nrLocatii, int nrTratamente){
        this.server = server;
        this.nrLocatii = nrLocatii;
        this.nrTratamente = nrTratamente;
    }

    private Programare genereazaProgramare(){
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String numeClient = new String(array, StandardCharsets.UTF_8);
        StringBuilder cnp = new StringBuilder();
        for(int i=0; i<13; i++){
            int cifra = new Random().nextInt(10);
            cnp.append(cifra);
        }
        Date data = new Date();
        int idTratament = new Random().nextInt(nrTratamente)+1;
        int idLocatie = new Random().nextInt(nrLocatii)+1;
        Date dataTratament = new Date();
        return new Programare(numeClient, cnp.toString(), data, idLocatie, idTratament, dataTratament);
    }

    public void run() {
        scheduler.scheduleAtFixedRate(()->{
            Programare programare = genereazaProgramare();
            if(!server.getScheduledExecutorService().isTerminated()) {
                Future<String> future = null;
                if(!server.getScheduledExecutorService().isShutdown()) {
                    future = server.getScheduledExecutorService().schedule(server.verificaAdaugareProgramare(programare), 0, TimeUnit.SECONDS);
                }
                List<Future<String>> programari = server.getProgramari();
                programari.add(future);
                server.setProgramari(programari);
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                scheduler.shutdownNow();
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
