/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.server;

import java.net.Socket;

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

    // =================================
    // == Constructors
    // ================================= 
    public ClientThread(Socket clientSocket, String clientID) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // =================================
    // == Constructors
    // ================================= 
}
