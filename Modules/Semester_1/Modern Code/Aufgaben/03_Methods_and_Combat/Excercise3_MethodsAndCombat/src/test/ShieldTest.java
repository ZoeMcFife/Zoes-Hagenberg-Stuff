package test;

import main.item.Shield;
import main.item.ItemRarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ShieldTest
{
    private Shield testShield;

    @BeforeEach
    void setUp()
    {
        testShield = new Shield("Test Shield", 6.0, 80, 15);
    }

    @Test
    void testShieldCreation()
    {
        assertNotNull(testShield);
        assertEquals("Test Shield", testShield.getName());
        assertEquals(6.0, testShield.getWeight());
        assertEquals(80, testShield.getValue());
        assertEquals(15, testShield.getDefense());
    }

    @Test
    void testShieldWithRarity()
    {
        Shield rareShield = new Shield("Rare Shield", 7.0, 200, 20, ItemRarity.HIGH);
        assertEquals(ItemRarity.HIGH, rareShield.getRarity());
        assertEquals(20, rareShield.getDefense());
    }

    @Test
    void testShieldWithPpGain()
    {
        Shield ppShield = new Shield("PP Shield", 6.5, 150, 18, ItemRarity.MEDIUM, 10);
        assertEquals(10, ppShield.getPpGain());
        assertEquals(18, ppShield.getDefense());
    }

    @Test
    void testSetDefense()
    {
        testShield.setDefense(20);
        assertEquals(20, testShield.getDefense());
    }

    @Test
    void testNegativeDefenseNotAllowed()
    {
        Shield shield = new Shield("Weak Shield", 5.0, 40, -5);
        assertEquals(0, shield.getDefense());
    }

    @Test
    void testSetDefenseWithNegativeValue()
    {
        testShield.setDefense(-10);
        assertEquals(0, testShield.getDefense());
    }

    @Test
    void testPpGain()
    {
        testShield.setPpGain(12);
        assertEquals(12, testShield.getPpGain());
    }

    @Test
    void testNegativePpGainNotAllowed()
    {
        testShield.setPpGain(-5);
        assertEquals(0, testShield.getPpGain());
    }

    @Test
    void testDefaultPpGain()
    {
        Shield defaultShield = new Shield("Default", 6.0, 80, 15);
        assertEquals(0, defaultShield.getPpGain());
    }

    @Test
    void testShieldToString()
    {
        String str = testShield.toString();
        assertNotNull(str);
        assertTrue(str.contains(testShield.getName()));
        assertTrue(str.contains("DEF"));
    }
}
