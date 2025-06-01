package view;

import util.RevenueObserver;

/**
 * Observer that displays total revenue to the console.
 */
public class TotalRevenueView implements RevenueObserver {
    @Override
    public void newRevenue(double totalRevenue) {
        System.out.printf("Total revenue (view): %.2f kr%n", totalRevenue);
    }
}
