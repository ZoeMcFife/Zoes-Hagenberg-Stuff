package main.ui.components.character;

import main.character.Player;
import main.global.GameManager;
import main.inventory.Inventory;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;
import main.ui.components.inventory.DisplayInventoryUI;

public class PlayerStatsUI extends UserInterface
{
    Player player = GameManager.getPlayer();

    @Override
    public void startUI()
    {
        UserInterfaceHelper.clearScreen();

        displayPlayerInformation();
        displayPlayerStats();
        displayPlayerEquipment();
        displayPlayerInventory();

        UserInterfaceHelper.waitForEnterKey();
    }

    private void displayPlayerInformation()
    {
        UserInterfaceHelper.printHeading("Information");
        IO.println("Name: \t\t" + player.getName());
    }

    private void displayPlayerStats()
    {
        UserInterfaceHelper.printHeading("Stats");
        IO.println("Health: \t\t" + player.getHealth() + "/" + player.getMaxHealth());
        IO.println("Defense: \t\t" + player.getCurrentDefense());
        IO.println("Attack: \t\t" + player.getDamage());
        IO.println("Strength: \t\t" + player.getStrength());
        IO.println("Dexterity: \t\t" + player.getDexterity());
        IO.println("Intelligence: \t\t" + player.getIntelligence());
    }

    private void displayPlayerEquipment()
    {
        UserInterfaceHelper.printHeading("Equipment");

        UserInterfaceHelper.printSubHeading("Weapon");
        player.getEquippedWeapon().printItemStats();

        UserInterfaceHelper.printSubHeading("Armor");
        player.getEquippedArmour().printItemStats();

        UserInterfaceHelper.printSubHeading("Shield");
        player.getEquippedShield().printItemStats();
    }

    private void displayPlayerInventory()
    {
        UserInterfaceHelper.printHeading("Inventory");

        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(player);
        displayInventoryUI.startUI();
    }


}
