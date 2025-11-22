package main.character;

import main.global.GameManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player character in the game.
 * Extends GameCharacter with player-specific defaults and functionality.
 */
public class Player extends GameCharacter
{
    private int level = 1;
    private int experience = 0;
    private int availableStatPoints = 0;
    private int currentPP = 0;
    private int maxPP = 100;

    /** Default maximum health for all player characters */
    public static double DEFAULT_PLAYER_MAX_HEALTH = 100.0;
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

    public int getLevel()
    {
        return this.level;
    }

    public int getExperience()
    {
        return this.experience;
    }

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

    public int getExperienceNeededForNextLevel()
    {
        return (50 * this.level);
    }

    public int getAvailableStatPoints()
    {
        return this.availableStatPoints;
    }

    public void spendStatPoint()
    {
        this.availableStatPoints -= 1;

        if (this.availableStatPoints < 0)
        {
            this.availableStatPoints = 0;
        }
    }

    private void levelUp()
    {
        this.level += 1;
        this.availableStatPoints += GameManager.STAT_POINTS_PER_LEVEL;
        setMaxPP(getMaxPP() + GameManager.MAX_PP_INCREASE_PER_LEVEL);
        setMaxHealth(getMaxHealth() + GameManager.HEALTH_INCREASE_PER_LEVEL);
        setHealth(getMaxHealth());

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
     * Uses the equipped weapon's special attack on a target.
     * Checks if player has enough PP, deducts cost, deals damage, and displays flavor text.
     * 
     * @param target The character to attack with the special
     */
    public void useSpecial(GameCharacter target)
    {
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
        
        target.takeDamage(totalDamage);
    }

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
