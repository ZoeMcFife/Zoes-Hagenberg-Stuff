package main.ui.components.inventory;

import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

public class DisplayInventoryUI extends UserInterface
{
    private final Inventory inventory;

    public DisplayInventoryUI(Inventory inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public void startUI()
    {
        displayInventory(inventory);
    }

    private void displayInventory(Inventory inventory)
    {
        IO.println("Inventory:");

        for (int i = 0; i < inventory.getItems().size(); i++)
        {
            IO.println((i + 1) + ". " + inventory.getItems().get(i).getName());
        }
    }
}
