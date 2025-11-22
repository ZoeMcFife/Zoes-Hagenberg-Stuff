package main.character;

import main.combat.ActionType;
import main.factory.baseFactories.ArmourFactory;
import main.factory.baseFactories.ShieldFactory;
import main.factory.baseFactories.WeaponFactory;
import main.global.GameManager;
import main.inventory.Inventory;
import main.item.*;
import main.ui.UIHelper;
import java.util.List;

/**
 * Base class for all game characters (players and enemies).
 * Manages character stats, equipment, inventory, and combat actions.
 */
public abstract class GameCharacter
{
    //region Fields and Constants

    /** The character's name */
    private String name;
    /** The next action this character will take in combat */
    public ActionType nextAction;

    /** The character's current health points */
    private double health;
    /** The character's maximum health points */
    private double maxHealth;

    /** The character's strength stat. Affects damage of non magic weapons.*/
    private int strength;
    /** The character's dexterity stat. Affects turn order in combat */
    private int dexterity;
    /** The character's intelligence stat. Affects damage of magic weapons.*/
    private int intelligence;

    /** Minimum allowed value for character stats */
    public static final int MIN_STAT_VALUE = 1;
    
    /** Maximum allowed value for character stats */
    public static final int MAX_STAT_VALUE = 10;

    private Weapon equippedWeapon = WeaponFactory.createBaseWeapon();
    private Shield equippedShield = ShieldFactory.createBaseShield();
    private Armour equippedArmour = ArmourFactory.createBaseArmour();

    /** Indicates whether the character is currently in a defensive stance */
    private boolean isDefending = false;

    private final Inventory inventory = new Inventory(this);

    //endregion

    //region Constructors
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

    //endregion

    //region Inventory Management

    /** Adds a single item to the character's inventory.
     *  Displays message by default.
     *  Weight Limit is not ignored by default.
     *
     * @param item The item to add
     */
    public void addItemToInventory(Item item)
    {
        addItemToInventory(item, true, false);
    }


    /** Adds a single item to the character's inventory.
     *  Weight Limit is not ignored by default.
     *
     * @param item The item to add
     * @param displayMessage if obtain message gets displayed or not
     */
    public void addItemToInventory(Item item, boolean displayMessage)
    {
        addItemToInventory(item, displayMessage, false);
    }

    /**
     * Adds a single item to the character's inventory.
     * 
     * @param item The item to add
     * @param displayMessage if obtain message gets displayed or not
     * @param ignoreWeightLimit if true, ignores carry weight limit when adding item
     */
    public void addItemToInventory(Item item, boolean displayMessage, boolean ignoreWeightLimit)
    {
        if (!ignoreWeightLimit)
        {
            if (inventory.getWeight() + item.getWeight() > getCarryCapacity())
            {
                if (displayMessage)
                {
                    IO.println(getName() + " cannot carry any more items!");
                }
                return;
            }
        }

        inventory.addItem(item);
        if (displayMessage)
        {
            IO.println(getName() + " obtained " + item.getName() + "!");
        }
    }

    /**
     * Adds multiple items to the character's inventory.
     * Directly adds to the inventory without weight checks or messages.
     * Only use in special cases.
     *
     * @param items Variable number of items to add
     */
    public void addItemsToInventory(Item... items)
    {
        inventory.addItems(items);
    }

    /**
     * Adds a list of items to the character's inventory.
     * Directly adds to the inventory without weight checks or messages.
     * Only use in special cases.
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
     * Calculates the character's carrying capacity based on strength.
     *
     * @return Maximum weight the character can carry
     */
    public double getCarryCapacity()
    {
        return strength * GameManager.CARRY_CAPACITY_PER_STRENGTH;
    }

    /**
     * Checks if the character can carry a specific item without exceeding capacity.
     *
     * @param selectedItem The item to check
     * @return true if the item can be carried, false otherwise
     */
    public boolean canCarry(Item selectedItem)
    {
        return (inventory.getWeight() + selectedItem.getWeight() <= getCarryCapacity());
    }

    //endregion

    // region Equipment Management

    /**
     * Equips an item to the appropriate slot.
     * Displays a message.
     * Stores previous item in inventory.
     *
     * @param item Item to be equipped.
     */
    public void equipItem(Item item)
    {
        equipItem(item, true, true);
    }

    /**
     * Equips an item to the appropriate slot.
     * Stores previous item in inventory.
     *
     * @param item Item to be equipped.
     */
    public void equipItem(Item item, boolean displayMessage)
    {
        equipItem(item, displayMessage, true);
    }

