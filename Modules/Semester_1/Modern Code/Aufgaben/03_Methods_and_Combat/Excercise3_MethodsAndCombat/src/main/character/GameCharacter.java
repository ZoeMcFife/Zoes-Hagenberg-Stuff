package main.character;

import main.global.GameManager;
import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;

import java.util.ArrayList;
import java.util.List;

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

    private Weapon equippedWeapon = new Weapon("Fists", 0, 0, 1);
    private Shield equippedShield = new Shield("None", 0, 0, 0);
    private Armour equippedArmour = new Armour("Clothes", 0, 0, 0);

    public boolean isDefending = false;

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

    public void defend()
    {
        isDefending = true;
    }

    public void stopDefending()
    {
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
        return equippedWeapon.getDamage() * (1 + strength * GameManager.DAMAGE_MULTIPLIER_PER_STRENGTH);
    }

    public void takeDamage(double damage)
    {
        health -= Math.max(damage - getCurrentDefense(), 0);
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
