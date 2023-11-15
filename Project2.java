// This project takes an input file and uses the data from it to convert it into different objects.
// You are able to store the data into an array where you can display and sort the information.

import java.io.*;
import java.util.Scanner;

public class Rewritten {
    public static void main(String[] args) throws IOException {
    	// Making sure the array size does not exceed 100
        final int MAX_ITEMS = 100;
        // Creating the object Inventory with the 
        Item_DL425467[] inventory = new Item_DL425467[MAX_ITEMS];
        // Variable used to count items in the array
        int itemCount = 0;
        // Flag for continuation of program
        boolean running = true;
        
        // Read inventory data from "inv.txt"
        File file = new File("inv.txt");
        Scanner scanner = new Scanner(file);
        Scanner keyboard = new Scanner(System.in);

        int index = 0;
        // Reading inventory data & making sure the items do not exceed 100
        while (scanner.hasNext() && index < MAX_ITEMS) {
        	// Reads the line from the file and split them into strings with commas
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            
            // Take the data stored from name, price, and amount and trim from the array
            String name = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            int quantity = Integer.parseInt(parts[2].trim());
            
            // New object with the new information
            inventory[index] = new Item_DL425467(name, price, quantity);
            
            // Keeping track the item and index count
            itemCount++;
            index++;
        }
        // Loop for the program
        while (running) {
        	// Display menu
            showMenu();
            // Assigns user's choice to a variable
            int userChoice = getUserChoice(keyboard);
            // Switch Case to go to the method when the user choice matches the case
            switch (userChoice) {
                case 1:
                    showInventory(inventory, itemCount); // Show Inventory
                    break;
                case 2:
                    showInventoryValue(inventory, itemCount); // Show Inventory Value
                    break;
                case 3:
                    sortByPriceLowToHigh(inventory, itemCount); // Sort from lowest to highest (price)
                    showInventory(inventory, itemCount);
                    break;
                case 4:
                    sortByName(inventory, itemCount); // Sort by name
                    showInventory(inventory, itemCount);
                    break;
                case 5:
                    searchItemByName(inventory, itemCount, keyboard); // Search a specific item
                    break;
                case 6:
                    running = false; // Quit program
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option."); // Invalid user choice
            }
        }
    }

    // Display all menu options 
    public static void showMenu() {
        System.out.println("Inventory Menu:");
        System.out.println("1. Show the inventory");
        System.out.println("2. Calculate the value of inventory");
        System.out.println("3. Show the inventory sorted from lowest to highest price");
        System.out.println("4. Show the inventory sorted by item's name");
        System.out.println("5. Find an item by name");
        System.out.println("6. Quit");
    }
    
    // Method to take the user's choice from the menu
    public static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validChoice = false;
       
        while (!validChoice) { // While validChoice is true
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 6) { // Making sure it's between the valid options 1-6
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.next();
            }
        }

        return choice;
    }
    // Method to display the inventory items
    public static void showInventory(Item_DL425467[] inventory, int itemCount) {
        System.out.println("Inventory List: ");
        System.out.println("=====================================");
        // Loop until all items are shown
        for (int i = 0; i < itemCount; i++) { 
            System.out.println(inventory[i]);
        }
        System.out.println("=====================================");
    }
    // Calculate the inventory value and displays the value
    public static void showInventoryValue(Item_DL425467[] inventory, int itemCount) {
        double totalValue = 0.0;
        // Loop to calculate all the value stored in the inventory
        for (int i = 0; i < itemCount; i++) {
            totalValue += inventory[i].getPrice() * inventory[i].getAmount();
        }
        System.out.printf("The total value of the inventory is: $%.2f%n", totalValue);
    }
    // Method to sort the items out from highest to lowest
    public static void sortByPriceLowToHigh(Item_DL425467[] inventory, int itemCount) {
        // Bubble sort algorithm from lowest to highest
        for (int i = 0; i < itemCount - 1; i++) {
            for (int j = 0; j < itemCount - i - 1; j++) {
                if (inventory[j].getPrice() > inventory[j + 1].getPrice()) {
                    // Swap wrong order items
                    Item_DL425467 temp = inventory[j];
                    inventory[j] = inventory[j + 1];
                    inventory[j + 1] = temp;
                }
            }
        }
    }
    // Method to sort the items alphabetically 
    public static void sortByName(Item_DL425467[] inventory, int itemCount) {
        // Bubble sort algorithm for item's name alphabetically
        for (int i = 0; i < itemCount - 1; i++) {
            for (int j = 0; j < itemCount - i - 1; j++) {
                if (inventory[j].getName().compareTo(inventory[j + 1].getName()) > 0) {
                    // Swap wrong order items
                    Item_DL425467 temp = inventory[j];
                    inventory[j] = inventory[j + 1];
                    inventory[j + 1] = temp;
                }
            }
        }
    }

    // Method to search item by exact name
    public static void searchItemByName(Item_DL425467[] inventory, int itemCount, Scanner scanner) {
        System.out.print("Enter the item name you want to search for: ");
        String searchName = scanner.next(); // User's item name
        
        // Loop to search through every item until Item is found
        for (int i = 0; i < itemCount; i++) {
            if (inventory[i].getName().equalsIgnoreCase(searchName)) {
                System.out.println("Item found:");
                System.out.println(inventory[i]);
                return;
            }
        }
        System.out.println("Item not found."); // If not found
    }
}
