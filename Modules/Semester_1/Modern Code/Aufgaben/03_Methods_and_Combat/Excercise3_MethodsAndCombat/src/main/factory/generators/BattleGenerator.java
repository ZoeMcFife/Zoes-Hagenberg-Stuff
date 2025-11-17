package main.factory.generators;

import main.character.DangerLevel;
import main.character.Enemy;
import main.combat.Battle;
import main.factory.baseFactories.ShieldFactory;
import main.global.GameManager;
import main.item.ItemRarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates battle encounters with enemies based on danger level.
 * Determines the number of enemies to spawn for each danger level.
 */
public class BattleGenerator
{
    /**
     * Creates a new battle with enemies appropriate for the specified danger level.
     * 
     * @param dangerLevel The danger level determining enemy count and strength
     * @return A new battle instance with generated enemies
     */
    public static Battle generateBattle(DangerLevel dangerLevel)
    {
        int numberOfEnemies = getEnemyCountForDangerLevel(dangerLevel);

        List<Enemy> enemies = new ArrayList<>();

        for (int i = 0; i < numberOfEnemies; i++)
        {
            Enemy enemy = EnemyGenerator.generateEnemy(dangerLevel);
            enemies.add(enemy);
        }

        return new Battle(enemies, GameManager.getPlayer());
    }

    /**
     * Determines a random number of enemies based on danger level.
     * Higher danger levels allow for more enemies.
     * 
     * @param dangerLevel The danger level
     * @return Random number of enemies within the range for that danger level
     */
    private static int getEnemyCountForDangerLevel(DangerLevel dangerLevel)
    {
        Random random = new Random();

        return switch (dangerLevel)
        {
            case DangerLevel.HARMLESS -> getRandomIntInRange(1, GameManager.MAX_ENEMIES_PER_BATTLE_HARMLESS);
            case DangerLevel.MOSTLY_HARMLESS -> getRandomIntInRange(1, GameManager.MAX_ENEMIES_PER_BATTLE_MOSTLY_HARMLESS);
            case DangerLevel.DANGEROUS -> getRandomIntInRange(1, GameManager.MAX_ENEMIES_PER_BATTLE_DANGEROUS);
            case DangerLevel.EXTREME -> getRandomIntInRange(1, GameManager.MAX_ENEMIES_PER_BATTLE_EXTREME);
            case DangerLevel.DEATH -> getRandomIntInRange(1, GameManager.MAX_ENEMIES_PER_BATTLE_DEATH);
        };
    }

    /**
     * Generates a random integer within the specified range (inclusive).
     * 
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Random integer between min and max
     */
    private static int getRandomIntInRange(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

}
