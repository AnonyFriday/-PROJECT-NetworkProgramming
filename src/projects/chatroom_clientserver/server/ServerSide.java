/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class ServerSide {

    private static final int SERVER_PORT = 5000;
    private static final String SERVER_IP = "localhost";

    public void startServer() {

        try {
            // Webserver
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
