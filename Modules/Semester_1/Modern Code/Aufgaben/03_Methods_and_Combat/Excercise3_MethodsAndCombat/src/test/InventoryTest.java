package test;

import main.character.Player;
import main.inventory.Inventory;
import main.item.Item;
import main.item.Weapon;
import main.item.Armour;
import main.item.HealingPotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest
{
    private Player testPlayer;
    private Inventory testInventory;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 10, 5, 5);
        testInventory = new Inventory(testPlayer);
    }

    @Test
    void testInventoryCreation()
    {
        assertNotNull(testInventory);
        assertEquals(0, testInventory.getWeight());
    }

    @Test
    void testAddItem()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, 10, false);
        testInventory.addItem(sword);
        assertEquals(5.0, testInventory.getWeight());
    }

    @Test
    void testRemoveItem()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, 10, false);
        testInventory.addItem(sword);
        assertEquals(5.0, testInventory.getWeight());
        
        testInventory.removeItem(sword);
        assertEquals(0, testInventory.getWeight());
    }

    @Test
    void testAddMultipleItems()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, 10, false);
        Armour armour = new Armour("Test Armour", 10.0, 200, 15);
        HealingPotion potion = new HealingPotion("Test Potion", 1.0, 50, 20);
        
        Item[] items = {sword, armour, potion};
        testInventory.addItems(items);
        
        assertEquals(16.0, testInventory.getWeight());
    }

    @Test
    void testWeightCalculation()
    {
        Weapon lightSword = new Weapon("Light Sword", 2.0, 50, 5, false);
        Weapon heavySword = new Weapon("Heavy Sword", 8.0, 150, 15, false);
        
        testInventory.addItem(lightSword);
        testInventory.addItem(heavySword);
        
        assertEquals(10.0, testInventory.getWeight());
    }

    @Test
    void testCarryCapacityLimit()
    {
        // Player with strength 10 should have limited carry capacity
        double carryCapacity = testPlayer.getCarryCapacity();
        
        // Try to add an item that exceeds capacity
        Weapon veryHeavyWeapon = new Weapon("Very Heavy Weapon", carryCapacity + 10, 1000, 50, false);
        testInventory.addItem(veryHeavyWeapon);
        
        // Item should not be added if it exceeds capacity
        assertEquals(0, testInventory.getWeight());
    }

    @Test
    void testAddItemsWithinCapacity()
    {
        Weapon lightWeapon = new Weapon("Light Weapon", 2.0, 50, 5, false);
        Armour lightArmour = new Armour("Light Armour", 3.0, 100, 10);
        
        testInventory.addItem(lightWeapon);
        testInventory.addItem(lightArmour);
        
        assertEquals(5.0, testInventory.getWeight());
    }
}
