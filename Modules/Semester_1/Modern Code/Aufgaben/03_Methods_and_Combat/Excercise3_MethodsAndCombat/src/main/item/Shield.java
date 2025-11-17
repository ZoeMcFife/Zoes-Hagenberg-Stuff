package main.item;

/**
 * Represents a shield item that can be equipped by characters.
 * Shields provide defense, especially when actively defending.
 */
public class Shield extends Item
{
    private double defense;

    /**
     * Creates a new shield with default MEDIUM rarity.
     * 
     * @param name The shield's name
     * @param weight The shield's weight
     * @param value The shield's monetary value
     * @param defense The defense value the shield provides
     */
    public Shield(String name, double weight, double value, double defense)
    {
        super(name, weight, value);
        setDefense(defense);
    }

    /**
     * Creates a new shield with specified rarity.
     * 
     * @param name The shield's name
     * @param weight The shield's weight
     * @param value The shield's monetary value
     * @param defense The defense value the shield provides
     * @param rarity The shield's rarity level
     */
    public Shield(String name, double weight, double value, double defense, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setDefense(defense);
    }

    /**
     * Gets the shield's defense value.
     * 
     * @return The defense value
     */
    public double getDefense()
    {
        return defense;
    }

    /**
     * Sets the shield's defense value.
     * Minimum value is 0.
     * 
     * @param defense The new defense value
     */
    public void setDefense(double defense)
    {
        this.defense = Math.max(0, defense);
    }

    /**
     * Prints the shield's statistics including defense value.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Defense:\t" + defense);
    }
}
