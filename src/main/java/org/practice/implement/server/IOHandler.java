package org.practice.implement.server;

import org.practice.configurables.Configurable;
import org.practice.implement.message.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


// reads input and writes output to TCP connection
public class IOHandler {
    private IOHandler(){
        throw new AssertionError("Cannot instantiate utility class");
    }
    public static String read(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        byte[] buffer = new byte[Configurable.MAX_REQUEST_SIZE_IN_BYTES];
        int bytesRead = inputStream.read(buffer);
        return  bytesRead != - 1 ? new String(buffer,0,bytesRead) : "";
    }

    public static Message getMessage(Socket clientSocket) throws IOException {

        return Message.parseMessage(read(clientSocket));
    }

    public static void write(String response,Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
    }

}

