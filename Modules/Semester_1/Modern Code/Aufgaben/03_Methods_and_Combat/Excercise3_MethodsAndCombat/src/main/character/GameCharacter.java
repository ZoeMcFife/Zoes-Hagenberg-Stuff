package main.character;

import main.combat.ActionType;
import main.factory.baseFactories.ArmourFactory;
import main.factory.baseFactories.ShieldFactory;
import main.factory.baseFactories.WeaponFactory;
import main.global.GameManager;
import main.inventory.Inventory;
import main.item.*;
import main.ui.UserInterfaceHelper;

import java.util.List;

/**
 * Base class for all game characters (players and enemies).
 * Manages character stats, equipment, inventory, and combat actions.
 */
public class GameCharacter
{
    private String name;
    /** The next action this character will take in combat */
    public ActionType nextAction;

    private double health;
    private double maxHealth;

    private int strength;
    private int dexterity;
    private int intelligence;

    /** Minimum allowed value for character stats */
    public static final int MIN_STAT_VALUE = 1;
    
    /** Maximum allowed value for character stats */
    public static final int MAX_STAT_VALUE = 10;

    private Weapon equippedWeapon = WeaponFactory.createBaseWeapon();
    private Shield equippedShield = ShieldFactory.createBaseShield();
    private Armour equippedArmour = ArmourFactory.createBaseArmour();

    /** Indicates whether the character is currently in a defensive stance */
    public boolean isDefending = false;

    private final Inventory inventory = new Inventory(this);

    /**
     * Creates a new game character with specified attributes.
     * 
     * @param name The character's name
     * @param maxHealth The maximum health points
     * @param strength Strength stat (affects physical damage, range 1-10)
     * @param dexterity Dexterity stat (affects turn order, range 1-10)
     * @param intelligence Intelligence stat (affects magic damage, range 1-10)
     */
    public GameCharacter(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        setName(name);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setStrength(strength);
        setDexterity(dexterity);
        setIntelligence(intelligence);
    }

    /**
     * Adds a single item to the character's inventory.
     * 
     * @param item The item to add
     */
    public void addItemToInventory(Item item)
    {
        inventory.addItem(item);
        IO.println(getName() + " obtained " + item.getName() + "!");
    }

    /**
     * Adds multiple items to the character's inventory.
     * 
     * @param items Variable number of items to add
     */
    public void addItemsToInventory(Item... items)
    {
        inventory.addItems(items);
    }

    /**
     * Adds a list of items to the character's inventory.
     * 
     * @param items List of items to add
     */
    public void addItemsToInventory(List<Item> items)
    {
        Item[] itemArray = new Item[items.size()];
        items.toArray(itemArray);

        inventory.addItems(itemArray);
    }

    /**
     * Gets the character's inventory.
     *
     * @return The inventory object
     */
    public Inventory getInventory()
    {
        return inventory;
    }

    /**
     * Puts the character in a defensive stance.
     * While defending, the character gains additional defense from their shield.
     */
    public void defend()
    {
        IO.println(getName() + " is defending!");
        isDefending = true;
    }

    /**
     * Removes the character from defensive stance.
     */
    public void stopDefending()
    {
        IO.println(getName() + " stopped defending!");
        isDefending = false;
    }

    /**
     * Calculates the character's current health as a percentage of max health.
     * 
     * @return Health percentage (0.0 to 1.0)
     */
    public double getHealthPercentage()
    {
        return (health / maxHealth);
    }

    /**
     * Calculates the character's carrying capacity based on strength.
     * 
     * @return Maximum weight the character can carry
     */
    public double getCarryCapacity()
    {
        return strength * GameManager.CARRY_CAPACITY_PER_STRENGTH;
    }

    /**
     * Sets the character's name.
     * 
     * @param name The new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the character's name.
     * 
     * @return The character's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the character's current health.
     * Automatically caps health at max health and floors at 0.
     * 
     * @param health The new health value
     */
    public void setHealth(double health)
    {
        if (health > maxHealth)
        {
            this.health = maxHealth;
            return;
        }

        if (health < 0)
        {
            this.health = 0;
            return;
        }

        this.health = health;
    }

    /**
     * Checks if the character has any healing items in their inventory.
     * 
     * @return true if character can heal, false otherwise
     */
    public boolean canHeal()
    {
        return inventory.containsHealingItem();
    }

    /**
     * Gets the character's current health.
     * 
     * @return Current health points
     */
    public double getHealth()
    {
        return health;
    }

    /**
     * Sets the character's strength stat.
     * Automatically clamps value between MIN_STAT_VALUE and MAX_STAT_VALUE.
     * 
     * @param strength The new strength value
     */
    public void setStrength(int strength)
    {
        if (strength < MIN_STAT_VALUE)
        {
            this.strength = MIN_STAT_VALUE;
            return;
        }

        if (strength > MAX_STAT_VALUE)
        {
            this.strength = MAX_STAT_VALUE;
            return;
        }

        this.strength = strength;
    }

