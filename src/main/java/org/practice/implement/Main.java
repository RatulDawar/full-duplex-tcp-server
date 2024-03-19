package org.practice.implement;

import org.practice.implement.server.FullDuplexServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FullDuplexServer fullDuplexServer = new FullDuplexServer(8080);
        fullDuplexServer.start();
    }
}