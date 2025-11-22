package main.ui.components.inventory;

import main.character.GameCharacter;
import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;

public class DisplayInventoryUI extends UserInterface
{
    private final GameCharacter gameCharacter;

    public DisplayInventoryUI(GameCharacter gameCharacter)
    {
        this.gameCharacter = gameCharacter;
    }

    @Override
    public void startUI()
    {
        displayInventory(gameCharacter);
    }

    private void displayInventory(GameCharacter gameCharacter)
    {
        IO.println("Inventory:");
        IO.println("Weight: " + Math.round(gameCharacter.getInventory().getWeight()) + "/" + gameCharacter.getCarryCapacity());

        for (int i = 0; i < gameCharacter.getInventory().getItems().size(); i++)
        {
            IO.println((i + 1) + ". " + gameCharacter.getInventory().getItems().get(i).getName());
        }
    }
}
