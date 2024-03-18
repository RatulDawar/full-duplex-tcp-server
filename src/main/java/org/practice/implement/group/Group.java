package org.practice.implement.group;

import org.practice.implement.client.ClientID;

import java.util.HashSet;
import java.util.Set;
public class Group {
    private GroupID groupID;
    private Set<ClientID> clients;

    public synchronized void add(ClientID clientID){
        clients.add(clientID);
    }

    public synchronized void remove(ClientID clientID){
        clients.remove(clientID);
    }

    public Set<ClientID> getClients(){
        return clients;
    }

    Group(GroupID groupID){
        this.groupID = groupID;
        clients = new HashSet<>();
    }

}

