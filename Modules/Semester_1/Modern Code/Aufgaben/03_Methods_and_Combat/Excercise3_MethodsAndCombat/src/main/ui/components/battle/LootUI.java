package main.ui.components.battle;

import main.global.GameManager;
import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;
import main.ui.UIHelper;
import main.ui.UserInterface;

import java.util.List;

public class LootUI extends UserInterface
{
    private final List<Item> loot;
    private int lootableItemsLeft;
    private boolean stopLooting = false;

    public LootUI(List<Item> loot)
    {
        this.loot = loot;
        this.lootableItemsLeft = GameManager.getItemLootCount();

        if (lootableItemsLeft > loot.size())
        {
            lootableItemsLeft = loot.size();
        }
    }

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

    private void displayLootItems()
    {
        IO.println("Lootable Items:");

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

    private void displayLootPrompt()
    {
        IO.println("Select an item to loot!");
    }

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
