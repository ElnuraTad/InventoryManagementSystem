public class Product {
    String name;
    int quantity;
    double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getStatus() {
        if (quantity == 0) return "Out of Stock";
        else if (quantity < 5) return "Low Stock";
        else return "In Stock";
    }

    public double getTotalValue() {
        return quantity * price;
    }
}
