package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

public class EquipItemUI extends ItemUsageUI
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

        GameManager.getPlayer().equipItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }

}
