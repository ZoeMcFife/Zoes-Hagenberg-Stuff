package main.character;


import main.combat.ActionType;
import main.item.Armour;
import main.item.Shield;
import main.item.Weapon;

public class Enemy extends GameCharacter
{
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

    public ActionType think(GameCharacter attacker)
    {
        CharacterStatus selfStatus = getStatus();
        CharacterStatus attackerStatus = attacker.getStatus();

        if (attackerStatus == CharacterStatus.CRITICALLY_HURT)
        {
            return randomWeightedAction(80, 20, 0);
        }
        else if (attackerStatus == CharacterStatus.SEVERELY_HURT)
        {
            return randomWeightedAction(90, 5, 5);
        }

        if (selfStatus == CharacterStatus.CRITICALLY_HURT)
        {
            return randomWeightedAction(10, 20, 70);

        }
        else if (selfStatus == CharacterStatus.SEVERELY_HURT)
        {
            return randomWeightedAction(50, 20, 30);
        }
        else if (selfStatus == CharacterStatus.HURT)
        {
            return randomWeightedAction(80, 10, 10);
        }
        else
        {
            return ActionType.ATTACK;
        }
    }

    private ActionType randomWeightedAction(int attackWeight, int defendWeight, int healWeight)
    {
        if (!canHeal())
        {
            attackWeight = healWeight / 2;
            defendWeight = defendWeight / 2;
            healWeight = 0;
        }

        int totalWeight = attackWeight + defendWeight + healWeight;
        int randomValue = (int) (Math.random() * totalWeight);

        if (randomValue < attackWeight)
        {
            return ActionType.ATTACK;
        }
        else if (randomValue < attackWeight + defendWeight)
        {
            return ActionType.DEFEND;
        }
        else
        {
            return ActionType.HEAL;
        }
    }
}
