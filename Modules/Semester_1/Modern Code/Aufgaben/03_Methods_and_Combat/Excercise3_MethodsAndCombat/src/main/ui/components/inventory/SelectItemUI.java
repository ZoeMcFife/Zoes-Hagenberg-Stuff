package main.ui.components.inventory;

import main.character.GameCharacter;
import main.ui.UserInterface;
import main.ui.UIHelper;

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
}
