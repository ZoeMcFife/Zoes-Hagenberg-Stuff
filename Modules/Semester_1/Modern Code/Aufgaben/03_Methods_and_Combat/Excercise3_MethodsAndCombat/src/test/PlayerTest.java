package test;

import main.character.Player;
import main.character.GameCharacter;
import main.item.Weapon;
import main.item.Shield;
import main.item.ItemRarity;
import main.global.GameManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    private Player testPlayer;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 7, 6);
        GameManager.setPlayer(testPlayer);
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
    }

    @Test
    void testPlayerLevelingUp()
    {
        int initialLevel = testPlayer.getLevel();
        double initialMaxHealth = testPlayer.getMaxHealth();
        int initialMaxPP = testPlayer.getMaxPP();
        
        // Add enough experience to level up
        int expNeeded = testPlayer.getExperienceNeededForNextLevel();
        testPlayer.addExperience(expNeeded);
        
        assertEquals(initialLevel + 1, testPlayer.getLevel());
        assertTrue(testPlayer.getMaxHealth() > initialMaxHealth);
        assertTrue(testPlayer.getMaxPP() > initialMaxPP);
    }

    @Test
    void testExperienceGain()
    {
        int initialExp = testPlayer.getExperience();
        testPlayer.addExperience(25);
        assertEquals(initialExp + 25, testPlayer.getExperience());
    }

    @Test
    void testExperienceNeededForNextLevel()
    {
        int level1Exp = testPlayer.getExperienceNeededForNextLevel();
        assertEquals(50, level1Exp); // Level 1 should need 50 exp
        
        testPlayer.addExperience(50);
        int level2Exp = testPlayer.getExperienceNeededForNextLevel();
        assertEquals(100, level2Exp); // Level 2 should need 100 exp
    }

    @Test
    void testStatPointsGainedOnLevelUp()
    {
        int initialStatPoints = testPlayer.getAvailableStatPoints();
        int expNeeded = testPlayer.getExperienceNeededForNextLevel();
        testPlayer.addExperience(expNeeded);
        
        assertEquals(initialStatPoints + GameManager.STAT_POINTS_PER_LEVEL, testPlayer.getAvailableStatPoints());
    }

    @Test
    void testSpendStatPoint()
    {
        // Give player some stat points
        testPlayer.addExperience(testPlayer.getExperienceNeededForNextLevel());
        
        int initialStatPoints = testPlayer.getAvailableStatPoints();
        testPlayer.spendStatPoint();
        assertEquals(initialStatPoints - 1, testPlayer.getAvailableStatPoints());
    }

    @Test
    void testSpendStatPointCannotGoNegative()
    {
        assertEquals(0, testPlayer.getAvailableStatPoints());
        testPlayer.spendStatPoint();
        assertEquals(0, testPlayer.getAvailableStatPoints());
    }

    @Test
    void testPPSystem()
    {
        assertEquals(0, testPlayer.getCurrentPP());
        assertEquals(Player.DEFAULT_PLAYER_MAX_PP, testPlayer.getMaxPP());
    }

    @Test
    void testGainPP()
    {
        testPlayer.gainPP(20);
        assertEquals(20, testPlayer.getCurrentPP());
    }

    @Test
    void testGainPPCannotExceedMax()
    {
        testPlayer.gainPP(150);
        assertEquals(testPlayer.getMaxPP(), testPlayer.getCurrentPP());
    }

    @Test
    void testGainPPIgnoresNegative()
    {
        testPlayer.gainPP(30);
        testPlayer.gainPP(-10);
        assertEquals(30, testPlayer.getCurrentPP());
    }

    @Test
    void testGainPPIgnoresZero()
    {
        testPlayer.gainPP(30);
        testPlayer.gainPP(0);
        assertEquals(30, testPlayer.getCurrentPP());
    }

    @Test
    void testUseSpecialAttackWithEnoughPP()
    {
        // Create a weapon with special attack
        Weapon specialWeapon = new Weapon(
            "Special Sword", 5.0, 100, 20, false, ItemRarity.HIGH,
            30, "Power Strike", "The sword glows with power!", 25, 5
        );
        testPlayer.equipItem(specialWeapon);
        testPlayer.gainPP(50);
        
        Player target = new Player("Target", 10, 5, 5);
        double initialHealth = target.getHealth();
        int initialPP = testPlayer.getCurrentPP();
        
        testPlayer.useSpecial(target);
        
        assertEquals(initialPP - 25, testPlayer.getCurrentPP());
        assertTrue(target.getHealth() < initialHealth);
    }

    @Test
    void testUseSpecialAttackWithoutEnoughPP()
    {
        Weapon specialWeapon = new Weapon(
            "Special Sword", 5.0, 100, 20, false, ItemRarity.HIGH,
            30, "Power Strike", "The sword glows with power!", 50, 5
        );
        testPlayer.equipItem(specialWeapon);
        testPlayer.gainPP(25); // Not enough
        
        Player target = new Player("Target", 10, 5, 5);
        double initialHealth = target.getHealth();
        int initialPP = testPlayer.getCurrentPP();
        
        testPlayer.useSpecial(target);
        
        // PP should not change and target should not take damage
        assertEquals(initialPP, testPlayer.getCurrentPP());
        assertEquals(initialHealth, target.getHealth());
    }

    @Test
    void testPPGainOnAttack()
    {
        Weapon weaponWithPPGain = new Weapon(
            "PP Sword", 5.0, 100, 20, false, ItemRarity.MEDIUM,
            0, "", "", 0, 10
        );
        testPlayer.equipItem(weaponWithPPGain);
        
        Player target = new Player("Target", 10, 5, 5);
        int initialPP = testPlayer.getCurrentPP();
        
        testPlayer.attack(target);
        
        assertEquals(initialPP + 10, testPlayer.getCurrentPP());
    }

    @Test
    void testPPGainOnDefend()
    {
        Shield shieldWithPPGain = new Shield(
            "PP Shield", 6.0, 80, 15, ItemRarity.MEDIUM, 8
        );
        testPlayer.equipItem(shieldWithPPGain);
        
        int initialPP = testPlayer.getCurrentPP();
        testPlayer.defend();
        
        assertEquals(initialPP + 8, testPlayer.getCurrentPP());
    }

    @Test
    void testMultipleLevelUps()
    {
        int initialLevel = testPlayer.getLevel();
        
        // Add enough exp for 3 levels
        testPlayer.addExperience(50 + 100 + 150);
        
        assertEquals(initialLevel + 3, testPlayer.getLevel());
    }

    @Test
    void testPlayerConstructorWithName()
    {
        Player player = new Player("Simple Hero");
        assertEquals("Simple Hero", player.getName());
        assertEquals(Player.DEFAULT_PLAYER_MAX_HEALTH, player.getMaxHealth());
        assertEquals(GameCharacter.MIN_STAT_VALUE, player.getStrength());
        assertEquals(GameCharacter.MIN_STAT_VALUE, player.getDexterity());
        assertEquals(GameCharacter.MIN_STAT_VALUE, player.getIntelligence());
    }

    @Test
    void testPlayerConstructorWithStats()
    {
        Player player = new Player("Strong Hero", 8, 6, 4);
        assertEquals("Strong Hero", player.getName());
        assertEquals(Player.DEFAULT_PLAYER_MAX_HEALTH, player.getMaxHealth());
        assertEquals(8, player.getStrength());
        assertEquals(6, player.getDexterity());
        assertEquals(4, player.getIntelligence());
    }

    @Test
    void testPlayerConstructorWithMaxHealth()
    {
        Player player = new Player("Tanky Hero", 200, 7, 5, 5);
        assertEquals("Tanky Hero", player.getName());
        assertEquals(200, player.getMaxHealth());
        assertEquals(7, player.getStrength());
    }

    @Test
    void testHealthIncreasesOnLevelUp()
    {
        double initialHealth = testPlayer.getHealth();
        double initialMaxHealth = testPlayer.getMaxHealth();
        
        testPlayer.addExperience(testPlayer.getExperienceNeededForNextLevel());
        
        assertEquals(testPlayer.getMaxHealth(), testPlayer.getHealth()); // Should be full health after level up
        assertTrue(testPlayer.getMaxHealth() > initialMaxHealth);
    }

    @Test
    void testPlayerDisplayBox()
    {
        var displayBox = testPlayer.getDisplayBox();
        assertNotNull(displayBox);
        assertFalse(displayBox.isEmpty());
        
        // Check that display box contains player name
        boolean containsName = displayBox.stream().anyMatch(line -> line.contains(testPlayer.getName()));
        assertTrue(containsName);
    }
}
