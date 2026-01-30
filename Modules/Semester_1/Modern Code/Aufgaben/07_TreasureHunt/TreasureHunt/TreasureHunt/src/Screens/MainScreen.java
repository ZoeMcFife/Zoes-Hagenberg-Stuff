package Screens;

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

            Menu mainMenu = new Menu("Main Menu", mazeTestMenuItem, mazeMenuItem);

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    public void mazeTestScreen()
    {
        MazeTestScreen mazeTestScreen = new MazeTestScreen();
        mazeTestScreen.startScreen();
    }

    public void mazeScreen()
    {
        MazeScreen mazeScreen = new MazeScreen();
        mazeScreen.startScreen();
    }
}
