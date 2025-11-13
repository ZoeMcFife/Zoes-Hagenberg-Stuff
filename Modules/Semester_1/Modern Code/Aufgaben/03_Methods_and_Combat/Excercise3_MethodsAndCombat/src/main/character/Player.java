package main.character;

public class Player extends GameCharacter
{
    public static double DEFAULT_PLAYER_MAX_HEALTH = 100.0;

    public Player(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        super(name, maxHealth, strength, dexterity, intelligence);
    }

    public Player(String name, int strength, int dexterity, int intelligence)
    {
        this(name, DEFAULT_PLAYER_MAX_HEALTH, strength, dexterity, intelligence);
    }
}
