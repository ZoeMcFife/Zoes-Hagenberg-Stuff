package main.character;

/**
 * Enumeration representing the danger level of enemies in the game.
 * Used to categorize enemies by their threat level to the player.
 */
public enum DangerLevel
{
    /** Very weak enemies that pose minimal threat */
    HARMLESS,
    
    /** Weak enemies with limited combat capability */
    MOSTLY_HARMLESS,
    
    /** Enemies that present a moderate threat */
    DANGEROUS,
    
    /** High-threat enemies with strong combat abilities */
    EXTREME,
    
    /** Extremely dangerous enemies that can easily kill the player */
    DEATH
}
