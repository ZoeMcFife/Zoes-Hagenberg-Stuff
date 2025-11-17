package main.inventory;

import main.character.GameCharacter;
import main.character.Player;
import main.item.HealingPotion;
import main.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a character's inventory of items.
 * Enforces weight limits based on the character's carrying capacity.
 */
public class Inventory
{
    private List<Item> items = new ArrayList<>();
    private GameCharacter character;

    /**
     * Creates a new inventory for the specified character.
     * 
     * @param character The character who owns this inventory
     */
    public Inventory(GameCharacter character)
    {
        this.character = character;
    }

    /**
     * Calculates the total weight of all items in the inventory.
     * 
     * @return The total weight of all items
     */
    public double getWeight()
    {
        double totalWeight = 0.0;
        for (Item item : items)
        {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Adds an item to the inventory if there is enough carrying capacity.
     * If adding the item would exceed capacity, the item is not added.
     * 
     * @param item The item to add
     */
    public void addItem(Item item)
    {
        if (character.getCarryCapacity() < getWeight() + item.getWeight())
        {
            return;
        }

        items.add(item);
    }

    /**
     * Removes an item from the inventory.
     * 
     * @param item The item to remove
     */
    public void removeItem(Item item)
    {
        items.remove(item);
    }

    /**
     * Adds multiple items to the inventory.
     * Each item is checked against the carrying capacity individually.
     * 
     * @param items Array of items to add
     */
    public void addItems(Item[] items)
    {
        for (Item item : items)
        {
            addItem(item);
        }
    }

    /**
     * Checks if the inventory contains at least one healing item.
     * 
     * @return true if a healing potion is found, false otherwise
     */
    public boolean containsHealingItem()
    {
        for (Item item : items)
        {
            if (item instanceof HealingPotion)
            {
                return true;
            }
        }
        return false;
    }
}
