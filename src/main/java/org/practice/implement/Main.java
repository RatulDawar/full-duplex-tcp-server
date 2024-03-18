package org.practice.implement;

import org.practice.implement.server.WebSocketServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WebSocketServer webSocketServer = new WebSocketServer(8080);
        webSocketServer.start();
    }
}