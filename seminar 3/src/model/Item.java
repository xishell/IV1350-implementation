package model;

/**
 * Represents an item available for sale.
 */
public class Item {
    private String id;
    private String description;
    private double price;
    private double vatRate;

    public Item(String id, String description, double price, double vatRate) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getVATRate() { return vatRate; }
}