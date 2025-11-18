package main.ui.components.battle;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.ActionType;
import main.combat.Battle;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;


/**
 * UI screen for managing a single combat turn.
 * Handles character thinking, defense, actions, and turn flow.
 */
public class TurnUI extends UserInterface
{
    private final Battle battle;
    private int selectedEnemy;
    private int actionChoice;

    /**
     * Creates a new turn UI for the specified battle.
     * 
     * @param battle The battle context for this turn
     */
    public TurnUI(Battle battle)
    {
        this.battle = battle;
    }

    /**
     * Executes a single combat turn.
     * Displays combatants, processes thinking, defense, actions, and cleanup.
     */
    @Override
    public void startUI()
    {
        while (GameManager.getPlayer().isAlive() && !battle.isBattleOver())
        {
            UserInterfaceHelper.displayEnemies(battle);
            UserInterfaceHelper.displayPlayer(GameManager.getPlayer());

            thinkingPass();

            defensePass();

            actionPass();

            stopDefendingPass();

            UserInterfaceHelper.waitForEnterKey();
        }

    }

    /**
     * Executes all character actions in turn order.
     * Characters attack or use healing items based on their selected action.
     */
    private void actionPass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.isAlive())
            {
                if (character.nextAction == ActionType.SUICIDE)
                {
                    character.suicide();
                }
                else if (character instanceof Enemy enemy)
                {
                    switch (enemy.nextAction)
                    {
                        case ATTACK -> enemy.attack(GameManager.getPlayer());
                        case HEAL -> enemy.useHealingItem();
                    }

                    UserInterfaceHelper.delayMedium();
                }
                else if (character instanceof Player player)
                {
                    switch (player.nextAction)
                    {
                        case ATTACK -> player.attack(battle.getEnemies().get(selectedEnemy - 1));
                        case HEAL -> IO.println("Healing item used!");
                    }
                    UserInterfaceHelper.delayMedium();
                }
            }
        }
    }

    /**
     * Removes defensive stance from all defending characters at the end of turn.
     */
    private void stopDefendingPass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.isAlive())
            {
                if (character.isDefending)
                {
                    character.stopDefending();
                }
            }
        }
    }


    /**
     * Applies defensive stance to characters who chose to defend.
     */
    private void defensePass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.nextAction == ActionType.DEFEND && character.isAlive())
            {
                character.defend();
                UserInterfaceHelper.delayMedium();
            }
        }
    }

    /**
     * Thinking phase where each character decides their action.
     * Enemies use AI, player chooses through menu.
     */
    private void thinkingPass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.isAlive())
            {
                if (character instanceof Enemy enemy)
                {
                    enemy.think(GameManager.getPlayer());
                    UserInterfaceHelper.delayMedium();
                }
                else if (character instanceof Player)
                {
                    UserInterfaceHelper.printHeading("Your Turn!");

                    enemySelection();
                    actionSelection();

                    IO.println();

                    UserInterfaceHelper.delayMedium();
                }
            }
        }
    }

    /**
     * Prompts the player to select a target enemy.
     * Continues until a valid enemy is selected.
     */
    private void enemySelection()
    {
        selectedEnemy = -1;

        while (selectedEnemy < 1)
        {
            displayEnemySelection();
            selectedEnemy = UserInterfaceHelper.getIntInput(1, battle.getEnemies().size());

            if (selectedEnemy < 1 || selectedEnemy > battle.getEnemies().size())
            {
                IO.println("Invalid selection. Please try again.");
                selectedEnemy = -1;
            }
        }
    }

    /**
     * Prompts the player to select their action for this turn.
     * Options include Attack, Defend, or Use Item.
     */
    private void actionSelection()
    {
        actionChoice = -1;

        while (actionChoice < 1)
        {
            displayBattleOptions();
            actionChoice = UserInterfaceHelper.getIntInput(1, 3);

            switch (actionChoice)
            {
                case 1:
                    GameManager.getPlayer().nextAction = ActionType.ATTACK;
                    //GameManager.getPlayer().attack(battle.getEnemies().get(selectedEnemy - 1));
                    break;
                case 2:
                    GameManager.getPlayer().nextAction = ActionType.DEFEND;
                    //GameManager.getPlayer().defend();
                    break;
                case 3:
                    GameManager.getPlayer().nextAction = ActionType.HEAL;
                    IO.println("You chose to Use Item!");
                    break;
                default:
                    IO.println("Invalid selection. Please try again.");
                    actionChoice = -1;
                    break;
            }
        }
    }

    /**
     * Displays the available battle action options.
     */
    private void displayBattleOptions()
    {
        UserInterfaceHelper.printSubHeading("Battle Options");
        IO.println("1. Attack");
        IO.println("2. Defend");
        IO.println("3. Use Item");
        IO.println("Select an action by entering the corresponding number.");
    }

    /**
     * Displays the list of enemies that can be targeted.
     */
    private void displayEnemySelection()
    {
        UserInterfaceHelper.printSubHeading("Select an Enemy to Target");

        int i = 1;

        for (Enemy enemy : battle.getEnemies())
        {
            IO.println(i + ". " +enemy.getName());
            i++;
        }

        IO.println("Select an enemy by entering the corresponding number.");
    }

}

