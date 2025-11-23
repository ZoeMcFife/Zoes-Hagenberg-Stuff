package test;

import main.character.Player;
import main.global.GameManager;
import main.global.Difficulty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest
{
    @BeforeEach
    void setUp()
    {
        // Reset game manager before each test
        GameManager.removePlayer();
        GameManager.difficulty = Difficulty.NONE;
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
    }

    @Test
    void testSetPlayer()
    {
        Player player = new Player("Test Hero", 5, 5, 5);
        GameManager.setPlayer(player);
        
        assertTrue(GameManager.hasPlayerBeenInitialized);
        assertNotNull(GameManager.getPlayer());
        assertEquals("Test Hero", GameManager.getPlayer().getName());
    }

    @Test
    void testGetPlayer()
    {
        Player player = new Player("Test Hero", 5, 5, 5);
        GameManager.setPlayer(player);
        
        Player retrieved = GameManager.getPlayer();
        assertEquals(player, retrieved);
    }

    @Test
    void testRemovePlayer()
    {
        Player player = new Player("Test Hero", 5, 5, 5);
        GameManager.setPlayer(player);
        
        GameManager.removePlayer();
        
        assertFalse(GameManager.hasPlayerBeenInitialized);
        assertNull(GameManager.getPlayer());
    }

    @Test
    void testDifficultySettings()
    {
        GameManager.difficulty = Difficulty.EASY;
        assertEquals(Difficulty.EASY, GameManager.difficulty);
        
        GameManager.difficulty = Difficulty.MEDIUM;
        assertEquals(Difficulty.MEDIUM, GameManager.difficulty);
        
        GameManager.difficulty = Difficulty.HARD;
        assertEquals(Difficulty.HARD, GameManager.difficulty);
    }

    @Test
    void testItemLootCountEasy()
    {
        GameManager.difficulty = Difficulty.EASY;
        assertEquals(GameManager.ITEM_LOOT_COUNT_EASY, GameManager.getItemLootCount());
    }

    @Test
    void testItemLootCountMedium()
    {
        GameManager.difficulty = Difficulty.MEDIUM;
        assertEquals(GameManager.ITEM_LOOT_COUNT_MEDIUM, GameManager.getItemLootCount());
    }

    @Test
    void testItemLootCountHard()
    {
        GameManager.difficulty = Difficulty.HARD;
        assertEquals(GameManager.ITEM_LOOT_COUNT_HARD, GameManager.getItemLootCount());
    }

    @Test
    void testItemLootCountNone()
    {
        GameManager.difficulty = Difficulty.NONE;
        assertEquals(0, GameManager.getItemLootCount());
    }

    @Test
    void testDamageMultiplierConstants()
    {
        assertTrue(GameManager.DAMAGE_MULTIPLIER_PER_STRENGTH > 0);
        assertTrue(GameManager.DAMAGE_MULTIPLIER_PER_INTELLIGENCE > 0);
    }

    @Test
    void testDodgeChancePerDexterity()
    {
        assertTrue(GameManager.DODGE_CHANCE_PER_DEXTERITY > 0);
        assertTrue(GameManager.DODGE_CHANCE_PER_DEXTERITY < 1);
    }

    @Test
    void testCarryCapacityPerStrength()
    {
        assertTrue(GameManager.CARRY_CAPACITY_PER_STRENGTH > 0);
    }

    @Test
    void testDifficultyIncreaseTurns()
    {
        assertTrue(GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_EASY > 0);
        assertTrue(GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM > 0);
        assertTrue(GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_HARD > 0);
        
        // Easy should have more turns before increase
        assertTrue(GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_EASY > GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM);
        assertTrue(GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM > GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_HARD);
    }

    @Test
    void testMaxEnemiesPerBattleConstants()
    {
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_HARMLESS >= 1);
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_MOSTLY_HARMLESS >= 1);
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_DANGEROUS >= 1);
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_EXTREME >= 1);
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_DEATH >= 1);
        
        // Should increase with danger level
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_DEATH >= GameManager.MAX_ENEMIES_PER_BATTLE_EXTREME);
        assertTrue(GameManager.MAX_ENEMIES_PER_BATTLE_EXTREME >= GameManager.MAX_ENEMIES_PER_BATTLE_DANGEROUS);
    }

    @Test
    void testLevelUpConstants()
    {
        assertTrue(GameManager.MAX_PP_INCREASE_PER_LEVEL > 0);
        assertTrue(GameManager.STAT_POINTS_PER_LEVEL > 0);
        assertTrue(GameManager.HEALTH_INCREASE_PER_LEVEL > 0);
    }

    @Test
    void testHealthIncreasePerStatConstants()
    {
        assertTrue(GameManager.HEALTH_INCREASE_PER_STRENGTH > 0);
        assertTrue(GameManager.HEALTH_INCREASE_PER_INTELLIGENCE > 0);
        assertTrue(GameManager.HEALTH_INCREASE_PER_DEXTERITY > 0);
    }

    @Test
    void testDamageReductionWhenCritical()
    {
        assertTrue(GameManager.DAMAGE_REDUCTION_WHEN_CRITICAL_STATUS > 0);
        assertTrue(GameManager.DAMAGE_REDUCTION_WHEN_CRITICAL_STATUS < 1);
    }

    @Test
    void testDelayConstants()
    {
        assertTrue(GameManager.DELAY_SHORT > 0);
        assertTrue(GameManager.DELAY_MEDIUM > 0);
        assertTrue(GameManager.DELAY_LONG > 0);
        
        assertTrue(GameManager.DELAY_LONG > GameManager.DELAY_MEDIUM);
        assertTrue(GameManager.DELAY_MEDIUM > GameManager.DELAY_SHORT);
    }

    @Test
    void testPlayerBaseDefence()
    {
        assertTrue(GameManager.PLAYER_BASE_DEFENCE > 0);
    }
}
