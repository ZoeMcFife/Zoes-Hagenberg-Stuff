package main.ui.components.character;

import main.character.GameCharacter;
import main.character.Player;
import main.ui.UIHelper;
import main.ui.UserInterface;

public class StatAllocationUI extends UserInterface
{

    private int availableSkillPoints;
    private Player player;

    public StatAllocationUI(int availableSkillPoints, Player player)
    {
        this.availableSkillPoints = availableSkillPoints;
        this.player = player;
    }

    /**
     * Starts the stat allocation user interface, allowing the player to distribute available skill points
     * among Strength, Dexterity, and Intelligence. The method prompts the user to select which stat to
     * increase, ensures no stat exceeds its maximum value, and updates the player's stats and remaining
     * points accordingly. The process continues until all points are allocated or all stats reach their maximum.
     */
    @Override
    public void startUI()
    {
        int remainingPoints = availableSkillPoints;

        while (remainingPoints > 0 && ((player.getStrength() + player.getDexterity() + player.getIntelligence()) < GameCharacter.MAX_STAT_VALUE * 3))
        {
            printStatAllocationMenu(player.getStrength(), player.getDexterity(), player.getIntelligence(), remainingPoints);
            int choice = UIHelper.getIntInput(1, 3);

            switch (choice)
            {
                case 1:
                    if (player.getStrength() >= GameCharacter.MAX_STAT_VALUE)
                    {
                        IO.println("Strength is already at maximum value.");
                        continue;
                    }

                    player.addStrength(1);
                    player.spendStatPoint();

                    remainingPoints--;

                    break;
                case 2:
                    if (player.getDexterity() >= GameCharacter.MAX_STAT_VALUE)
                    {
                        IO.println("Dexterity is already at maximum value.");
                        continue;
                    }

                    player.addDexterity(1);
                    player.spendStatPoint();

                    remainingPoints--;

                    break;
                case 3:
                    if (player.getIntelligence() >= GameCharacter.MAX_STAT_VALUE)
                    {
                        IO.println("Intelligence is already at maximum value.");
                        continue;
                    }

                    player.addIntelligence(1);
                    player.spendStatPoint();
                    remainingPoints--;

                    break;
            }

            UIHelper.clearScreen();

        }

    }

    /**
     * Displays the stat allocation menu showing current stats and remaining points.
     *
     * @param strength Current strength value
     * @param dexterity Current dexterity value
     * @param intelligence Current intelligence value
     * @param remainingPoints Number of unallocated skill points
     */
    private void printStatAllocationMenu(int strength, int dexterity, int intelligence, int remainingPoints)
    {
        IO.println("\nAllocate your skill points:");
        IO.println("1. Strength: " + strength);
        IO.println("2. Dexterity: " + dexterity);
        IO.println("3. Intelligence: " + intelligence);
        IO.println("Remaining Points: " + remainingPoints);
        IO.println("Choose a stat to increase: (1=STR, 2=DEX, 3=INT)");
    }
}
