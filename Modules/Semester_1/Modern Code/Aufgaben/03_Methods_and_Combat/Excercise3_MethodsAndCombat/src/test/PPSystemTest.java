package test;

import main.character.Player;
import main.character.Enemy;
import main.item.*;
import main.combat.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the PP (Power Points) system including special attacks and shield PP gain.
 */
class PPSystemTest
{
    private Player player;
    private Enemy enemy;

    @BeforeEach
    void setUp()
    {
        player = new Player("Test Player", 100, 5, 5, 5);
        enemy = new Enemy("Test Enemy", 100, 5, 5, 5);
    }

    @Test
    void testWeaponWithPPValues()
    {
        Weapon weapon = new Weapon("Test Weapon", 5.0, 100, 20, false, ItemRarity.MEDIUM, 
                                   15, "Special attack!", 30);
        
        assertEquals(15, weapon.getSpecialDamage());
        assertEquals("Special attack!", weapon.getSpecialFlavorText());
        assertEquals(30, weapon.getPpCost());
    }

    @Test
    void testWeaponDefaultPPValues()
    {
        Weapon weapon = new Weapon("Basic Weapon", 5.0, 100, 20, false);
        
        assertEquals(0, weapon.getSpecialDamage());
        assertEquals("", weapon.getSpecialFlavorText());
        assertEquals(0, weapon.getPpCost());
    }

    @Test
    void testShieldWithPPGain()
    {
        Shield shield = new Shield("Test Shield", 6.0, 80, 12, ItemRarity.MEDIUM, 8);
        
        assertEquals(8, shield.getPpGain());
    }

    @Test
    void testShieldDefaultPPGain()
    {
        Shield shield = new Shield("Basic Shield", 6.0, 80, 12);
        
        assertEquals(0, shield.getPpGain());
    }

    @Test
    void testPlayerStartsWithZeroPP()
    {
        assertEquals(0, player.getCurrentPP());
    }

    @Test
    void testPlayerGainsPP()
    {
        player.gainPP(10);
        assertEquals(10, player.getCurrentPP());
        
        player.gainPP(5);
        assertEquals(15, player.getCurrentPP());
    }

    @Test
    void testPlayerCannotUseSpecialWithoutEnoughPP()
    {
        Weapon weapon = new Weapon("Test Weapon", 5.0, 100, 20, false, ItemRarity.MEDIUM, 
                                   15, "Special!", 30);
        player.equipItem(weapon, false, false);
        
        // Player has 0 PP, weapon costs 30 PP
        assertEquals(0, player.getCurrentPP());
        
        // Attempting to use special should not crash
        double enemyHealthBefore = enemy.getHealth();
        player.useSpecial(enemy);
        
        // Enemy should not be damaged since player doesn't have enough PP
        assertEquals(enemyHealthBefore, enemy.getHealth());
    }

    @Test
    void testPlayerCanUseSpecialWithEnoughPP()
    {
        Weapon weapon = new Weapon("Test Weapon", 5.0, 100, 20, false, ItemRarity.MEDIUM, 
                                   15, "Special!", 30);
        player.equipItem(weapon, false, false);
        player.gainPP(50); // Give player enough PP
        
        double enemyHealthBefore = enemy.getHealth();
        player.useSpecial(enemy);
        
        // PP should be deducted
        assertEquals(20, player.getCurrentPP()); // 50 - 30 = 20
        
        // Enemy should take damage (base damage + special damage)
        assertTrue(enemy.getHealth() < enemyHealthBefore);
    }

    @Test
    void testActionTypeHasUseSpecial()
    {
        assertNotNull(ActionType.USE_SPECIAL);
    }

    @Test
    void testWeaponSettersMaintainNonNegativeValues()
    {
        Weapon weapon = new Weapon("Test", 1, 1, 1, false);
        
        weapon.setSpecialDamage(-10);
        assertEquals(0, weapon.getSpecialDamage());
        
        weapon.setPpCost(-5);
        assertEquals(0, weapon.getPpCost());
    }

    @Test
    void testShieldSetterMaintainsNonNegativeValue()
    {
        Shield shield = new Shield("Test", 1, 1, 1);
        
        shield.setPpGain(-10);
        assertEquals(0, shield.getPpGain());
    }

    @Test
    void testSpecialFlavorTextCanBeSet()
    {
        Weapon weapon = new Weapon("Test", 1, 1, 1, false);
        
        weapon.setSpecialFlavorText("Custom flavor text");
        assertEquals("Custom flavor text", weapon.getSpecialFlavorText());
        
        // Null should convert to empty string
        weapon.setSpecialFlavorText(null);
        assertEquals("", weapon.getSpecialFlavorText());
    }
}
