package main.global;

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

    public static int DIFFICULTY_INCREASE_AFTER_TURNS_EASY = 6;
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM = 4;
    public static int DIFFICULTY_INCREASE_AFTER_TURNS_HARD = 1;

    public static void setPlayer(Player player)
    {
        GameManager.player = player;
        hasPlayerBeenInitialized = true;
    }

    public static Player getPlayer()
    {
        return GameManager.player;
    }

}
