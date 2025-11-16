package main.combat;

/**
 * Enumeration of possible actions a character can take during combat.
 */
public enum ActionType
{
    /** Attack the opponent to deal damage */
    ATTACK,
    
    /** Take a defensive stance to reduce incoming damage */
    DEFEND,
    
    /** Use a healing item to restore health */
    HEAL,
}
