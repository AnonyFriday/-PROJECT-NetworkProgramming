/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._tcp_multithread_clientserver.server;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A single thread server only for an individual request
 *
 * @author duyvu
 */
public class ServiceThread extends Thread {

    // =================================
    // == Fields
    // =================================
    private Socket socket;  // Client socket connecting with the server 
    private QuoteService quoteService;

    public ServiceThread(Socket socket) {
        this.socket = socket;
        quoteService = new QuoteService();
    }

    @Override
    public void run() {

        try {
            // Access the input and output stream of the client socket\
            OutputStream outputStream = socket.getOutputStream();

            // Receive the message from the client
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            byte[] requestBuffer = new byte[100];

            if (bis.read(requestBuffer) != -1) {
                // Getting product from the client
                String product = new String(requestBuffer).trim();
                String price = quoteService.getQuote(product);

                // Check if price is not null
                if (price == null) {
                    price = "\t+ The product info is invalid";
                }

                // Send back the price to the client 
                outputStream.write(price.getBytes());

                System.out.println("Response sent to client ...");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServiceThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the connection
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
