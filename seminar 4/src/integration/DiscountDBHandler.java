package integration;

import model.discounts.DiscountStrategy;
import model.discounts.NoDiscount;
import model.discounts.PercentageDiscount;

/**
 * Returns appropriate discount strategies based on customer ID.
 */
public class DiscountDBHandler {

    public DiscountStrategy getStrategy(int customerID) {
        if (customerID == 12345) {
            return new PercentageDiscount(0.10); // 10%
        } else {
            return new NoDiscount();
        }
    }

    public double getDiscount(int customerID, double totalPrice) {
        return getStrategy(customerID).calculateDiscount(totalPrice);
    }
}
