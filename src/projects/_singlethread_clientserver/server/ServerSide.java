/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._singlethread_clientserver.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This application demo the concept of singlethread application
 * Drawback:
 * - Since the application runs only in 1 single thread, it stucks
 * when the client does not enter any input
 * - Other clients connects to the server will be blocked
 *
 * @author duyvu
 */
public class ServerSide {
    // ============================
    // = Fields
    // ============================

    public final static int SERVER_PORT = 9999;
    public final static String SERVER_IP = "localhost";
    private final static QuoteService quoteService = new QuoteService();

    // ============================
    // = Main function for sending request and get response from 1 client
    // ============================
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            while (true) {
                System.out.println("Waiting for client ...");
                // Listen for the connection to this socket and accept it 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted: " + clientSocket);

                // Access the input and output stream of the client socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                // Receive the message from the client
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                byte[] request = new byte[100];

                if (bis.read(request) != -1) {
                    // Getting product from the client
                    String product = new String(request).trim();
                    String price = quoteService.getQuote(product);

                    // Check if price is not null
                    if (price == null) {
                        price = "\t+ The product info is invalid";
                    }

                    // Send back the price to the client 
                    outputStream.write(price.getBytes());

                    System.out.println("Response sent to client ...");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(projects._nothread_clientserver.ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
