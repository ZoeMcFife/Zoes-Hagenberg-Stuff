package main.ui.components.battle;

import main.character.GameCharacter;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.character.PlayerStatsUI;
import main.ui.components.character.UseAvailableStatPointsUI;
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
        int choice = UIHelper.getIntInput(1, 7);

        switch (choice)
        {
            case 1:
                displayCurrentPlayerStats();
                break;
            case 2:
                UseAvailableStatPointsUI useAvailableStatPointsUI = new UseAvailableStatPointsUI();
                useAvailableStatPointsUI.startUI();
                UIHelper.delayLong();
                break;
            case 3:
                EquipItemUI equipItemUI = new EquipItemUI();
                equipItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 4:
                DropItemUI dropItemUI = new DropItemUI();
                dropItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 5:
                UseItemUI useItemUI = new UseItemUI();
                useItemUI.startUI();
                UIHelper.delayLong();
                break;
            case 6:
                IO.println("Continuing may lead to unforeseen consequences...");
                UIHelper.delayLong();
                isResting = false;
                break;
            case 7:
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
        IO.println("2. Use Stat Points (" + GameManager.getPlayer().getAvailableStatPoints() + " available)");
        IO.println("3. Equip Item");
        IO.println("4. Drop Item");
        IO.println("5. Use Items");
        IO.println("6. Continue Journey");
        IO.println("7. Give up");
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
