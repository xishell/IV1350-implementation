package integration;

/**
 * Simulates a database lookup for customer-specific discounts.
 */
public class DiscountDBHandler {
    public double getDiscount(int customerID, double total) {
        if (customerID == 12345) {
            return total * 0.10; // 10% discount
        }
        return 0;
    }
}