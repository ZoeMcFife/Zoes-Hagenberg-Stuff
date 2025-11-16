package test;

import main.character.Enemy;
import main.character.Player;
import main.combat.Battle;
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

    @BeforeEach
    void setUp()
    {
        testBattle = new Battle();
        testPlayer = new Player("Test Hero", 5, 5, 5);
        testEnemy1 = new Enemy("Goblin", 50, 3, 4, 2);
        testEnemy2 = new Enemy("Orc", 80, 6, 3, 2);
    }

    @Test
    void testBattleCreation()
    {
        assertNotNull(testBattle);
        assertNotNull(testBattle.getEnemies());
        assertEquals(0, testBattle.getEnemies().size());
    }

    @Test
    void testSetSingleEnemy()
    {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy1);
        
        testBattle.setEnemies(enemies);
        
        assertEquals(1, testBattle.getEnemies().size());
        assertEquals("Goblin", testBattle.getEnemies().get(0).getName());
    }

    @Test
    void testSetMultipleEnemies()
    {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(testEnemy1);
        enemies.add(testEnemy2);
        
        testBattle.setEnemies(enemies);
        
        assertEquals(2, testBattle.getEnemies().size());
        assertEquals("Goblin", testBattle.getEnemies().get(0).getName());
        assertEquals("Orc", testBattle.getEnemies().get(1).getName());
    }

    @Test
    void testCombatBasicAttack()
    {
        double initialHealth = testEnemy1.getHealth();
        testPlayer.attack(testEnemy1);
        
        // Enemy health should have decreased after attack
        assertTrue(testEnemy1.getHealth() <= initialHealth);
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
    void testMultipleAttacks()
    {
        double initialHealth = testEnemy1.getHealth();
        
        testPlayer.attack(testEnemy1);
        testPlayer.attack(testEnemy1);
        
        // Enemy should have taken damage twice
        assertTrue(testEnemy1.getHealth() < initialHealth);
    }
}
