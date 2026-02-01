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

    public MazeScreen(int mazeSize, int treasureCount, AiMode aiMode)
    {
        super();
        this.maze = new Maze(mazeSize, treasureCount, aiMode);
    }

    /**
     * Starts and displays this screen.
     * Each implementation should handle its own display logic and user interaction.
     */
    @Override
    public void startScreen()
    {
        while (!maze.isMazeCompleted())
        {
            UI.clearScreen();

            maze.displayMazeWithAIPath();

            maze.aiGetMinimaxMove();

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

            maze.aiTurn();
        }

        UI.clearScreen();

        if (maze.playerTreasuresCollected > maze.aiTreasuresCollected)
        {
            UI.printlnGreen("Congratulations! You have completed the maze and collected more treasures than the AI!");
        }
        else if (maze.playerTreasuresCollected < maze.aiTreasuresCollected)
        {
            UI.printlnRed("The AI has collected more treasures than you. Better luck next time!");
        }
        else
        {
            UI.printlnYellow("It's a tie! Both you and the AI collected the same number of treasures.");
        }

        UI.waitForEnterKey();
        UI.clearScreen();
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
