package main.ui.components.inventory;

import main.ui.UIHelper;
import main.ui.UserInterface;

/**
 * UI screen for selecting an action to perform on inventory items.
 * Provides options to use, equip, or drop items.
 */
public class ItemActionSelectionUI extends UserInterface
{
    /**
     * Starts the item action selection interface.
     * Displays options and delegates to the appropriate item action UI.
     */
    @Override
    public void startUI()
    {
        int choice = -1;

        displayOptions();

        while(choice == -1)
        {
            choice = UIHelper.getIntInput(1, 3);
        }

        switch (choice)
        {
            case 1 -> new UseItemUI().startUI();
            case 2 -> new EquipItemUI().startUI();
            case 3 -> new DropItemUI().startUI();
        }
    }

    /**
     * Displays the available inventory action options.
     */
    private void displayOptions()
    {
        IO.println("1. Use Item");
        IO.println("2. Equip Item");
        IO.println("3. Drop Item");
    }
}
