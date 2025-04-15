import java.util.Scanner;

public class InventorySystem {
    Product[] products = new Product[50];
    int count = 0;
    Scanner input = new Scanner(System.in);

    public void addProducts() {
        System.out.print("Enter the number of products: ");
        count = input.nextInt();
        input.nextLine(); // consume newline

        for (int i = 0; i < count; i++) {
            System.out.print("Enter product name: ");
            String name = input.nextLine();

            int quantity;
            do {
                System.out.print("Enter quantity: ");
                quantity = input.nextInt();
                if (quantity < 0) System.out.println("Quantity must be positive.");
            } while (quantity < 0);

            double price;
            do {
                System.out.print("Enter price: ");
                price = input.nextDouble();
                if (price < 0) System.out.println("Price must be positive.");
            } while (price < 0);

            input.nextLine(); // consume newline
            products[i] = new Product(name, quantity, price);
        }
    }

    public void displayInventory() {
        double totalValue = 0;
        int maxIndex = 0;
        int minIndex = 0;

        System.out.println("\nInventory Summary:");
        System.out.println("----------------------------------");

        for (int i = 0; i < count; i++) {
            Product p = products[i];
            System.out.printf("Product: %s | Price: $%.2f | Quantity: %d | %s\n",
                    p.name, p.price, p.quantity, p.getStatus());

            totalValue += p.getTotalValue();

            if (p.price > products[maxIndex].price) maxIndex = i;
            if (p.price < products[minIndex].price) minIndex = i;
        }

        System.out.println("----------------------------------");
        System.out.printf("Total Inventory Value: $%.2f\n", totalValue);
        System.out.printf("Most Expensive Product: %s ($%.2f)\n", products[maxIndex].name, products[maxIndex].price);
        System.out.printf("Least Expensive Product: %s ($%.2f)\n", products[minIndex].name, products[minIndex].price);
    }

    public void searchProduct() {
        System.out.print("\nEnter product name to search: ");
        String searchName = input.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].name.equalsIgnoreCase(searchName)) {
                Product p = products[i];
                System.out.printf("Found: %s | Price: $%.2f | Quantity: %d | %s\n",
                        p.name, p.price, p.quantity, p.getStatus());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public void updateStock() {
        System.out.print("\nEnter product name to update stock: ");
        String name = input.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].name.equalsIgnoreCase(name)) {
                System.out.print("Enter new quantity: ");
                int newQty = input.nextInt();
                input.nextLine(); // consume newline
                if (newQty >= 0) {
                    products[i].quantity = newQty;
                    System.out.println("Stock updated.");
                } else {
                    System.out.println("Invalid quantity.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public void showLowStock() {
        System.out.println("\nLow-stock Products (Quantity < 5):");
        for (int i = 0; i < count; i++) {
            if (products[i].quantity < 5) {
                Product p = products[i];
                System.out.printf("%s | Price: $%.2f | Quantity: %d\n", p.name, p.price, p.quantity);
            }
        }
    }

    public void run() {
        addProducts();
        displayInventory();

        int choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("1. Search Product");
            System.out.println("2. Update Stock");
            System.out.println("3. Show Low-stock Products");
            System.out.println("4. Show Inventory Summary");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1: searchProduct(); break;
                case 2: updateStock(); break;
                case 3: showLowStock(); break;
                case 4: displayInventory(); break;
                case 0: System.out.println("Exiting system."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
