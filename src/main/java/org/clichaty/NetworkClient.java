package org.clichaty;

import java.io.IOException;
import java.net.Socket;

public class NetworkClient {
    private String serverAddress;
    private int serverPort;

    public NetworkClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);
            // Handle communication
        }
    }
}