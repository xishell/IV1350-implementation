package integration;

import model.Item;
import model.exceptions.ItemNotFoundException;
import model.exceptions.DatabaseFailureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {

    @Test
    public void testFetchItemExists() {
        ExternalInventorySystem inventory = ExternalInventorySystem.getInstance();

        try {
            Item item = inventory.fetchItem("1001");
            assertNotNull(item);
            assertEquals("Milk", item.getDescription());
        } catch (ItemNotFoundException | DatabaseFailureException e) {
            fail("Should not have thrown exception: " + e.getMessage());
        }
    }

    @Test
    public void testFetchItemThrowsItemNotFoundException() {
        ExternalInventorySystem inventory = ExternalInventorySystem.getInstance();

        assertThrows(ItemNotFoundException.class, () -> {
            inventory.fetchItem("9999"); // Item not in database
        });
    }

    @Test
    public void testFetchItemThrowsDatabaseFailureException() {
        ExternalInventorySystem inventory = ExternalInventorySystem.getInstance();

        assertThrows(DatabaseFailureException.class, () -> {
            inventory.fetchItem("999"); // Special ID that simulates DB failure
        });
    }
}
