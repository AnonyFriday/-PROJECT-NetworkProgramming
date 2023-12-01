package projects._tcp_multithread_clientserver.server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import projects._tcp_multithread_clientserver.server.ServiceThread;

/**
 * This application demo the concept of multithread application
 *
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
                System.out.println("\tWaiting for client ...");
                // Listen for the connection to this socket and accept it 
                Socket clientSocket = serverSocket.accept();

                // Create a new thread to service EACH client
                System.out.println("Client accepted: " + clientSocket + ".");
                System.out.println("Starting a thread serving the client.");

                // Conducting a multithread service wont block the server
                new ServiceThread(clientSocket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(projects._tcp_nothread_clientserver.ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
