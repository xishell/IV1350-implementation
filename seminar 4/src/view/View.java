package view;

import controller.Controller;
import model.Receipt;
import model.exceptions.DatabaseFailureException;
import model.exceptions.ItemNotFoundException;
import util.LogHandler;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles user input and output in a console-based interface.
 */
public class View {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
    private LogHandler logger;

    public View(Controller controller) {
        this.controller = controller;
        try {
            this.logger = new LogHandler();
        } catch (IOException e) {
            System.out.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public void start() {
        System.out.println("--- Welcome to the POS System ---");
        controller.startSale();

        while (true) {
            try {
                System.out.print("Enter item ID (or 'done'): ");
                String itemID = scanner.nextLine();
                if (itemID.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                controller.addItem(itemID, quantity);
                System.out.println("Item added.");

            } catch (ItemNotFoundException e) {
                System.out.println("UI: Item not found - " + e.getItemID());
                logger.logException(e);
            } catch (DatabaseFailureException e) {
                System.out.println("UI: Inventory system is currently unavailable. Please try later.");
                logger.logException(e);
            } catch (NumberFormatException e) {
                System.out.println("UI: Invalid input. Please enter a number for quantity.");
            } catch (Exception e) {
                System.out.println("UI: An unexpected error occurred.");
                logger.logException(e);
            }
        }

        System.out.print("Enter customer ID for discount: ");
        int customerID = Integer.parseInt(scanner.nextLine());
        double discount = controller.identifyCustomer(customerID);
        System.out.println("Eligible discount: " + discount);

        controller.endSale();

        System.out.print("Amount paid: ");
        double paid = Double.parseDouble(scanner.nextLine());
        Receipt receipt = controller.registerPayment(paid, "cash");

        displayReceipt(receipt);
    }

    public void displayReceipt(Receipt receipt) {
        System.out.println("\n--- RECEIPT ---");
        receipt.printReceiptInfo();
    }
}
