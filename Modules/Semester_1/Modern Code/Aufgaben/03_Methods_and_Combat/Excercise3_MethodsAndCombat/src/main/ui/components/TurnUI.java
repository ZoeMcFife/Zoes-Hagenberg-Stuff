package main.ui.components;

import main.character.Enemy;
import main.combat.Battle;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class TurnUI extends UserInterface
{
    private final Battle battle;
    private int selectedEnemy;
    private int actionChoice;

    public TurnUI(Battle battle)
    {
        this.battle = battle;
    }

    @Override
    public void startUI()
    {
        enemySelection();

        actionSelection();

    }

    public void enemySelection()
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

    public void actionSelection()
    {
        actionChoice = -1;

        while (actionChoice < 1)
        {
            displayBattleOptions();
            actionChoice = UserInterfaceHelper.getIntInput(1, 3);

            switch (actionChoice)
            {
                case 1:
                    GameManager.getPlayer().attack(battle.getEnemies().get(selectedEnemy - 1));
                    break;
                case 2:
                    GameManager.getPlayer().defend();
                    break;
                case 3:
                    IO.println("You chose to Use Item!");
                    break;
                default:
                    IO.println("Invalid selection. Please try again.");
                    actionChoice = -1;
                    break;
            }
        }
    }

    public void displayBattleOptions()
    {
        UserInterfaceHelper.printSubHeading("Battle Options");
        IO.println("1. Attack");
        IO.println("2. Defend");
        IO.println("3. Use Item");
        IO.println("Select an action by entering the corresponding number.");
    }

    public void displayEnemySelection()
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

