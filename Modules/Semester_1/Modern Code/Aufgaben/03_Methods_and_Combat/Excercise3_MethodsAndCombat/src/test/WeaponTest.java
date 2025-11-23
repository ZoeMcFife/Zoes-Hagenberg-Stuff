package test;

import main.item.Weapon;
import main.item.ItemRarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest
{
    private Weapon testWeapon;

    @BeforeEach
    void setUp()
    {
        testWeapon = new Weapon("Test Sword", 5.0, 100, 20, false);
    }

    @Test
    void testWeaponCreation()
    {
        assertNotNull(testWeapon);
        assertEquals("Test Sword", testWeapon.getName());
        assertEquals(5.0, testWeapon.getWeight());
        assertEquals(100, testWeapon.getValue());
        assertEquals(20, testWeapon.getDamage());
        assertFalse(testWeapon.isMagic());
    }

    @Test
    void testWeaponWithRarity()
    {
        Weapon legendaryWeapon = new Weapon("Legendary Blade", 7.0, 500, 40, true, ItemRarity.LEGENDARY);
        assertEquals(ItemRarity.LEGENDARY, legendaryWeapon.getRarity());
        assertTrue(legendaryWeapon.isMagic());
    }

    @Test
    void testWeaponWithSpecialAttack()
    {
        Weapon specialWeapon = new Weapon(
            "Special Sword", 6.0, 200, 25, false, ItemRarity.HIGH,
            35, "Power Slash", "The blade glows with energy!", 30, 5
        );
        
        assertEquals(35, specialWeapon.getSpecialDamage());
        assertEquals("Power Slash", specialWeapon.getSpecialAttackName());
        assertEquals("The blade glows with energy!", specialWeapon.getSpecialFlavorText());
        assertEquals(30, specialWeapon.getPpCost());
        assertEquals(5, specialWeapon.getPpGainPerUse());
    }

    @Test
    void testMagicWeapon()
    {
        Weapon magicStaff = new Weapon("Magic Staff", 3.0, 150, 18, true);
        assertTrue(magicStaff.isMagic());
    }

    @Test
    void testPhysicalWeapon()
    {
        Weapon sword = new Weapon("Iron Sword", 5.0, 80, 15, false);
        assertFalse(sword.isMagic());
    }

    @Test
    void testSetDamage()
    {
        testWeapon.setDamage(30);
        assertEquals(30, testWeapon.getDamage());
    }

    @Test
    void testNegativeDamageNotAllowed()
    {
        Weapon weapon = new Weapon("Weak Weapon", 5.0, 50, -10, false);
        assertEquals(0, weapon.getDamage());
    }

    @Test
    void testSetDamageWithNegativeValue()
    {
        testWeapon.setDamage(-5);
        assertEquals(0, testWeapon.getDamage());
    }

    @Test
    void testSetMagic()
    {
        assertFalse(testWeapon.isMagic());
        testWeapon.setMagic(true);
        assertTrue(testWeapon.isMagic());
    }

    @Test
    void testSpecialDamage()
    {
        Weapon specialWeapon = new Weapon(
            "Special Axe", 8.0, 300, 30, false, ItemRarity.HIGH,
            20, "Whirlwind", "Spins with fury!", 25, 8
        );
        
        assertEquals(20, specialWeapon.getSpecialDamage());
    }

    @Test
    void testSetSpecialDamage()
    {
        testWeapon.setSpecialDamage(15);
        assertEquals(15, testWeapon.getSpecialDamage());
    }

    @Test
    void testNegativeSpecialDamageNotAllowed()
    {
        testWeapon.setSpecialDamage(-10);
        assertEquals(0, testWeapon.getSpecialDamage());
    }

    @Test
    void testSpecialAttackName()
    {
        testWeapon.setSpecialAttackName("Thunder Strike");
        assertEquals("Thunder Strike", testWeapon.getSpecialAttackName());
    }

    @Test
    void testNullSpecialAttackName()
    {
        testWeapon.setSpecialAttackName(null);
        assertEquals("", testWeapon.getSpecialAttackName());
    }

    @Test
    void testSpecialFlavorText()
    {
        testWeapon.setSpecialFlavorText("Lightning crackles around the blade!");
        assertEquals("Lightning crackles around the blade!", testWeapon.getSpecialFlavorText());
    }

    @Test
    void testNullSpecialFlavorText()
    {
        testWeapon.setSpecialFlavorText(null);
        assertEquals("", testWeapon.getSpecialFlavorText());
    }

    @Test
    void testPpCost()
    {
        testWeapon.setPpCost(20);
        assertEquals(20, testWeapon.getPpCost());
    }

    @Test
    void testNegativePpCostNotAllowed()
    {
        testWeapon.setPpCost(-5);
        assertEquals(0, testWeapon.getPpCost());
    }

    @Test
    void testPpGainPerUse()
    {
        testWeapon.setPpGainPerUse(10);
        assertEquals(10, testWeapon.getPpGainPerUse());
    }

    @Test
    void testNegativePpGainNotAllowed()
    {
        testWeapon.setPpGainPerUse(-3);
        assertEquals(0, testWeapon.getPpGainPerUse());
    }

    @Test
    void testWeaponToString()
    {
        String str = testWeapon.toString();
        assertNotNull(str);
        assertTrue(str.contains(testWeapon.getName()));
        assertTrue(str.contains("DMG"));
    }

    @Test
    void testDefaultSpecialValues()
    {
        Weapon defaultWeapon = new Weapon("Default", 5.0, 100, 15, false);
        assertEquals(0, defaultWeapon.getSpecialDamage());
        assertEquals("", defaultWeapon.getSpecialAttackName());
        assertEquals("", defaultWeapon.getSpecialFlavorText());
        assertEquals(0, defaultWeapon.getPpCost());
    }
}
