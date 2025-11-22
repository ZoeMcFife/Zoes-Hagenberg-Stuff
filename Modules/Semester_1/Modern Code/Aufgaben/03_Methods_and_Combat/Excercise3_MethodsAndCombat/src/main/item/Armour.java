package main.item;


/**
 * Represents armour that can be equipped by characters.
 * Armour provides permanent defense that applies to all incoming damage.
 */
public class Armour extends Item
{
    private double defense;
    /**
     * The durability of the armour. When durability reaches 0, the armour breaks and provides no defense.
     */
    private double durability = 100;
    /**
     * The maximum durability of the armour.
     */
    private double maxDurability = 100;

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

        this.maxDurability = 100;
        this.durability = maxDurability;
    }

    /**
     * Creates new armour with specified maximum durability.
     *
     * @param name The armour's name
     * @param weight The armour's weight
     * @param value The armour's monetary value
     * @param defense The defense value the armour provides
     * @param maxDurability The maximum durability of the armour
     */
    public Armour(String name, double weight, double value, double defense, double maxDurability)
    {
        super(name, weight, value);
        setDefense(defense);
        this.maxDurability = maxDurability;
        this.durability = maxDurability;
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
        return defense * (durability / maxDurability);
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

    public ArmourState getState()
    {
        double durabilityRatio = durability / maxDurability;

        if (durabilityRatio >= 0.95) return ArmourState.PRISTINE;
        if (durabilityRatio >= 0.80) return ArmourState.SCRATCHED;
        if (durabilityRatio >= 0.50) return ArmourState.WORN;
        if (durabilityRatio >= 0.10) return ArmourState.DAMAGED;
        return ArmourState.BROKEN;
    }

    /**
     * Reduces the durability of the armour by a specified amount, adjusted by the armour's current state.
     *
     * @param amount The base amount to reduce durability by
     */
    public void reduceDurability(double amount)
    {
        if (amount < 0)
        {
            amount = 0;
        }

        ArmourState state = getState();
        double wearMultiplier = state.wearMultiplier;

        double finalWear = amount * wearMultiplier;

        durability = Math.max(0, durability - finalWear);
    }

    /**
     * Gets the current durability of the armour.
     *
     * @return The current durability
     */
    public double getDurability()
    {
        return durability;
    }

    /**
     * Gets the maximum durability of the armour.
     *
     * @return The maximum durability
     */
    public double getMaxDurability()
    {
        return maxDurability;
    }

    /**
     * Prints the armour's statistics including defense value.
     */
    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Defense:\t" + getDefense());
        IO.println("Durability:\t" + getDurability() + " / " + getMaxDurability() + " (" + getState().name() + ")");
        IO.println("State:\t" + getState().name());
    }

    @Override
    public String toString()
    {
        return getName() + " +" + getDefense() + " DEF" + " (" + getState().name() + ")";
    }
}
