/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class URLCheckWebsiteAlive {

    public static void main(String[] args) {
        String[] websites = {
            "https://titv.vn",
            "https://vnexpress1.net",
            "https://google.com"
        };

        for (String website : websites) {
            checkWebsite(website);
        }
    }

    public static void checkWebsite(String urlString) {
        try {
            URL url = URI.create(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            /**
             * Http Code
             * 200: OK
             * 401: Unauthorized
             * 403: Forbidden
             * 500: Internal Server Error
             * 404: Not Found
             */
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("This website is active");
            }

        } catch (Exception es) {
            System.out.println(urlString + " Cannot connect!");
        }
    }
}
