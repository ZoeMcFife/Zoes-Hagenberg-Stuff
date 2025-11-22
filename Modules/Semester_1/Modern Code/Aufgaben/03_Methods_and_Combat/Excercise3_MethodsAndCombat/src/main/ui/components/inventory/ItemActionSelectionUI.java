package main.ui.components.inventory;

import main.ui.UIHelper;
import main.ui.UserInterface;

public class ItemActionSelectionUI extends UserInterface
{
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

    private void displayOptions()
    {
        IO.println("1. Use Item");
        IO.println("2. Equip Item");
        IO.println("3. Drop Item");
    }
}
