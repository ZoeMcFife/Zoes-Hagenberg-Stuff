package main.item;

/**
 * Enumeration representing the condition state of armour equipment.
 * Each state has a wear multiplier that affects how quickly durability decreases when taking damage.
 * As armour degrades, it wears out faster (higher multiplier means faster degradation).
 */
public enum ArmourState
{
    /** Armour is in pristine condition (durability >= 95%), degrades slowly with 0.6x wear rate */
    PRISTINE(0.6),
    
    /** Armour has minor scratches (durability >= 80%), degrades at normal 1.0x wear rate */
    SCRATCHED(1.0),
    
    /** Armour is worn (durability >= 50%), degrades faster with 1.2x wear rate */
    WORN(1.2),
    
    /** Armour is damaged (durability >= 10%), degrades much faster with 1.5x wear rate */
    DAMAGED(1.5),
    
    /** Armour is broken (durability < 10%), degrades very rapidly with 3.0x wear rate */
    BROKEN(3);

    /** The multiplier applied to damage taken that determines durability loss rate */
    public final double wearMultiplier;

    /**
     * Creates an armour state with the specified wear multiplier.
     * 
     * @param wearMultiplier The multiplier for durability degradation rate
     */
    ArmourState(double wearMultiplier)
    {
        this.wearMultiplier = wearMultiplier;
    }
}
