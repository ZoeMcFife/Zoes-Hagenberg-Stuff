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

        enemy.equipItem(weapon);
        enemy.equipItem(armour);
        enemy.equipItem(shield);

        List<HealingPotion> healingPotions = generateHealingPotions(dangerLevel);

        for (HealingPotion potion : healingPotions)
        {
            enemy.addItemToInventory(potion);
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
            case DangerLevel.HARMLESS, DangerLevel.MOSTLY_HARMLESS -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.LOW);
            case DangerLevel.DANGEROUS -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.MEDIUM);
            case DangerLevel.EXTREME -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.HIGH);
            case DangerLevel.DEATH -> WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);
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
            case DangerLevel.HARMLESS, DangerLevel.MOSTLY_HARMLESS -> ArmourFactory.createRandomArmourByRarity(ItemRarity.LOW);
            case DangerLevel.DANGEROUS -> ArmourFactory.createRandomArmourByRarity(ItemRarity.MEDIUM);
            case DangerLevel.EXTREME -> ArmourFactory.createRandomArmourByRarity(ItemRarity.HIGH);
            case DangerLevel.DEATH -> ArmourFactory.createRandomArmourByRarity(ItemRarity.LEGENDARY);
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
            case DangerLevel.HARMLESS, DangerLevel.MOSTLY_HARMLESS -> ShieldFactory.createRandomShieldByRarity(ItemRarity.LOW);
            case DangerLevel.DANGEROUS -> ShieldFactory.createRandomShieldByRarity(ItemRarity.MEDIUM);
            case DangerLevel.EXTREME -> ShieldFactory.createRandomShieldByRarity(ItemRarity.HIGH);
            case DangerLevel.DEATH -> ShieldFactory.createRandomShieldByRarity(ItemRarity.LEGENDARY);
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
            case DangerLevel.HARMLESS, DangerLevel.MOSTLY_HARMLESS -> 1;
            case DangerLevel.DANGEROUS -> 2;
            case DangerLevel.EXTREME -> 3;
            case DangerLevel.DEATH -> 5;
        };

        for (int i = 0; i < potionCount; i++)
        {
            HealingPotion potion = HealingPotionFactory.createRandomPotionByRarity(
                switch (dangerLevel)
                {
                    case DangerLevel.HARMLESS, DangerLevel.MOSTLY_HARMLESS -> ItemRarity.LOW;
                    case DangerLevel.DANGEROUS -> ItemRarity.MEDIUM;
                    case DangerLevel.EXTREME -> ItemRarity.HIGH;
                    case DangerLevel.DEATH -> ItemRarity.LEGENDARY;
                }
            );

            potions.add(potion);
        }

        return potions;
    }
}
