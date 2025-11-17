package main.ui.components.inventory;

import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

public class UseItemUI extends UserInterface
{
    private final Inventory inventory;

    public UseItemUI(Inventory inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public void startUI()
    {
        int selectedItem = -1;

        selectedItem = SelectItemUI.itemSelection(inventory);

        GameManager.getPlayer().useItem(inventory.getItemAt(selectedItem));
    }


}
