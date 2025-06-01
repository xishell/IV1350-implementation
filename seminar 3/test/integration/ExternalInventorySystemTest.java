package integration;

import model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {

    @Test
    public void testFetchItemExists() {
        ExternalInventorySystem system = new ExternalInventorySystem();
        Item item = system.fetchItem("1001");
        assertNotNull(item);
        assertEquals("Milk", item.getDescription());
    }

    @Test
    public void testFetchItemDoesNotExist() {
        ExternalInventorySystem system = new ExternalInventorySystem();
        Item item = system.fetchItem("9999");
        assertNull(item);
    }
}