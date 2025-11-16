package main.item;

/**
 * Represents a weapon item that can be equipped by characters.
 * Weapons deal damage and can be either physical or magical.
 */
public class Weapon extends Item
{
    private double damage;
    private boolean isMagic;

    /**
     * Creates a new weapon with default MEDIUM rarity.
     * 
     * @param name The weapon's name
     * @param weight The weapon's weight
     * @param value The weapon's monetary value
     * @param damage The base damage the weapon deals
     * @param isMagic Whether the weapon is magical (scales with intelligence instead of strength)
     */
    public Weapon(String name, double weight, double value, double damage, boolean isMagic)
    {
        super(name, weight, value);
        setDamage(damage);
        setMagic(isMagic);
    }

    /**
     * Creates a new weapon with specified rarity.
     * 
     * @param name The weapon's name
     * @param weight The weapon's weight
     * @param value The weapon's monetary value
     * @param damage The base damage the weapon deals
     * @param isMagic Whether the weapon is magical (scales with intelligence instead of strength)
     * @param rarity The weapon's rarity level
     */
    public Weapon(String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setDamage(damage);
        setMagic(isMagic);
    }

    /**
     * Gets the weapon's base damage.
     * 
     * @return The damage value
     */
    public double getDamage()
    {
        return damage;
    }

    /**
     * Sets the weapon's base damage.
     * Minimum value is 0.
     * 
     * @param damage The new damage value
     */
    public void setDamage(double damage)
    {
        this.damage = Math.max(0, damage);
    }

    /**
     * Checks if the weapon is magical.
     * 
     * @return true if magical, false if physical
     */
    public boolean isMagic()
    {
        return isMagic;
    }

    /**
     * Sets whether the weapon is magical.
     * 
     * @param isMagic true for magical, false for physical
     */
    public void setMagic(boolean isMagic)
    {
        this.isMagic = isMagic;
    }

    /**
     * Prints the weapon's statistics including damage and magic status.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Damage:\t" + damage);
        IO.println("Is Magic:\t" + isMagic);
    }
}
