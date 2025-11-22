package main.item;

/**
 * Represents a healing potion that can restore a character's health.
 * Healing potions are consumable items stored in the inventory.
 */
public class HealingPotion extends Item
{
    /** The amount of health this potion restores when used */
    public double healingAmount;

    /**
     * Creates a new healing potion with default MEDIUM rarity.
     * 
     * @param name The potion's name
     * @param weight The potion's weight
     * @param value The potion's monetary value
     * @param healingAmount The amount of health restored when used
     */
    public HealingPotion(String name, double weight, double value, double healingAmount)
    {
        super(name, weight, value);
        setHealingAmount(healingAmount);
    }

    /**
     * Creates a new healing potion with specified rarity.
     * 
     * @param name The potion's name
     * @param weight The potion's weight
     * @param value The potion's monetary value
     * @param healingAmount The amount of health restored when used
     * @param rarity The potion's rarity level
     */
    public HealingPotion(String name, double weight, double value, double healingAmount, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setHealingAmount(healingAmount);
    }

    /**
     * Gets the amount of health this potion restores.
     * 
     * @return The healing amount
     */
    public double getHealingAmount()
    {
        return healingAmount;
    }

    /**
     * Sets the amount of health this potion restores.
     * Minimum value is 0.
     * 
     * @param healingAmount The new healing amount
     */
    public void setHealingAmount(double healingAmount)
    {
        this.healingAmount = Math.max(0, healingAmount);
    }

    /**
     * Prints the potion's statistics including healing amount.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Healing Amount:\t" + healingAmount);
    }

    @Override
    public String toString()
    {
        return getName() + " +" + healingAmount + " HP";
    }
}
