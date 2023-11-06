import java.util.Scanner;
import java.io.File;

public class ItemDriver {
	private String name;
	private double price;
	private int amount;
	
	public Item_DL425467(String nm, double pr, int am) {
		this.name = nm;
		this.price = pr;
		this.amount = am;
		
	}
	public String getName() {
		return name;
		
	}
	public double getPrice() {
		return price;
		
	}
	public int getAmount() {
		return amount;
		
	}
	public String toString() {
		return "Item: " + name + "\nPrice: $" + price + "\nAmount: " + amount;
		
	}
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		
		System.out.println("===============================================");
		System.out.println("Enter your item's information:");
		System.out.println("===============================================");
		System.out.print("Name: ");
		String name = keyboard.nextLine();
		
		System.out.print("Price: $");
		double price = keyboard.nextDouble();
		
		System.out.print("Amount: ");
		int amount = keyboard.nextInt();
		
		Item_DL425467 newItem = new Item_DL425467(name, price, amount);
		System.out.println("===============================================");
		System.out.println("Created: \n" + newItem.toString());
		System.out.println("===============================================");
		
		
	}
	
}
