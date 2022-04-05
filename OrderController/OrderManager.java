package OrderController;

import ProductManager.Product;

public final class OrderManager {

    public Order order;
    public int itemId;

    public OrderManager() {

    }

    public OrderManager(Order order, int itemId) {
        this.setOrder(order);
        this.setItemId(itemId);
    }

    private Object getOrderId() {
        return null;
    }

    @Override
    public String toString() {
        return "" + getOrder() + "-" + getItemId();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return ((this.getOrder() == null
                ? 0
                : this.getOrder().hashCode())
                ^ ((int) this.getItemId()));
    }

    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof OrderManager)) {
            return false;
        }
        OrderManager other = (OrderManager) otherOb;
        return ((this.getOrder() == null
                ? other.getOrder() == null
                : this.getOrderId()
                        .equals(other.getOrder()))
                && (this.getItemId() == other.getItemId()));
    }

    // public static void main(String[] args) {
    // Product product = new Product("Bleach", 10, 100, "Mr.Bean");
    // Order order = new Order("Al", "12345678", "Hellsing Ultimate", "Hellsing
    // Ave.", "12:00", product);
    // OrderManager om = new OrderManager(null, 5678);
    // System.out.println(om.hashCode());
    // }
}