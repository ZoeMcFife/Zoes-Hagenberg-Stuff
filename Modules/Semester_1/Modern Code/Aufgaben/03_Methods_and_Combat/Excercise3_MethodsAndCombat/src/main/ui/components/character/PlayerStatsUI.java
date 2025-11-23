package main.ui.components.character;

import main.character.Player;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.inventory.DisplayInventoryUI;

/**
 * UI screen for displaying comprehensive player character statistics.
 * Shows information, stats, equipment, and inventory details.
 */
public class PlayerStatsUI extends UserInterface
{
    /** The player character whose stats are being displayed */
    Player player = GameManager.getPlayer();

    /**
     * Starts the player stats display interface.
     * Shows all player information and waits for confirmation.
     */
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

    /**
     * Displays basic player information including name, level, and experience.
     */
    private void displayPlayerInformation()
    {
        UIHelper.printHeading("Information");
        IO.println("Name: \t\t" + player.getName());
        IO.println("Level: \t\t" + player.getLevel());
        IO.println("Experience: \t" + player.getExperience() + "/" + player.getExperienceNeededForNextLevel());
    }

    /**
     * Displays player combat and attribute statistics.
     */
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

    /**
     * Displays player's currently equipped items and their stats.
     */
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

    /**
     * Displays player's inventory contents.
     */
    private void displayPlayerInventory()
    {
        UIHelper.printHeading("Inventory");

        DisplayInventoryUI displayInventoryUI = new DisplayInventoryUI(player);
        displayInventoryUI.startUI();
    }


}
