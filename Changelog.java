package proj;

public class Changelog {
    private String date;
    private String time;
    private String change;
    private String name;

    public Changelog(String date, String time, String personName, String change)
    {
        
        this.date = date;
        this.time = time;
        this.name = personName;
        this.change = change;
    }


    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }


    public String getChange()
    {
        return change;
    }

    public String getName()
    {
        return name;
    }
    
}
