package main.character;

import main.combat.ActionType;
import main.global.GameManager;
import main.inventory.Inventory;
import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;

import java.util.List;

public class GameCharacter
{
    private String name;
    public ActionType nextAction;

    private double health;
    private double maxHealth;

    private int strength;
    private int dexterity;
    private int intelligence;

    public static final int MIN_STAT_VALUE = 1;
    public static final int MAX_STAT_VALUE = 10;

    private Weapon equippedWeapon = new Weapon("Fists", 0, 0, 1, false);
    private Shield equippedShield = new Shield("None", 0, 0, 0);
    private Armour equippedArmour = new Armour("Clothes", 0, 0, 0);

    public boolean isDefending = false;

    //private CharacterStatus status = CharacterStatus.ALIVE;

    private Inventory inventory = new Inventory(this);

    public GameCharacter(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        setName(name);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setStrength(strength);
        setDexterity(dexterity);
        setIntelligence(intelligence);
    }

    public void addItemToInventory(Item item)
    {
        inventory.addItem(item);
    }

    public void addItemsToInventory(Item... items)
    {
        inventory.addItems(items);
    }

    public void addItemsToInventory(List<Item> items)
    {
        Item[] itemArray = new Item[items.size()];
        items.toArray(itemArray);

        inventory.addItems(itemArray);
    }

    public void defend()
    {
        IO.println(getName() + " is defending!");
        isDefending = true;
    }

    public void stopDefending()
    {
        IO.println(getName() + " stopped defending!");
        isDefending = false;
    }

    public double getHeatlthPercentage()
    {
        return (health / maxHealth);
    }

    public double getCarryCapacity()
    {
        return strength * GameManager.CARRY_CAPACITY_PER_STRENGTH;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

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

    public boolean canHeal()
    {
        return inventory.containsHealingItem();
    }

    public double getHealth()
    {
        return health;
    }

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

    public int getStrength()
    {
        return strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }

    public int getIntelligence()
    {
        return intelligence;
    }

    public double getCurrentDefense()
    {
        if (isDefending)
        {
            return (equippedArmour.getDefense() + equippedShield.getDefense());
        }

        return equippedArmour.getDefense();
    }

    public void attack(GameCharacter target)
    {
        if (!target.isAlive())
        {
            return;
        }

        IO.println(getName() + " attacks " + target.getName() + " for " + Math.round(getDamage()) + " damage!");
        target.takeDamage(getDamage());
    }

    public void equipItem(Item item)
    {
        if (item instanceof Weapon)
        {
            dropItem(equippedWeapon);
            equippedWeapon = (Weapon) item;
        }
        else if (item instanceof Shield)
        {
            dropItem(equippedWeapon);
            equippedShield = (Shield) item;
        }
        else if (item instanceof Armour)
        {
            dropItem(equippedWeapon);
            equippedArmour = (Armour) item;
        }
    }

    public void dropItem(Item item)
    {

    }

    public double getDamage()
    {
        if (equippedWeapon.isMagic())
        {
            return equippedWeapon.getDamage() * (1 + intelligence * GameManager.DAMAGE_MULTIPLIER_PER_INTELLIGENCE);
        }

        return equippedWeapon.getDamage() * (1 + strength * GameManager.DAMAGE_MULTIPLIER_PER_STRENGTH);
    }

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

    public boolean isAlive()
    {
        return health > 0;
    }

    public CharacterStatus getStatus()
    {
        if (getHeatlthPercentage() == 1)
        {
            return CharacterStatus.ALIVE;
        }

        if (getHeatlthPercentage() > 0.5)
        {
            return CharacterStatus.HURT;
        }

        if (getHeatlthPercentage() > 0.3)
        {
            return CharacterStatus.SEVERELY_HURT;
        }

        if (getHeatlthPercentage() > 0)
        {
            return CharacterStatus.CRITICALLY_HURT;
        }

        return CharacterStatus.DEAD;

    }

    public void setMaxHealth(double maxHealth)
    {
        if (maxHealth < 1)
        {
            this.maxHealth = 1;
            return;
        }

        this.maxHealth = maxHealth;
    }

    public double getMaxHealth()
    {
        return maxHealth;
    }

    public Weapon getEquippedWeapon()
    {
        return equippedWeapon;
    }

    public Armour getEquippedArmour()
    {
        return equippedArmour;
    }

    public  Shield getEquippedShield()
    {
        return equippedShield;
    }

    public List<String> getDisplayBox()
    {
        List<String> box = new java.util.ArrayList<>();

        String nameLine = String.format("| %-36s |", getName());
        String hpLine   = String.format("| Health: %.0f / %.0f%20s|", getHealth(), getMaxHealth(), "");

        int barLength = 30;
        double pct = getHeatlthPercentage();
        int filled = (int) (pct * barLength);
        String bar = "=".repeat(filled) + " ".repeat(barLength - filled);
        String barLine = "| " + bar + " |";

        String state = getStatus().toString();
        String stateLine = String.format("| %-36s |", "(" + state + ")");

        String border = "----------------------------------------";

        box.add(border);
        box.add(nameLine);
        box.add(hpLine);
        box.add("| " + bar + "       |");
        box.add(stateLine);
        box.add(border);

        return box;
    }

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
