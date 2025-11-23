package main.ui.components.inventory;

import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

/**
 * UI screen for using consumable items from the player's inventory.
 * Currently focuses on healing potions and other usable items.
 */
public class UseItemUI extends ItemUsageUI
{
    /**
     * Starts the item usage interface.
     * Prompts the player to select a healing item to use.
     */
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
