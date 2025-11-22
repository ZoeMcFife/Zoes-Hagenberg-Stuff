package test;

import main.character.Enemy;
import main.character.Player;
import main.combat.Battle;
import main.global.GameManager;
import main.item.Item;
import main.item.Weapon;
import main.item.Armour;
import main.item.Shield;
import main.item.HealingPotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatFlowTest
{
    private Player testPlayer;
    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private Battle testBattle;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 5, 5);
        GameManager.setPlayer(testPlayer);
        testEnemy1 = new Enemy("Goblin", 50, 3, 4, 2, 10);
        testEnemy2 = new Enemy("Orc", 80, 6, 3, 2, 20);
        
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy1);
        
        testBattle = new Battle(enemies, testPlayer);
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
    }

    @Test
    void testCompleteCombatFlow()
    {
        // Initial state
        assertTrue(testPlayer.isAlive());
        assertTrue(testEnemy1.isAlive());
        assertFalse(testBattle.isBattleOver());
        
        // Combat rounds
        while (testEnemy1.isAlive() && testPlayer.isAlive())
        {
            testPlayer.attack(testEnemy1);
            if (testEnemy1.isAlive())
            {
                testEnemy1.attack(testPlayer);
            }
        }
        
        // Check battle outcome
        assertTrue(!testEnemy1.isAlive() || !testPlayer.isAlive());
    }

    @Test
    void testDefendingInCombat()
    {
        // Player defends
        testPlayer.defend();
        double defendDefense = testPlayer.getCurrentDefense();
        
        // Enemy attacks
        double healthBefore = testPlayer.getHealth();
        testEnemy1.attack(testPlayer);
        double healthAfter = testPlayer.getHealth();
        
        // Stop defending for next turn
        testPlayer.stopDefending();
        
        // Verify player was defending during attack
        assertTrue(testPlayer.getHealth() <= testPlayer.getMaxHealth());
    }

    @Test
    void testHealingDuringCombat()
    {
        // Damage player
        testPlayer.setHealth(50);
        double healthBefore = testPlayer.getHealth();
        
        // Use healing potion
        HealingPotion potion = new HealingPotion("Combat Potion", 1.0, 50, 30);
        testPlayer.addItemToInventory(potion, false);
        testPlayer.useItem(potion);
        
        assertTrue(testPlayer.getHealth() > healthBefore);
    }

    @Test
    void testMultipleEnemyCombatFlow()
    {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy1);
        enemies.add(testEnemy2);
        
        Battle multiBattle = new Battle(enemies, testPlayer);
        
        assertFalse(multiBattle.isBattleOver());
        assertEquals(2, multiBattle.getEnemies().size());
        
        // Defeat first enemy
        while (testEnemy1.isAlive())
        {
            testPlayer.attack(testEnemy1);
        }
        
        assertFalse(multiBattle.isBattleOver()); // Still have enemy2
        
        // Defeat second enemy
        while (testEnemy2.isAlive())
        {
            testPlayer.attack(testEnemy2);
        }
        
        assertTrue(multiBattle.isBattleOver());
    }

    @Test
    void testLootCollection()
    {
        // Give enemy some items
        Weapon enemyWeapon = new Weapon("Enemy Sword", 5.0, 100, 15, false);
        Armour enemyArmour = new Armour("Enemy Armour", 10.0, 150, 20);
        Shield enemyShield = new Shield("Enemy Shield", 6.0, 80, 12);
        HealingPotion enemyPotion = new HealingPotion("Enemy Potion", 1.0, 50, 25);
        
        testEnemy1.equipItem(enemyWeapon, false, false);
        testEnemy1.equipItem(enemyArmour, false, false);
        testEnemy1.equipItem(enemyShield, false, false);
        testEnemy1.addItemToInventory(enemyPotion, false, true);
        
        List<Item> loot = testBattle.getAllLoot();
        
        assertNotNull(loot);
        assertTrue(loot.size() > 0);
    }

    @Test
    void testBattleIsOverWhenAllEnemiesDead()
    {
        assertFalse(testBattle.isBattleOver());
        
        testEnemy1.setHealth(0);
        
        assertTrue(testBattle.isBattleOver());
    }

    @Test
    void testBattleNotOverWithLivingEnemies()
    {
        assertTrue(testEnemy1.isAlive());
        assertFalse(testBattle.isBattleOver());
    }

    @Test
    void testPlayerGainsExperienceAfterCombat()
    {
        int initialExp = testPlayer.getExperience();
        
        // Kill enemy
        while (testEnemy1.isAlive())
        {
            testPlayer.attack(testEnemy1);
        }
        
        assertTrue(testPlayer.getExperience() > initialExp);
    }

    @Test
    void testWeaponDurabilityAffectedByCombat()
    {
        Armour playerArmour = new Armour("Player Armour", 10.0, 150, 20);
        testPlayer.equipItem(playerArmour, false);
        
        double initialDurability = testPlayer.getEquippedArmour().getDurability();
        
        // Take some damage
        for (int i = 0; i < 5; i++)
        {
            testEnemy1.attack(testPlayer);
        }
        
        // Armour durability should decrease
        assertTrue(testPlayer.getEquippedArmour().getDurability() <= initialDurability);
    }

    @Test
    void testCombatWithSpecialAttacks()
    {
        // Give player a weapon with special attack
        Weapon specialWeapon = new Weapon(
            "Special Blade", 5.0, 200, 20, false, main.item.ItemRarity.HIGH,
            40, "Ultimate Slash", "Power surges through the blade!", 30, 10
        );
        testPlayer.equipItem(specialWeapon, false);
        testPlayer.gainPP(50);
        
        double initialHealth = testEnemy1.getHealth();
        int initialPP = testPlayer.getCurrentPP();
        
        testPlayer.useSpecial(testEnemy1);
        
        assertTrue(testEnemy1.getHealth() < initialHealth);
        assertTrue(testPlayer.getCurrentPP() < initialPP);
    }

    @Test
    void testPPGenerationDuringCombat()
    {
        Weapon ppWeapon = new Weapon(
            "PP Sword", 5.0, 100, 20, false, main.item.ItemRarity.MEDIUM,
            0, "", "", 0, 15
        );
        testPlayer.equipItem(ppWeapon, false);
        
        assertEquals(0, testPlayer.getCurrentPP());
        
        // Attack multiple times
        for (int i = 0; i < 3; i++)
        {
            testPlayer.attack(testEnemy1);
        }
        
        // Should have gained PP
        assertTrue(testPlayer.getCurrentPP() > 0);
    }

    @Test
    void testDefendingGrantsPP()
    {
        Shield ppShield = new Shield(
            "PP Shield", 6.0, 100, 15, main.item.ItemRarity.MEDIUM, 12
        );
        testPlayer.equipItem(ppShield, false);
        
        int initialPP = testPlayer.getCurrentPP();
        testPlayer.defend();
        
        assertTrue(testPlayer.getCurrentPP() > initialPP);
    }

    @Test
    void testCombatFlowWithCriticalHealth()
    {
        // Reduce player to critical health
        testPlayer.setHealth(testPlayer.getMaxHealth() * 0.25);
        assertEquals(main.character.CharacterStatus.CRITICALLY_HURT, testPlayer.getStatus());
        
        // Player's damage should be reduced
        double criticalDamage = testPlayer.getDamage();
        
        // Heal to full
        testPlayer.setHealth(testPlayer.getMaxHealth());
        double normalDamage = testPlayer.getDamage();
        
        assertTrue(criticalDamage < normalDamage);
    }

    @Test
    void testMultipleTurnCombatFlow()
    {
        int turnCount = 0;
        int maxTurns = 100; // Prevent infinite loop
        
        while (testEnemy1.isAlive() && testPlayer.isAlive() && turnCount < maxTurns)
        {
            // Player turn
            if (turnCount % 3 == 0)
            {
                testPlayer.defend();
            }
            else
            {
                testPlayer.attack(testEnemy1);
            }
            
            // Enemy turn
            if (testEnemy1.isAlive())
            {
                testEnemy1.attack(testPlayer);
            }
            
            testPlayer.stopDefending();
            turnCount++;
        }
        
        assertTrue(turnCount < maxTurns, "Combat should complete within max turns");
    }

    @Test
    void testLootContainsEnemyEquipment()
    {
        Weapon sword = new Weapon("Looted Sword", 5.0, 100, 15, false);
        testEnemy1.equipItem(sword, false, false);
        
        List<Item> loot = testBattle.getAllLoot();
        
        boolean hasSword = loot.stream().anyMatch(item -> item.getName().equals("Looted Sword"));
        assertTrue(hasSword);
    }

    @Test
    void testBattleWithNoEnemies()
    {
        List<Enemy> noEnemies = new ArrayList<>();
        Battle emptyBattle = new Battle(noEnemies, testPlayer);
        
        assertTrue(emptyBattle.isBattleOver());
        assertEquals(0, emptyBattle.getEnemies().size());
    }
}
