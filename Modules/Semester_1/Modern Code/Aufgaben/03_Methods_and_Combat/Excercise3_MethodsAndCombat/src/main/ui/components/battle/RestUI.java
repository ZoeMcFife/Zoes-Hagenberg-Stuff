package main.ui.components.battle;

import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.character.PlayerStatsUI;
import main.ui.components.inventory.DropItemUI;
import main.ui.components.inventory.EquipItemUI;
import main.ui.components.inventory.UseItemUI;

public class RestUI extends UserInterface
{
    private boolean isResting = true;

    @Override
    public void startUI()
    {
        while (isResting)
        {
            displayRestTitle();
            displayPlayer();
            displayOptions();
            selectOption();
            UIHelper.clearScreen();
        }
    }

    private void displayPlayer()
    {
        UIHelper.displayPlayer(GameManager.getPlayer());
    }

    private void displayCurrentPlayerStats()
    {
        PlayerStatsUI playerStatsUI = new PlayerStatsUI();
        playerStatsUI.startUI();
    }

    private void selectOption()
    {
        int choice = UIHelper.getIntInput(1, 6);

        switch (choice)
        {
            case 1:
                displayCurrentPlayerStats();
                break;
            case 2:
                EquipItemUI equipItemUI = new EquipItemUI();
                equipItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 3:
                DropItemUI dropItemUI = new DropItemUI();
                dropItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 4:
                UseItemUI useItemUI = new UseItemUI();
                useItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 5:
                IO.println("Continuing may lead to unforeseen consequences...");
                UIHelper.delayLong();
                isResting = false;
                break;
            case 6:
                IO.println("You have chosen to give up. Game Over.");
                UIHelper.delayMedium();
                GameManager.getPlayer().suicide();
                break;
            default:
                IO.println("Invalid choice. Please try again.");
                UIHelper.delayMedium();
                break;
        }
    }

    /** Displays the options available to the player after surviving a battle. */
    private void displayOptions()
    {
        UIHelper.printSubHeading("You have survived the battle!");
        IO.println("1. View Character");
        IO.println("2. Equip Item");
        IO.println("3. Drop Item");
        IO.println("4. Use Items");
        IO.println("5. Continue Journey");
        IO.println("6. Give up");
        IO.println("Select an action by entering the corresponding number.");
    }

    /** Displays the "REST" title in ASCII art. */
    private void displayRestTitle()
    {
        IO.println("  _____  ______  _____ _______ \n" +
                " |  __ \\|  ____|/ ____|__   __|\n" +
                " | |__) | |__  | (___    | |   \n" +
                " |  _  /|  __|  \\___ \\   | |   \n" +
                " | | \\ \\| |____ ____) |  | |   \n" +
                " |_|  \\_\\______|_____/   |_|   \n" +
                "                               ");
    }
}
