package utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerHelper implements Runnable {
    private Socket client;
    private ObjectOutputStream outputStream;
    private Future<String> future;
    private ServerSocket server;
    private ExecutorService serverPool;
    private List<Socket> clients;

    public ServerHelper(Future<String> future, Socket client, ServerSocket server,ExecutorService serverPool,List<Socket> clients) {
        this.future = future;
        this.client = client;
        this.server = server;
        this.serverPool = serverPool;
        this.clients = clients;
        try {
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendStopToOneClient(Socket client){

        try {
            ObjectOutputStream myoutputStream = new ObjectOutputStream(client.getOutputStream());
            myoutputStream.flush();
            myoutputStream.writeObject("stop");
            myoutputStream.flush();
            myoutputStream.close();
            client.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        try {
            if(client.isClosed()){
                outputStream.close();
                future.cancel(true);
                return;}
            String responseMessage = future.get();
            outputStream.writeObject(responseMessage);
            outputStream.flush();
            outputStream.close();
            client.close();

            if(responseMessage.equals("stop")){
                System.out.println("Clients in size:" + clients.size());
                for (Socket socket : clients) {
                    if (!socket.isClosed()) {
                        System.out.println("one");
                        sendStopToOneClient(socket);
                    }
                }
                if(!server.isClosed())
                    server.close();
                if(!serverPool.isShutdown())
                    serverPool.shutdown();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
