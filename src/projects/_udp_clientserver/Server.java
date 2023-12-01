/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_clientserver;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class Server {

    public final static int SERVER_PORT = 7;
    public final static String SERVER_IP = "localhost";
    public final static byte[] BUFFER = new byte[4096];

    public void startServer() {
        try {
            // Create the connectionless socket for server
            DatagramSocket ds = new DatagramSocket();

            // Binding the port and server host of the server to the socket
            ds.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            System.out.println("Server started");
            System.out.println("Waiting for messages from Client ... ");

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
