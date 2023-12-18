package projects._multicast_clientserver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;


/**
 * A server uses multicast protocol for data transmission to clients
 */
public class Server {

    public void startServer() {
        try {

            // 224.0.0.0 -> 239.255.255.255
            InetAddress groupIpAddress = InetAddress.getByName("232.0.0.1");
            MulticastSocket socket = new MulticastSocket();

            String message = "Hello from sender!";
            byte[] buffer = message.getBytes();

            // Set a thread to sleep for 1s sending a msg continuously
            while (true) {
                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, groupIpAddress, 6789);
                socket.send(sendPacket);
                Thread.sleep((long) Math.random() * 5);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
