/**
 * ICIS 201 Introduction to Computer Science>
 * Fall 2023
 * Lab Section <Lab section <Friday 11:40 A.M - 12:35 P.M>
 * David Lau
 * DL425467
 * dlau2@albany.edu
 *
 * This project takes an input file and uses the data from it to convert it into different objects.
 * You are able to store the data into an array where you can display and sort the information.
 * 
 */
import java.io.*;
import java.util.Scanner;

public class P2Driver_DL425467 {
	//Main method to show Menu
    public static void main(String[] args) throws IOException {

    	// Size of Array (Max)
        int arr_size = 100;
        // Array of items in inventory
        Item_DL425467[] inventory = new Item_DL425467[arr_size]; 
        // Variable for amount of items in array
        int act_size = 0; 
        // Variable for input's choice menu
        int userChoice; 
        // Variable to continue or exit
        String menuOrExit; 
        // Valid choice for menu option
        boolean validChoice = false; 
        // Loop Flag
        boolean running = true; 
        // Text File
        File file = new File("inv.txt");
        // Scanner for file
        Scanner scanner = new Scanner(file); 
        // Scanner for input
        Scanner keyboard = new Scanner(System.in); 
        // Variable for item to not exceed 100
        int i = 0; 

      //Loop to read the file
        while (scanner.hasNext() && i < 100) {
            // Reading & Assigning their values to their respective category
            String line = scanner.nextLine();
            String[] parts = line.split(","); // Split the line using commas
            String name = parts[0].trim(); // Name is the first part
            double price = Double.parseDouble(parts[1].trim()); // Price is the second part
            int amount = Integer.parseInt(parts[2].trim()); // Amount is the third part

            // Create objects and add to array with index i
            inventory[i] = new Item_DL425467(name, price, amount);
            // Count the actual amount of elements in the array
            act_size++;
            // Increment i for the index and max count of objects.
            i++;
        }

        // Loop Program until stopped
        while (running) {
            // Display Menu
            System.out.println("Inventory List :\n"
                    + "===================================================================\n"
                    + "1.  Show the inventory.\n"
                    + "2.  Calculate the value of inventory.\n"
                    + "3.  Show the inventory sorted from the lowest to the highest price. \n"
                    + "4.  Show the inventory sorted by the item's name.\n"
                    + "5.  Find an item by name.\n"
                    + "6.  Quit.\n"
                    + "===================================================================\n" );
       
            // Asking user's choice
            System.out.print("What would you like to do: ");
            // Validate the input
            do{
                userChoice = keyboard.nextInt(); //User input
                //Check if choice is valid
                if ((userChoice >= 1) && (userChoice <= 6)){
                    validChoice = true; }
                
                //Invalid Input
                else{
                    System.out.println("That is not an option.");
                    System.out.println("Please input a valid option: "); } }
                
            while (!validChoice);

            System.out.println(); //Print new line

            switch(userChoice) {
                // Show the inventory
                case 1:
                    P2Driver_DL425467.showInventory(inventory, act_size);
                    break;
                // Show the value of the inventory
                case 2:
                	P2Driver_DL425467.inventoryValue(inventory, act_size);
                    break;
                // Show the inventory sorted by price (Low to High)
                case 3:
                    showInventory(lowToHigh(inventory, act_size), act_size);
                    break;
                // Show the inventory sorted alphabetically
                case 4:
                    showInventory(byName(inventory, act_size), act_size);
                    break;
                // Search for an item
                case 5:
                	P2Driver_DL425467.itemSearch(inventory, act_size);
                    break;
                // End program
                case 6:
                    quitProgram();
                    break;
            }
            // If the user does not end program, ask if they would like to return to menu or exit
                System.out.println();
                System.out.print("'Y' to return to menu or 'N' to quit the program: ");  //Ask for input
                menuOrExit = keyboard.next(); //Get user input
                System.out.println();

                //If the user inputs '2', the loop will get set to false and end program
                if (menuOrExit.equalsIgnoreCase("N")) {
                    quitProgram();
                    //If input is invalid, return user to menu
                } else if (!menuOrExit.equalsIgnoreCase("Y")){
                    System.out.println("Invalid Input. Returning to the menu.");
                    System.out.println();
                }
                //If the user input is 1, the program returns to menu and does not display a message
            }

        }
    // Method to showInventory when prompt to
    public static void showInventory(Item_DL425467[] inv, int size){
        //Print out header for inventory
        System.out.println();
        System.out.println("========================================================================");
        System.out.println("   Listed By: Name                                                      ");    
        System.out.println("              Price                                                     ");
        System.out.println("              Amount                                                    ");
        System.out.println("========================================================================");

        //Traverse through the array and print out the items for each index
        for (int i = 0; i < size; i++){
            //Correct the format for single digit numbers
            String itemNumber = Integer.toString(i + 1);
            if (i < 9){
                itemNumber = itemNumber + " ";
            }
            //Print out numbers and the item
            System.out.println(itemNumber + "  " + inv[i].toString());
        }
        //Print out footer for inventory
        System.out.println("========================================================================");
    }
    // Method to showInventory Value when prompt to
    public static void inventoryValue(Item_DL425467[] inv, int size){
        //Variable for total
        double total = 0.00;
        //Traverse through array and add the price and inventory of each item to total
        for(int i = 0; i < size; i++){
            total += (inv[i].getPrice() * inv[i].getAmount());
        }
        //Print out message stating what the total value for the inventory is
        System.out.printf("The total value of the inventory is: $%.2f", total);
        System.out.println();
    }
    // Method to showInventory items from low to high when prompt to
    public static Item_DL425467[] lowToHigh(Item_DL425467[] inv, int size){
    	Item_DL425467 [] arr = inv.clone(); 
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if (arr[j].getPrice() > arr[j+1].getPrice()){
                	Item_DL425467 temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    // Method to showInventory items by Name when prompt to
    public static Item_DL425467[] byName(Item_DL425467[] inv, int size){
    	Item_DL425467 [] arr = inv.clone(); 
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if (arr[j].getName().compareToIgnoreCase(arr[j+1].getName()) > 0){
                	Item_DL425467 temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    // Method to find exact Item when prompt to
    public static int findItem(Item_DL425467[] arr, int size, String name){
        int first = 0;
        int last = size - 1;
        while (first <= last) {
            int middle = first + (last - first) / 2;
            int result = name.compareToIgnoreCase(arr[middle].getName());
            if (result == 0) {
                return middle;
            }
            else if (result > 0) {
                first = middle + 1;
            }
            else {
                last = middle - 1;
            }
        }
        return -1;
    }
    // Method to search item when prompt to
    public static void itemSearch(Item_DL425467[] inv, int size){
        inv = byName(inv, size);
        boolean searching = true;
        Scanner keyboard = new Scanner(System.in);
        char continueSearch;

        do {
            System.out.print("Enter the item's name that you looking for: ");
            String itemLookup = keyboard.next(); //Store input
            System.out.println();

            int foundItemIndex = findItem(inv,size, itemLookup);

            //If item is not found
            if (foundItemIndex == -1){
                System.out.println("Item does not exist or is incorrect.");
            }
            //Print out item information
            else{
                System.out.println("Product: " + inv[foundItemIndex].getName());
                System.out.printf("Price: $%.2f\n", inv[foundItemIndex].getPrice());
                System.out.println("Stock: " + inv[foundItemIndex].getAmount());
            }
            System.out.println(); //New line

            //Ask if they would like to search for another item
            System.out.print("Would you like to search again? [Y/N]: ");
            continueSearch = keyboard.next().charAt(0); //Get character input
            //If the user says yes
            if (continueSearch == 'Y' || continueSearch == 'y') {
                System.out.println();
            }
            //If the user says no
            else if (continueSearch == 'N' || continueSearch == 'n') {
                searching = false;
            }
            //If invalid input
            else {
                System.out.println("Invalid input. Ending item search.");
                searching = false;
            }
        } while (searching);
    }
    // Method to quit the program
    public static void quitProgram(){
        System.out.println("Stopping Program.");
        //End the program
        System.exit(0);
    }
}