package view;

import controller.Controller;
import model.Receipt;

import java.util.Scanner;

/**
 * Handles user input and output in a console-based interface.
 */
public class View {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public View(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("--- Welcome to the POS System ---");
        controller.startSale();

        while (true) {
            System.out.print("Enter item ID (or 'done'): ");
            String itemID = scanner.nextLine();
            if (itemID.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            controller.addItem(itemID, quantity);
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