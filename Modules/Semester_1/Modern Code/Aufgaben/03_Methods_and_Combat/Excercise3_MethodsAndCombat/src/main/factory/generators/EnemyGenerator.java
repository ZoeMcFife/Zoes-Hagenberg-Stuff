package main.factory.generators;

import main.character.DangerLevel;
import main.character.Enemy;
import main.factory.baseFactories.*;
import main.global.Difficulty;
import main.item.*;

import java.util.ArrayList;
import java.util.List;

public class EnemyGenerator
{
    public static Enemy generateEnemy(DangerLevel dangerLevel)
    {
        Enemy enemy = EnemyFactory.createRandomEnemyByDangerLevel(dangerLevel);

        Weapon weapon = generateWeapon(dangerLevel);
        Armour armour = generateArmour(dangerLevel);
        Shield shield = generateShield(dangerLevel);

        enemy.equipItem(weapon);
        enemy.equipItem(armour);
        enemy.equipItem(shield);

        enemy.addItemsToInventory((Item) generateHealingPotions(dangerLevel));

        return enemy;
    }

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
