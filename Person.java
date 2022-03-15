
// package proj;
import java.util.Comparator;

<<<<<<< HEAD
public class Person implements Comparable<Person> {
    private double budget;
    private String name;
    private int age;
    private int id;

    private static int nextId = 0;

    public Person(String name, int age, double budget) {
        this.name = name;
        this.age = age;
        this.budget = budget;
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String toString() {
        return (getId() + "\t" + getName() + "\t" + getAge() + "\t" + getBudget());
    }

    public static void resetId() {
        nextId = 0;
    }

    public int compareTo(Person other) {
        if (this.getBudget() > other.getBudget()) {
            return 1;
        } else if (this.getBudget() < other.getBudget()) {
=======
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
>>>>>>> 7effa9ed3a5c7ac3da077980852e2a6c4e737937
            return -1;
        }
        return 0;
    }
<<<<<<< HEAD

}

class SortName implements Comparator<Person> {
    public int compare(Person person, Person other) {
        if (person.getName().compareToIgnoreCase(other.getName()) > 0) {
            return 1;
        } else if (person.getName().compareToIgnoreCase(other.getName()) < 0) {
=======
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
>>>>>>> 7effa9ed3a5c7ac3da077980852e2a6c4e737937
            return -1;
        } else {
            return 0;
        }
    }
}
