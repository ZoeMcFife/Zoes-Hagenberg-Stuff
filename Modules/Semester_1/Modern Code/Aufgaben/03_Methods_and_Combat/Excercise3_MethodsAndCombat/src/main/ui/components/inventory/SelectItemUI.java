package main.ui.components.inventory;

import main.inventory.Inventory;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class SelectItemUI extends UserInterface
{
    @Override
    public void startUI()
    {
        throw new UnsupportedOperationException("This UI component doesn't need to be started. Use itemSelection(Inventory) instead.");
    }

    /**
     * Displays inventory and prompts user to select an item.
     *
     * @param inventory The inventory from which to select an item.
     *
     * @return The index of the selected item in the inventory. Zero-based index.
     */
    public static int itemSelection(Inventory inventory)
    {
        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(inventory);
        displayInventoryUI.startUI();

        int itemSelected = -1;

        while (itemSelected == -1)
        {
            itemSelected = UserInterfaceHelper.getIntInput(1, inventory.getItemCount());
        }

        return itemSelected - 1; // Convert to zero-based index
    }
}
