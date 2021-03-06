
package ProductManager;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    private double price;
    private String productName;
    private int quantity;
    private String supplier;
    private int id;

    private static int nextId = 0;

    public Product(String productName, int quantity, double price, String supplier) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public int getID() {
        return id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // changed this function to have one space between words so that OrdersEntry can
    // print them to the file without too much effort and the code can run
    // efficiently.
    public String toString() {
        return (getName() + " " + getQuantity() + " " + getPrice() + " " + getSupplier());
    }

    public int compareTo(Product other) {
        if (this.getPrice() > other.getPrice()) {
            return 1;
        } else if (this.getPrice() < other.getPrice()) {
            return -1;
        }
        return 0;
    }

}

class SortName implements Comparator<Product> {
    public int compare(Product product, Product other) {
        if (product.getName().compareToIgnoreCase(other.getName()) > 0) {
            return 1;
        } else if (product.getName().compareToIgnoreCase(other.getName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
