/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The
 *
 * @author duyvu
 */
public class PortScanner {

    public static void main(String[] args) {
        checkPort("www.titv.com", 0, 900000);
    }

    public static void checkPort(String urlString,
            int startPort,
            int endPort) {
        System.out.format("Currently scanning all ports from %d to %d\n", startPort, endPort);

        for (int i = startPort; i <= endPort; i++) {
            try {
                /**
                 * Create the socket object at client and connect to server
                 * by passing urlString and check each server's ports
                 */
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(urlString, i), 1);
                System.out.println("Port: " + i + " opens");
                socket.close();
            } catch (Exception ex) {
            }
        }

        System.out.println("Scanning port completedly.");
    }
}
