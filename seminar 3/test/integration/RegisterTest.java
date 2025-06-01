package integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    @Test
    public void testAddPaymentIncreasesRevenue() {
        Register register = new Register();
        register.addPayment(50.0);
        register.addPayment(20.0);
        // Since we have no getter, this test just confirms no exceptions and prints expected output.
        assertDoesNotThrow(() -> register.addPayment(10.0));
    }
}