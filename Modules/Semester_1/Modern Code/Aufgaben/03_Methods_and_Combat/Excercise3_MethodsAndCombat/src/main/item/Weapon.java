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
    private String specialAttackName;
    private String specialFlavorText;
    private int ppCost;
    private int ppGainPerUse;

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
        this.specialAttackName = "";
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
        this.specialAttackName = "";
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
     * @param specialAttackName Name of the special attack
     * @param specialFlavorText Flavor text displayed when special is used
     * @param ppCost Power Points required to use special attack
     * @param ppGainPerUse Power Points gained per normal attack with this weapon
     */
    public Weapon(String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity,
                  double specialDamage, String specialAttackName, String specialFlavorText, int ppCost, int ppGainPerUse)
    {
        super(name, weight, value, rarity);
        setDamage(damage);
        setMagic(isMagic);
        setSpecialDamage(specialDamage);
        setSpecialAttackName(specialAttackName);
        setSpecialFlavorText(specialFlavorText);
        setPpCost(ppCost);
        setPpGainPerUse(ppGainPerUse);
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
     * Gets the special attack name.
     * 
     * @return Name of the special attack
     */
    public String getSpecialAttackName()
    {
        return specialAttackName;
    }

    /**
     * Sets the special attack name.
     * 
     * @param specialAttackName Name of the special attack
     */
    public void setSpecialAttackName(String specialAttackName)
    {
        this.specialAttackName = specialAttackName != null ? specialAttackName : "";
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
     * Sets the PP gained per normal attack with this weapon.
     * Minimum value is 0.
     *
     * @param ppGainPerUse The PP gain per use
     */
    public void setPpGainPerUse(int ppGainPerUse)
    {
        this.ppGainPerUse = Math.max(0, ppGainPerUse);
    }

    /**
     * Gets the PP gained per normal attack with this weapon.
     *
     * @return PP gain per use
     */
    public int getPpGainPerUse()
    {
        return ppGainPerUse;
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
