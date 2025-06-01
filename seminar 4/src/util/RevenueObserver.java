package util;

/**
 * Observer interface for receiving total revenue updates.
 */
public interface RevenueObserver {
    void newRevenue(double totalRevenue);
}
