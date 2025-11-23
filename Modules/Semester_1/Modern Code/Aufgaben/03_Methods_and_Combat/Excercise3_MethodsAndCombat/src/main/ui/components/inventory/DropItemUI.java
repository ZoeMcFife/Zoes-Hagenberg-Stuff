package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

/**
 * UI screen for dropping items from the player's inventory.
 * Allows the player to discard unwanted items to free up carry weight.
 */
public class DropItemUI extends ItemUsageUI
{
    /**
     * Starts the item dropping interface.
     * Prompts the player to select an item to drop.
     */
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
