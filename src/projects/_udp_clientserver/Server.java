/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_clientserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The server of the UDP Connection
 *
 * @author duyvu
 */
public class Server {

    // Server environments
    public final static int SERVER_PORT = 9000;
    public final static String SERVER_IP = "localhost";
    public final static byte[] BUFFER = new byte[4096];

    // Method to start a server
    public static void startServer() {
        try {
            // Create the connectionless socket for server
            // [Warning]: passing null to make the unbound socket
            DatagramSocket ds = new DatagramSocket(null);

            // Binding the port and server host of the server to the socket
            ds.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            System.out.println("Server started");
            System.out.println("Waiting for messages from Client ... ");

            // Continuously listerning for the clients request
            while (true) {

                // Waiting for the incoming datagram
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming);

                if (incoming.getLength() > 0) {
                    // Decompose the data from datagram
                    String senderAddressrMsg = incoming.getAddress().getHostAddress();
                    String senderHostnameMsg = incoming.getAddress().getHostName();
                    int portMsg = incoming.getPort();
                    String message = new String(incoming.getData(), 0, incoming.getLength());

                    // Print the message to the console
                    System.out.printf("---\n"
                            + "From Address: %s\n"
                            + "From Hostname: %s\n"
                            + "Port: %d\n"
                            + "Message: %s\n"
                            + "---\n", senderAddressrMsg, senderHostnameMsg, portMsg, message);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        startServer();
    }
}
