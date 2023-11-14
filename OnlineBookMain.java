package miniprojectjdbc;

import java.util.Scanner;

public class OnlineBookMain {
    public static void main(String[] args) {
        OnlineBookApp bookApp = new OnlineBookApp();
        Scanner scanner = new Scanner(System.in);
       System.out.println("======================================");
        System.out.println("  Welcome to the Online Book Store  ");
        System.out.println("======================================");
        String choice;
        do {
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Purchase a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.next();
                    System.out.print("Enter the author: ");
                    String author = scanner.next();
                    System.out.print("Enter the price: $");
                    double price = scanner.nextDouble();
                    System.out.print("Enter the stock quantity: ");
                    int stock = scanner.nextInt();
                    bookApp.addBook(title, author, price, stock);
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    bookApp.displayAllBooks();
                    break;

                case 3:
                    System.out.print("Enter the book ID you want to purchase: ");
                    int bookId = scanner.nextInt();
                    bookApp.purchaseBook(bookId);
                    break;

                case 4:
                    System.out.println("Thank you for visiting the Online Book Store!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.print("Do you want to continue (yes/no): ");
            choice = scanner.next();
        } while (choice.equalsIgnoreCase("yes"));

        scanner.close();
    }
}