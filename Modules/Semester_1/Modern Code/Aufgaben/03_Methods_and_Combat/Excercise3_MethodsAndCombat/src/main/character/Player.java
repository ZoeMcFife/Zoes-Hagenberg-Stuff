package main.character;

/**
 * Represents a player character in the game.
 * Extends GameCharacter with player-specific defaults and functionality.
 */
public class Player extends GameCharacter
{
    private int level;
    private int experience;
    private int availableStatPoints;

    /** Default maximum health for all player characters */
    public static double DEFAULT_PLAYER_MAX_HEALTH = 100.0;

    /**
     * Creates a new player with specified stats and max health.
     * 
     * @param name The player's name
     * @param maxHealth The maximum health of the player
     * @param strength The player's strength stat (affects physical damage)
     * @param dexterity The player's dexterity stat (affects turn order)
     * @param intelligence The player's intelligence stat (affects magic damage)
     */
    public Player(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        super(name, maxHealth, strength, dexterity, intelligence);
    }

    /**
     * Creates a new player with specified stats and default max health.
     * Uses DEFAULT_PLAYER_MAX_HEALTH for the max health value.
     * 
     * @param name The player's name
     * @param strength The player's strength stat (affects physical damage)
     * @param dexterity The player's dexterity stat (affects turn order)
     * @param intelligence The player's intelligence stat (affects magic damage)
     */
    public Player(String name, int strength, int dexterity, int intelligence)
    {
        this(name, DEFAULT_PLAYER_MAX_HEALTH, strength, dexterity, intelligence);
    }

    public void addExperience(int exp)
    {
        this.experience += exp;

        while (this.experience >= getExperienceNeededForNextLevel())
        {
            this.experience -= getExperienceNeededForNextLevel();
            levelUp();
        }
    }

    private int getExperienceNeededForNextLevel()
    {
        return (100 * this.level);
    }

    public int getAvailableStatPoints()
    {
        return this.availableStatPoints;
    }

    public void spendStatPoint()
    {
        this.availableStatPoints -= 1;
    }

    private void levelUp()
    {
        this.level += 1;
        this.availableStatPoints += 1;
    }
}
