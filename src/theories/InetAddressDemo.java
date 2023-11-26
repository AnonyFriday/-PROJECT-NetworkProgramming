/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package theories;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class InetAddressDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String domain = "www.google.com";
        try {
            InetAddress address = InetAddress.getByName(domain);

            // Get domain
            System.out.println("Domain: " + domain);

            // Get IP address
            System.out.println("IP: " + address.getHostAddress());

            // Get IP address and domain of the localhost
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("IP of localhost: " + localHost.getHostAddress());
            System.out.println("Domain of localhost: " + localHost.getHostName());

        } catch (UnknownHostException ex) {
            Logger.getLogger(InetAddressDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
