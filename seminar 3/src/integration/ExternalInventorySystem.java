package integration;

import model.*;
import java.util.*;

/**
 * Simulates integration with an external inventory system.
 */
public class ExternalInventorySystem {
    private Map<String, Item> itemDB = new HashMap<>();

    public ExternalInventorySystem() {
        itemDB.put("1001", new Item("1001", "Milk", 1.00, 0.12));
        itemDB.put("1002", new Item("1002", "Cookies", 2.50, 0.12));
    }

    public Item fetchItem(String itemID) {
        return itemDB.get(itemID);
    }

    public void updateInventory(Sale sale) {
        System.out.println("Updating external inventory system.");
    }
}