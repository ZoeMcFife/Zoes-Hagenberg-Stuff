package main.item;

public class Item
{
    private String name;
    private double weight;
    private double value;

    public void printItemStats()
    {
        System.out.println("This is a generic item.");
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getWeight()
    {
        return weight;
    }


    public double getValue()
    {
        return value;
    }

    public void setWeight(double weight)
    {
        this.weight = Math.max(0, weight);
    }

    public void setValue(double value)
    {
        this.value = Math.max(0, value);
    }

}
