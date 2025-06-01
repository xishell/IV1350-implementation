package model.discounts;

/**
 * Strategy interface for discount calculation.
 */
public interface DiscountStrategy {
    double calculateDiscount(double totalPrice);
}

