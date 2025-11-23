package test;

import main.item.Armour;
import main.item.ArmourState;
import main.item.ItemRarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ArmourTest
{
    private Armour testArmour;

    @BeforeEach
    void setUp()
    {
        testArmour = new Armour("Test Armour", 10.0, 150, 20);
    }

    @Test
    void testArmourCreation()
    {
        assertNotNull(testArmour);
        assertEquals("Test Armour", testArmour.getName());
        assertEquals(10.0, testArmour.getWeight());
        assertEquals(150, testArmour.getValue());
        assertEquals(20, testArmour.getDefense());
    }

    @Test
    void testArmourWithRarity()
    {
        Armour legendaryArmour = new Armour("Legendary Plate", 15.0, 1000, 40, ItemRarity.LEGENDARY);
        assertEquals(ItemRarity.LEGENDARY, legendaryArmour.getRarity());
        assertEquals(40, legendaryArmour.getDefense());
    }

    @Test
    void testArmourWithCustomDurability()
    {
        Armour customArmour = new Armour("Custom Armour", 12.0, 200, 25, 150.0);
        assertEquals(150.0, customArmour.getMaxDurability());
        assertEquals(150.0, customArmour.getDurability());
    }

    @Test
    void testArmourWithCustomDurabilityAndRarity()
    {
        Armour customArmour = new Armour("Custom Armour", 12.0, 200, 25, 150.0, ItemRarity.HIGH);
        assertEquals(150.0, customArmour.getMaxDurability());
        assertEquals(ItemRarity.HIGH, customArmour.getRarity());
    }

    @Test
    void testInitialDurabilityIsFull()
    {
        assertEquals(testArmour.getMaxDurability(), testArmour.getDurability());
    }

    @Test
    void testInitialStateIsPristine()
    {
        assertEquals(ArmourState.PRISTINE, testArmour.getState());
    }

    @Test
    void testDurabilityReduction()
    {
        double initialDurability = testArmour.getDurability();
        testArmour.reduceDurability(10);
        assertTrue(testArmour.getDurability() < initialDurability);
    }

    @Test
    void testDurabilityCannotGoNegative()
    {
        testArmour.reduceDurability(1000);
        assertEquals(0, testArmour.getDurability());
    }

    @Test
    void testNegativeDurabilityReductionIgnored()
    {
        double initialDurability = testArmour.getDurability();
        testArmour.reduceDurability(-10);
        assertEquals(initialDurability, testArmour.getDurability());
    }

    @Test
    void testArmourStateTransitions()
    {
        // Start at PRISTINE
        assertEquals(ArmourState.PRISTINE, testArmour.getState());
        
        // Reduce to SCRATCHED (80-95% durability)
        testArmour.reduceDurability(10); // ~90% durability
        assertEquals(ArmourState.SCRATCHED, testArmour.getState());
        
        // Reduce to WORN (50-80% durability)
        testArmour.reduceDurability(30); // ~60% durability
        assertEquals(ArmourState.WORN, testArmour.getState());
        
        // Reduce to DAMAGED (10-50% durability)
        testArmour.reduceDurability(35); // ~25% durability
        assertEquals(ArmourState.DAMAGED, testArmour.getState());
        
        // Reduce to BROKEN (<10% durability)
        testArmour.reduceDurability(20); // ~5% durability
        assertEquals(ArmourState.BROKEN, testArmour.getState());
    }

    @Test
    void testDefenseScalesWithDurability()
    {
        double initialDefense = testArmour.getDefense();
        
        // At full durability, defense should be full
        assertEquals(20, testArmour.getDefense(), 0.01);
        
        // Reduce durability to 50%
        testArmour.reduceDurability(50);
        double halfDefense = testArmour.getDefense();
        
        // Defense should be approximately half
        assertTrue(halfDefense < initialDefense);
        assertEquals(10, halfDefense, 1.0); // Allow some variance due to wear multiplier
    }

    @Test
    void testBrokenArmourProvidesLessDefense()
    {
        // Reduce to broken state
        testArmour.reduceDurability(95);
        assertEquals(ArmourState.BROKEN, testArmour.getState());
        
        // Defense should be very low
        assertTrue(testArmour.getDefense() < 5);
    }

    @Test
    void testWearMultiplierIncreasesWithDamage()
    {
        // PRISTINE has lowest wear multiplier (0.6)
        assertEquals(ArmourState.PRISTINE, testArmour.getState());
        
        // Damage to WORN state - should wear faster
        testArmour.reduceDurability(40);
        ArmourState wornState = testArmour.getState();
        assertEquals(ArmourState.WORN, wornState);
        
        // Further damage should accumulate faster
        double durabilityBefore = testArmour.getDurability();
        testArmour.reduceDurability(10);
        double durabilityAfter = testArmour.getDurability();
        
        // Wear should be more than 10 due to multiplier
        assertTrue(durabilityBefore - durabilityAfter > 10);
    }

    @Test
    void testNegativeDefenseNotAllowed()
    {
        Armour armour = new Armour("Weak Armour", 5.0, 50, -10);
        assertEquals(0, armour.getDefense());
    }

    @Test
    void testSetDefenseWithNegativeValue()
    {
        testArmour.setDefense(-5);
        assertTrue(testArmour.getDefense() >= 0);
    }

    @Test
    void testArmourToString()
    {
        String str = testArmour.toString();
        assertNotNull(str);
        assertTrue(str.contains(testArmour.getName()));
        assertTrue(str.contains("DEF"));
        assertTrue(str.contains(testArmour.getState().name()));
    }

    @Test
    void testArmourStateWearMultipliers()
    {
        assertEquals(0.6, ArmourState.PRISTINE.wearMultiplier);
        assertEquals(1.0, ArmourState.SCRATCHED.wearMultiplier);
        assertEquals(1.2, ArmourState.WORN.wearMultiplier);
        assertEquals(1.5, ArmourState.DAMAGED.wearMultiplier);
        assertEquals(3.0, ArmourState.BROKEN.wearMultiplier);
    }
}
