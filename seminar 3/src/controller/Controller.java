package controller;

import integration.*;
import model.*;

/**
 * Coordinates application logic between the view and model layers.
 */
public class Controller {
    private ExternalInventorySystem inventorySystem;
    private DiscountDBHandler discountHandler;
    private ExternalAccountingSystem accountingSystem;
    private Register register;
    private Sale currentSale;

    public Controller(ExternalInventorySystem inventorySystem,
                      DiscountDBHandler discountHandler,
                      ExternalAccountingSystem accountingSystem,
                      Register register) {
        this.inventorySystem = inventorySystem;
        this.discountHandler = discountHandler;
        this.accountingSystem = accountingSystem;
        this.register = register;
    }

    public void startSale() {
        currentSale = new Sale();
    }

    public void addItem(String itemID, int quantity) {
        Item item = inventorySystem.fetchItem(itemID);
        if (item != null) {
            currentSale.addItem(item, quantity);
        } else {
            System.out.println("Item not found: " + itemID);
        }
    }

    public double identifyCustomer(int customerID) {
        double discount = discountHandler.getDiscount(customerID, currentSale.getTotal());
        currentSale.applyDiscount(discount);
        return discount;
    }

    public void endSale() {
        currentSale.completeSale();
    }

    public Receipt registerPayment(double amountPaid, String paymentMethod) {
        Receipt receipt = currentSale.createReceipt();
        receipt.setPayment(amountPaid);
        register.addPayment(amountPaid);
        accountingSystem.recordSale(receipt);
        inventorySystem.updateInventory(currentSale);
        return receipt;
    }
}