/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.client;

import projects.chatroom_clientserver.server.ServerSide;

/**
 *
 * @author duyvu
 */
public class ChatRoomClient {

    public static void main(String[] args) {
        int serverPort = ServerSide.SERVER_PORT;
        String serverIP = ServerSide.SERVER_IP;

        ClientSide client = new ClientSide(serverPort, serverIP);
        client.startClient();
    }
}
