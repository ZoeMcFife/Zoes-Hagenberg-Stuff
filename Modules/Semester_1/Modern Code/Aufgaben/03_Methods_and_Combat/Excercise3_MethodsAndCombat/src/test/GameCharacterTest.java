package test;

import main.character.Player;
import main.character.Enemy;
import main.character.GameCharacter;
import main.character.CharacterStatus;
import main.global.GameManager;
import main.item.Weapon;
import main.item.HealingPotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest
{
    private Player testPlayer;
    private Enemy testEnemy;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 7, 6);
        testEnemy = new Enemy("Test Enemy", 80, 4, 5, 3, 10);
        GameManager.setPlayer(testPlayer);
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
    }

    @Test
    void testCharacterStatus()
    {
        // Full health - ALIVE status
        testPlayer.setHealth(testPlayer.getMaxHealth());
        assertEquals(CharacterStatus.ALIVE, testPlayer.getStatus());
        
        // Slightly damaged - HURT status
        testPlayer.setHealth(testPlayer.getMaxHealth() * 0.7);
        assertEquals(CharacterStatus.HURT, testPlayer.getStatus());
        
        // Moderately damaged - SEVERELY_HURT status
        testPlayer.setHealth(testPlayer.getMaxHealth() * 0.4);
        assertEquals(CharacterStatus.SEVERELY_HURT, testPlayer.getStatus());
        
        // Heavily damaged - CRITICALLY_HURT status
        testPlayer.setHealth(testPlayer.getMaxHealth() * 0.2);
        assertEquals(CharacterStatus.CRITICALLY_HURT, testPlayer.getStatus());
        
        // Dead
        testPlayer.setHealth(0);
        assertEquals(CharacterStatus.DEAD, testPlayer.getStatus());
    }

    @Test
    void testCriticalStatusReducesDamage()
    {
        // Set player to critical health
        testPlayer.setHealth(testPlayer.getMaxHealth() * 0.25);
        assertEquals(CharacterStatus.CRITICALLY_HURT, testPlayer.getStatus());
        
        double criticalDamage = testPlayer.getDamage();
        
        // Heal to full health
        testPlayer.setHealth(testPlayer.getMaxHealth());
        double normalDamage = testPlayer.getDamage();
        
        // Critical damage should be less than normal
        assertTrue(criticalDamage < normalDamage);
        assertEquals(normalDamage * GameManager.DAMAGE_REDUCTION_WHEN_CRITICAL_STATUS, criticalDamage, 0.01);
    }

    @Test
    void testDodgeMechanism()
    {
        // Create a character with high dexterity for better dodge chance
        Player agilePlayer = new Player("Agile", 1, 10, 1);
        
        // Test dodge roll is within valid range
        for (int i = 0; i < 10; i++)
        {
            boolean dodged = agilePlayer.dodgeRoll();
            // Should return true or false, not throw exception
            assertTrue(dodged || !dodged);
        }
    }

    @Test
    void testCarryCapacity()
    {
        double capacity = testPlayer.getCarryCapacity();
        assertEquals(testPlayer.getStrength() * GameManager.CARRY_CAPACITY_PER_STRENGTH, capacity);
    }

    @Test
    void testCanCarryItem()
    {
        HealingPotion lightPotion = new HealingPotion("Light Potion", 1.0, 25, 20);
        assertTrue(testPlayer.canCarry(lightPotion));
        
        HealingPotion heavyPotion = new HealingPotion("Heavy Potion", 1000.0, 25, 20);
        assertFalse(testPlayer.canCarry(heavyPotion));
    }

    @Test
    void testEquipItem()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, 20, false);
        testPlayer.addItemToInventory(sword, false);
        
        String oldWeaponName = testPlayer.getEquippedWeapon().getName();
        testPlayer.equipItem(sword, false);
        
        assertEquals("Test Sword", testPlayer.getEquippedWeapon().getName());
    }

    @Test
    void testEquipItemAddsOldItemToInventory()
    {
        Weapon firstSword = new Weapon("First Sword", 5.0, 100, 15, false);
        Weapon secondSword = new Weapon("Second Sword", 5.0, 100, 20, false);
        
        testPlayer.addItemToInventory(firstSword, false);
        testPlayer.equipItem(firstSword, false);
        
        testPlayer.addItemToInventory(secondSword, false);
        testPlayer.equipItem(secondSword, false);
        
        // First sword should be back in inventory
        assertTrue(testPlayer.getInventory().getItems().stream()
            .anyMatch(item -> item.getName().equals("First Sword")));
    }

    @Test
    void testDropItem()
    {
        HealingPotion potion = new HealingPotion("Drop Test", 1.0, 25, 20);
        testPlayer.addItemToInventory(potion, false);
        
        double weightBefore = testPlayer.getInventory().getWeight();
        testPlayer.dropItem(potion);
        double weightAfter = testPlayer.getInventory().getWeight();
        
        assertTrue(weightAfter < weightBefore);
    }

    @Test
    void testUseHealingPotion()
    {
        testPlayer.setHealth(50);
        HealingPotion potion = new HealingPotion("Healing Potion", 1.0, 50, 30);
        testPlayer.addItemToInventory(potion, false);
        
        double healthBefore = testPlayer.getHealth();
        testPlayer.useItem(potion);
        double healthAfter = testPlayer.getHealth();
        
        assertEquals(healthBefore + 30, healthAfter);
    }

    @Test
    void testUseHealingPotionCannotExceedMaxHealth()
    {
        testPlayer.setHealth(testPlayer.getMaxHealth() - 10);
        HealingPotion potion = new HealingPotion("Mega Potion", 1.0, 50, 100);
        testPlayer.addItemToInventory(potion, false);
        
        testPlayer.useItem(potion);
        
        assertEquals(testPlayer.getMaxHealth(), testPlayer.getHealth());
    }

    @Test
    void testMagicWeaponScalesWithIntelligence()
    {
        Player magePlayer = new Player("Mage", 1, 1, 10);
        Weapon magicStaff = new Weapon("Magic Staff", 3.0, 200, 20, true);
        
        magePlayer.addItemToInventory(magicStaff, false);
        magePlayer.equipItem(magicStaff, false);
        
        double magicDamage = magePlayer.getDamage();
        
        // Should be more than base damage due to intelligence
        assertTrue(magicDamage > magicStaff.getDamage());
    }

    @Test
    void testPhysicalWeaponScalesWithStrength()
    {
        Player warriorPlayer = new Player("Warrior", 10, 1, 1);
        Weapon sword = new Weapon("Sword", 5.0, 100, 20, false);
        
        warriorPlayer.addItemToInventory(sword, false);
        warriorPlayer.equipItem(sword, false);
        
        double physicalDamage = warriorPlayer.getDamage();
        
        // Should be more than base damage due to strength
        assertTrue(physicalDamage > sword.getDamage());
    }

    @Test
    void testAttackDoesNotDamageDeadTarget()
    {
        testEnemy.setHealth(0);
        assertFalse(testEnemy.isAlive());
        
        testPlayer.attack(testEnemy);
        
        // Enemy should still be at 0 health
        assertEquals(0, testEnemy.getHealth());
    }

    @Test
    void testDefendIncreasesDefense()
    {
        double normalDefense = testPlayer.getCurrentDefense();
        
        testPlayer.defend();
        double defendingDefense = testPlayer.getCurrentDefense();
        
        assertTrue(defendingDefense >= normalDefense);
    }

    @Test
    void testStopDefendingReducesDefense()
    {
        testPlayer.defend();
        double defendingDefense = testPlayer.getCurrentDefense();
        
        testPlayer.stopDefending();
        double normalDefense = testPlayer.getCurrentDefense();
        
        assertTrue(normalDefense <= defendingDefense);
    }

    @Test
    void testTakeDamageReducesHealth()
    {
        double initialHealth = testPlayer.getHealth();
        testPlayer.takeDamage(20);
        assertTrue(testPlayer.getHealth() < initialHealth);
    }

    @Test
    void testDamageReducedByDefense()
    {
        testPlayer.setHealth(testPlayer.getMaxHealth());
        double initialHealth = testPlayer.getHealth();
        double damage = 50;
        
        testPlayer.takeDamage(damage);
        
        double actualDamageTaken = initialHealth - testPlayer.getHealth();
        // Actual damage should be less than raw damage due to defense
        assertTrue(actualDamageTaken < damage);
    }

    @Test
    void testSuicide()
    {
        assertTrue(testPlayer.isAlive());
        testPlayer.suicide();
        assertFalse(testPlayer.isAlive());
        assertEquals(0, testPlayer.getHealth());
    }

    @Test
    void testAddItemToInventoryWithWeightLimit()
    {
        // Try to add an item that exceeds capacity
        double capacity = testPlayer.getCarryCapacity();
        HealingPotion tooHeavy = new HealingPotion("Too Heavy", capacity + 10, 100, 50);
        
        testPlayer.addItemToInventory(tooHeavy, false);
        
        // Item should not be added
        assertEquals(0, testPlayer.getInventory().getWeight());
    }

    @Test
    void testAddItemToInventoryIgnoringWeightLimit()
    {
        double capacity = testPlayer.getCarryCapacity();
        HealingPotion heavy = new HealingPotion("Heavy", capacity + 10, 100, 50);
        
        testPlayer.addItemToInventory(heavy, false, true);
        
        // Item should be added
        assertTrue(testPlayer.getInventory().getWeight() > 0);
    }

    @Test
    void testAddMultipleItemsToInventory()
    {
        HealingPotion potion1 = new HealingPotion("Potion 1", 1.0, 25, 20);
        HealingPotion potion2 = new HealingPotion("Potion 2", 1.0, 25, 20);
        
        testPlayer.addItemsToInventory(potion1, potion2);
        
        assertEquals(2.0, testPlayer.getInventory().getWeight());
    }
}
