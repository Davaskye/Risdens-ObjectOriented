package proj;
import java.util.Comparator;

import javax.management.loading.PrivateClassLoader;

public class Product implements Comparable<Product>
{
    private double cost;
    private String product_name;
	private int quantity;
	private String supplier;

    public Product(String product_name, int quantity, double cost, String supplier)
    {
        this.product_name = product_name;
		this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
    }

    public String getProductName()
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
	public void setCost(double cost)
	{
		this.price = price;
	}

    public String toString()
    {
        return(getProductName()+"\t"+getQuantity()+"\t"+getPrice()+"\t"+getSupplier());
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
        }
        else
        {
            return 0;
        }
    }
}

