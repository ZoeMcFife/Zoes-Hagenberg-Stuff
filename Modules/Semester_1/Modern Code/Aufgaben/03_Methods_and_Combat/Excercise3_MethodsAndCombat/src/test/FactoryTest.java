package test;

import main.character.DangerLevel;
import main.character.Enemy;
import main.factory.baseFactories.*;
import main.item.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest
{
    @Test
    void testEnemyFactoryById()
    {
        Enemy enemy = EnemyFactory.createEnemyById(1);
        assertNotNull(enemy);
        assertEquals("Abyssal Marauder", enemy.getName());
        assertEquals(60, enemy.getMaxHealth());
        assertEquals(5, enemy.getStrength());
        assertEquals(6, enemy.getDexterity());
        assertEquals(6, enemy.getIntelligence());
    }

    @Test
    void testEnemyFactoryByName()
    {
        Enemy enemy = EnemyFactory.createEnemyByName("Gateway Corsair");
        assertNotNull(enemy);
        assertEquals("Gateway Corsair", enemy.getName());
        assertEquals(85, enemy.getMaxHealth());
    }

    @Test
    void testEnemyFactoryRandom()
    {
        Enemy enemy = EnemyFactory.createRandomEnemy();
        assertNotNull(enemy);
        assertTrue(enemy.getMaxHealth() > 0);
    }

    @Test
    void testEnemyFactoryByDangerLevel()
    {
        Enemy enemy = EnemyFactory.createRandomEnemyByDangerLevel(DangerLevel.DEATH);
        assertNotNull(enemy);
        assertTrue(enemy.getMaxHealth() >= 200); // DEATH level enemies should be powerful
    }

    @Test
    void testEnemyCount()
    {
        assertEquals(50, EnemyFactory.getEnemyCount());
    }

    @Test
    void testWeaponFactoryById()
    {
        Weapon weapon = WeaponFactory.createWeaponById(1);
        assertNotNull(weapon);
        assertEquals("Gateway Cutlass", weapon.getName());
        assertEquals(3.0, weapon.getWeight());
        assertEquals(120, weapon.getValue());
        assertEquals(18, weapon.getDamage());
        assertFalse(weapon.isMagic());
        assertEquals(ItemRarity.MEDIUM, weapon.getRarity());
    }

    @Test
    void testWeaponFactoryByName()
    {
        Weapon weapon = WeaponFactory.createWeaponByName("Phasebreaker Blade");
        assertNotNull(weapon);
        assertEquals("Phasebreaker Blade", weapon.getName());
        assertTrue(weapon.isMagic());
        assertEquals(ItemRarity.LEGENDARY, weapon.getRarity());
    }

    @Test
    void testWeaponFactoryRandom()
    {
        Weapon weapon = WeaponFactory.createRandomWeapon();
        assertNotNull(weapon);
        assertTrue(weapon.getDamage() > 0);
    }

    @Test
    void testWeaponFactoryByRarity()
    {
        Weapon weapon = WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);
        assertNotNull(weapon);
        assertEquals(ItemRarity.LEGENDARY, weapon.getRarity());
    }

    @Test
    void testShieldFactoryById()
    {
        Shield shield = ShieldFactory.createShieldById(1);
        assertNotNull(shield);
        assertEquals("Echo Shard Shield", shield.getName());
        assertEquals(28, shield.getDefense());
        assertEquals(ItemRarity.LEGENDARY, shield.getRarity());
    }

    @Test
    void testShieldFactoryByName()
    {
        Shield shield = ShieldFactory.createShieldByName("Stormbreaker Shield");
        assertNotNull(shield);
        assertEquals(26, shield.getDefense());
    }

    @Test
    void testShieldFactoryRandom()
    {
        Shield shield = ShieldFactory.createRandomShield();
        assertNotNull(shield);
        assertTrue(shield.getDefense() > 0);
    }

    @Test
    void testArmourFactoryById()
    {
        Armour armour = ArmourFactory.createArmourById(1);
        assertNotNull(armour);
        assertEquals("Fargoth War Gauntlet", armour.getName());
        assertEquals(12, armour.getDefense());
        assertEquals(ItemRarity.HIGH, armour.getRarity());
    }

    @Test
    void testArmourFactoryByName()
    {
        Armour armour = ArmourFactory.createArmourByName("Skymetal Plate");
        assertNotNull(armour);
        assertEquals(36, armour.getDefense());
        assertEquals(ItemRarity.LEGENDARY, armour.getRarity());
    }

    @Test
    void testArmourFactoryRandom()
    {
        Armour armour = ArmourFactory.createRandomArmour();
        assertNotNull(armour);
        assertTrue(armour.getDefense() > 0);
    }

    @Test
    void testHealingPotionFactoryById()
    {
        HealingPotion potion = HealingPotionFactory.createPotionById(1);
        assertNotNull(potion);
        assertEquals("Small Health Potion", potion.getName());
        assertEquals(20, potion.getHealingAmount());
    }

    @Test
    void testHealingPotionFactoryByName()
    {
        HealingPotion potion = HealingPotionFactory.createPotionByName("Emergency Tonic");
        assertNotNull(potion);
        assertEquals(50, potion.getHealingAmount());
        assertEquals(ItemRarity.HIGH, potion.getRarity());
    }

    @Test
    void testHealingPotionFactoryRandom()
    {
        HealingPotion potion = HealingPotionFactory.createRandomPotion();
        assertNotNull(potion);
        assertTrue(potion.getHealingAmount() > 0);
    }

    @Test
    void testAllFactoryCounts()
    {
        assertEquals(50, EnemyFactory.getEnemyCount());
        assertEquals(24, WeaponFactory.getWeaponCount());
        assertEquals(7, ShieldFactory.getShieldCount());
        assertEquals(18, ArmourFactory.getArmourCount());
        assertEquals(22, HealingPotionFactory.getPotionCount());
    }
}
