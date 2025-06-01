package view;

import util.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Observer that logs total revenue to a file.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String FILENAME = "total-revenue.txt";

    @Override
    public void newRevenue(double totalRevenue) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.printf("Total revenue: %.2f kr%n", totalRevenue);
        } catch (IOException e) {
            System.out.println("Could not write total revenue to file: " + e.getMessage());
        }
    }
}
