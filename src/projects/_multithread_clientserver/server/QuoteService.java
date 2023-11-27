/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects._singlethread_clientserver.server;

import java.util.HashMap;
import java.util.Map;

/**
 * The class is created as the demo of quote service with synthetic data
 *
 * @author duyvu
 */
public class QuoteService {

    // =================================
    // == Fields
    // =================================
    private final Map<String, String> productInfos = new HashMap<>();

    // =================================
    // == Constructor
    // =================================
    public QuoteService() {
        productInfos.put("a", "$100");
        productInfos.put("b", "$200");
        productInfos.put("c", "$300");
        productInfos.put("d", "$400");
    }

    // =================================
    // == Methods
    // =================================
    /**
     * A function to return the quote based on product info
     *
     * @param productInfo: product name
     *
     * @return a quote
     */
    public String getQuote(String productInfo) {
        return productInfos.get(productInfo);
    }
}
