/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._udp_dns_resolver.server;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duyvu
 */
public class DNSLookup {

    // =================================
    // == Constructor
    // =================================
    public Map<String, String> dnsMapping;

    // =================================
    // == Constructor (Singleton)
    // =================================
    private static DNSLookup dns_instance = null;

    // Setting some default domain name
    private DNSLookup() {
        dnsMapping.put("www.google.com", "8.8.8.8");
        dnsMapping.put("www.example1.com", "192.168.1.2");
        dnsMapping.put("www.example2.com", "192.168.1.12");
    }

    // =================================
    // == Methods
    // =================================
    /**
     * Get the only instance of the class DNSLookup
     *
     * @return an instance of the class
     */
    public static DNSLookup getInstance() {
        if (dns_instance == null) {
            dns_instance = new DNSLookup();
        }
        return dns_instance;
    }
}
