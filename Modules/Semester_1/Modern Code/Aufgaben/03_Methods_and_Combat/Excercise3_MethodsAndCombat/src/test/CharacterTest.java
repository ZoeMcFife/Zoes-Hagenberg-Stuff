package test;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest
{
    private Player testPlayer;
    private GameCharacter testEnemy;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 7, 6);
        testEnemy = new Enemy("Test Enemy", 80, 4, 5, 3, 3);
    }

    @Test
    void testPlayerCreation()
    {
        assertNotNull(testPlayer);
        assertEquals("Test Hero", testPlayer.getName());
        assertEquals(Player.DEFAULT_PLAYER_MAX_HEALTH, testPlayer.getMaxHealth());
        assertEquals(5, testPlayer.getStrength());
        assertEquals(7, testPlayer.getDexterity());
        assertEquals(6, testPlayer.getIntelligence());
    }

    @Test
    void testGameCharacterCreation()
    {
        assertNotNull(testEnemy);
        assertEquals("Test Enemy", testEnemy.getName());
        assertEquals(80, testEnemy.getMaxHealth());
        assertEquals(4, testEnemy.getStrength());
        assertEquals(5, testEnemy.getDexterity());
        assertEquals(3, testEnemy.getIntelligence());
    }

    @Test
    void testHealthManagement()
    {
        testPlayer.setHealth(50);
        assertEquals(50, testPlayer.getHealth());
        
        // Test health cannot exceed max health
        testPlayer.setHealth(150);
        assertEquals(Player.DEFAULT_PLAYER_MAX_HEALTH, testPlayer.getHealth());
        
        // Test health cannot go below 0
        testPlayer.setHealth(-10);
        assertEquals(0, testPlayer.getHealth());
    }

    @Test
    void testIsAlive()
    {
        assertTrue(testPlayer.isAlive());
        testPlayer.setHealth(0);
        assertFalse(testPlayer.isAlive());
    }

    @Test
    void testDefendMechanism()
    {
        assertFalse(testPlayer.isDefending());
        testPlayer.defend();
        assertTrue(testPlayer.isDefending());
        testPlayer.stopDefending();
        assertFalse(testPlayer.isDefending());
    }

    @Test
    void testTakeDamageWithoutDefense()
    {
        double initialHealth = testPlayer.getHealth();
        testPlayer.takeDamage(20);
        assertTrue(testPlayer.getHealth() < initialHealth);
    }

    @Test
    void testStatBoundaries()
    {
        // Test strength boundaries
        testPlayer.setStrength(0);
        assertEquals(GameCharacter.MIN_STAT_VALUE, testPlayer.getStrength());
        
        testPlayer.setStrength(15);
        assertEquals(GameCharacter.MAX_STAT_VALUE, testPlayer.getStrength());
        
        // Test dexterity boundaries
        testPlayer.setDexterity(-5);
        assertEquals(GameCharacter.MIN_STAT_VALUE, testPlayer.getDexterity());
        
        testPlayer.setDexterity(20);
        assertEquals(GameCharacter.MAX_STAT_VALUE, testPlayer.getDexterity());
        
        // Test intelligence boundaries
        testPlayer.setIntelligence(0);
        assertEquals(GameCharacter.MIN_STAT_VALUE, testPlayer.getIntelligence());
        
        testPlayer.setIntelligence(12);
        assertEquals(GameCharacter.MAX_STAT_VALUE, testPlayer.getIntelligence());
    }

    @Test
    void testHealthPercentage()
    {
        testPlayer.setHealth(testPlayer.getMaxHealth());
        assertEquals(1.0, testPlayer.getHealthPercentage(), 0.01);
        
        testPlayer.setHealth(testPlayer.getMaxHealth() / 2);
        assertEquals(0.5, testPlayer.getHealthPercentage(), 0.01);
        
        testPlayer.setHealth(0);
        assertEquals(0.0, testPlayer.getHealthPercentage(), 0.01);
    }

    @Test
    void testMaxHealthBoundary()
    {
        testPlayer.setMaxHealth(0);
        assertEquals(1, testPlayer.getMaxHealth());
        
        testPlayer.setMaxHealth(200);
        assertEquals(200, testPlayer.getMaxHealth());
    }

    @Test
    void testDefaultEquipment()
    {
        assertNotNull(testPlayer.getEquippedWeapon());
        assertNotNull(testPlayer.getEquippedShield());
        assertNotNull(testPlayer.getEquippedArmour());
    }

    @Test
    void testBaseDamage()
    {
        double baseDamage = testPlayer.getDamage();
        assertTrue(baseDamage >= 0);
    }

    @Test
    void testDefenseCalculation()
    {
        double baseDefense = testPlayer.getCurrentDefense();
        
        testPlayer.defend();
        double defendingDefense = testPlayer.getCurrentDefense();
        
        assertTrue(defendingDefense >= baseDefense);
    }
}
