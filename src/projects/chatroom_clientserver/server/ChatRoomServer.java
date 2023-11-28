/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.chatroom_clientserver.server;

/**
 *
 * @author duyvu
 */
public class ChatRoomServer {

    public static void main(String[] args) {
        ServerSide server = new ServerSide();
        server.startServer();
    }
}
