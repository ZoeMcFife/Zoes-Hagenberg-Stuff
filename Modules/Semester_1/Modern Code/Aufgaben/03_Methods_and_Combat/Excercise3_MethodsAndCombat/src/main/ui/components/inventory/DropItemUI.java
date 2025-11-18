package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

public class DropItemUI extends UserInterface
{

    @Override
    public void startUI()
    {
        int selectedItem = SelectItemUI.itemSelection(GameManager.getPlayer().getInventory());

        GameManager.getPlayer().dropItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }
}
