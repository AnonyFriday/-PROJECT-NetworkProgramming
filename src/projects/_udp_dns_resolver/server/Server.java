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
            // If calling the default socket, it will bounnd to the random port
            serverSocket = new DatagramSocket(null);
        } catch (SocketException ex) {
            ex.getStackTrace();
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

            while (!serverSocket.isClosed()) {
                // Receive a packet by creating a bucket size for limitation
                byte[] buffer = new byte[1024];
                DatagramPacket receivingPacket = new DatagramPacket(buffer, buffer.length);

                // This method will block until receive a packet from sender
                serverSocket.receive(receivingPacket);
                printClientInfo(receivingPacket);

                // Get IP Address msg from domain name and sender's port, sender's ip address
                InetAddress senderAddress = receivingPacket.getAddress();
                int senderPort = receivingPacket.getPort();

                String domainName = new String(receivingPacket.getData(), 0, receivingPacket.getLength());
                String ipAddressMsg = DNSLookup.getInstance().getIpAddress(domainName.trim().toLowerCase());

                if (ipAddressMsg == null) {
                    // Send Message not found to the sender
                    sendMsgToClient("IP address not found. Please try again.", senderAddress, senderPort);
                } else {
                    // Send information to the sender
                    sendMsgToClient(ipAddressMsg, senderAddress, senderPort);
                }
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
    private void sendMsgToClient(String msg, InetAddress senderAddr, int senderPort) {
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
     * Print the client's infor based on the datagram packet
     *
     * @param clientPacket
     */
    private void printClientInfo(DatagramPacket clientPacket) {
        System.out.println("Client IP: " + clientPacket.getAddress());
        System.out.println("Client Port: " + clientPacket.getPort());
        System.out.println("Client Domain Request: " + new String(clientPacket.getData(), 0, clientPacket.getLength()));
        System.out.println("---------------------------------------");
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
