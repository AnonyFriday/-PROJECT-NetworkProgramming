/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_dns_resolver.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import projects._udp_dns_resolver.server.Server;

/**
 * @author duyvu
 */
public class Client {

    // =================================
    // == Fields
    // =================================
    private DatagramSocket clientSocket;
    private Scanner sc;

    // =================================
    // == Constructor
    // =================================
    public Client() {
        try {
            clientSocket = new DatagramSocket();
            sc = new Scanner(System.in);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Start a client
     */
    public void startClient() {
        while (true) {
            try {
                System.out.println("Lookup IP address based on domain name:");
                String domainNameRequest = sc.nextLine();

                // Send a request to a server
                InetAddress serverAddress = InetAddress.getByName(Server.SERVER_IP);
                sendMsgToServer(domainNameRequest, serverAddress, Server.SERVER_PORT);

                // Receive a response from a server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                // Print result to the screen
                String ip = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("IP: " + ip);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * A function to send request to server
     *
     * @param msg
     * @param serverAddress
     * @param port
     * @throws IOException
     */
    public void sendMsgToServer(String msg, InetAddress serverAddress, int port) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.length());
        sendPacket.setPort(port);
        sendPacket.setAddress(serverAddress);
        clientSocket.send(sendPacket);
    }

    /**
     * A main function for testing
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
