package main.character;

import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;

public class GameCharacter
{
    private String name;

    private double health;
    private double maxHealth;

    private int strength;
    private int dexterity;
    private int intelligence;

    public static final int MIN_STAT_VALUE = 1;
    public static final int MAX_STAT_VALUE = 10;

    public static final int CARRY_CAPACITY_PER_STRENGTH = 10;

    private Weapon equippedWeapon = new Weapon();
    private Shield equippedShield = new Shield();
    private Armour equippedArmour = new Armour();

    private CharacterStatus status = CharacterStatus.ALIVE;

    public GameCharacter(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        setName(name);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setStrength(strength);
        setDexterity(dexterity);
        setIntelligence(intelligence);
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

    public int getCurrentDefense()
    {
        return 1;
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

    public void takeDamage(int damage)
    {


        health -= damage;
        if (health < 0)
        {
            health = 0;
        }
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public String getStatus()
    {
        switch (status)
        {
            case ALIVE ->
            {
                return "Alive";
            }
            case DEAD ->
            {
                return "Dead";
            }
        }

        return "Unknown";
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
}
