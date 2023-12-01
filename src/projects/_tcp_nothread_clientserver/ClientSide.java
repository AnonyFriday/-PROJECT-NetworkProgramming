/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._tcp_nothread_clientserver;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of this application is to demonstrate the client side socket
 *
 * @author duyvu
 */
public class ClientSide {

    // ============================
    // = Fields
    // ============================
    public final static String SERVER_IP = "localhost";
    public final static int SERVER_PORT = 9999;

    // ============================
    // = Main function for sending request and get response from server
    // ============================
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket()) {

            // Create the connection to the ip address "localhost" with port 9999
            clientSocket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), 100);

            // Access the input and output stream
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Sending message to server with a buffer of 100 bytes
            BufferedOutputStream bos = new BufferedOutputStream(outputStream, 128);

            // Send msg to server
            for (int i = 0; i <= 10; i++) {
                bos.write(("Client: Hello Vũ client..." + i + "\n").getBytes());
            }

            // Reading data from the server
            byte[] response = new byte[1028];
            inputStream.read(response);
            System.out.println("Client: Received from server (" + new String(response).trim() + ")");

        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
