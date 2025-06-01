package model;

/**
 * Represents one line in a sale: a specific item and quantity.
 */
public class SaleLineItem {
    private Item item;
    private int quantity;

    public SaleLineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getLineTotal() {
        return item.getPrice() * quantity;
    }

    public double getLineVATTotal() {
        return getLineTotal() * item.getVATRate();
    }

    public Item getItem() { return item; }
    public int getQuantity() { return quantity; }
}