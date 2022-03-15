// package proj;

public class Changelog {
    private String date;
    private String time;
    private String productname;
    private String quantity;
    private String cost;

    public Changelog(String date, String time, String productname, String quantity, String cost) {

        this.date = date;
        this.time = time;
        this.productname = productname;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getProductName() {
        return productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCost() {
        return cost;
    }

    // public String getChange()
    // {
    // return change;
    // }

    // public String getName()
    // {
    // return name;
    // }

    // public static void main(String[] args) {
    // Changelog test = new Changelog("date", "time", "bleach", "10", "150");
    // System.out.println(test.getProductName());
    // }

}
