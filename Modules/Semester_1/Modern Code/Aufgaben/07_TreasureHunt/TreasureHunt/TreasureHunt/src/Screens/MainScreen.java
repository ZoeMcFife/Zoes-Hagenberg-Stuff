package Screens;

import Global.Config;
import Maze.AiMode;
import Screens.Maze.MazeScreen;
import UserInterface.Menu.Menu;
import UserInterface.Menu.MenuItem;
import UserInterface.Screen;
import UserInterface.UI;

public class MainScreen extends Screen
{
    @Override
    public void startScreen()
    {
        while (true)
        {
            UI.printBlankSeparatorLine();

            MenuItem mazeTestMenuItem = new MenuItem("Maze Test Screen", this::mazeTestScreen);
            MenuItem mazeMenuItem = new MenuItem("Maze Screen", this::mazeScreen);
            MenuItem settingsMenu = new MenuItem("Settings Screen", this::settingsScreen);

            Menu mainMenu = new Menu("Main Menu", mazeTestMenuItem, mazeMenuItem, settingsMenu);

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    public void mazeTestScreen()
    {
        MazeTestScreen mazeTestScreen = new MazeTestScreen();
        mazeTestScreen.startScreen();
    }

    public void settingsScreen()
    {
        SettingsScreen settingsScreen = new SettingsScreen();
        settingsScreen.startScreen();
    }

    public void mazeScreen()
    {
        MazeScreen mazeScreen = new MazeScreen(Config.mazeSize, Config.treasureCount, Config.aiMode);
        mazeScreen.startScreen();
    }
}
