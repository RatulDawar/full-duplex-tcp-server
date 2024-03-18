package org.practice.implement.client;

import java.net.Socket;

public class Client {
    private final ClientID clientID;
    private final Socket clientSocket;
    public Client(Socket clientSocket){
        this.clientSocket = clientSocket;
        this.clientID = ClientID.generateClientID(clientSocket);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ClientID getClientID() {
        return clientID;
    }


}

