package main.item;

/**
 * Represents a weapon item that can be equipped by characters.
 * Weapons deal damage and can be either physical or magical.
 */
public class Weapon extends Item
{
    private double damage;
    private boolean isMagic;
    private double specialDamage;
    private String specialFlavorText;
    private int ppCost;

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
        this.specialDamage = 0;
        this.specialFlavorText = "";
        this.ppCost = 0;
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
        this.specialDamage = 0;
        this.specialFlavorText = "";
        this.ppCost = 0;
    }

    /**
     * Creates a new weapon with all parameters including PP special attack data.
     * 
     * @param name The weapon's name
     * @param weight The weapon's weight
     * @param value The weapon's monetary value
     * @param damage The base damage the weapon deals
     * @param isMagic Whether the weapon is magical (scales with intelligence instead of strength)
     * @param rarity The weapon's rarity level
     * @param specialDamage Additional damage dealt by special attack
     * @param specialFlavorText Flavor text displayed when special is used
     * @param ppCost Power Points required to use special attack
     */
    public Weapon(String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity,
                  double specialDamage, String specialFlavorText, int ppCost)
    {
        super(name, weight, value, rarity);
        setDamage(damage);
        setMagic(isMagic);
        setSpecialDamage(specialDamage);
        setSpecialFlavorText(specialFlavorText);
        setPpCost(ppCost);
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
     * Gets the special attack damage.
     * 
     * @return Special damage value
     */
    public double getSpecialDamage()
    {
        return specialDamage;
    }

    /**
     * Sets the special attack damage.
     * Minimum value is 0.
     * 
     * @param specialDamage The special damage value
     */
    public void setSpecialDamage(double specialDamage)
    {
        this.specialDamage = Math.max(0, specialDamage);
    }

    /**
     * Gets the special attack flavor text.
     * 
     * @return Flavor text shown when special is used
     */
    public String getSpecialFlavorText()
    {
        return specialFlavorText;
    }

    /**
     * Sets the special attack flavor text.
     * 
     * @param specialFlavorText Flavor text for special attack
     */
    public void setSpecialFlavorText(String specialFlavorText)
    {
        this.specialFlavorText = specialFlavorText != null ? specialFlavorText : "";
    }

    /**
     * Gets the PP cost for the special attack.
     * 
     * @return PP cost
     */
    public int getPpCost()
    {
        return ppCost;
    }

    /**
     * Sets the PP cost for the special attack.
     * Minimum value is 0.
     * 
     * @param ppCost The PP cost
     */
    public void setPpCost(int ppCost)
    {
        this.ppCost = Math.max(0, ppCost);
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

    @Override
    public String toString()
    {
        return getName() + " +" + damage + (isMagic ? " M" : " P" + " DMG" );
    }
}
