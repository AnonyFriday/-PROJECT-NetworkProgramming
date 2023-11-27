/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.client_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of this application is to demonstrate the server side socket
 *
 * @author duyvu
 */
public class ServerSide {

    public final static int SERVER_PORT = 9999;
    public final static String SERVER_IP = "localhost";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            try (Socket clientSocket = serverSocket.accept()) {
                // Access the input and output stream of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                // Receive the response from the client
                byte buffer[] = new byte[1024];
                inputStream.read(buffer);
                System.out.println("Client: Received from client . " + new String(buffer).trim());
                outputStream.write("Server: Hello from Server ...".getBytes());
                // Close the
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
