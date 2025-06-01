package controller;

import integration.*;
import model.*;
import model.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.RevenueObserver;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setup() {
        ExternalInventorySystem inventory = ExternalInventorySystem.getInstance();
        DiscountDBHandler discountHandler = new DiscountDBHandler();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        Register register = new Register();
        controller = new Controller(inventory, discountHandler, accountingSystem, register);
        controller.startSale();
    }

    @Test
    public void testItemNotFoundExceptionThrown() {
        String unknownID = "404";
        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            controller.addItem(unknownID, 1);
        });
        assertTrue(exception.getMessage().contains(unknownID));
    }

    @Test
    public void testDatabaseFailureExceptionThrown() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.addItem("999", 1);
        });
    }

    @Test
    public void testRevenueObserverIsNotified() {
        AtomicReference<Double> lastReportedRevenue = new AtomicReference<>(0.0);

        RevenueObserver mockObserver = lastReportedRevenue::set;
        controller.addRevenueObserver(mockObserver);

        try {
            controller.addItem("1001", 1); // Milk = 1.00 + 12% = 1.12
        } catch (Exception e) {
            fail("Setup item should exist.");
        }

        controller.endSale();
        controller.registerPayment(10.0, "cash");

        assertTrue(lastReportedRevenue.get() > 0.0);
    }
}