    /**
     * Equips an item to the appropriate slot.
     * Automatically adds currently equipped item back to inventory if it has weight.
     *
     *
     * @param item The item to equip
     * @param displayMessage if equip message gets displayed or not
     */
    public void equipItem(Item item, boolean displayMessage, boolean storePreviousItem)
    {
        switch (item)
        {
            case Weapon weapon ->
            {
                /* Since fists are Weapons, check if an item has weight or not before being added to inventory, so that the player can't have their fists in the inventory */
                if (!equippedWeapon.getName().equals("Fists") && storePreviousItem)
                {
                    addItemToInventory(equippedWeapon, displayMessage, false);
                }
                equippedWeapon = weapon;

                if (displayMessage)
                {
                    IO.println(getName() + " equipped " + item.getName() + "!");
                }

                inventory.removeItem(item);
            }
            case Shield shield ->
            {
                if (!equippedShield.getName().equals("Fists") && storePreviousItem)
                {
                    addItemToInventory(equippedShield, displayMessage, false);
                }
                equippedShield = shield;

                if (displayMessage)
                {
                    IO.println(getName() + " equipped " + item.getName() + "!");
                }

                inventory.removeItem(item);
            }
            case Armour armour ->
            {
                if (item.getWeight() != 0 && storePreviousItem)
                {
                    addItemToInventory(equippedArmour, displayMessage, false);
                }
                equippedArmour = armour;

                if (displayMessage)
                {
                    IO.println(getName() + " equipped " + item.getName() + "!");
                }

                inventory.removeItem(item);
            }
            case null, default ->
            {
                if (displayMessage)
                {
                    assert item != null;
                    IO.println("Cannot equip " + item.getName() + "!");
                }
            }
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

    /**
     * Uses an item from inventory.
     * Currently only supports HealingPotions.
     *
     * @param item The item to use
     */
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
    //endregion

    //region Combat Methods
    public boolean dodgeRoll()
    {
        double dodgeChance = dexterity * GameManager.DODGE_CHANCE_PER_DEXTERITY;
        double roll = Math.random();

        return roll < dodgeChance;
    }

    /**
     * Puts the character in a defensive stance.
     * While defending, the character gains additional defense from their shield.
     */
    public void defend()
    {
        IO.println(getName() + " is defending for " + equippedShield.getDefense() + " extra defense!");
        isDefending = true;
        
        // Grant PP to players when defending
        if (this instanceof Player player)
        {
            player.gainPP(equippedShield.getPpGain());
        }
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
     * Checks if the character is currently defending.
     *
     * @return true if defending, false otherwise
     */
    public boolean isDefending()
    {
        return isDefending;
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

        if (this instanceof Player player)
        {
            player.gainPP(equippedWeapon.getPpGainPerUse());
        }

        IO.println(getName() + " attacks " + target.getName() + " for " + Math.round(getDamage()) + " damage!");
        target.takeDamage(getDamage());
    }

    /**
     * Causes the character to repeatedly attack themselves until death.
     */
    public void suicide()
    {
        IO.println(getName() + " has given up.");
        UIHelper.delayLong();

        while (isAlive())
        {
            attack(this);
            UIHelper.delayShort();
            IO.println();
        }
        IO.println(getName() + " has given up.");
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
        if (dodgeRoll())
        {
            IO.println(getName() + " dodged the attack!");
            return;
        }

        double damageTaken = Math.max(damage - getCurrentDefense(), 0);
        IO.println(getName() + " takes " + Math.round(damageTaken) + " damage!");

        getEquippedArmour().reduceDurability(damageTaken);

        health -= damageTaken;
        if (health < 0)
        {
            health = 0;
            IO.println(getName() + " died!");
            onDeath();
        }
    }

    /**
     * Abstract method called when the character dies.
     * Subclasses must implement specific death behavior.
     */
    protected abstract void onDeath();

    //endregion

    //region Health
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

    //endregion

    //region Getters and Setters
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

    //endregion

    //region Stats
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
     * Increases the character's strength stat by a specified amount.
     * @param amount The amount to increase strength by
     */
    public void addStrength(int amount)
    {
        setStrength(this.strength + amount);
    }

    /**
     * Increases the character's dexterity stat by a specified amount.
     * @param amount The amount to increase dexterity by
     */
    public void addDexterity(int amount)
    {
        setDexterity(this.dexterity + amount);
    }

    /**
     * Increases the character's intelligence stat by a specified amount.
     * @param amount The amount to increase intelligence by
     */
    public void addIntelligence(int amount)
    {
        setIntelligence(this.intelligence + amount);
    }

    //endregion

    //region Display Box Methods

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


    //endregion
}
