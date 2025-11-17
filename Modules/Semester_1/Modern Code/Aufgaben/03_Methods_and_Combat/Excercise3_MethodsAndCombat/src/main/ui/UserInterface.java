package main.ui;

/**
 * Abstract base class for all user interface screens in the game.
 * Defines the contract that all UI screens must implement.
 */
public abstract class UserInterface
{
    /**
     * Starts and displays this UI screen.
     * Each implementation should handle its own display logic and user interaction.
     */
    public abstract void startUI();

}
