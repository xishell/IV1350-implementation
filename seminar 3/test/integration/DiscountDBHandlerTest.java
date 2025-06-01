package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DiscountDBHandler class.
 */
public class DiscountDBHandlerTest {

    private DiscountDBHandler discountDBHandler;

    @BeforeEach
    public void setUp() {
        discountDBHandler = new DiscountDBHandler();
    }

    @Test
    public void testGetDiscount_KnownCustomer() {
        // Known customer 12345 has 10% discount
        double discount = discountDBHandler.getDiscount(12345, 100.0);
        assertEquals(10.0, discount, 0.001, "Should return 10% discount");
    }

    @Test
    public void testGetDiscount_KnownCustomerDifferentAmount() {
        // Discount should scale with the total amount
        double discount = discountDBHandler.getDiscount(12345, 200.0);
        assertEquals(20.0, discount, 0.001, "Should return 10% of 200.0");
    }

    @Test
    public void testGetDiscount_UnknownCustomer() {
        // Unknown customer should receive no discount
        double discount = discountDBHandler.getDiscount(99999, 100.0);
        assertEquals(0.0, discount, 0.001, "Should return 0 for unknown customer");
    }

    @Test
    public void testGetDiscount_ZeroAmount() {
        // Discount on zero total should be zero
        double discount = discountDBHandler.getDiscount(12345, 0.0);
        assertEquals(0.0, discount, 0.001, "Discount on 0 total should be 0");
    }
}
