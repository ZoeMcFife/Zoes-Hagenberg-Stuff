package main.ui.components.inventory;

import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

public class UseItemUI extends ItemUsageUI
{
    @Override
    public void startUI()
    {
        if (!doesInventoryHaveHealthItems())
        {
            displayEmptyInventoryMessage();
            return;
        }

        int selectedItem = SelectItemUI.healthItemSelection(GameManager.getPlayer());

        GameManager.getPlayer().useItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }


}
