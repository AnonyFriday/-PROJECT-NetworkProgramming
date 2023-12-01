/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_clientserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class demonstrates UDP connection of the client to Server
 *
 * @author duyvu
 */
public class Client {

    public final static int SERVER_PORT = 9000;
    public final static String SERVER_IP = "localhost";
    public final static byte[] BUFFER = new byte[4096];

    public static void startClient() {
        try {

            // Create the UDP socket for client
            DatagramSocket ds = new DatagramSocket();

            System.out.println("Client started");

            while (true) {
                System.out.println("Enter your message: ");
                String input = new Scanner(System.in).nextLine();

                // Construct the buffer for sending
                byte[] data = input.getBytes(StandardCharsets.UTF_8);

                // Create the datagram for sending
                DatagramPacket dp = new DatagramPacket(data, data.length);
                dp.setSocketAddress(new InetSocketAddress(SERVER_IP, SERVER_PORT));
                ds.send(dp);
            }

        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
