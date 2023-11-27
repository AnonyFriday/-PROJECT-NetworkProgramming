/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._multithread_clientserver.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import projects._singlethread_clientserver.server.QuoteService;

/**
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
            // Access the input and output stream of the client socket
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

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

                // Close the connection
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServiceThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
