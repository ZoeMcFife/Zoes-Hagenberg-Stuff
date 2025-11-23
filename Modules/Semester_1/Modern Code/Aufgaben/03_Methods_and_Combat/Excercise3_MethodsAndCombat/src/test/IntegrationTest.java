package test;

import main.character.Player;
import main.character.Enemy;
import main.character.CharacterStatus;
import main.combat.Battle;
import main.global.GameManager;
import main.global.Difficulty;
import main.item.*;
import main.inventory.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests covering complex scenarios across multiple systems.
 */
class IntegrationTest
{
    private Player player;
    private GameManager gameManager;

    @BeforeEach
    void setUp()
    {
        player = new Player("Integration Hero", 10, 8, 6);
        GameManager.setPlayer(player);
        GameManager.difficulty = Difficulty.MEDIUM;
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
        GameManager.difficulty = Difficulty.NONE;
    }

    @Test
    void testCompletePlayerProgression()
    {
        // Start at level 1
        assertEquals(1, player.getLevel());
        
        // Gain experience to level up multiple times
        player.addExperience(50);  // Level 2
        player.addExperience(100); // Level 3
        player.addExperience(150); // Level 4
        
        assertEquals(4, player.getLevel());
        assertTrue(player.getAvailableStatPoints() >= 3);
        assertTrue(player.getMaxHealth() > Player.DEFAULT_PLAYER_MAX_HEALTH);
        assertTrue(player.getMaxPP() > Player.DEFAULT_PLAYER_MAX_PP);
    }

    @Test
    void testCompleteEquipmentProgression()
    {
        // Start with basic equipment
        double initialDamage = player.getDamage();
        double initialDefense = player.getCurrentDefense();
        
        // Upgrade weapon
        Weapon betterWeapon = new Weapon("Better Sword", 6.0, 200, 30, false, ItemRarity.HIGH);
        player.addItemToInventory(betterWeapon, false);
        player.equipItem(betterWeapon, false);
        
        assertTrue(player.getDamage() > initialDamage);
        
        // Upgrade armor
        Armour betterArmour = new Armour("Better Armour", 12.0, 300, 35, ItemRarity.HIGH);
        player.addItemToInventory(betterArmour, false);
        player.equipItem(betterArmour, false);
        
        assertTrue(player.getCurrentDefense() > initialDefense);
        
        // Upgrade shield
        Shield betterShield = new Shield("Better Shield", 7.0, 180, 20, ItemRarity.HIGH, 10);
        player.addItemToInventory(betterShield, false);
        player.equipItem(betterShield, false);
        
        player.defend();
        assertTrue(player.getCurrentDefense() > initialDefense);
    }

    @Test
    void testCompleteBattleScenarioWithLoot()
    {
        // Create enemies with equipment
        Enemy enemy1 = new Enemy("Goblin", 40, 3, 3, 2, 15);
        Enemy enemy2 = new Enemy("Orc", 60, 5, 2, 2, 25);
        
        Weapon goblinSword = new Weapon("Goblin Sword", 3.0, 80, 12, false);
        enemy1.equipItem(goblinSword, false, false);
        
        Armour orcArmour = new Armour("Orc Armour", 8.0, 120, 18);
        enemy2.equipItem(orcArmour, false, false);
        
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        
        Battle battle = new Battle(enemies, player);
        
        // Fight enemies
        while (!battle.isBattleOver() && player.isAlive())
        {
            for (Enemy enemy : battle.getEnemies())
            {
                if (enemy.isAlive())
                {
                    player.attack(enemy);
                    break;
                }
            }
        }
        
        // Check battle outcome
        assertTrue(battle.isBattleOver());
        
        // Collect loot
        List<Item> loot = battle.getAllLoot();
        assertNotNull(loot);
        assertTrue(loot.size() > 0);
    }

