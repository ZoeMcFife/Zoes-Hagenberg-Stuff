package main.ui.components.battle;

import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class RestUI extends UserInterface
{
    @Override
    public void startUI()
    {

    }

    /** Displays the options available to the player after surviving a battle. */
    private void displayOptions()
    {
        UserInterfaceHelper.printSubHeading("You have survived the battle!");
        IO.println("1. Equip Item");
        IO.println("2. Drop Item");
        IO.println("3. Use Items");
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
