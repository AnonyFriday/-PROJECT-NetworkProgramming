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
    // == Constructor
    // =================================
    private static DNSLookup dns_instance = null;

    private DNSLookup() {
    }

    public static DNSLookup getInstance() {
        if (dns_instance == null) {
            dns_instance = new DNSLookup();
        }
        return dns_instance;
    }

}
