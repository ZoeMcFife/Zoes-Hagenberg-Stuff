package main.ui.components.battle;

import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;
import main.ui.components.inventory.DropItemUI;
import main.ui.components.inventory.EquipItemUI;
import main.ui.components.inventory.UseItemUI;

public class RestUI extends UserInterface
{
    @Override
    public void startUI()
    {
        displayRestTitle();
        displayOptions();
        selectOption();

        //UseItemUI useItemUI = new UseItemUI(GameManager.getPlayer().getInventory());
        //useItemUI.startUI();
    }

    private void selectOption()
    {
        int choice = UserInterfaceHelper.getIntInput(1, 5);

        switch (choice)
        {
            case 1:
                EquipItemUI equipItemUI = new EquipItemUI();
                equipItemUI.startUI();
                break;
            case 2:
                DropItemUI dropItemUI = new DropItemUI();
                dropItemUI.startUI();
                break;
            case 3:
                UseItemUI useItemUI = new UseItemUI(GameManager.getPlayer().getInventory());
                useItemUI.startUI();
                break;
            case 4:
                IO.println("Continuing may lead to unforeseen consequences...");
                break;
            case 5:
                IO.println("You have chosen to give up. Game Over.");
                GameManager.getPlayer().suicide();
                break;
            default:
                IO.println("Invalid choice. Please try again.");
                selectOption();
                break;
        }
    }

    /** Displays the options available to the player after surviving a battle. */
    private void displayOptions()
    {
        UserInterfaceHelper.printSubHeading("You have survived the battle!");
        IO.println("1. Equip Item");
        IO.println("2. Drop Item");
        IO.println("3. Use Items");
        IO.println("4. Continue Journey");
        IO.println("5. Give up");
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
