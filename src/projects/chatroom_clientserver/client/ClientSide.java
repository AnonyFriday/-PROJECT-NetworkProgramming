/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class ClientSide {

    // ======================================
    // = Fields
    // ======================================
    private Socket clientSocket;
    private String serverIP;
    private int serverPort;

    private PrintWriter writer;
    private BufferedReader reader;
    private final Scanner sc = new Scanner(System.in);

    // ======================================
    // = Constructor
    // ======================================
    public ClientSide(int serverPort,
                      String serverIP) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    /**
     * A function to create a client and connect to the server
     */
    public void startClient() {

        try {
            clientSocket = new Socket();
            clientSocket.bind(new InetSocketAddress(serverIP, serverPort));

            this.writer = new PrintWriter(clientSocket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            boolean isDisconnect = false;

            do {
                System.out.println("Client");
                String msg = sc.nextLine();
            } while (!isDisconnect);
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
