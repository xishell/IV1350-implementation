package startup;

import controller.Controller;
import integration.*;
import view.View;

/**
 * Starts the application by wiring components and launching the user interface.
 */
public class Main {
    public static void main(String[] args) {
        // Create external system stubs and register
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();
        DiscountDBHandler discountHandler = new DiscountDBHandler();
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        Register register = new Register();

        // Create controller and view
        Controller controller = new Controller(inventorySystem, discountHandler, accountingSystem, register);
        View view = new View(controller);

        // Start the POS session
        view.start();
    }
}
