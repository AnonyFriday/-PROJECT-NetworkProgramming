/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._nothread_clientserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of this application is to demonstrate the server side socket
 * - No threading supported
 * - Not multiclient connection supported
 *
 * @author duyvu
 */
public class ServerSide {

    // ============================
    // = Fields
    // ============================
    public final static int SERVER_PORT = 9999;
    public final static String SERVER_IP = "localhost";

    // ============================
    // = Main function for sending request and get response from 1 client
    // ============================
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            while (true) {

                // Listen for the connection to this socket and accept it 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted: " + clientSocket);

                // Access the input and output stream of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                // Receive the message from the client
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                byte[] buffer = new byte[100];
                int bytes;

                if ((bytes = bis.read(buffer)) != -1) {
                    // Print out the byte reads from the buffer
                    System.out.println(new String(buffer).trim() + "(" + bytes + ")");

                    // Send message to the client 
                    outputStream.write("Server: Hello from Server ...".getBytes());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
