package utils;
import domain.Programare;

import java.io.File;
import java.io.FileWriter;
import service.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private boolean serverStop = false;
    private Timer timer;
    private List<Socket> clients;
    private Socket client;
    private FileWriter myObj ;

    private Service service;
    //private Repo

    public Server(int port, int numberOfThreads) {
        this.port = port;
        threadPool = Executors.newFixedThreadPool(numberOfThreads);
        clients = new ArrayList<Socket>();
        service = new Service();
        try {
            myObj = new FileWriter("src/main/java/plati.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopServerScheduledTask() {
        timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Close server task performed on: " + new Date() + "\n");
                serverStop = true;

            }
        };
        //long delay = 180000L;//3 minute
        long delay = 40000L;
        timer.schedule(task, delay);
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);

            System.out.println("Server has started...");
            stopServerScheduledTask();

            while (!serverSocket.isClosed() && !serverStop) {

                System.out.println("Waiting for clients...");

                try {
                    client = serverSocket.accept();
                    clients.add(client);

                } catch (SocketException ex) {
                    System.out.println("Server connection closed");
                    return;

                }
                System.out.println("Client connected...");
                processRequest(client);
            }
            System.out.println("In the end:" + clients.size());
            serverSocket.close();
            myObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private synchronized void cleanupAction() {
        List<Socket> updatedClients = new ArrayList<Socket>();
        for (int i = 0; i < clients.size(); i++) {
            if (!clients.get(i).isClosed()) updatedClients.add(clients.get(i));
        }
        clients = updatedClients;
    }

    private void processRequest(Socket client) {
        Future<String> future = threadPool.submit(new MyCallable(client));
        Thread thread = new Thread(new ServerHelper(future, client, serverSocket, threadPool, clients));
        thread.setDaemon(false);
        thread.start();
    }

    private class MyCallable implements Callable<String> {
        private Socket client;
        private ObjectInputStream inputStream;

        public MyCallable(Socket client) {
            this.client = client;
            try {
                inputStream = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String call() throws Exception {
            cleanupAction();
            if (serverStop) {
                timer.cancel();
                inputStream.readObject();
                return "stop";
            }
            Programare programare = (Programare) inputStream.readObject();
            if(programare.getNumeClient().equals("-1plata")){
                System.out.println("heli");
                myObj.append(programare.getData().toString() + " " + programare.getCnp() + " " +programare.getSuma() + "\n");
                return "plata efectuata";
            }
            System.out.println("Received from client " + programare.toString() + "\n");
            if(service.verificaProgramare(programare)){


                return "programare reusita";
            }
            else {
                return "programare nereusita";
            }


        }
    }
}