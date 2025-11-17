package main.item;


/**
 * Represents armour that can be equipped by characters.
 * Armour provides permanent defense that applies to all incoming damage.
 */
public class Armour extends Item
{
    private double defense;

    /**
     * Creates new armour with default MEDIUM rarity.
     * 
     * @param name The armour's name
     * @param weight The armour's weight
     * @param value The armour's monetary value
     * @param defense The defense value the armour provides
     */
    public Armour(String name, double weight, double value, double defense)
    {
        super(name, weight, value);
        setDefense(defense);
    }

    /**
     * Creates new armour with specified rarity.
     * 
     * @param name The armour's name
     * @param weight The armour's weight
     * @param value The armour's monetary value
     * @param defense The defense value the armour provides
     * @param rarity The armour's rarity level
     */
    public Armour(String name, double weight, double value, double defense, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setDefense(defense);
    }

    /**
     * Gets the armour's defense value.
     * 
     * @return The defense value
     */
    public double getDefense()
    {
        return defense;
    }

    /**
     * Sets the armour's defense value.
     * Minimum value is 0.
     * 
     * @param defense The new defense value
     */
    public void setDefense(double defense)
    {
        this.defense = Math.max(0, defense);
    }

    /**
     * Prints the armour's statistics including defense value.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Defense:\t" + defense);
    }
}
