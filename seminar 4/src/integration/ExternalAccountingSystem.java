package integration;

import model.Receipt;

/**
 * Simulates integration with an external accounting system.
 */
public class ExternalAccountingSystem {
    public void recordSale(Receipt receipt) {
        System.out.println("Recording sale in external accounting system.");
    }
}