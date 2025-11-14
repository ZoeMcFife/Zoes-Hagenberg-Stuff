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

public class BattleGenerator
{
    public static Battle generateBattle(DangerLevel dangerLevel)
    {
        int numberOfEnemies = getEnemyCountForDangerLevel(dangerLevel);

        List<Enemy> enemies = new ArrayList<>();

        for (int i = 0; i < numberOfEnemies; i++)
        {
            Enemy enemy = EnemyGenerator.generateEnemy(dangerLevel);
            enemies.add(enemy);
        }

        Battle battle = new Battle();

        battle.setEnemies(enemies);

        return battle;
    }

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

    private static int getRandomIntInRange(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

}
