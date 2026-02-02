package Screens;

import Maze.Maze;
import UserInterface.Screen;
import UserInterface.UI;

public class MazeTestScreen extends Screen
{
    @Override
    public void startScreen()
    {
        Maze maze = new Maze(20, 10);

        maze.displayMaze();

        UI.waitForEnterKey();
    }

}
