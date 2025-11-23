package main;

import main.ui.components.main_menu.MainMenu;

/*
 * Unit Tests were automatically generated with copilot so they're kinda useless.
 * Factory Classes were also generated with copilot.
 * the rest is pretty much just me. Did have copilot help out due to time constraints.
 * not my finest work but it works.
 */

/**
 * Main entry point for the game application.
 * Initializes and starts the game through the main menu.
 */
public class Main
{
    /**
     * Application entry point.
     * Creates and displays the main menu to start the game.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        MainMenu menu = new MainMenu();
        menu.startUI();
    }
}