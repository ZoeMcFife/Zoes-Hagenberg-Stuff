package main.ui.components.inventory;

import main.character.GameCharacter;
import main.item.HealingPotion;
import main.ui.UserInterface;
import main.ui.UIHelper;

/**
 * UI screen for selecting items from a character's inventory.
 * Provides static methods for item selection with various filters.
 */
public class SelectItemUI extends ItemUsageUI
{
    /**
     * This method is unsupported for this UI component.
     * Use static itemSelection methods instead.
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void startUI()
    {
        throw new UnsupportedOperationException("This UI component doesn't need to be started. Use itemSelection(Inventory) instead.");
    }

    /**
     * Displays inventory and prompts user to select an item.
     *
     * @param gameCharacter The character from which to select an item.
     *
     * @return The index of the selected item in the inventory. Zero-based index.
     */
    public static int itemSelection(GameCharacter gameCharacter)
    {
        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(gameCharacter);
        displayInventoryUI.startUI();

        int itemSelected = -1;

        while (itemSelected == -1)
        {
            itemSelected = UIHelper.getIntInput(1, gameCharacter.getInventory().getItemCount());
        }

        return itemSelected - 1; // Convert to zero-based index
    }

    /**
     * Displays inventory and prompts user to select a health item. Only allows health items to be selected.
     *
     * @param gameCharacter The character from which to select a health item.
     *
     * @return The index of the selected health item in the inventory. Zero-based index.
     */
    public static int healthItemSelection(GameCharacter gameCharacter)
    {
        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(gameCharacter);
        displayInventoryUI.startUI();

        int itemSelected = -1;

        while (itemSelected == -1)
        {
            itemSelected = UIHelper.getIntInput(1, gameCharacter.getInventory().getItemCount());

            if (!((gameCharacter.getInventory().getItemAt(itemSelected - 1) instanceof HealingPotion)))
            {
                IO.println("Please select a health item.");
                itemSelected = -1;
            }
        }

        return itemSelected - 1; // Convert to zero-based index
    }
}