    @Test
    void testArmourDegradationDuringCombat()
    {
        Armour playerArmour = new Armour("Combat Armour", 10.0, 150, 25, 100.0);
        player.equipItem(playerArmour, false);
        
        assertEquals(ArmourState.PRISTINE, player.getEquippedArmour().getState());
        double initialDurability = player.getEquippedArmour().getDurability();
        
        Enemy enemy = new Enemy("Strong Enemy", 100, 8, 5, 3, 20);
        
        // Take multiple hits
        for (int i = 0; i < 10; i++)
        {
            enemy.attack(player);
        }
        
        // Armour should be damaged
        assertTrue(player.getEquippedArmour().getDurability() < initialDurability);
        assertNotEquals(ArmourState.PRISTINE, player.getEquippedArmour().getState());
    }

    @Test
    void testPPGenerationAndUsageInCombat()
    {
        // Equip weapon with PP generation and special attack
        Weapon ppWeapon = new Weapon(
            "PP Blade", 5.0, 200, 20, false, ItemRarity.HIGH,
            40, "Power Strike", "Energy surges!", 30, 10
        );
        player.equipItem(ppWeapon, false);
        
        assertEquals(0, player.getCurrentPP());
        
        Enemy enemy = new Enemy("Test Enemy", 150, 5, 5, 5, 20);
        
        // Attack to generate PP
        player.attack(enemy);
        assertEquals(10, player.getCurrentPP());
        
        player.attack(enemy);
        assertEquals(20, player.getCurrentPP());
        
        player.attack(enemy);
        assertEquals(30, player.getCurrentPP());
        
        // Use special attack
        double enemyHealthBefore = enemy.getHealth();
        player.useSpecial(enemy);
        assertEquals(0, player.getCurrentPP());
        assertTrue(enemy.getHealth() < enemyHealthBefore);
    }

    @Test
    void testCarryCapacityAndInventoryManagement()
    {
        // Player has limited carry capacity
        double capacity = player.getCarryCapacity();
        
        // Add items up to capacity
        HealingPotion potion1 = new HealingPotion("Potion 1", capacity * 0.2, 50, 20);
        HealingPotion potion2 = new HealingPotion("Potion 2", capacity * 0.2, 50, 20);
        HealingPotion potion3 = new HealingPotion("Potion 3", capacity * 0.2, 50, 20);
        
        player.addItemToInventory(potion1, false);
        player.addItemToInventory(potion2, false);
        player.addItemToInventory(potion3, false);
        
        double currentWeight = player.getInventory().getWeight();
        assertTrue(currentWeight <= capacity);
        
        // Try to add item that exceeds capacity
        HealingPotion tooHeavy = new HealingPotion("Too Heavy", capacity, 100, 50);
        player.addItemToInventory(tooHeavy, false);
        
        // Weight should not have increased
        assertEquals(currentWeight, player.getInventory().getWeight());
    }

    @Test
    void testCriticalHealthEffectOnDamage()
    {
        // At full health
        player.setHealth(player.getMaxHealth());
        double normalDamage = player.getDamage();
        
        // At critical health (25%)
        player.setHealth(player.getMaxHealth() * 0.25);
        assertEquals(CharacterStatus.CRITICALLY_HURT, player.getStatus());
        double criticalDamage = player.getDamage();
        
        // Critical damage should be significantly reduced
        assertTrue(criticalDamage < normalDamage * 0.5);
    }

    @Test
    void testMultipleHealingPotionsUsage()
    {
        player.setHealth(30);
        double initialHealth = player.getHealth();
        
        // Add multiple potions
        HealingPotion smallPotion = new HealingPotion("Small", 1.0, 25, 20);
        HealingPotion mediumPotion = new HealingPotion("Medium", 1.5, 50, 35);
        HealingPotion largePotion = new HealingPotion("Large", 2.0, 75, 50);
        
        player.addItemToInventory(smallPotion, false);
        player.addItemToInventory(mediumPotion, false);
        player.addItemToInventory(largePotion, false);
        
        // Use potions in sequence
        player.useItem(smallPotion);
        assertEquals(initialHealth + 20, player.getHealth());
        
        player.useItem(mediumPotion);
        assertEquals(initialHealth + 20 + 35, player.getHealth());
        
        player.useItem(largePotion);
        assertTrue(player.getHealth() <= player.getMaxHealth()); // Capped at max
    }

