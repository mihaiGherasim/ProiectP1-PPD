package server;

import domain.Programare;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server{
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private List<Future<String>> programari = new ArrayList<>();
    private boolean serverOprit = false;

    public Callable<String> verificaAdaugareProgramare(Programare programare){
        return ()->{
            return "programare reusita";
        };
    }

    public void startServer(){
        scheduledExecutorService.schedule(this::stopServer, 5000, TimeUnit.MILLISECONDS);
    }

    public void stopServer(){
        scheduledExecutorService.shutdown();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public List<Future<String>> getProgramari() {
        return programari;
    }

    public void setProgramari(List<Future<String>> programari) {
        this.programari = programari;
    }
}
