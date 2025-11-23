package test;

import main.character.Enemy;
import main.character.Player;
import main.character.GameCharacter;
import main.global.GameManager;
import main.item.HealingPotion;
import main.item.Weapon;
import main.item.Armour;
import main.item.Shield;
import main.combat.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest
{
    private Enemy testEnemy;
    private Player testPlayer;

    @BeforeEach
    void setUp()
    {
        testPlayer = new Player("Test Hero", 5, 5, 5);
        GameManager.setPlayer(testPlayer);
        testEnemy = new Enemy("Goblin", 60, 4, 5, 3, 15);
    }

    @AfterEach
    void tearDown()
    {
        GameManager.removePlayer();
    }

    @Test
    void testEnemyCreation()
    {
        assertNotNull(testEnemy);
        assertEquals("Goblin", testEnemy.getName());
        assertEquals(60, testEnemy.getMaxHealth());
        assertEquals(4, testEnemy.getStrength());
        assertEquals(5, testEnemy.getDexterity());
        assertEquals(3, testEnemy.getIntelligence());
        assertEquals(15, testEnemy.experienceReward);
    }

    @Test
    void testEnemyCreationWithEquipment()
    {
        Weapon sword = new Weapon("Enemy Sword", 5.0, 100, 15, false);
        Armour armor = new Armour("Enemy Armor", 10.0, 150, 20);
        Shield shield = new Shield("Enemy Shield", 6.0, 80, 12);
        
        Enemy equippedEnemy = new Enemy("Armed Orc", 80, 6, 4, 2, 20, sword, armor, shield);
        
        assertNotNull(equippedEnemy);
        assertEquals("Armed Orc", equippedEnemy.getName());
        assertEquals(sword.getName(), equippedEnemy.getEquippedWeapon().getName());
        assertEquals(armor.getName(), equippedEnemy.getEquippedArmour().getName());
        assertEquals(shield.getName(), equippedEnemy.getEquippedShield().getName());
    }

    @Test
    void testEnemyAttackAction()
    {
        double initialHealth = testPlayer.getHealth();
        testEnemy.executeAction(ActionType.ATTACK, testPlayer);
        assertTrue(testPlayer.getHealth() <= initialHealth);
    }

    @Test
    void testEnemyDefendAction()
    {
        assertFalse(testEnemy.isDefending());
        testEnemy.executeAction(ActionType.DEFEND, testPlayer);
        assertTrue(testEnemy.isDefending());
    }

    @Test
    void testEnemyUseItemAction()
    {
        // Give enemy a healing potion
        HealingPotion potion = new HealingPotion("Health Potion", 1.0, 50, 30);
        testEnemy.addItemToInventory(potion, false, true);
        
        // Damage the enemy first
        testEnemy.setHealth(30);
        double initialHealth = testEnemy.getHealth();
        
        testEnemy.executeAction(ActionType.USE_ITEM, testPlayer);
        
        // Health should increase or stay the same (if no potions were used)
        assertTrue(testEnemy.getHealth() >= initialHealth);
    }

    @Test
    void testEnemyDeathGrantsExperience()
    {
        int initialExp = testPlayer.getExperience();
        
        // Kill the enemy
        testEnemy.takeDamage(1000000); // Overkill to ensure death
        
        // Experience should be granted
        assertEquals(initialExp + testEnemy.experienceReward, testPlayer.getExperience());
    }

    @Test
    void testEnemyIsAlive()
    {
        assertTrue(testEnemy.isAlive());
        
        testEnemy.setHealth(0);
        assertFalse(testEnemy.isAlive());
    }

    @Test
    void testEnemyTakesDamage()
    {
        double initialHealth = testEnemy.getHealth();
        testEnemy.takeDamage(20);
        assertTrue(testEnemy.getHealth() < initialHealth);
    }

    @Test
    void testEnemyCanAttackPlayer()
    {
        double initialPlayerHealth = testPlayer.getHealth();
        testEnemy.attack(testPlayer);
        assertTrue(testPlayer.getHealth() <= initialPlayerHealth);
    }

    @Test
    void testEnemyDamageCalculation()
    {
        double damage = testEnemy.getDamage();
        assertTrue(damage > 0);
    }

    @Test
    void testEnemyDefenseCalculation()
    {
        double defense = testEnemy.getCurrentDefense();
        assertTrue(defense >= 0);
    }

    @Test
    void testEnemyCanCarryItems()
    {
        HealingPotion potion = new HealingPotion("Small Potion", 1.0, 25, 20);
        testEnemy.addItemToInventory(potion, false, true);
        
        assertTrue(testEnemy.getInventory().getWeight() > 0);
    }

    @Test
    void testEnemyCanEquipWeapon()
    {
        Weapon newWeapon = new Weapon("Better Sword", 6.0, 200, 25, false);
        testEnemy.addItemToInventory(newWeapon, false, true);
        
        double oldDamage = testEnemy.getDamage();
        testEnemy.equipItem(newWeapon, false);
        double newDamage = testEnemy.getDamage();
        
        assertTrue(newDamage > oldDamage);
    }

    @Test
    void testEnemyCanEquipArmour()
    {
        Armour newArmour = new Armour("Better Armour", 12.0, 300, 30);
        testEnemy.addItemToInventory(newArmour, false, true);
        testEnemy.equipItem(newArmour, false);
        
        assertTrue(testEnemy.getCurrentDefense() > 0);
    }

    @Test
    void testEnemyCanEquipShield()
    {
        Shield newShield = new Shield("Better Shield", 7.0, 150, 18);
        testEnemy.addItemToInventory(newShield, false, true);
        
        testEnemy.equipItem(newShield, false);
        testEnemy.defend();
        
        assertTrue(testEnemy.getCurrentDefense() > 0);
    }

    @Test
    void testEnemyHealthPercentage()
    {
        assertEquals(1.0, testEnemy.getHealthPercentage(), 0.01);
        
        testEnemy.setHealth(testEnemy.getMaxHealth() / 2);
        assertEquals(0.5, testEnemy.getHealthPercentage(), 0.01);
        
        testEnemy.setHealth(0);
        assertEquals(0.0, testEnemy.getHealthPercentage(), 0.01);
    }

    @Test
    void testEnemyExperienceReward()
    {
        assertEquals(15, testEnemy.experienceReward);
    }

    @Test
    void testEnemyWithHigherStats()
    {
        Enemy strongEnemy = new Enemy("Boss", 150, 8, 7, 6, 50);
        
        assertTrue(strongEnemy.getDamage() > testEnemy.getDamage());
        assertTrue(strongEnemy.getMaxHealth() > testEnemy.getMaxHealth());
        assertTrue(strongEnemy.experienceReward > testEnemy.experienceReward);
    }

    @Test
    void testEnemyCannotAttackDeadTarget()
    {
        Player deadPlayer = new Player("Dead", 5, 5, 5);
        deadPlayer.setHealth(0);
        
        double health = deadPlayer.getHealth();
        testEnemy.attack(deadPlayer);
        
        // Health should remain 0
        assertEquals(health, deadPlayer.getHealth());
    }

    @Test
    void testEnemyStopDefending()
    {
        testEnemy.defend();
        assertTrue(testEnemy.isDefending());
        
        testEnemy.stopDefending();
        assertFalse(testEnemy.isDefending());
    }
}