    @Test
    void testDifficultyAffectsLootCount()
    {
        GameManager.difficulty = Difficulty.EASY;
        assertEquals(GameManager.ITEM_LOOT_COUNT_EASY, GameManager.getItemLootCount());
        
        GameManager.difficulty = Difficulty.MEDIUM;
        assertEquals(GameManager.ITEM_LOOT_COUNT_MEDIUM, GameManager.getItemLootCount());
        
        GameManager.difficulty = Difficulty.HARD;
        assertEquals(GameManager.ITEM_LOOT_COUNT_HARD, GameManager.getItemLootCount());
        
        assertTrue(GameManager.ITEM_LOOT_COUNT_EASY > GameManager.ITEM_LOOT_COUNT_MEDIUM);
        assertTrue(GameManager.ITEM_LOOT_COUNT_MEDIUM > GameManager.ITEM_LOOT_COUNT_HARD);
    }

    @Test
    void testEquipmentSwapping()
    {
        Weapon sword1 = new Weapon("Sword 1", 5.0, 100, 20, false);
        Weapon sword2 = new Weapon("Sword 2", 6.0, 150, 25, false);
        Weapon sword3 = new Weapon("Sword 3", 7.0, 200, 30, false);
        
        // Equip first weapon
        player.addItemToInventory(sword1, false);
        player.equipItem(sword1, false);
        assertEquals("Sword 1", player.getEquippedWeapon().getName());
        
        // Swap to second weapon
        player.addItemToInventory(sword2, false);
        player.equipItem(sword2, false);
        assertEquals("Sword 2", player.getEquippedWeapon().getName());
        
        // Sword 1 should be in inventory
        assertTrue(player.getInventory().getItems().stream()
            .anyMatch(item -> item.getName().equals("Sword 1")));
        
        // Swap to third weapon
        player.addItemToInventory(sword3, false);
        player.equipItem(sword3, false);
        assertEquals("Sword 3", player.getEquippedWeapon().getName());
        
        // Both previous swords should be in inventory
        long swordCount = player.getInventory().getItems().stream()
            .filter(item -> item instanceof Weapon)
            .count();
        assertEquals(2, swordCount);
    }

    @Test
    void testBattleTurnOrderRespectsDexterity()
    {
        Player fastPlayer = new Player("Fast", 5, 10, 5);
        Enemy slowEnemy = new Enemy("Slow", 50, 5, 1, 5, 10);
        Enemy fastEnemy = new Enemy("Fast", 50, 5, 8, 5, 10);
        
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(slowEnemy);
        enemies.add(fastEnemy);
        
        Battle battle = new Battle(enemies, fastPlayer);
        var participants = battle.getParticipantsOrderedByDexterity();
        
        // Order should be: fastPlayer (10), fastEnemy (8), slowEnemy (1)
        assertEquals(10, participants.get(0).getDexterity());
        assertEquals(8, participants.get(1).getDexterity());
        assertEquals(1, participants.get(2).getDexterity());
    }

    @Test
    void testPlayerDeathStopsCombat()
    {
        player.setHealth(1);
        Enemy strongEnemy = new Enemy("Strong", 100, 10, 5, 5, 20);
        
        strongEnemy.attack(player);
        
        assertFalse(player.isAlive());
        assertEquals(CharacterStatus.DEAD, player.getStatus());
    }

    @Test
    void testInventoryWeightTracking()
    {
        Inventory inventory = player.getInventory();
        assertEquals(0, inventory.getWeight());
        
        Weapon sword = new Weapon("Sword", 5.0, 100, 20, false);
        Armour armour = new Armour("Armour", 10.0, 150, 25);
        Shield shield = new Shield("Shield", 6.0, 80, 15);
        HealingPotion potion = new HealingPotion("Potion", 1.0, 50, 20);
        
        player.addItemToInventory(sword, false);
        assertEquals(5.0, inventory.getWeight());
        
        player.addItemToInventory(armour, false);
        assertEquals(15.0, inventory.getWeight());
        
        player.addItemToInventory(shield, false);
        assertEquals(21.0, inventory.getWeight());
        
        player.addItemToInventory(potion, false);
        assertEquals(22.0, inventory.getWeight());
    }
}