    /**
     * Sets the character's dexterity stat.
     * Automatically clamps value between MIN_STAT_VALUE and MAX_STAT_VALUE.
     * 
     * @param dexterity The new dexterity value
     */
    public void setDexterity(int dexterity)
    {
        if (dexterity < MIN_STAT_VALUE)
        {
            this.dexterity = MIN_STAT_VALUE;
            return;
        }

        if (dexterity > MAX_STAT_VALUE)
        {
            this.dexterity = MAX_STAT_VALUE;
            return;
        }

        this.dexterity = dexterity;
    }

    /**
     * Sets the character's intelligence stat.
     * Automatically clamps value between MIN_STAT_VALUE and MAX_STAT_VALUE.
     * 
     * @param intelligence The new intelligence value
     */
    public void setIntelligence(int intelligence)
    {
        if (intelligence < MIN_STAT_VALUE)
        {
            this.intelligence = MIN_STAT_VALUE;
            return;
        }

        if (intelligence > MAX_STAT_VALUE)
        {
            this.intelligence = MAX_STAT_VALUE;
            return;
        }

        this.intelligence = intelligence;
    }

    /**
     * Gets the character's strength stat.
     * 
     * @return Strength value
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * Gets the character's dexterity stat.
     * 
     * @return Dexterity value
     */
    public int getDexterity()
    {
        return dexterity;
    }

    /**
     * Gets the character's intelligence stat.
     * 
     * @return Intelligence value
     */
    public int getIntelligence()
    {
        return intelligence;
    }

    /**
     * Calculates the character's total defense value.
     * Includes shield defense only when actively defending.
     * 
     * @return Total defense points
     */
    public double getCurrentDefense()
    {
        if (isDefending)
        {
            return (equippedArmour.getDefense() + equippedShield.getDefense());
        }

        return equippedArmour.getDefense();
    }

    /**
     * Attacks another character.
     * Does not attack if the target is already dead.
     * 
     * @param target The character to attack
     */
    public void attack(GameCharacter target)
    {
        if (!target.isAlive())
        {
            return;
        }

        IO.println(getName() + " attacks " + target.getName() + " for " + Math.round(getDamage()) + " damage!");
        target.takeDamage(getDamage());
    }

    /**
     * Causes the character to repeatedly attack themselves until death.
     */
    public void suicide()
    {
        while (isAlive())
        {
            attack(this);
            UserInterfaceHelper.delay(1);
            IO.println(getName() + " has given up.");
            IO.println();
        }
    }

    /**
     * Equips an item to the appropriate slot.
     * Automatically adds currently equipped item back to inventory if it has weight.
     *
     * @param item The item to equip
     */
    public void equipItem(Item item)
    {
        if (item instanceof Weapon)
        {
            if (item.getWeight() != 0) /* Since fists are Weapons, check if an item has weight or not before being added to inventory, so that the player can't have their fists in the inventory */
            {
                addItemToInventory(equippedWeapon);
            }
            equippedWeapon = (Weapon) item;
        }
        else if (item instanceof Shield)
        {
            if (item.getWeight() != 0)
            {
                addItemToInventory(equippedShield);
            }
            equippedShield = (Shield) item;
        }
        else if (item instanceof Armour)
        {
            if (item.getWeight() != 0)
            {
                addItemToInventory(equippedArmour);
            }
            equippedArmour = (Armour) item;
        }
    }

    /**
     * Drops an item from inventory.
     * 
     * @param item The item to drop
     */
    public void dropItem(Item item)
    {
        inventory.removeItem(item);
        IO.println(getName() + " dropped " + item.getName() + "!");
    }

    public void useItem(Item item)
    {
        if (item instanceof HealingPotion)
        {
            setHealth(getHealth() + ((HealingPotion) item).getHealingAmount());
            IO.println(getName() + " used " + item.getName() + " and healed for " + ((HealingPotion) item).getHealingAmount() + " health!");
            inventory.removeItem(item);
        }
        else
        {
            IO.println("Cannot use " + item.getName() + "!");
        }
    }

    /**
     * Calculates the character's damage output.
     * Uses strength for physical weapons, intelligence for magical weapons.
     * 
     * @return Total damage the character can deal
     */
    public double getDamage()
    {
        if (equippedWeapon.isMagic())
        {
            return equippedWeapon.getDamage() * (1 + intelligence * GameManager.DAMAGE_MULTIPLIER_PER_INTELLIGENCE);
        }

        return equippedWeapon.getDamage() * (1 + strength * GameManager.DAMAGE_MULTIPLIER_PER_STRENGTH);
    }

