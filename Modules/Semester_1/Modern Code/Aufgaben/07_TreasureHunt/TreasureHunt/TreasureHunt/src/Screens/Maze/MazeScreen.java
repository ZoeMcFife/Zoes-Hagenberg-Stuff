package Screens.Maze;

import Maze.*;
import UserInterface.Screen;
import UserInterface.UI;

import java.util.List;

public class MazeScreen extends Screen
{
    private final Maze maze;

    private boolean mazeCompleted = false;

    public MazeScreen()
    {
        super();
        maze = new Maze();
    }

    public MazeScreen(int mazeSize)
    {
        super();
        this.maze = new Maze(mazeSize);
    }

    public MazeScreen(int mazeSize, int treasureCount)
    {
        super();
        this.maze = new Maze(mazeSize, treasureCount);
    }

    /**
     * Starts and displays this screen.
     * Each implementation should handle its own display logic and user interaction.
     */
    @Override
    public void startScreen()
    {
        while (!mazeCompleted)
        {
            UI.clearScreen();

            //maze.displayMaze();

            List<TilePosition> path = maze.findPath(maze.getPlayerPosition(), maze.getAiPosition());
            IO.println("Path: " + path.toString());

            maze.displayMazeWithPath(path);

            char playerInput;
            Direction moveDirection;
            boolean hasPlayerMoved = false;

            do
            {
                playerInput = UI.getFilteredCharInput('w', 'a', 's', 'd');

                moveDirection = getDirectionFromInput(playerInput);

                if (maze.canMovePlayer(moveDirection))
                {
                    maze.movePlayer(moveDirection);
                    hasPlayerMoved = true;
                }
            }
            while (!hasPlayerMoved);




        }

    }

    private Direction getDirectionFromInput(char input)
    {
        return switch (input)
        {
            case 'w' -> Direction.UP;
            case 'a' -> Direction.LEFT;
            case 's' -> Direction.DOWN;
            case 'd' -> Direction.RIGHT;
            default -> throw new IllegalArgumentException("Invalid input character: " + input);
        };
    }
}
