
// package proj;
import java.util.Comparator;
public class Product implements Comparable<Product>
{
    private double price;
    private String productName;
	private int quantity;
	private String supplier;

    public Product(String productName, int quantity, double price, String supplier)
    {
        this.productName = productName;
		this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
    }

    public String getName()
    {
        return productName;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}

    public String toString()
    {
        return(getName()+"\t"+getQuantity()+"\t"+getPrice()+"\t"+getSupplier());
    }
    
    public int compareTo(Product other)
    {
        if (this.getPrice()> other.getPrice())
        {
            return 1;
        }
        else if (this.getPrice() < other.getPrice())
        {
            return -1;
        }
        return 0;
    }

}

class SortName implements Comparator<Product>
{
    public int compare(Product product, Product other)
    {
        if(product.getName().compareToIgnoreCase(other.getName())>0)
        {
            return 1;
        }
        else if(product.getName().compareToIgnoreCase(other.getName())<0)
        {
            return -1;
        } else {
            return 0;
        }
    }
}
