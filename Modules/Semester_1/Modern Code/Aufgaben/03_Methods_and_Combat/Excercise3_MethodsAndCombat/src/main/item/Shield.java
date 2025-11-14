package main.item;

public class Shield extends Item
{
    private double defense;

    public Shield(String name, double weight, double value, double defense)
    {
        super(name, weight, value);
        setDefense(defense);
    }

    public Shield(String name, double weight, double value, double defense, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setDefense(defense);
    }

    public double getDefense()
    {
        return defense;
    }

    public void setDefense(double defense)
    {
        this.defense = Math.max(0, defense);
    }

    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Defense:\t" + defense);
    }
}
