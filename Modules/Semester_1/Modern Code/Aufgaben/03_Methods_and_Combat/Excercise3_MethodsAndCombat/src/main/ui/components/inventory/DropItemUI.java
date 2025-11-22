package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

public class DropItemUI extends ItemUsageUI
{

    @Override
    public void startUI()
    {
        if (isInventoryEmpty())
        {
            displayEmptyInventoryMessage();
            return;
        }

        int selectedItem = SelectItemUI.itemSelection(GameManager.getPlayer());

        GameManager.getPlayer().dropItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }
}
