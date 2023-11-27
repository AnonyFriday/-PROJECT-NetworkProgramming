/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._multithread_clientserver.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
        Scanner sc = new Scanner(System.in);

        try (Socket clientSocket = new Socket()) {

            // Create the connection to the ip address "localhost" with port 9999
            clientSocket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), 100);

            // Access the input and output stream
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Enter the product info and sends to the server
            System.out.print("Enter your product info: ");
            String product = sc.nextLine();

            outputStream.write(product.getBytes(StandardCharsets.UTF_8));

            // Reading data response back from the server
            byte[] response = new byte[1028];
            inputStream.read(response);
            System.out.println(new String(response).trim());

        } catch (IOException ex) {
            Logger.getLogger(projects._nothread_clientserver.ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
