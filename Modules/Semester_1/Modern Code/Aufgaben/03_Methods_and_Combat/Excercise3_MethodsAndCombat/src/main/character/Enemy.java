package main.character;


import main.combat.ActionType;
import main.item.Armour;
import main.item.Shield;
import main.item.Weapon;

public class Enemy extends GameCharacter
{
    private ActionType nextAction;

    public Enemy(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        super(name, maxHealth, strength, dexterity, intelligence);
    }

    public Enemy(String name, double maxHealth, int strength, int dexterity, int intelligence, Weapon weapon, Armour armour, Shield shield)
    {
        this(name, maxHealth, strength, dexterity, intelligence);
        equipItem(weapon);
        equipItem(armour);
        equipItem(shield);
    }

    public void think(GameCharacter attacker)
    {
        // if self is healthy and player is healthy, attack 100%
        // if
    }
}
