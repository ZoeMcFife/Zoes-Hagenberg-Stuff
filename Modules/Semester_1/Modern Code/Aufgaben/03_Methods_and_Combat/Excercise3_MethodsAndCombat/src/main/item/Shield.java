package main.item;

/**
 * Represents a shield item that can be equipped by characters.
 * Shields provide defense, especially when actively defending.
 */
public class Shield extends Item
{
    private double defense;
    private int ppGain;

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
        this.ppGain = 0;
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
        this.ppGain = 0;
    }

    /**
     * Creates a new shield with specified rarity and PP gain.
     * 
     * @param name The shield's name
     * @param weight The shield's weight
     * @param value The shield's monetary value
     * @param defense The defense value the shield provides
     * @param rarity The shield's rarity level
     * @param ppGain PP gained when defending with this shield
     */
    public Shield(String name, double weight, double value, double defense, ItemRarity rarity, int ppGain)
    {
        super(name, weight, value, rarity);
        setDefense(defense);
        setPpGain(ppGain);
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
     * Gets the PP gain when defending with this shield.
     * 
     * @return PP gain value
     */
    public int getPpGain()
    {
        return ppGain;
    }

    /**
     * Sets the PP gain when defending with this shield.
     * Minimum value is 0.
     * 
     * @param ppGain The PP gain value
     */
    public void setPpGain(int ppGain)
    {
        this.ppGain = Math.max(0, ppGain);
    }

    /**
     * Prints the shield's statistics including defense value.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Defense:\t" + String.format("%.1f", defense));
    }

    @Override
    public String toString()
    {
        return getName() + " +" + String.format("%.1f", defense) + " DEF";
    }
}
