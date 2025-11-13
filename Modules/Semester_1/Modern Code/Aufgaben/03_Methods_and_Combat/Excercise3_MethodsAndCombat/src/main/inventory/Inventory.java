package main.inventory;

import main.character.GameCharacter;
import main.character.Player;
import main.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    private List<Item> items = new ArrayList<>();
    private GameCharacter character;

    public Inventory(GameCharacter character)
    {
        this.character = character;
    }

    public double getWeight()
    {
        double totalWeight = 0.0;
        for (Item item : items)
        {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public void addItem(Item item)
    {
        if (character.getCarryCapacity() < getWeight() + item.getWeight())
        {
            return;
        }

        items.add(item);
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }

}
