package integration;

import model.*;
import model.exceptions.DatabaseFailureException;
import model.exceptions.ItemNotFoundException;

import java.util.*;

/**
 * Singleton: Simulates integration with an external inventory system.
 */
public class ExternalInventorySystem {
    private static final ExternalInventorySystem instance = new ExternalInventorySystem();

    private final Map<String, Item> itemDB = new HashMap<>();

    private ExternalInventorySystem() {
        itemDB.put("1001", new Item("1001", "Milk", 1.00, 0.12));
        itemDB.put("1002", new Item("1002", "Cookies", 2.50, 0.12));
    }

    public static ExternalInventorySystem getInstance() {
        return instance;
    }

    public Item fetchItem(String itemID) throws ItemNotFoundException, DatabaseFailureException {
        if ("999".equals(itemID)) {
            throw new DatabaseFailureException("Inventory system is unavailable.");
        }

        Item item = itemDB.get(itemID);
        if (item == null) {
            throw new ItemNotFoundException(itemID);
        }

        return item;
    }

    public void updateInventory(Sale sale) {
        System.out.println("Updating external inventory system.");
    }
}
