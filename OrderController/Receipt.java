package OrderController;


public class Receipt {
    public Order served;
    public double payment;
    public double total; // this is the total payment for the order
    public double change; //this is the difference between payment and cost


    public Receipt(Order served, double payment){
        this.served = served;
        this.payment = payment;
        this.total = served.getProduct().getQuantity()*served.getProduct().getPrice();
        this.change = total-payment;
    }

    public Order getOrder(){
        return served;
    }

    public double getPayment(){
        return payment;
    }

    public Double getTotal(){
        return total;
    }

    public double getChange(){
        return Math.abs(change);
    }

    public void updateReceipt(){
        this.total = served.getProduct().getQuantity()*served.getProduct().getPrice();
        this.change = total-payment;
    }

    
}
