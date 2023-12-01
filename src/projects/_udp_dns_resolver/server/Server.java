/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_dns_resolver.server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public abstract class Server {

    // =================================
    // == Fields
    // =================================
    private static String SERVER_IP = "localhost";
    private static int SERVER_PORT = 8080;

    // =================================
    // =================================
    public static void startServer() {

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