    /**
     * Applies damage to the character.
     * Damage is reduced by defense, then subtracted from health.
     * Character dies if health reaches 0.
     * 
     * @param damage The raw damage to apply
     */
    public void takeDamage(double damage)
    {
        double damageTaken = Math.max(damage - getCurrentDefense(), 0);
        IO.println(getName() + " takes " + Math.round(damageTaken) + " damage!");

        health -= damageTaken;
        if (health < 0)
        {
            health = 0;
            IO.println(getName() + " died!");
        }
    }

    /**
     * Checks if the character is still alive.
     * 
     * @return true if health is greater than 0, false otherwise
     */
    public boolean isAlive()
    {
        return health > 0;
    }

    /**
     * Determines the character's current status based on health percentage.
     * 
     * @return The character's status (ALIVE, HURT, SEVERELY_HURT, CRITICALLY_HURT, or DEAD)
     */
    public CharacterStatus getStatus()
    {
        if (getHealthPercentage() == 1)
        {
            return CharacterStatus.ALIVE;
        }

        if (getHealthPercentage() > 0.5)
        {
            return CharacterStatus.HURT;
        }

        if (getHealthPercentage() > 0.3)
        {
            return CharacterStatus.SEVERELY_HURT;
        }

        if (getHealthPercentage() > 0)
        {
            return CharacterStatus.CRITICALLY_HURT;
        }

        return CharacterStatus.DEAD;

    }

    /**
     * Sets the character's maximum health.
     * Minimum value is 1.
     * 
     * @param maxHealth The new maximum health
     */
    public void setMaxHealth(double maxHealth)
    {
        if (maxHealth < 1)
        {
            this.maxHealth = 1;
            return;
        }

        this.maxHealth = maxHealth;
    }

    /**
     * Gets the character's maximum health.
     * 
     * @return Maximum health points
     */
    public double getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Gets the character's equipped weapon.
     * 
     * @return The equipped weapon
     */
    public Weapon getEquippedWeapon()
    {
        return equippedWeapon;
    }

    /**
     * Gets the character's equipped armour.
     * 
     * @return The equipped armour
     */
    public Armour getEquippedArmour()
    {
        return equippedArmour;
    }

    /**
     * Gets the character's equipped shield.
     * 
     * @return The equipped shield
     */
    public  Shield getEquippedShield()
    {
        return equippedShield;
    }

    /**
     * Generates a text-based display box showing character information.
     * Includes name, health, stats, attack/defense values, and status.
     * 
     * @return List of strings representing lines of the display box
     */
    public List<String> getDisplayBox()
    {
        List<String> box = new java.util.ArrayList<>();

        String nameLine = String.format("| %-36s |", getName());
        
        // Format health with proper spacing to fit in 36 characters
        String healthText = String.format("Health: %.0f / %.0f", getHealth(), getMaxHealth());
        String hpLine = String.format("| %-36s |", healthText);

        // Health bar (36 '=' or ' ' characters to fill the entire box width)
        int barLength = 36;
        double pct = getHealthPercentage();
        int filled = (int) (pct * barLength);
        String bar = "=".repeat(filled) + " ".repeat(barLength - filled);
        String barLine = String.format("| %-36s |", bar);

        // Stats line in format: D6, I2, S10
        String statsText = String.format("D%d, I%d, S%d", getDexterity(), getIntelligence(), getStrength());
        String statsLine = String.format("| %-36s |", statsText);

        // Attack and Defense stats line
        String attackDefenseText = String.format("Attack: %.0f, Defense: %.0f", getDamage(), getCurrentDefense());
        String attackDefenseLine = String.format("| %-36s |", attackDefenseText);

        String state = getStatus().toString();
        String stateLine = String.format("| %-36s |", "(" + state + ")");

        String border = "----------------------------------------";

        box.add(border);
        box.add(nameLine);
        box.add(hpLine);
        box.add(barLine);
        box.add(statsLine);
        box.add(attackDefenseLine);
        box.add(stateLine);
        box.add(border);

        return box;
    }

    /**
     * Combines multiple character display boxes side by side.
     * Used to display multiple enemies in a single view.
     * 
     * @param boxes List of display boxes to combine
     * @return Combined display with boxes arranged horizontally
     */
    public static List<String> combineEnemyBoxes(List<List<String>> boxes)
    {
        List<String> result = new java.util.ArrayList<>();

        int height = boxes.getFirst().size(); // all same height

        for (int line = 0; line < height; line++)
        {
            StringBuilder sb = new StringBuilder();

            for (List<String> box : boxes)
            {
                sb.append(box.get(line)).append("   "); // 3 spaces padding
            }

            result.add(sb.toString());
        }

        return result;
    }

}
