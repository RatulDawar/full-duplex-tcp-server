package org.practice.implement.client;

import java.net.Socket;

public class ClientID{
    String value;
    ClientID(String value){
        this.value = value;
    }

    public static ClientID generateClientID(Socket clientSocket){
        return new ClientID(clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClientID other = (ClientID) obj;
        return value.equals(other.value);
    }
}
