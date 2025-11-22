package main.ui.components.battle;

import main.character.DangerLevel;
import main.character.GameCharacter;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;

/**
 * Main game loop that manages sequential battles.
 * Tracks battle count and progressively increases difficulty based on game settings.
 */
public class GameLoop extends UserInterface
{
    private int battleCount = 1;
    private int currentTurnCount = 0;

    private DangerLevel currentDangerLevel = DangerLevel.HARMLESS;

    private final int difficultyIncreaseInterval;

    /**
     * Creates a new game loop.
     * Initializes difficulty increase interval based on the selected game difficulty.
     */
    public GameLoop()
    {
        switch (GameManager.difficulty)
        {
            case EASY -> difficultyIncreaseInterval = GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_EASY;
            case MEDIUM -> difficultyIncreaseInterval = GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_MEDIUM;
            case HARD -> difficultyIncreaseInterval = GameManager.DIFFICULTY_INCREASE_AFTER_TURNS_HARD;
            default -> difficultyIncreaseInterval = 1;
        }
    }

    /**
     * Starts the main game loop.
     * Continues running battles until the player dies, then shows the death screen.
     */
    @Override
    public void startUI()
    {
        while (GameManager.getPlayer().isAlive())
        {
            UIHelper.clearScreen();

            BattleUI battleUI = new BattleUI(currentDangerLevel, battleCount);
            battleUI.startUI();

            battleCount++;
            updateDangerLevel();
            
            UIHelper.clearScreen();

            if (GameManager.getPlayer().isAlive())
            {
                RestUI restUI = new RestUI();
                restUI.startUI();
            }
        }

        UIHelper.clearScreen();
        DeathUI deathUI = new DeathUI();
        deathUI.startUI();
    }

    /**
     * Updates the danger level based on battle count and difficulty settings.
     * Progressively increases danger from HARMLESS to DEATH.
     */
    private void updateDangerLevel()
    {
        if (battleCount % difficultyIncreaseInterval == 0)
        {
            switch (currentDangerLevel)
            {
                case HARMLESS -> currentDangerLevel = DangerLevel.MOSTLY_HARMLESS;
                case MOSTLY_HARMLESS -> currentDangerLevel = DangerLevel.DANGEROUS;
                case DANGEROUS -> currentDangerLevel = DangerLevel.EXTREME;
                case EXTREME -> currentDangerLevel = DangerLevel.DEATH;
            }
        }
    }

}
