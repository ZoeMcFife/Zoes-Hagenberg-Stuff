package main.item;

public class Item
{
    private String name;
    private double weight;
    private double value;
    private ItemRarity rarity;

    public Item(String name, double weight, double value)
    {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public void printItemStats()
    {
        IO.println("Name:\t" + name);
        IO.println("Weight:\t" + weight);
        IO.println("Value:\t" + value);
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

    @Override
    public String toString()
    {
        return name;
    }
}
