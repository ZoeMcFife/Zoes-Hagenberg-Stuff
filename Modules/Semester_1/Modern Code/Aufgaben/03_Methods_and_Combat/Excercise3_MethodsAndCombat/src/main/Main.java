package main;

import main.ui.components.main_menu.MainMenu;



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