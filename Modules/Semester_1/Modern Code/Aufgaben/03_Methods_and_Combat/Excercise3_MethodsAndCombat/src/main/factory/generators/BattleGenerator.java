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
            case DangerLevel.HARMLESS -> random.nextInt(1, GameManager.MAX_ENEMIES_PER_BATTLE_HARMLESS);
            case DangerLevel.MOSTLY_HARMLESS -> random.nextInt(1, GameManager.MAX_ENEMIES_PER_BATTLE_MOSTLY_HARMLESS);
            case DangerLevel.DANGEROUS -> random.nextInt(1, GameManager.MAX_ENEMIES_PER_BATTLE_DANGEROUS);
            case DangerLevel.EXTREME -> random.nextInt(1, GameManager.MAX_ENEMIES_PER_BATTLE_EXTREME);
            case DangerLevel.DEATH -> random.nextInt(1, GameManager.MAX_ENEMIES_PER_BATTLE_DEATH);
        };
    }


}
