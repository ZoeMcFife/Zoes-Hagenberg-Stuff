package main.global;

import main.character.DangerLevel;
import main.character.Player;

public class GameManager
{
    private static Player player;
    public static boolean hasPlayerBeenInitialized = false;

    public static double DAMAGE_MULTIPLIER_PER_STRENGTH = 0.05;
    public static double DAMAGE_MULTIPLIER_PER_INTELLIGENCE = 0.03;
    public static double DODGE_CHANCE_PER_DEXTERITY = 0.02;
    public static double DAMAGE_REDUCTION_PER_DEFENSE = 0.04;
    public static final int CARRY_CAPACITY_PER_STRENGTH = 10;

    public static Difficulty difficulty = Difficulty.NONE;

    public static int DIFFICULTY_INCREASE_AFTER_TURNS_EASY = 5;
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM = 3;
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_HARD = 1;

    public static int MAX_ENEMIES_PER_BATTLE_HARMLESS = 1;
    public static int MAX_ENEMIES_PER_BATTLE_MOSTLY_HARMLESS = 2;
    public static int MAX_ENEMIES_PER_BATTLE_DANGEROUS = 2;
    public static int MAX_ENEMIES_PER_BATTLE_EXTREME = 3;
    public static int MAX_ENEMIES_PER_BATTLE_DEATH = 4;

    public static void setPlayer(Player player)
    {
        GameManager.player = player;
        hasPlayerBeenInitialized = true;
    }

    public static Player getPlayer()
    {
        return GameManager.player;
    }

    public static void removePlayer()
    {
        GameManager.player = null;
        hasPlayerBeenInitialized = false;
    }

}
