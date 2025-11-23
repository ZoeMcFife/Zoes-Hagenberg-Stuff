package main.ui.components.battle;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.ActionType;
import main.combat.Battle;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.inventory.ItemActionSelectionUI;
import main.ui.components.inventory.UseItemUI;


/**
 * UI screen for managing a single combat turn.
 * Handles character thinking, defense, actions, and turn flow.
 */
public class TurnUI extends UserInterface
{
    private final Battle battle;
    private int selectedEnemy;
    private int turnCount = 1;

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
            displayTurnHeader();

            UIHelper.displayEnemies(battle);
            UIHelper.displayPlayer(GameManager.getPlayer());

            thinkingPass();

            IO.println();

            defensePass();

            IO.println();

            actionPass();

            IO.println();

            stopDefendingPass();

            UIHelper.waitForEnterKey();
            UIHelper.clearScreen();
        }

    }

    private void displayTurnHeader()
    {
        UIHelper.printHeading("Turn " + turnCount);
        turnCount++;
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
                        case USE_ITEM -> enemy.useHealingItem();
                    }

                    UIHelper.delayMedium();
                }
                else if (character instanceof Player player)
                {
                    switch (player.nextAction)
                    {
                        case ATTACK -> player.attack(battle.getEnemies().get(selectedEnemy - 1));
                        case USE_ITEM ->
                        {
                            ItemActionSelectionUI itemActionSelectionUI = new ItemActionSelectionUI();
                            itemActionSelectionUI.startUI();
                        }
                        case USE_SPECIAL -> player.useSpecial(battle.getEnemies().get(selectedEnemy - 1));
                    }
                    UIHelper.delayMedium();
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
                if (character.isDefending())
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
                UIHelper.delayMedium();
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
                    UIHelper.delayMedium();
                }
                else if (character instanceof Player)
                {
                    UIHelper.printHeading("Your Turn!");

                    enemySelection();
                    actionSelection();

                    IO.println();

                    UIHelper.delayMedium();
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
            selectedEnemy = UIHelper.getIntInput(1, battle.getEnemies().size());

            if (selectedEnemy < 1 || selectedEnemy > battle.getEnemies().size())
            {
                IO.println("Invalid selection. Please try again.");
                selectedEnemy = -1;
            }
        }
    }

    /**
     * Prompts the player to select their action for this turn.
     * Options include Attack, Defend, Use Item, or Use Special.
     */
    private void actionSelection()
    {
        int actionChoice = -1;

        while (actionChoice < 1)
        {
            displayBattleOptions();
            actionChoice = UIHelper.getIntInput(1, 4);

            switch (actionChoice)
            {
                case 1:
                    GameManager.getPlayer().nextAction = ActionType.ATTACK;
                    IO.println("You chose to Attack!");
                    break;
                case 2:
                    if (GameManager.getPlayer().getCurrentPP() < GameManager.getPlayer().getEquippedWeapon().getPpCost())
                    {
                        IO.println("Not enough PP to use " + GameManager.getPlayer().getEquippedWeapon().getSpecialAttackName() + ". Please choose another action.");
                        actionChoice = -1;
                        break;
                    }

                    GameManager.getPlayer().nextAction = ActionType.USE_SPECIAL;
                    IO.println("You chose to use " + GameManager.getPlayer().getEquippedWeapon().getSpecialAttackName());
                    break;
                case 3:
                    GameManager.getPlayer().nextAction = ActionType.DEFEND;
                    IO.println("You chose to Defend!");
                    break;
                case 4:
                    GameManager.getPlayer().nextAction = ActionType.USE_ITEM;
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
        UIHelper.printSubHeading("Battle Options");
        IO.println("Current PP: " + GameManager.getPlayer().getCurrentPP() + "/" + GameManager.getPlayer().getMaxPP());
        
        String specialName = GameManager.getPlayer().getEquippedWeapon().getSpecialAttackName();
        if (specialName == null || specialName.isEmpty()) {
            specialName = "Special";
        }
        
        IO.println("1. Attack");
        IO.println("2. " + specialName + " (" + GameManager.getPlayer().getEquippedWeapon().getPpCost() + " PP)");
        IO.println("3. Defend (+" + GameManager.getPlayer().getCurrentPP() + " PP)" + "(+" + GameManager.getPlayer().getEquippedShield().getDefense() + " DEF)");
        IO.println("4. Use Item");
        IO.println("Select an action by entering the corresponding number.");
    }

    /**
     * Displays the list of enemies that can be targeted.
     */
    private void displayEnemySelection()
    {
        UIHelper.printSubHeading("Select an Enemy to Target");

        int i = 1;

        for (Enemy enemy : battle.getEnemies())
        {
            IO.println(i + ". " +enemy.getName());
            i++;
        }

        IO.println("Select an enemy by entering the corresponding number.");
    }

}

