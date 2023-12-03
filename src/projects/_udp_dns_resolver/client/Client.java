/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_dns_resolver.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import projects._udp_dns_resolver.server.Server;

/**
 *
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
                System.out.println("Nhập vào tên miền cần tìm IP:");
                String domainName = sc.nextLine();

                // Send a request to a server
                byte[] sendData = domainName.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
                sendPacket.setPort(Server.SERVER_PORT);
                sendPacket.setAddress(InetAddress.getByName(Server.SERVER_IP));
                clientSocket.send(sendPacket);

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
     * A main function for testing
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
