package model.exceptions;

/**
 * Thrown when the requested item ID does not exist in the inventory.
 */
public class ItemNotFoundException extends Exception {
    private final String itemID;

    public ItemNotFoundException(String itemID) {
        super("Item with ID " + itemID + " was not found in inventory.");
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }
}
