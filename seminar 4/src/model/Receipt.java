package model;

import java.util.*;

/**
 * Represents a printed receipt for a completed sale.
 */
public class Receipt {
    private Date saleDateTime;
    private List<SaleLineItem> purchasedItems;
    private double totalPrice;
    private double vatTotal;
    private double amountPaid;
    private double change;

    public Receipt(Date saleDateTime, List<SaleLineItem> purchasedItems, double totalPrice, double vatTotal) {
        this.saleDateTime = saleDateTime;
        this.purchasedItems = purchasedItems;
        this.totalPrice = totalPrice;
        this.vatTotal = vatTotal;
    }

    public void setPayment(double amountPaid) {
        this.amountPaid = amountPaid;
        this.change = amountPaid - totalPrice;
    }

    public void printReceiptInfo() {
        System.out.println("Date: " + saleDateTime);
        for (SaleLineItem item : purchasedItems) {
            System.out.printf("%s x%d = %.2f\n", item.getItem().getDescription(), item.getQuantity(), item.getLineTotal());
        }
        System.out.printf("Total (incl. VAT): %.2f\n", totalPrice);
        System.out.printf("VAT: %.2f\n", vatTotal);
        System.out.printf("Paid: %.2f\nChange: %.2f\n", amountPaid, change);
    }

    public List<SaleLineItem> getPurchasedItems() {
        return purchasedItems;
    }

    public double getTotalWithVAT() {
        return totalPrice;
    }
}
