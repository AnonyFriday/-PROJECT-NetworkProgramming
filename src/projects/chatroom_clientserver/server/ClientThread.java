/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class ClientThread implements Runnable {

    // =================================
    // == Fields
    // =================================    
    private Socket clientSocket;
    private ServerSide server;
    private String clientID;

    private BufferedReader reader;
    private PrintWriter writer;

    // =================================
    // == Constructors
    // ================================= 
    public ClientThread(Socket clientSocket,
                        String clientID,
                        ServerSide server) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;
        this.server = server;

        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // =================================
    // == Methods
    // ================================= 
    @Override
    public void run() {

        // Continuously serve one client until the client's socket is closed
        while (!clientSocket.isClosed()) {
            try {

                // Check if the reader stream is avaialble or not 
                if (reader.ready()) {
                    String message = reader.readLine();
                    server.boardcastMessage(this.getClientID(), message);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * A function to send back message to the client from a server
     *
     * @param message
     */
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    // =================================
    // == Getters & Setters
    // ================================= 
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
