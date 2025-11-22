package main.ui.components.inventory;

import main.character.GameCharacter;
import main.global.GameManager;
import main.inventory.Inventory;
import main.item.Armour;
import main.item.Shield;
import main.item.Weapon;
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
        IO.println("Weight: " + String.format("%.1f", gameCharacter.getInventory().getWeight()) + "/" + String.format("%.1f", gameCharacter.getCarryCapacity()));

        for (int i = 0; i < gameCharacter.getInventory().getItemCount(); i++)
        {
            IO.print((i + 1) + ": " + gameCharacter.getInventory().getItemAt(i) + " | Weight: " + String.format("%.1f", gameCharacter.getInventory().getItemAt(i).getWeight()));

            switch (gameCharacter.getInventory().getItemAt(i)) {
                case Weapon weapon -> {
                    double diff = weapon.getDamage() - GameManager.getPlayer().getEquippedWeapon().getDamage();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + String.format("%.1f", diff) + " DMG";
                    } else if (diff < 0) {
                        compareText = "Compare: " + String.format("%.1f", diff) + " DMG";
                    } else {
                        compareText = "Compare: = DMG";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case Armour armour -> {
                    double diff = armour.getDefense() - GameManager.getPlayer().getEquippedArmour().getDefense();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + String.format("%.1f", diff) + " DEF";
                    } else if (diff < 0) {
                        compareText = "Compare: " + String.format("%.1f", diff) + " DEF";
                    } else {
                        compareText = "Compare: = DEF";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case Shield shield -> {
                    double diff = shield.getDefense() - GameManager.getPlayer().getEquippedShield().getDefense();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + String.format("%.1f", diff) + " DEF";
                    } else if (diff < 0) {
                        compareText = "Compare: " + String.format("%.1f", diff) + " DEF";
                    } else {
                        compareText = "Compare: = DEF";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case null, default -> IO.print("\n");
            }
        }
    }
}
