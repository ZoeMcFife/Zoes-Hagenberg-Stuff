package test;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.ActionType;
import main.combat.Battle;
import main.item.Shield;
import main.item.Weapon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest
{
    private Battle testBattle;
    private Player testPlayer;
    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private Enemy testEnemy3;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 5, 5);
        testEnemy1 = new Enemy("Goblin", 50, 3, 4, 2, 10);
        testEnemy2 = new Enemy("Orc", 80, 6, 3, 2, 20);
        testEnemy3 = new Enemy("Troll", 100, 7, 8, 1 ,10);
        
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy1);
        enemies.add(testEnemy2);
        
        testBattle = new Battle(enemies, testPlayer);
    }

    @Test
    void testBattleCreation()
    {
        assertNotNull(testBattle);
        assertNotNull(testBattle.getEnemies());
        assertEquals(2, testBattle.getEnemies().size());
    }

    @Test
    void testBattleInitializesWithEnemies()
    {
        assertEquals(2, testBattle.getEnemies().size());
        assertEquals("Goblin", testBattle.getEnemies().get(0).getName());
        assertEquals("Orc", testBattle.getEnemies().get(1).getName());
    }

    @Test
    void testParticipantsOrderedByDexterity()
    {
        // Player has dexterity 5, Goblin has 4, Orc has 3
        // Should be ordered: Player (5), Goblin (4), Orc (3)
        List<GameCharacter> participants = testBattle.getParticipantsOrderedByDexterity();
        
        assertNotNull(participants);
        assertEquals(3, participants.size());
        
        // Check order: highest dexterity first
        assertEquals(5, participants.get(0).getDexterity());
        assertEquals(4, participants.get(1).getDexterity());
        assertEquals(3, participants.get(2).getDexterity());
    }

    @Test
    void testTurnOrderWithHighDexterityEnemy()
    {
        // Create battle with high-dexterity enemy
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy3); // Troll has dexterity 8
        
        Battle battle = new Battle(enemies, testPlayer);
        List<GameCharacter> participants = battle.getParticipantsOrderedByDexterity();
        
        // Troll should go first (dex 8), then Player (dex 5)
        assertEquals("Troll", participants.get(0).getName());
        assertEquals("Test Hero", participants.get(1).getName());
    }

    @Test
    void testCombatBasicAttack()
    {
        double initialHealth = testEnemy1.getHealth();
        testPlayer.attack(testEnemy1);
        
        // Enemy health should have decreased after attack
        assertTrue(testEnemy1.getHealth() < initialHealth);
    }

    @Test
    void testCombatFlowPlayerAttacksEnemy()
    {
        double initialEnemyHealth = testEnemy1.getHealth();
        
        // Player attacks enemy
        testPlayer.attack(testEnemy1);
        
        // Verify damage was dealt
        assertTrue(testEnemy1.getHealth() < initialEnemyHealth);
        assertTrue(testEnemy1.isAlive());
    }

    @Test
    void testCombatFlowEnemyAttacksPlayer()
    {
        double initialPlayerHealth = testPlayer.getHealth();
        
        // Enemy attacks player
        testEnemy1.attack(testPlayer);
        
        // Verify damage was dealt
        assertTrue(testPlayer.getHealth() < initialPlayerHealth);
        assertTrue(testPlayer.isAlive());
    }

    @Test
    void testCombatFlowMultipleTurns()
    {
        double initialPlayerHealth = testPlayer.getHealth();
        double initialEnemyHealth = testEnemy1.getHealth();
        
        // Simulate multiple combat turns
        testPlayer.attack(testEnemy1);
        testEnemy1.attack(testPlayer);
        testPlayer.attack(testEnemy1);
        testEnemy1.attack(testPlayer);
        
        // Both should have taken damage
        assertTrue(testPlayer.getHealth() < initialPlayerHealth);
        assertTrue(testEnemy1.getHealth() < initialEnemyHealth);
    }

    @Test
    void testDefendMechanicReducesDamage()
    {
        // Give player a shield
        Shield testShield = new Shield("Test Shield", 5.0, 50, 10);
        Player player = new Player("Test Player", 5, 5, 5);
        player.equipItem(testShield);
        
        // Check defense without defending (should be just armor = 0)
        double defenseWithoutDefend = player.getCurrentDefense();
        
        // Check defense when defending (should include shield = 10)
        player.defend();
        double defenseWithDefend = player.getCurrentDefense();
        
        // Defending should increase defense value
        assertTrue(defenseWithDefend > defenseWithoutDefend, 
            "Defense with defend (" + defenseWithDefend + ") should be > defense without defend (" + defenseWithoutDefend + ")");
        
        player.stopDefending();
    }

    @Test
    void testEnemyDefeat()
    {
        // Reduce enemy health to 0
        testEnemy1.setHealth(0);
        assertFalse(testEnemy1.isAlive());
    }

    @Test
    void testPlayerDefeat()
    {
        testPlayer.setHealth(0);
        assertFalse(testPlayer.isAlive());
    }

    @Test
    void testMultipleEnemiesCombat()
    {
        double enemy1InitialHealth = testEnemy1.getHealth();
        double enemy2InitialHealth = testEnemy2.getHealth();
        
        // Player attacks both enemies
        testPlayer.attack(testEnemy1);
        testPlayer.attack(testEnemy2);
        
        // Both enemies should have taken damage
        assertTrue(testEnemy1.getHealth() < enemy1InitialHealth);
        assertTrue(testEnemy2.getHealth() < enemy2InitialHealth);
    }

    @Test
    void testBattleUntilEnemyDefeated()
    {
        // Keep attacking until enemy is defeated
        while (testEnemy1.isAlive())
        {
            testPlayer.attack(testEnemy1);
        }
        
        assertFalse(testEnemy1.isAlive());
        assertEquals(0, testEnemy1.getHealth());
    }

    @Test
    void testActionTypeAssignment()
    {
        // Test that action types can be assigned to characters
        testPlayer.nextAction = ActionType.ATTACK;
        assertEquals(ActionType.ATTACK, testPlayer.nextAction);
        
        testPlayer.nextAction = ActionType.DEFEND;
        assertEquals(ActionType.DEFEND, testPlayer.nextAction);
        
        testPlayer.nextAction = ActionType.USE_ITEM;
        assertEquals(ActionType.USE_ITEM, testPlayer.nextAction);
    }

    @Test
    void testCombatWithWeapons()
    {
        // Equip player with a better weapon
        Weapon strongWeapon = new Weapon("Strong Sword", 5.0, 100, 20, false);
        testPlayer.equipItem(strongWeapon);
        
        double initialHealth = testEnemy1.getHealth();
        testPlayer.attack(testEnemy1);
        
        double damageDealt = initialHealth - testEnemy1.getHealth();
        assertTrue(damageDealt > 0);
    }

    @Test
    void testParticipantCountInBattle()
    {
        // Should have all enemies plus player
        List<GameCharacter> participants = testBattle.getParticipantsOrderedByDexterity();
        assertEquals(testBattle.getEnemies().size() + 1, participants.size());
    }
}
