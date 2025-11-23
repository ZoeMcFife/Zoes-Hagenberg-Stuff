package main.ui.components.battle;

import main.global.GameManager;
import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;
import main.ui.UIHelper;
import main.ui.UserInterface;

import java.util.List;

/**
 * UI screen for managing post-battle looting.
 * Allows players to select items from defeated enemies within a limited number of picks.
 */
public class LootUI extends UserInterface
{
    /** List of items available to loot */
    private final List<Item> loot;
    
    /** Number of items the player can still loot based on difficulty */
    private int lootableItemsLeft;
    
    /** Flag to exit looting early */
    private boolean stopLooting = false;

    /**
     * Creates a new loot UI with the specified items.
     * The number of lootable items is limited by game difficulty.
     * 
     * @param loot List of items dropped by defeated enemies
     */
    public LootUI(List<Item> loot)
    {
        this.loot = loot;
        this.lootableItemsLeft = GameManager.getItemLootCount();

        if (lootableItemsLeft > loot.size())
        {
            lootableItemsLeft = loot.size();
        }
    }

    /**
     * Starts the looting interface.
     * Continues until all lootable items are claimed or player exits.
     */
    @Override
    public void startUI()
    {
        while (lootableItemsLeft > 0 && !stopLooting)
        {
            displayLootHeader();

            lootItem();

            UIHelper.delayMedium();
            UIHelper.clearScreen();
        }
    }

    /**
     * Prompts the player to select and loot a single item.
     * Checks carry capacity before adding item to inventory.
     */
    private void lootItem()
    {
        displayLootItems();
        displayLootPrompt();

        int choice = UIHelper.getIntInput(0, loot.size()) - 1;

        if (choice == -1)
        {
            stopLooting = true;
            return;
        }

        Item selectedItem = loot.get(choice);

        if (GameManager.getPlayer().canCarry(selectedItem))
        {
            GameManager.getPlayer().getInventory().addItem(selectedItem);
            loot.remove(choice);
            lootableItemsLeft--;
            IO.println("You looted: " + selectedItem);
        }
        else
        {
            IO.println("You cannot carry that item. It's too heavy!");
        }
    }

    /**
     * Displays the list of lootable items with comparison to equipped items.
     * Shows weight and stat comparisons for weapons, armour, and shields.
     */
    private void displayLootItems()
    {
        IO.println("Lootable Items:");
        IO.println("Carry Capacity: " + Math.round(GameManager.getPlayer().getInventory().getWeight()) + "/" + GameManager.getPlayer().getCarryCapacity());

        IO.println(0 + ": Exit");

        for (int i = 0; i < loot.size(); i++)
        {
            IO.print((i + 1) + ": " + loot.get(i) + " | Weight: " + loot.get(i).getWeight());

            switch (loot.get(i)) {
                case Weapon weapon -> {
                    double diff = weapon.getDamage() - GameManager.getPlayer().getEquippedWeapon().getDamage();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + diff + " DMG";
                    } else if (diff < 0) {
                        compareText = "Compare: " + diff + " DMG";
                    } else {
                        compareText = "Compare: = DMG";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case Armour armour -> {
                    double diff = armour.getDefense() - GameManager.getPlayer().getEquippedArmour().getDefense();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + diff + " DEF";
                    } else if (diff < 0) {
                        compareText = "Compare: " + diff + " DEF";
                    } else {
                        compareText = "Compare: = DEF";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case Shield shield -> {
                    double diff = shield.getDefense() - GameManager.getPlayer().getEquippedShield().getDefense();

                    String compareText;

                    if (diff > 0) {
                        compareText = "Compare: +" + diff + " DEF";
                    } else if (diff < 0) {
                        compareText = "Compare: " + diff + " DEF";
                    } else {
                        compareText = "Compare: = DEF";
                    }

                    IO.print("    " + compareText + "\n");
                }
                case null, default -> IO.print("\n");
            }
        }
        
    }

    /**
     * Displays the prompt asking the player to select an item.
     */
    private void displayLootPrompt()
    {
        IO.println("Select an item to loot!");
    }

    /**
     * Displays the loot screen header with ASCII art and remaining loot count.
     */
    private void displayLootHeader()
    {
        IO.println(".____    ________   ___________________\n" +
                "|    |   \\_____  \\  \\_____  \\__    ___/\n" +
                "|    |    /   |   \\  /   |   \\|    |   \n" +
                "|    |___/    |    \\/    |    \\    |   \n" +
                "|_______ \\_______  /\\_______  /____|   \n" +
                "        \\/       \\/         \\/         ");

        IO.println("You can still loot " + lootableItemsLeft + " items. THEY ARE COMING...");
    }
}
