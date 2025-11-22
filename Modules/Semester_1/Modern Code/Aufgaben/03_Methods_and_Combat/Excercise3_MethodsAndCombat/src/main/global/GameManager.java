package main.global;

import main.character.DangerLevel;
import main.character.Player;

/**
 * Manages global game state and configuration parameters.
 * Provides access to the player instance and game balance constants.
 */
public class GameManager
{
    private static Player player;
    /** Flag indicating whether the player has been initialized */
    public static boolean hasPlayerBeenInitialized = false;

    /** Damage multiplier applied per point of strength for physical weapons */
    public static double DAMAGE_MULTIPLIER_PER_STRENGTH = 0.05;

    /** Damage multiplier applied per point of intelligence for magical weapons */
    public static double DAMAGE_MULTIPLIER_PER_INTELLIGENCE = 0.03;

    /** Dodge chance percentage gained per point of dexterity */
    public static double DODGE_CHANCE_PER_DEXTERITY = 0.02;

    /** Damage reduction percentage per point of defense */
    public static double DAMAGE_REDUCTION_PER_DEFENSE = 0.04;

    /** Weight carrying capacity granted per point of strength */
    public static final int CARRY_CAPACITY_PER_STRENGTH = 10;

    /** Current game difficulty setting */
    public static Difficulty difficulty = Difficulty.NONE;

    /** Number of turns before difficulty increases on Easy mode */
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_EASY = 5;

    /** Number of turns before difficulty increases on Medium mode */
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM = 3;

    /** Number of turns before difficulty increases on Hard mode */
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_HARD = 1;

    /** Maximum enemies per battle at HARMLESS danger level */
    public static int MAX_ENEMIES_PER_BATTLE_HARMLESS = 1;

    /** Maximum enemies per battle at MOSTLY_HARMLESS danger level */
    public static int MAX_ENEMIES_PER_BATTLE_MOSTLY_HARMLESS = 2;

    /** Maximum enemies per battle at DANGEROUS danger level */
    public static int MAX_ENEMIES_PER_BATTLE_DANGEROUS = 2;

    /** Maximum enemies per battle at EXTREME danger level */
    public static int MAX_ENEMIES_PER_BATTLE_EXTREME = 3;

    /** Maximum enemies per battle at DEATH danger level */
    public static int MAX_ENEMIES_PER_BATTLE_DEATH = 4;

    /** Number of items able to be looted after battle on Easy mode */
    public static int ITEM_LOOT_COUNT_EASY = 5;
    /** Number of items able to be looted after battle on Medium mode */
    public static int ITEM_LOOT_COUNT_MEDIUM = 3;
    /** Number of items able to be looted after battle on Hard mode */
    public static int ITEM_LOOT_COUNT_HARD = 2;

    public static double DELAY_SHORT = 0.5;
    public static double DELAY_MEDIUM = 1;
    public static double DELAY_LONG = 2;

    /**
     * Sets the active player character.
     *
     * @param player The player character to set
     */
    public static void setPlayer(Player player)
    {
        GameManager.player = player;
        hasPlayerBeenInitialized = true;
    }

    /**
     * Gets the active player character.
     *
     * @return The current player character
     */
    public static Player getPlayer()
    {
        return GameManager.player;
    }

    /**
     * Removes the player character from the game.
     * Resets the initialization flag.
     */
    public static void removePlayer()
    {
        GameManager.player = null;
        hasPlayerBeenInitialized = false;
    }

    /**
     * Gets the number of items able to be looted after battle based on current difficulty.
     *
     * @return The item loot count
     */
    public static int getItemLootCount()
    {
        return switch (difficulty)
        {
            case EASY -> ITEM_LOOT_COUNT_EASY;
            case MEDIUM -> ITEM_LOOT_COUNT_MEDIUM;
            case HARD -> ITEM_LOOT_COUNT_HARD;
            default -> 0;
        };
    }
}
