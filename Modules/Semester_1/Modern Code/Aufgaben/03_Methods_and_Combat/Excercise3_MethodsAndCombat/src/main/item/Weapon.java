package main.item;

public class Weapon extends Item
{
    private double damage;

    public Weapon(String name, double weight, double value, double damage)
    {
        super(name, weight, value);
        setDamage(damage);
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = Math.max(0, damage);
    }

    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Damage:\t" + damage);
    }
}
