package main.character;

import main.global.GameManager;
import main.ui.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player character in the game.
 * Extends GameCharacter with player-specific defaults and functionality.
 */
public class Player extends GameCharacter
{
    /** The player's current level, starts at 1 and increases through experience */
    private int level = 1;
    
    /** The player's current experience points toward the next level */
    private int experience = 0;
    
    /** Stat points available to spend on improving character attributes */
    private int availableStatPoints = 0;
    
    /** The player's current Power Points (PP) used for special attacks */
    private int currentPP = 0;
    
    /** The player's maximum Power Points capacity */
    private int maxPP = 100;

    /** Default maximum health for all player characters */
    public static double DEFAULT_PLAYER_MAX_HEALTH = 100.0;
    
    /** Default maximum Power Points for all player characters */
    public static int DEFAULT_PLAYER_MAX_PP = 100;

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
        this.setMaxPP(DEFAULT_PLAYER_MAX_PP);
    }

    /**
     * Called when the player dies.
     * Currently has no implementation (empty method).
     */
    @Override
    protected void onDeath()
    {

    }

    /**
     * Creates a new player with default stats and max health.
     * Uses DEFAULT_PLAYER_MAX_HEALTH for the max health value and
     * minimum values for strength, dexterity, and intelligence.
     *
     * @param name The player's name
     */
    public Player(String name)
    {
        this(name, DEFAULT_PLAYER_MAX_HEALTH, GameCharacter.MIN_STAT_VALUE, GameCharacter.MIN_STAT_VALUE, GameCharacter.MIN_STAT_VALUE);
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

    /**
     * Gets the player's current level.
     * 
     * @return The current level
     */
    public int getLevel()
    {
        return this.level;
    }

    /**
     * Gets the player's current experience points.
     * 
     * @return The current experience points
     */
    public int getExperience()
    {
        return this.experience;
    }

    /**
     * Adds experience points to the player and handles level-ups.
     * Automatically levels up when enough experience is gained.
     * 
     * @param exp The amount of experience to add
     */
    public void addExperience(int exp)
    {
        this.experience += exp;

        IO.println(getName() + " gained " + exp + " experience points.");

        while (this.experience >= getExperienceNeededForNextLevel())
        {
            this.experience -= getExperienceNeededForNextLevel();
            levelUp();
        }
    }

    /**
     * Calculates the experience points needed to reach the next level.
     * Formula: 40 * current level
     * 
     * @return Experience points required for next level
     */
    public int getExperienceNeededForNextLevel()
    {
        return (40 * this.level);
    }

    /**
     * Gets the number of stat points available to spend.
     * 
     * @return Available stat points
     */
    public int getAvailableStatPoints()
    {
        return this.availableStatPoints;
    }

    /**
     * Spends one stat point.
     * Decreases available stat points by 1, with a minimum of 0.
     */
    public void spendStatPoint()
    {
        this.availableStatPoints -= 1;

        if (this.availableStatPoints < 0)
        {
            this.availableStatPoints = 0;
        }
    }

    /**
     * Levels up the player character.
     * Increases level, grants stat points, increases max PP and health, and fully heals the player.
     * Private method called automatically when enough experience is gained.
     */
    private void levelUp()
    {
        this.level += 1;
        this.availableStatPoints += GameManager.STAT_POINTS_PER_LEVEL;
        setMaxPP(getMaxPP() + GameManager.MAX_PP_INCREASE_PER_LEVEL);
        setMaxHealth(getMaxHealth() + GameManager.HEALTH_INCREASE_PER_LEVEL);
        setHealth(getHealth() + GameManager.HEALTH_INCREASE_PER_LEVEL * 2);

        IO.println(getName() + " leveled up to level " + this.level + "!");
    }

    /**
     * Gets the player's current Power Points.
     * 
     * @return Current PP
     */
    public int getCurrentPP()
    {
        return currentPP;
    }

    /**
     * Adds PP to the player's current PP pool.
     * 
     * @param amount Amount of PP to add (must be positive)
     */
    public void gainPP(int amount)
    {
        if (amount <= 0)
        {
            return;
        }

        if (this.currentPP + amount > this.maxPP)
        {
            amount = this.maxPP - this.currentPP;
        }

        this.currentPP += amount;
        IO.println(getName() + " gained " + amount + " PP! (Current PP: " + currentPP + ")");
    }

    /**
     * Gets the player's maximum Power Points.
     *
     * @return Maximum PP
     */
    public int getMaxPP()
    {
        return maxPP;
    }

    /**
     * Sets the player's maximum Power Points.
     * @param maxPP New maximum PP value
     */
    private void setMaxPP(int maxPP)
    {
        this.maxPP = maxPP;
    }

    /**
     * Calculates the character's total defense value.
     * Includes shield defense only when actively defending.
     *
     * @return Total defense points
     */
    @Override
    public double getCurrentDefense()
    {
        if (isDefending())
        {
            return (getEquippedArmour().getDefense() + getEquippedShield().getDefense()) + GameManager.PLAYER_BASE_DEFENCE;
        }

        return getEquippedArmour().getDefense() + GameManager.PLAYER_BASE_DEFENCE;
    }

    /**
     * Uses the equipped weapon's special attack on a target.
     * Checks if player has enough PP, deducts cost, deals damage, and displays flavor text.
     * 
     * @param target The character to attack with the special
     */
    public void useSpecial(GameCharacter target)
    {
        if (!target.isAlive())
        {
            IO.println(target.getName() + " is already defeated!");
            return;
        }

        if (getEquippedWeapon().getPpCost() > getCurrentPP())
        {
            String specialName = getEquippedWeapon().getSpecialAttackName().isEmpty() ? "special attack" : getEquippedWeapon().getSpecialAttackName();
            IO.println(getName() + " doesn't have enough PP to use " + specialName + "!");
            IO.println("Need " + getEquippedWeapon().getPpCost() + " PP, but only have " + currentPP + " PP.");
            return;
        }

        currentPP -= getEquippedWeapon().getPpCost();
        
        String specialName = getEquippedWeapon().getSpecialAttackName().isEmpty() ? "special attack" : getEquippedWeapon().getSpecialAttackName();
        
        if (!getEquippedWeapon().getSpecialFlavorText().isEmpty())
        {
            IO.println(getEquippedWeapon().getSpecialFlavorText());
        }
        
        double totalDamage = getDamage() + getEquippedWeapon().getSpecialDamage();
        IO.println(getName() + " uses " + specialName + " on " + target.getName() + " for " + Math.round(totalDamage) + " damage!");
        IO.println("PP remaining: " + currentPP);
        UIHelper.delayMedium();
        
        target.takeDamage(totalDamage);
    }

    /**
     * Generates and returns the player's display box with additional information.
     * Overrides the base implementation to include level and armour status.
     * 
     * @return List of strings representing the player's display box
     */
    @Override
    public List<String> getDisplayBox()
    {
        List<String> box = new ArrayList<>();

        String nameLine = String.format("| %-36s |", getName());
        
        // Format health with proper spacing
        String healthText = String.format("Health: %.0f / %.0f", getHealth(), getMaxHealth());
        String hpLine = String.format("| %-36s |", healthText);

        // Health bar
        int barLength = 36;
        double pct = getHealthPercentage();
        int filled = (int) (pct * barLength);
        String bar = "=".repeat(filled) + " ".repeat(barLength - filled);
        String barLine = String.format("| %-36s |", bar);

        // Stats line with Level
        String statsText = String.format("Level %d - D%d, I%d, S%d", getLevel(), getDexterity(), getIntelligence(), getStrength());
        String statsLine = String.format("| %-36s |", statsText);

        // Attack and Defense stats line
        String attackDefenseText = String.format("Attack: %.0f, Defense: %.0f", getDamage(), getCurrentDefense());
        String attackDefenseLine = String.format("| %-36s |", attackDefenseText);

        // Armour status line
        String armourStatus = getEquippedArmour() != null ? getEquippedArmour().getState().name() : "NONE";
        String armourStatusText = String.format("Armour: %s", armourStatus);
        String armourStatusLine = String.format("| %-36s |", armourStatusText);

        String state = getStatus().toString();
        String stateLine = String.format("| %-36s |", "(" + state + ")");

        String border = "----------------------------------------";

        box.add(border);
        box.add(nameLine);
        box.add(hpLine);
        box.add(barLine);
        box.add(statsLine);
        box.add(attackDefenseLine);
        box.add(armourStatusLine);
        box.add(stateLine);
        box.add(border);

        return box;
    }
}
