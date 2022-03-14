package proj;
import java.util.Comparator;

public class Person implements Comparable<Person>
{
    private double budget;
    private String name;
	private int age;
	private int id;

    private static int nextId=0;


    public Person(String name, int age, double budget)
    {
        this.name =name;
		this.age=age;
        this.budget = budget;
        this.id = nextId;
        nextId++;
    }

    public String getName()
    {
        return name;
    }

    public double getBudget()
    {
        return budget;
    }

    public int getId()
    {
        return id;
    }

    public int getAge()
    {
        return age;
    }

    public void setName(String name)
	{
		this.name = name;
	}
	public void setBudget(double budget)
	{
		this.budget = budget;
	}

    public String toString()
    {
        return(getId()+"\t"+getName()+"\t"+getAge()+"\t"+getBudget());
    }

    
    public static void resetId()
    {
       nextId=0;   
    }
    
    public int compareTo(Person other)
    {
        if (this.getBudget()> other.getBudget())
        {
            return 1;
        }
        else if (this.getBudget() < other.getBudget())
        {
            return -1;
        }
        return 0;   
    }


    


}

class SortName implements Comparator<Person>
{
    public int compare(Person person, Person other)
    {
        if(person.getName().compareToIgnoreCase(other.getName())>0)
        {
            return 1;
        }
        else if(person.getName().compareToIgnoreCase(other.getName())<0)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}

