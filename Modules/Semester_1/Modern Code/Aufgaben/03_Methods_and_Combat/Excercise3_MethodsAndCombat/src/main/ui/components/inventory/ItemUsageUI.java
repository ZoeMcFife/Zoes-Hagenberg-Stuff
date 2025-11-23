package main.ui.components.inventory;

import main.global.GameManager;
import main.ui.UserInterface;

/**
 * Abstract base class for UI screens that perform actions on inventory items.
 * Provides common utility methods for checking inventory state.
 */
public abstract class ItemUsageUI extends UserInterface
{
    /**
     * Base implementation does nothing.
     * Subclasses must override to provide specific functionality.
     */
    @Override
    public void startUI()
    {

    }

    /**
     * Checks if the player's inventory is empty.
     * @return true if the inventory is empty, false otherwise.
     */
    protected boolean isInventoryEmpty()
    {
        return GameManager.getPlayer().getInventory().getItems().isEmpty();
    }

    /**
     * Checks if the player's inventory contains any health items.
     * @return true if the inventory has health items, false otherwise.
     */
    protected boolean doesInventoryHaveHealthItems()
    {
        return GameManager.getPlayer().getInventory().containsHealingItem();
    }

    /**
     * Displays a message indicating that the inventory is empty.
     */
    protected void displayEmptyInventoryMessage()
    {
        IO.println("Your inventory is empty.");
    }


}
