package main.ui.components.battle;

import main.character.DangerLevel;
import main.combat.Battle;
import main.factory.generators.BattleGenerator;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

/**
 * UI screen for managing a single battle encounter.
 * Generates enemies based on danger level and manages turn-based combat.
 */
public class BattleUI extends UserInterface
{
    private DangerLevel dangerLevel;
    private int battleNumber;

    /**
     * Creates a new battle UI screen.
     * 
     * @param dangerLevel The difficulty level of enemies to generate
     * @param battleNumber The sequential number of this battle in the game
     */
    public BattleUI(DangerLevel dangerLevel, int battleNumber)
    {
        this.dangerLevel = dangerLevel;
        this.battleNumber = battleNumber;
    }

    /**
     * Starts the battle sequence.
     * Generates enemies and runs turns until the player dies.
     */
    @Override
    public void startUI()
    {
        Battle battle = BattleGenerator.generateBattle(dangerLevel);

        displayBattleStartMessage();

        while (GameManager.getPlayer().isAlive() && !battle.isBattleOver())
        {
            TurnUI turnUI = new TurnUI(battle);
            turnUI.startUI();

            UserInterfaceHelper.delayLong();

            UserInterfaceHelper.clearScreen();

            displayBattleEndMessage();

            UserInterfaceHelper.waitForEnterKey();
        }

    }

    /**
     * Displays the battle start message with ASCII art and battle information.
     */
    public void displayBattleStartMessage()
    {
        IO.println("  ____        _   _   _         _____ _             _   \n" +
                " |  _ \\      | | | | | |       / ____| |           | |  \n" +
                " | |_) | __ _| |_| |_| | ___  | (___ | |_ __ _ _ __| |_ \n" +
                " |  _ < / _` | __| __| |/ _ \\  \\___ \\| __/ _` | '__| __|\n" +
                " | |_) | (_| | |_| |_| |  __/  ____) | || (_| | |  | |_ \n" +
                " |____/ \\__,_|\\__|\\__|_|\\___| |_____/ \\__\\__,_|_|   \\__|\n" +
                "                                                        \n" +
                "                                                        ");
        IO.println("Battle " + battleNumber + " begins!");
        IO.println("Danger Level: " + dangerLevel);
    }

    public void displayBattleEndMessage()
    {
        IO.println("Battle " + battleNumber + " has ended!");
    }
}
