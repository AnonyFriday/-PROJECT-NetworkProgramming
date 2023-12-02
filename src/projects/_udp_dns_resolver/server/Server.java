/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_dns_resolver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
final public class Server {

    // =================================
    // == Fields
    // =================================
    public static String SERVER_IP = "localhost";
    public static int SERVER_PORT = 8080;
    private DatagramSocket serverSocket;

    // =================================
    // == Constructor
    // =================================
    public Server() {
        try {
            // Create the socket object without starting it 
            serverSocket = new DatagramSocket(null);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // =================================
    // == Methods
    // =================================
    public void startServer() {
        try {
            // Staring the UDP server
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            System.out.println("DNS Server is running ... ");

            while (true) {
                // Receive a datagram by creating a bucket
                byte[] buffer = new byte[1024];
                DatagramPacket receivingPacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivingPacket);

                // Get IP Address msg from domain name and sender's port, sender's ip address
                String domainName = new String(receivingPacket.getData(), 0, receivingPacket.getLength());
                String ipAddress = DNSLookup.getInstance().getIpAddress(domainName.trim().toLowerCase());
                InetAddress senderAddress = receivingPacket.getAddress();
                int senderPort = receivingPacket.getPort();

                // Send information to the sender
                sendIpAddressToClients(ipAddress, senderAddress, senderPort);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sending an ip address looked up from the Hashmap and send to the sender
     *
     * @param msg
     * @param senderAddr
     * @param senderPort
     */
    private void sendIpAddressToClients(String msg, InetAddress senderAddr, int senderPort) {
        try {
            // Sending back the ip address for those who sent a domain name
            DatagramPacket sendingPacket = new DatagramPacket(msg.getBytes(), msg.length());
            sendingPacket.setAddress(senderAddr);
            sendingPacket.setPort(senderPort);
            serverSocket.send(sendingPacket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Testing a server
     *
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();

    }
}
