package main.character;


import main.combat.ActionType;
import main.item.Armour;
import main.item.Shield;
import main.item.Weapon;

/**
 * Represents an enemy character in the game.
 * Enemies have AI behavior to determine their actions in combat.
 */
public class Enemy extends GameCharacter
{
    /**
     * Creates a new enemy with specified stats.
     * 
     * @param name The enemy's name
     * @param maxHealth The maximum health of the enemy
     * @param strength The enemy's strength stat (affects physical damage)
     * @param dexterity The enemy's dexterity stat (affects turn order)
     * @param intelligence The enemy's intelligence stat (affects magic damage)
     */
    public Enemy(String name, double maxHealth, int strength, int dexterity, int intelligence)
    {
        super(name, maxHealth, strength, dexterity, intelligence);
    }

    /**
     * Creates a new enemy with specified stats and equipment.
     * 
     * @param name The enemy's name
     * @param maxHealth The maximum health of the enemy
     * @param strength The enemy's strength stat (affects physical damage)
     * @param dexterity The enemy's dexterity stat (affects turn order)
     * @param intelligence The enemy's intelligence stat (affects magic damage)
     * @param weapon The weapon to equip
     * @param armour The armour to equip
     * @param shield The shield to equip
     */
    public Enemy(String name, double maxHealth, int strength, int dexterity, int intelligence, Weapon weapon, Armour armour, Shield shield)
    {
        this(name, maxHealth, strength, dexterity, intelligence);
        equipItem(weapon, false, false);
        equipItem(armour, false, false);
        equipItem(shield, false, false);
    }

    /**
     * Executes the specified action against the target.
     * 
     * @param action The action to perform (ATTACK, DEFEND, or HEAL)
     * @param attacker The target of the action
     */
    public void executeAction(ActionType action, GameCharacter attacker)
    {
        switch (action)
        {
            case ATTACK -> attack(attacker);
            case DEFEND -> defend();
            case HEAL -> useHealingItem();
        }
    }

    /**
     * Uses a healing item from the enemy's inventory.
     * Outputs a message indicating the enemy is healing.
     */
    public void useHealingItem()
    {
        IO.println(getName() + " uses a healing item!");
    }

    /**
     * Makes the enemy think about their next action.
     * Determines the best action based on the current battle state.
     * 
     * @param attacker The character the enemy is fighting against
     */
    public void think(GameCharacter attacker)
    {
        IO.println(getName() + " is thinking...");
        nextAction = calculateNextAction(attacker);
    }

    /**
     * Calculates the next action for the enemy based on battle conditions.
     * Uses AI logic that considers both the enemy's and attacker's health status.
     * 
     * @param attacker The character the enemy is fighting against
     * @return The calculated action type (ATTACK, DEFEND, or HEAL)
     */
    public ActionType calculateNextAction(GameCharacter attacker)
    {
        CharacterStatus selfStatus = getStatus();
        CharacterStatus attackerStatus = attacker.getStatus();

        if (randomSuicideTrigger())
        {
            return ActionType.SUICIDE;
        }

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
            return randomWeightedAction(5, 35, 65);

        }
        else if (selfStatus == CharacterStatus.SEVERELY_HURT)
        {
            return randomWeightedAction(40, 40, 20);
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

    /**
     * Randomly selects an action based on weighted probabilities.
     * If healing is not available, redistributes heal weight to attack and defend.
     * 
     * @param attackWeight The relative probability of attacking
     * @param defendWeight The relative probability of defending
     * @param healWeight The relative probability of healing
     * @return The randomly selected action based on weights
     */
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

    /**
     * randomly determines if the enemy will commit suicide.
     * why did i bother implementing this?
     *
     * @return true if the enemy will suicide, false otherwise
     */
    private boolean randomSuicideTrigger()
    {
        return Math.random() < 0.005;
    }
}
