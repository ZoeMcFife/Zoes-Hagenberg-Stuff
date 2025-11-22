package main.factory.generators;

import main.character.DangerLevel;
import main.character.Enemy;
import main.factory.baseFactories.*;
import main.global.Difficulty;
import main.item.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates fully equipped enemies with gear and healing items appropriate for their danger level.
 * Higher danger levels result in better equipment and more healing potions.
 */
public class EnemyGenerator
{
    /**
     * Creates a fully equipped enemy with appropriate gear for the danger level.
     * 
     * @param dangerLevel The danger level determining enemy type and equipment quality
     * @return A fully equipped enemy character
     */
    public static Enemy generateEnemy(DangerLevel dangerLevel)
    {
        Enemy enemy = EnemyFactory.createRandomEnemyByDangerLevel(dangerLevel);

        Weapon weapon = generateWeapon(dangerLevel);
        Armour armour = generateArmour(dangerLevel);
        Shield shield = generateShield(dangerLevel);

        enemy.equipItem(weapon, false, false);
        enemy.equipItem(armour, false, false);
        enemy.equipItem(shield, false, false);

        List<HealingPotion> healingPotions = generateHealingPotions(dangerLevel);

        for (HealingPotion potion : healingPotions)
        {
            enemy.addItemToInventory(potion, false);
        }

        return enemy;
    }

    /**
     * Generates a weapon with rarity matching the danger level.
     * 
     * @param dangerLevel The danger level
     * @return A weapon of appropriate rarity
     */
    private static Weapon generateWeapon(DangerLevel dangerLevel)
    {
        return switch (dangerLevel)
        {
            case HARMLESS -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.LOW);
            case MOSTLY_HARMLESS -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.MEDIUM);
            case DANGEROUS -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.MEDIUM);
            case EXTREME -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.HIGH);
            case DEATH -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);
        };
    }

    /**
     * Generates armour with rarity matching the danger level.
     * 
     * @param dangerLevel The danger level
     * @return Armour of appropriate rarity
     */
    private static Armour generateArmour(DangerLevel dangerLevel)
    {
        return switch (dangerLevel)
        {
            case HARMLESS -> ArmourFactory.createRandomArmourByRarity(ItemRarity.LOW);
            case MOSTLY_HARMLESS -> ArmourFactory.createRandomArmourByRarity(ItemRarity.MEDIUM);
            case DANGEROUS -> ArmourFactory.createRandomArmourByRarity(ItemRarity.MEDIUM);
            case EXTREME -> ArmourFactory.createRandomArmourByRarity(ItemRarity.HIGH);
            case DEATH -> ArmourFactory.createRandomArmourByRarity(ItemRarity.LEGENDARY);
        };
    }

    /**
     * Generates a shield with rarity matching the danger level.
     * 
     * @param dangerLevel The danger level
     * @return A shield of appropriate rarity
     */
    private static Shield generateShield(DangerLevel dangerLevel)
    {
        return switch (dangerLevel)
        {
            case HARMLESS -> ShieldFactory.createRandomShieldByRarity(ItemRarity.LOW);
            case MOSTLY_HARMLESS -> ShieldFactory.createRandomShieldByRarity(ItemRarity.MEDIUM);
            case DANGEROUS -> ShieldFactory.createRandomShieldByRarity(ItemRarity.MEDIUM);
            case EXTREME -> ShieldFactory.createRandomShieldByRarity(ItemRarity.HIGH);
            case DEATH -> ShieldFactory.createRandomShieldByRarity(ItemRarity.LEGENDARY);
        };
    }

    /**
     * Generates healing potions appropriate for the danger level.
     * Higher danger levels result in more potions of higher quality.
     * 
     * @param dangerLevel The danger level
     * @return List of healing potions
     */
    private static List<HealingPotion> generateHealingPotions(DangerLevel dangerLevel)
    {
        List<HealingPotion> potions = new ArrayList<HealingPotion>();

        int potionCount = switch (dangerLevel)
        {
            case HARMLESS, MOSTLY_HARMLESS -> 1;
            case DANGEROUS -> 2;
            case EXTREME -> 3;
            case DEATH -> 5;
        };

        for (int i = 0; i < potionCount; i++)
        {
            HealingPotion potion = HealingPotionFactory.createRandomPotionByRarity(
                switch (dangerLevel)
                {
                    case HARMLESS, MOSTLY_HARMLESS -> ItemRarity.LOW;
                    case DANGEROUS -> ItemRarity.MEDIUM;
                    case EXTREME -> ItemRarity.HIGH;
                    case DEATH -> ItemRarity.LEGENDARY;
                }
            );

            potions.add(potion);
        }

        return potions;
    }
}
