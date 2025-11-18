package main.ui.components.inventory;

import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

public class UseItemUI extends UserInterface
{
    @Override
    public void startUI()
    {
        int selectedItem = SelectItemUI.itemSelection(GameManager.getPlayer());

        GameManager.getPlayer().useItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }


}
