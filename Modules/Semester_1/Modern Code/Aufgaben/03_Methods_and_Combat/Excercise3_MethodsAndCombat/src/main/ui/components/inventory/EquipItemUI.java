package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

/**
 * UI screen for equipping items from the player's inventory.
 * Allows the player to select and equip weapons, armour, or shields.
 */
public class EquipItemUI extends ItemUsageUI
{
    /**
     * Starts the item equipping interface.
     * Prompts the player to select an item to equip.
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

        GameManager.getPlayer().equipItem(GameManager.getPlayer().getInventory().getItemAt(selectedItem));
    }

}
