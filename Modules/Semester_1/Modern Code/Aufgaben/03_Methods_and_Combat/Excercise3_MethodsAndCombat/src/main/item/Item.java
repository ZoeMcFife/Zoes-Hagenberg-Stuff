package main.item;

/**
 * Base class for all items in the game.
 * Items have a name, weight, value, and rarity.
 */
public class Item
{
    private String name;
    private double weight;
    private double value;
    private ItemRarity rarity;

    /**
     * Creates a new item with default MEDIUM rarity.
     * 
     * @param name The item's name
     * @param weight The item's weight (affects inventory capacity)
     * @param value The item's monetary value
     */
    public Item(String name, double weight, double value)
    {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.rarity = ItemRarity.MEDIUM; // default
    }

    /**
     * Creates a new item with specified rarity.
     * 
     * @param name The item's name
     * @param weight The item's weight (affects inventory capacity)
     * @param value The item's monetary value
     * @param rarity The item's rarity level
     */
    public Item(String name, double weight, double value, ItemRarity rarity)
    {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.rarity = rarity;
    }

    /**
     * Prints the item's statistics to the console.
     * Displays name, weight, value, and rarity.
     */
    public void printItemStats()
    {
        IO.println("Name:\t" + name);
        IO.println("Weight:\t" + String.format("%.1f", weight));
        IO.println("Value:\t" + String.format("%.1f", value));
        IO.println("Rarity:\t" + rarity);
    }

    /**
     * Gets the item's name.
     * 
     * @return The item's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the item's name.
     * 
     * @param name The new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the item's weight.
     * 
     * @return The item's weight
     */
    public double getWeight()
    {
        return weight;
    }


    /**
     * Gets the item's value.
     * 
     * @return The item's monetary value
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Sets the item's weight.
     * Minimum value is 0.
     * 
     * @param weight The new weight
     */
    public void setWeight(double weight)
    {
        this.weight = Math.max(0, weight);
    }

    /**
     * Sets the item's value.
     * Minimum value is 0.
     * 
     * @param value The new value
     */
    public void setValue(double value)
    {
        this.value = Math.max(0, value);
    }

    /**
     * Gets the item's rarity level.
     * 
     * @return The item's rarity
     */
    public ItemRarity getRarity()
    {
        return rarity;
    }

    /**
     * Sets the item's rarity level.
     * 
     * @param rarity The new rarity
     */
    public void setRarity(ItemRarity rarity)
    {
        this.rarity = rarity;
    }

    /**
     * Returns a string representation of the item.
     * 
     * @return The item's name
     */
    @Override
    public String toString()
    {
        return name;
    }
}
