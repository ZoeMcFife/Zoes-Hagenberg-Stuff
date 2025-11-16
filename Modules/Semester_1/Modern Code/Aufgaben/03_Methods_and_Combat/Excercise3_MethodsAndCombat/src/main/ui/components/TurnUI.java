package main.ui.components;

import main.global.IO;
import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.ActionType;
import main.combat.Battle;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

import java.util.ArrayList;
import java.util.List;

public class TurnUI extends UserInterface
{
    private final Battle battle;
    private int selectedEnemy;
    private int actionChoice;

    private final int TURN_DELAY = 1;

    public TurnUI(Battle battle)
    {
        this.battle = battle;
    }

    @Override
    public void startUI()
    {
        while (GameManager.getPlayer().isAlive())
        {
            displayEnemies(battle);
            displayPlayer(GameManager.getPlayer());

            thinkingPass();

            defensePass();

            actionPass();

            UserInterfaceHelper.waitForEnterKey();
        }

    }

    private void actionPass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.isAlive())
            {
                if (character instanceof Enemy enemy)
                {
                    switch (enemy.nextAction)
                    {
                        case ATTACK -> enemy.attack(GameManager.getPlayer());
                        case HEAL -> enemy.useHealingItem();
                    }

                    UserInterfaceHelper.delay(TURN_DELAY);
                }
                else if (character instanceof Player player)
                {
                    switch (player.nextAction)
                    {
                        case ATTACK -> player.attack(battle.getEnemies().get(selectedEnemy - 1));
                        case HEAL -> IO.println("Healing item used!");
                    }
                    UserInterfaceHelper.delay(TURN_DELAY);
                }
            }
        }
    }

    private void defensePass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.nextAction == ActionType.DEFEND && character.isAlive())
            {
                character.defend();
                UserInterfaceHelper.delay(TURN_DELAY);
            }
        }
    }

    private void thinkingPass()
    {
        for (GameCharacter character : battle.getParticipantsOrderedByDexterity())
        {
            if (character.isAlive())
            {
                if (character instanceof Enemy enemy)
                {
                    enemy.think(GameManager.getPlayer());
                    UserInterfaceHelper.delay(TURN_DELAY);
                }
                else if (character instanceof Player)
                {
                    UserInterfaceHelper.printSubHeading("Your Turn!");

                    enemySelection();
                    actionSelection();

                    UserInterfaceHelper.delay(TURN_DELAY);
                }
            }
        }
    }

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

    private void displayBattleOptions()
    {
        UserInterfaceHelper.printSubHeading("Battle Options");
        IO.println("1. Attack");
        IO.println("2. Defend");
        IO.println("3. Use Item");
        IO.println("Select an action by entering the corresponding number.");
    }

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

    private void displayPlayer(Player player)
    {
        List<String> playerBox = player.getDisplayBox();

        for (String line : playerBox)
        {
            IO.println(line);
        }
    }

    private void displayEnemies(Battle battle)
    {
        List<List<String>> enemyBoxes = new ArrayList<>();

        for (Enemy enemy : battle.getEnemies())
        {
            enemyBoxes.add(enemy.getDisplayBox());
        }

        List<String> finalOutput = GameCharacter.combineEnemyBoxes(enemyBoxes);

        for (String line : finalOutput)
        {
            IO.println(line);
        }
    }

}

