package OrderController;

import ProductManager.Product;

// So at the time this class was created, it was unclear of the exact details this class should function under
// With that in mind, here goes nothing ...
// To be done by Chisomo and Jada, ya'll can co-ordinate your attacks
// I may help you do so with my sarcastic comments but at the moment of writing this I'm having second thoughts
// So if there are no comments on how you should work together, you know why.

public class Order {

    private String name;
    private String tele;
    private String address;
    private String deliveryAddress;
    private String TOD; // TOD means Time Of Delivery
    private Product product;

    // Constructor taking information to create an order
    // This can turn out to be infomation we can use elsewhere and just inputting
    // the info into a table
    // Not sure as yet of how it will be done
    public Order(String customerName, String telephone, String customerAddress, String deliveryAddress,
            String deliveryTime, Product product) {
        this.name = customerName;
        this.tele = telephone;
        this.address = customerAddress;
        this.deliveryAddress = deliveryAddress;
        this.TOD = deliveryTime;
        this.product = product;
    }

    // some getters to help
    public String getName() {
        return this.name;
    }

    public String getTele() {
        return this.tele;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDeliveryAdd() {
        return this.deliveryAddress;
    }

    public String getTOD() {
        return this.TOD;
    }

    public Product getProduct() {
        return this.product;
    }

    // Can create a function after we're clear on how this requirement will be done
    // in detail
    // If it is explained, you can go ahead and work on it as you like

    public String toString() {
        return (getName() + "@" + getTele() + "@" + getAddress() + "@" + getDeliveryAdd() + "@" + getTOD() + "@"
                + getProduct());
    }

}
