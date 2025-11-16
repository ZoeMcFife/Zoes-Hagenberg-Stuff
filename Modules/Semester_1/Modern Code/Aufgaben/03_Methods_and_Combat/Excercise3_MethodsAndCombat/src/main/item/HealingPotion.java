package main.item;

import main.global.IO;
public class HealingPotion extends Item
{
    public double healingAmount;

    public HealingPotion(String name, double weight, double value, double healingAmount)
    {
        super(name, weight, value);
        setHealingAmount(healingAmount);
    }

    public HealingPotion(String name, double weight, double value, double healingAmount, ItemRarity rarity)
    {
        super(name, weight, value, rarity);
        setHealingAmount(healingAmount);
    }

    public double getHealingAmount()
    {
        return healingAmount;
    }

    public void setHealingAmount(double healingAmount)
    {
        this.healingAmount = Math.max(0, healingAmount);
    }

    @Override
    public void printItemStats()
    {
        super.printItemStats();
        IO.println("Healing Amount:\t" + healingAmount);
    }
}
