package controller;

import integration.*;
import model.*;
import model.exceptions.DatabaseFailureException;
import model.exceptions.ItemNotFoundException;
import util.RevenueObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates application logic between the view and model layers.
 */
public class Controller {
    private ExternalInventorySystem inventorySystem;
    private DiscountDBHandler discountHandler;
    private ExternalAccountingSystem accountingSystem;
    private Register register;
    private Sale currentSale;

    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * Creates a new controller coordinating the main POS system logic.
     *
     * @param inventorySystem   Simulated external inventory system.
     * @param discountHandler   Handles discount lookups.
     * @param accountingSystem  Simulated external accounting system.
     * @param register          The register handling payments.
     */
    public Controller(ExternalInventorySystem inventorySystem,
                      DiscountDBHandler discountHandler,
                      ExternalAccountingSystem accountingSystem,
                      Register register) {
        this.inventorySystem = inventorySystem;
        this.discountHandler = discountHandler;
        this.accountingSystem = accountingSystem;
        this.register = register;
    }

    /**
     * Begins a new sale session.
     */
    public void startSale() {
        currentSale = new Sale();
    }

    /**
     * Adds an item to the current sale.
     *
     * @param itemID   The ID of the item.
     * @param quantity The quantity to add.
     * @throws ItemNotFoundException      If the item ID is not found in inventory.
     * @throws DatabaseFailureException   If the inventory system is unavailable.
     */
    public void addItem(String itemID, int quantity) throws ItemNotFoundException, DatabaseFailureException {
        Item item = inventorySystem.fetchItem(itemID);
        currentSale.addItem(item, quantity);
    }

    /**
     * Identifies a customer and applies any available discount.
     *
     * @param customerID The ID of the customer.
     * @return The discount applied.
     */
    public double identifyCustomer(int customerID) {
        double discount = discountHandler.getDiscount(customerID, currentSale.getTotal());
        currentSale.applyDiscount(discount);
        return discount;
    }

    /**
     * Finalizes the sale before payment.
     */
    public void endSale() {
        currentSale.completeSale();
    }

    /**
     * Registers payment and returns the generated receipt.
     *
     * @param amountPaid    The amount paid by the customer.
     * @param paymentMethod The payment method (e.g., cash, card).
     * @return The final receipt.
     */
    public Receipt registerPayment(double amountPaid, String paymentMethod) {
        Receipt receipt = currentSale.createReceipt();
        receipt.setPayment(amountPaid);

        register.addPayment(amountPaid);
        accountingSystem.recordSale(receipt);
        inventorySystem.updateInventory(currentSale);

        totalRevenue += receipt.getTotalWithVAT();
        notifyRevenueObservers();

        return receipt;
    }

    /**
     * Registers an observer to be notified of total revenue changes.
     *
     * @param observer The observer to register.
     */
    public void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Notifies all registered observers with the current total revenue.
     */
    private void notifyRevenueObservers() {
        for (RevenueObserver obs : revenueObservers) {
            obs.newRevenue(totalRevenue);
        }
    }
}
