/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A purpose of this class is to implement a thread listens to the upcoming message continously
 *
 * @author duyvu
 */
public class ClientListener implements Runnable {

    private BufferedReader reader;
    private Socket clientSocket;

    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            try {
                if (reader.ready()) {
                    String msg = reader.readLine();
                    System.out.println("\n" + msg);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
