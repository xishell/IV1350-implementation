package controller;

import integration.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller(
                new ExternalInventorySystem(),
                new DiscountDBHandler(),
                new ExternalAccountingSystem(),
                new Register()
        );
        controller.startSale();
    }

    @Test
    public void testAddItemAndTotal() {
        controller.addItem("1001", 2);
        Receipt receipt = controller.registerPayment(5.0, "cash");

        assertNotNull(receipt);
        assertEquals(1, receipt.getPurchasedItems().size());
        assertEquals("Milk", receipt.getPurchasedItems().get(0).getItem().getDescription());
        assertEquals(2, receipt.getPurchasedItems().get(0).getQuantity());
    }

    @Test
    public void testIdentifyCustomerAppliesDiscount() {
        controller.addItem("1002", 2);
        double discount = controller.identifyCustomer(12345);
        assertEquals(0.5, discount, 0.001);
    }
}