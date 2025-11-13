package main.item;

import static java.io.IO.*;

public class Weapon extends Item
{
    private double damage;
    private boolean isMagic;

    public Weapon(String name, double weight, double value, double damage, boolean isMagic)
    {
        super(name, weight, value);
        setDamage(damage);
        setMagic(isMagic);
    }

    public Weapon(String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setDamage(damage);
        setMagic(isMagic);
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = Math.max(0, damage);
    }

    public boolean isMagic()
    {
        return isMagic;
    }

    public void setMagic(boolean isMagic)
    {
        this.isMagic = isMagic;
    }

    @Override
    public void printItemStats()
    {
        super.printItemStats();
        println("Damage:\t" + damage);
        println("Is Magic:\t" + isMagic);
    }
}
