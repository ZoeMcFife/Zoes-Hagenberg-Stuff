package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

public class EquipItemUI extends UserInterface
{

    @Override
    public void startUI()
    {
        int selectedItem = SelectItemUI.itemSelection(GameManager.getPlayer());

        GameManager.getPlayer().equipItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }

}
