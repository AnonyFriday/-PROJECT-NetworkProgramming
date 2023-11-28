/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;

/**
 * The multi-client room chat server with broadcasting feature
 *
 * @author duyvu
 */
public class ServerSide {

    // ======================================
    // = Fields
    // ======================================
    public static final int SERVER_PORT = 5000;
    public static final String SERVER_IP = "localhost";

    private List<ClientThread> clientThreads;

    // ======================================
    // = Constructors
    // ======================================
    public ServerSide() {
        clientThreads = new ArrayList<>();
    }

    // ======================================
    // = Methods
    // ======================================
    /**
     * An entry point function to start the server
     */
    public void startServer() {
        try {
            // Webserver
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            // Multiple clients connect to server
            while (true) {

                // Create a connection to ONE client
                Socket clientSocket = serverSocket.accept();

                // After connecting to the client, create a thread to serve that client and add to the list
                ClientThread clientThread = new ClientThread(clientSocket, String.valueOf(System.currentTimeMillis()), this);
                clientThreads.add(clientThread);
                new Thread(clientThread).start();

                System.out.println("Connected to the client: " + clientSocket.getInetAddress());
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Board-casting a message received from the client to other clients
     * Exclude the sender id whose already sent a message to the server
     *
     * @param message
     */
    protected void boardcastMessage(String fromClientId,
                                    String message) {
//  [ERROR]
//        this.clientThreads.parallelStream().forEach((clientThread) -> {
//            if (!clientThread.getClientID().equals(fromClientId)) {
//                clientThread.sendMessage(message);
//            }
//        });

        for (ClientThread clientThread : clientThreads) {
            if (!clientThread.getClientID().equals(fromClientId)) {
                clientThread.sendMessage("From Client (" + fromClientId + "): " + message);
            }

        }
    }
}
