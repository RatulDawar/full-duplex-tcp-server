package org.practice.implement.server;

import org.practice.implement.client.Client;
import org.practice.implement.client.ClientManager;
import org.practice.implement.group.Group;
import org.practice.implement.group.GroupManager;
import org.practice.implement.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebSocketServer {
    ServerSocket serverSocket;
    GroupManager groupManager;
    ClientManager clientManager;
    int port;

    public WebSocketServer(int port) {
        this.port = port;
        this.groupManager = new GroupManager();
        this.clientManager = new ClientManager();
    }


    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        ExecutorService executor = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            Client connectionClient = new Client(clientSocket);
            executor.submit(()-> {
                try {
                    this.clientConnectionHandler(connectionClient);
                } catch (IOException e) {
                    clientManager.unregisterClient(connectionClient);
                    groupManager.removeFromAllGroups(connectionClient.getClientID());
                }
            });

        }


    }

    private void clientConnectionHandler(Client connectionClient) throws IOException,IllegalArgumentException {

        clientManager.registerClient(connectionClient);
        while(true) {
            try {
                Message message = SocketIOHandler.getMessage(connectionClient.getClientSocket());
                if (message.getJoinRequest()) {
                    groupManager.addToGroup(message.getGroupID(), connectionClient.getClientID());
                } else {
                    broadcastMessage(message, connectionClient);
                }
            }catch (IllegalArgumentException e){
                SocketIOHandler.write(e.getMessage(), connectionClient.getClientSocket());
            }
        }

    }


    public void broadcastMessage(Message message,Client senderClient) throws IOException {
        try {
            Group broadcastGroup = groupManager.getGroup(message.getGroupID());
            broadcastGroup.getClients().forEach(clientID -> {
                try {
                    SocketIOHandler.write(message.getData(),clientManager.getClient(clientID).getClientSocket());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (IllegalArgumentException e){
            SocketIOHandler.write(e.getMessage(),senderClient.getClientSocket());
        }
    }





}
