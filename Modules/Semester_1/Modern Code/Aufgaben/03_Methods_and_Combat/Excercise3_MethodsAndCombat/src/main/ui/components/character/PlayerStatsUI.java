package main.ui.components.character;

import main.character.Player;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.inventory.DisplayInventoryUI;

public class PlayerStatsUI extends UserInterface
{
    Player player = GameManager.getPlayer();

    @Override
    public void startUI()
    {
        UIHelper.clearScreen();

        displayPlayerInformation();
        displayPlayerStats();
        displayPlayerEquipment();
        displayPlayerInventory();

        UIHelper.waitForEnterKey();
    }

    private void displayPlayerInformation()
    {
        UIHelper.printHeading("Information");
        IO.println("Name: \t\t" + player.getName());
    }

    private void displayPlayerStats()
    {
        UIHelper.printHeading("Stats");
        IO.println("Health: \t\t" + player.getHealth() + "/" + player.getMaxHealth());
        IO.println("Defense: \t\t" + player.getCurrentDefense());
        IO.println("Attack: \t\t" + player.getDamage());
        IO.println("Strength: \t\t" + player.getStrength());
        IO.println("Dexterity: \t\t" + player.getDexterity());
        IO.println("Intelligence: \t\t" + player.getIntelligence());
    }

    private void displayPlayerEquipment()
    {
        UIHelper.printHeading("Equipment");

        UIHelper.printSubHeading("Weapon");
        player.getEquippedWeapon().printItemStats();

        UIHelper.printSubHeading("Armor");
        player.getEquippedArmour().printItemStats();

        UIHelper.printSubHeading("Shield");
        player.getEquippedShield().printItemStats();
    }

    private void displayPlayerInventory()
    {
        UIHelper.printHeading("Inventory");

        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(player);
        displayInventoryUI.startUI();
    }


}
