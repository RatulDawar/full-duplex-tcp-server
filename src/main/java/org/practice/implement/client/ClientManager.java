package org.practice.implement.client;

import java.util.HashMap;
import java.util.Map;


public class ClientManager {
    Map<ClientID, Client> clients;
    public ClientManager(){
        clients = new HashMap<>();
    }

    public  synchronized void registerClient(Client client){
        clients.put(client.getClientID(),client);
    }

    public synchronized void unregisterClient(Client client){
        clients.remove(client.getClientID());
    }

    public Client getClient(ClientID clientID){
        return clients.get(clientID);
    }




}
