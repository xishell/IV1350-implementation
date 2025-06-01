package integration;

/**
 * Handles internal register for tracking received payments.
 */
public class Register {
    private double totalRevenue;

    public void addPayment(double amount) {
        totalRevenue += amount;
        System.out.println("Payment registered. Total revenue: " + totalRevenue);
    }
}