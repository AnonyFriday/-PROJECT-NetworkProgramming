/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class URLDemo {

    public static void main(String[] args) throws IOException {

        try {

            // Create URL object
            String urlString = "https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam";
            URL url = new URI(urlString).toURL();

            // Read content from the website
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (MalformedURLException e) {

        } catch (URISyntaxException ex) {
            Logger.getLogger(URLDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
