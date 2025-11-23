package test;

import main.item.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest
{
    @Test
    void testWeaponCreation()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, 15, false);
        
        assertNotNull(sword);
        assertEquals("Test Sword", sword.getName());
        assertEquals(5.0, sword.getWeight());
        assertEquals(100, sword.getValue());
        assertEquals(15, sword.getDamage());
        assertFalse(sword.isMagic());
    }

    @Test
    void testWeaponWithRarity()
    {
        Weapon legendarySword = new Weapon("Legendary Sword", 7.0, 500, 30, true, ItemRarity.LEGENDARY);
        
        assertEquals(ItemRarity.LEGENDARY, legendarySword.getRarity());
        assertTrue(legendarySword.isMagic());
    }

    @Test
    void testWeaponDamageNonNegative()
    {
        Weapon sword = new Weapon("Test Sword", 5.0, 100, -10, false);
        assertEquals(0, sword.getDamage());
    }

    @Test
    void testShieldCreation()
    {
        Shield shield = new Shield("Test Shield", 6.0, 80, 12);
        
        assertNotNull(shield);
        assertEquals("Test Shield", shield.getName());
        assertEquals(6.0, shield.getWeight());
        assertEquals(80, shield.getValue());
        assertEquals(12, shield.getDefense());
    }

    @Test
    void testShieldWithRarity()
    {
        Shield rareShield = new Shield("Rare Shield", 5.0, 200, 18, ItemRarity.HIGH);
        
        assertEquals(ItemRarity.HIGH, rareShield.getRarity());
        assertEquals(18, rareShield.getDefense());
    }

    @Test
    void testArmourCreation()
    {
        Armour armour = new Armour("Test Armour", 10.0, 150, 20);
        
        assertNotNull(armour);
        assertEquals("Test Armour", armour.getName());
        assertEquals(10.0, armour.getWeight());
        assertEquals(150, armour.getValue());
        assertEquals(20, armour.getDefense());
    }

    @Test
    void testArmourWithRarity()
    {
        Armour legendaryArmour = new Armour("Legendary Armour", 15.0, 1000, 40, ItemRarity.LEGENDARY);
        
        assertEquals(ItemRarity.LEGENDARY, legendaryArmour.getRarity());
        assertEquals(40, legendaryArmour.getDefense());
    }

    @Test
    void testHealingPotionCreation()
    {
        HealingPotion potion = new HealingPotion("Test Potion", 1.0, 50, 30);
        
        assertNotNull(potion);
        assertEquals("Test Potion", potion.getName());
        assertEquals(1.0, potion.getWeight());
        assertEquals(50, potion.getValue());
        assertEquals(30, potion.getHealingAmount());
    }

    @Test
    void testHealingPotionWithRarity()
    {
        HealingPotion rarePotion = new HealingPotion("Rare Potion", 0.5, 100, 50, ItemRarity.HIGH);
        
        assertEquals(ItemRarity.HIGH, rarePotion.getRarity());
        assertEquals(50, rarePotion.getHealingAmount());
    }

    @Test
    void testItemRarityLevels()
    {
        assertNotNull(ItemRarity.LOW);
        assertNotNull(ItemRarity.MEDIUM);
        assertNotNull(ItemRarity.HIGH);
        assertNotNull(ItemRarity.LEGENDARY);
    }

    @Test
    void testWeaponMagicProperty()
    {
        Weapon magicStaff = new Weapon("Magic Staff", 3.0, 200, 20, true);
        Weapon normalSword = new Weapon("Normal Sword", 5.0, 100, 20, false);
        
        assertTrue(magicStaff.isMagic());
        assertFalse(normalSword.isMagic());
    }

    @Test
    void testItemValueAndWeight()
    {
        Item testItem = new Weapon("Test Item", 2.5, 75, 10, false);
        
        assertEquals(2.5, testItem.getWeight());
        assertEquals(75, testItem.getValue());
    }
}
