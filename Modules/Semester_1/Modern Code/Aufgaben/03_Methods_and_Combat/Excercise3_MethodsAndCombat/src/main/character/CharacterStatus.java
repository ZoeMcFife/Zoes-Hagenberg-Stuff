package main.character;

/**
 * Enumeration representing the health status of a game character.
 * The status is determined by the character's current health percentage.
 */
public enum CharacterStatus
{
    /** Character is at full health */
    ALIVE,
    
    /** Character has taken some damage (health > 50%) */
    HURT,
    
    /** Character has taken significant damage (health > 30% and <= 50%) */
    SEVERELY_HURT,
    
    /** Character is close to death (health > 0% and <= 30%) */
    CRITICALLY_HURT,
    
    /** Character has no health remaining */
    DEAD
}
