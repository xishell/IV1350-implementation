package model;

import java.util.*;

/**
 * Represents an ongoing or completed sale.
 */
public class Sale {
    private List<SaleLineItem> items = new ArrayList<>();
    private boolean isComplete = false;
    private double discountAmount = 0;

    public void addItem(Item item, int quantity) {
        for (SaleLineItem sli : items) {
            if (sli.getItem().getId().equals(item.getId())) {
                int updatedQty = sli.getQuantity() + quantity;
                items.set(items.indexOf(sli), new SaleLineItem(item, updatedQty));
                return;
            }
        }
        items.add(new SaleLineItem(item, quantity));
    }

    public double getTotal() {
        return items.stream().mapToDouble(SaleLineItem::getLineTotal).sum() - discountAmount;
    }

    public double getVATTotal() {
        return items.stream().mapToDouble(SaleLineItem::getLineVATTotal).sum();
    }

    public void applyDiscount(double amount) {
        this.discountAmount = amount;
    }

    public void completeSale() {
        this.isComplete = true;
    }

    public Receipt createReceipt() {
        return new Receipt(new Date(), items, getTotal(), getVATTotal());
    }
    public List<SaleLineItem> getItems() {
        return items;
    }
}