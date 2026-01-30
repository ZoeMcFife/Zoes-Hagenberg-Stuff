package Maze;

import UserInterface.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze
{
    private Tile[][] grid;

    private int size = 0;

    private boolean mazeCompleted = false;

    private TilePosition playerPosition;
    private TilePosition aiPosition;

    private int playerTreasuresCollected = 0;
    private int aiTreasuresCollected = 0;

    private AiMode aiMode;

    private static final int DEFAULT_TREASURE_COUNT = 5;
    private static final int DEFAULT_MAZE_SIZE = 15;
    private static final AiMode DEFAULT_AI_MODE = AiMode.MINIMAX;

    public Maze()
    {
        this(DEFAULT_MAZE_SIZE, DEFAULT_TREASURE_COUNT, DEFAULT_AI_MODE);
    }

    public Maze(int size)
    {
        this(size, DEFAULT_TREASURE_COUNT, DEFAULT_AI_MODE);
    }

    public Maze(int size, int treasureCount)
    {
        this(size, treasureCount, DEFAULT_AI_MODE);
    }

    public Maze(int size, int treasureCount, AiMode aiMode)
    {
        this.size = size;
        this.aiMode = aiMode;
        grid = initializeMaze(size);

        spawnTreasures(treasureCount);
        spawnPlayer();
        spawnAI();
    }

    public boolean canMovePlayer(Direction direction)
    {
        TilePosition newPosition = switch (direction)
        {
            case UP -> new TilePosition(playerPosition.row() - 1, playerPosition.col());
            case DOWN -> new TilePosition(playerPosition.row() + 1, playerPosition.col());
            case LEFT -> new TilePosition(playerPosition.row(), playerPosition.col() - 1);
            case RIGHT -> new TilePosition(playerPosition.row(), playerPosition.col() + 1);
        };

        UI.printlnRed(" " + isTraversableTile(newPosition));

        return isTraversableTile(newPosition);
    }

    public void movePlayer(Direction direction)
    {
        TilePosition newPosition = switch (direction)
        {
            case UP -> new TilePosition(playerPosition.row() - 1, playerPosition.col());
            case DOWN -> new TilePosition(playerPosition.row() + 1, playerPosition.col());
            case LEFT -> new TilePosition(playerPosition.row(), playerPosition.col() - 1);
            case RIGHT -> new TilePosition(playerPosition.row(), playerPosition.col() + 1);
        };

        movePlayer(newPosition);
    }

    private void checkTreasureStatus()
    {
        int treasureCount = getAllTreasurePositions().size();

        IO.println("Treasure Status: " + treasureCount);

        if (treasureCount == 0)
        {
            mazeCompleted = true;
        }

    }

    private void movePlayer(TilePosition newPosition)
    {
        if (isTraversableTile(newPosition))
        {
            if (getTileType(newPosition) == Tile.TREASURE)
            {
                playerTreasuresCollected++;
            }

            // Clear old position
            grid[playerPosition.row()][playerPosition.col()] = Tile.PATH;

            // Update to new position
            playerPosition = newPosition;
            grid[playerPosition.row()][playerPosition.col()] = Tile.PLAYER;

            checkTreasureStatus();
        }
    }

    private void moveAI(TilePosition newPosition)
    {
        if (isTraversableTile(newPosition))
        {
            if (getTileType(newPosition) == Tile.TREASURE)
            {
                aiTreasuresCollected++;
            }

            // Clear old position
            grid[aiPosition.row()][aiPosition.col()] = Tile.PATH;

            // Update to new position
            aiPosition = newPosition;
            grid[aiPosition.row()][aiPosition.col()] = Tile.AI;

            checkTreasureStatus();
        }
    }

    public void aiTurn()
    {
        switch (aiMode)
        {
            case GREEDY -> aiMoveGreedy();
            case MINIMAX -> aiMoveMinimax();
        }
    }

    public List<TilePosition> aiGetGreedyMove()
    {
        List<TilePosition> treasurePositions = getAllTreasurePositions();
        List<List<TilePosition>> pathsToTreasures = new ArrayList<>();

        for (TilePosition treasurePosition : treasurePositions)
        {
            List<TilePosition> path = findPath(aiPosition, treasurePosition);
            if (!path.isEmpty())
            {
                pathsToTreasures.add(path);
            }
        }

        if (!pathsToTreasures.isEmpty())
        {
            // Find the shortest path
            List<TilePosition> shortestPath = pathsToTreasures.getFirst();

            for (List<TilePosition> path : pathsToTreasures)
            {
                if (path.size() < shortestPath.size())
                {
                    shortestPath = path;
                }
            }

            UI.printlnRed("AI Path: " + shortestPath);
            return shortestPath;
        }

        return new ArrayList<>();
    }

    public List<TilePosition> aiGetMinimaxMove()
    {
        List<TilePosition> treasurePositions = getAllTreasurePositions();

        for (TilePosition treasurePosition : treasurePositions)
        {
            int score = minimax(aiPosition, playerPosition, treasurePosition, 0, true);
            IO.println("Minimax score for treasure at " + treasurePosition + ": " + score);
        }

        return new ArrayList<>();
    }

    private int minimax(TilePosition aiPos, TilePosition playerPos, TilePosition treasurePos, int depth, boolean isMaximizing)
    {
        // Base case: check if treasure is collected
        if (aiPos.equals(treasurePos))
        {
            return 10 - depth; // AI wins
        }
        if (playerPos.equals(treasurePos))
        {
            return depth - 10; // Player wins
        }

        if (depth >= 5) // Limit search depth
        {
            return 0;
        }

        if (isMaximizing)
        {
            int bestScore = Integer.MIN_VALUE;

            for (TilePosition move : getTraversableNeighbourTiles(aiPos))
            {
                int score = minimax(move, playerPos, treasurePos, depth + 1, false);
                bestScore = Math.max(bestScore, score);
            }

            return bestScore;
        }
        else
        {
            int bestScore = Integer.MAX_VALUE;

            for (TilePosition move : getTraversableNeighbourTiles(playerPos))
            {
                int score = minimax(aiPos, move, treasurePos, depth + 1, true);
                bestScore = Math.min(bestScore, score);
            }

            return bestScore;
        }
    }

    public void aiMoveGreedy()
    {
        List<TilePosition> path = aiGetGreedyMove();

        UI.printlnRed("AI Path: " + path);

        if (path != null && !path.isEmpty())
        {
            TilePosition nextMove = path.getFirst();
            moveAI(nextMove);
        }
    }

    public void aiMoveMinimax()
    {

    }

    public List<TilePosition> getAllTreasurePositions()
    {
        List<TilePosition> treasurePositions = new ArrayList<>();

        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                TilePosition position = new TilePosition(row, col);
                if (getTileType(position) == Tile.TREASURE)
                {
                    treasurePositions.add(position);
                }
            }
        }

        return treasurePositions;
    }

    private Tile[][] initializeMaze(int size)
    {
        grid = new Tile[size][size];

        // initialize all tiles as WALL
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = Tile.WALL;
            }
        }

        // carve maze

        return carveMaze(grid);
    }

    private void spawnTreasures(int count)
    {
        for (int i = 0; i < count; i++)
        {
            TilePosition position = new TilePosition(-1, -1);

            while (!isValidTile(position))
            {
                int row = (int) (Math.random() * size);
                int col = (int) (Math.random() * size);

                position = new TilePosition(row, col);
            }


            if (getTileType(position) == Tile.PATH)
            {
                grid[position.row()][position.col()] = Tile.TREASURE;
            }

        }
    }

    private void spawnPlayer()
    {
        int row = (int) (Math.random() * size);
        int col = (int) (Math.random() * size);

        TilePosition position = new TilePosition(row, col);

        if (getTileType(position) == Tile.PATH)
        {
            grid[row][col] = Tile.PLAYER;
            playerPosition = position;
        }
        else
        {
            spawnPlayer();
        }
    }

    private void spawnAI()
    {
        int row = (int) (Math.random() * size);
        int col = (int) (Math.random() * size);

        TilePosition position = new TilePosition(row, col);

        if (getTileType(position) == Tile.PATH)
        {
            if (getEuclideanDistance(position, playerPosition) < (double) size / 2)
            {
                spawnAI();
                return;
            }

            grid[row][col] = Tile.AI;
            aiPosition = position;
        }
        else
        {
            spawnAI();
        }
    }


    public static double getManhattanDistance(TilePosition to, TilePosition from)
    {
        return Math.abs(to.row() - from.row()) + Math.abs(to.col() - from.col());
    }

    public static double getEuclideanDistance(TilePosition to, TilePosition from)
    {
        return Math.sqrt(Math.pow(to.row() - from.row(), 2) + Math.pow(to.col() - from.col(), 2));
    }

    private Tile[][] carveMaze(Tile[][] grid)
    {
        carve(new TilePosition(0, 0), grid);
        return grid;
    }

    private void carve(TilePosition tile, Tile[][] grid)
    {
        if (!isValidTile(tile) || grid[tile.row()][tile.col()] != Tile.WALL)
        {
            return;
        }

        if (countAdjacentPaths(tile) > 1)
        {
            return;
        }

        grid[tile.row()][tile.col()] = Tile.PATH;

        List<TilePosition> neighbourTiles = getDirectNeighbourTiles(tile);
        Collections.shuffle(neighbourTiles);

        for (TilePosition neighbourTile : neighbourTiles)
        {
            if (getTileType(neighbourTile) == Tile.WALL)
            {
                carve(neighbourTile, grid);
            }
        }
    }

    public List<TilePosition> findPath(TilePosition start, TilePosition goal)
    {
        List<TilePosition> path = new ArrayList<>();

        simpleSearch(start, goal, path, new ArrayList<>());

        return path.reversed();
    }

    private boolean simpleSearch(TilePosition current, TilePosition goal, List<TilePosition> path, List<TilePosition> visited)
    {
        if (current.equals(goal))
        {
            return true;
        }

        if (!isTraversableTile(current, true) || visited.contains(current))
        {
            return false;
        }

        visited.add(current);

        for (TilePosition neighbour : getTraversableNeighbourTiles(current, true))
        {
            if (simpleSearch(neighbour, goal, path, visited))
            {
                path.add(neighbour);
                //IO.println("path: " + path.toString());
                return true;
            }

            path.remove(neighbour);
        }

        return false;
    }

    public Tile getTileType(TilePosition tile)
    {
        return grid[tile.row()][tile.col()];
    }

    public List<TilePosition> getTraversableNeighbourTiles(TilePosition tile)
    {
        return getTraversableNeighbourTiles(tile, false);
    }

    public List<TilePosition> getTraversableNeighbourTiles(TilePosition tile, boolean ignoreGameCharacters)
    {
        List<TilePosition> neighbours = new ArrayList<>();

        for (TilePosition n : getDirectNeighbourTiles(tile))
        {
            if (isTraversableTile(n, ignoreGameCharacters))
            {
                neighbours.add(n);
            }
        }

        return neighbours;
    }

    private List<TilePosition> getDirectNeighbourTiles(TilePosition tile)
    {
        List<TilePosition> neighbours = new ArrayList<>();

        int[] rowDeltas = {1, -1, 0, 0};
        int[] colDeltas = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++)
        {
            TilePosition neighbourTile = new TilePosition(
                tile.row() + rowDeltas[i],
                tile.col() + colDeltas[i]
            );

            if (isValidTile(neighbourTile))
            {
                neighbours.add(neighbourTile);
            }
        }

        return neighbours;
    }

    private int countAdjacentPaths(TilePosition tile)
    {
        int count = 0;

        for (TilePosition n : getDirectNeighbourTiles(tile))
        {
            if (getTileType(n) == Tile.PATH)
            {
                count++;
            }
        }

        return count;
    }


    private TilePosition pickRandomDirectNeighbourTile(TilePosition tile)
    {
        int[] rowDeltas = {1, -1, 0, 0};
        int[] colDeltas = {0, 0, -1, 1};

        int randomDirection = (int) (Math.random() * 4);

        TilePosition neighbourTile = new TilePosition(
            tile.row() + rowDeltas[randomDirection],
            tile.col() + colDeltas[randomDirection]
        );

        if (isValidTile(neighbourTile))
        {
            return neighbourTile;
        }
        else
        {
            return pickRandomDirectNeighbourTile(tile);
        }

    }

    private boolean isValidTile(TilePosition tile)
    {
        if (tile == null)
        {
            return false;
        }

        if (size <= 0)
        {
            return false;
        }

        if (tile.row() < 0 || tile.col() < 0)
        {
            return false;
        }

        return tile.row() < size && tile.col() < size;
    }

    private boolean isTraversableTile(TilePosition tile)
    {
        return isTraversableTile(tile, false);
    }

    private boolean isTraversableTile(TilePosition tile, boolean ignoreGameCharacters)
    {
        if (!isValidTile(tile))
        {
            return false;
        }

        Tile tileType = getTileType(tile);

        if (ignoreGameCharacters)
        {
            return tileType != Tile.WALL;
        }

        return tileType != Tile.WALL && tileType != Tile.PLAYER && tileType != Tile.AI;
    }

    public void displayMaze()
    {
        UI.clearScreen();
        printMaze();
        printScore();
    }

    public void displayMazeWithPath(List<TilePosition> path)
    {
        UI.clearScreen();
        printMazeWithPath(path);
        printScore();
    }

    public void displayMazeWithAIPath()
    {
        List<TilePosition> path = new ArrayList<>();

        switch (aiMode)
        {
            case GREEDY -> path = aiGetGreedyMove();
            case MINIMAX -> path = new ArrayList<>(); // TODO: implement minimax path retrieval
        }

        printMazeWithPath(path);
        printScore();
    }

    private void printMaze()
    {
        // print top wall
        for (int x = 0; x < size + 2; x++)
        {
            printWall();
        }

        IO.println();

        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                // print left wall
                if (x == 0)
                {
                    printWall();
                }

                Tile tile = grid[y][x];

                switch (tile)
                {
                    case WALL ->
                            printWall();

                    case PATH ->
                            printPath();

                    case PLAYER ->
                            printPlayer();

                    case AI ->
                            printAI();

                    case TREASURE ->
                            printTreasure();
                }
            }

            // print right wall
            printWall();

            IO.println(); // next row
        }

        // print bottom wall
        for (int x = 0; x < size + 2; x++)
        {
            printWall();
        }

        IO.println();
    }

    public void printMazeWithPath(List<TilePosition> path)
    {
        // print top wall
        for (int x = 0; x < size + 2; x++)
        {
            printWall();
        }
        IO.println();

        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                // print left wall
                if (x == 0)
                {
                    printWall();
                }

                Tile tile = grid[y][x];
                TilePosition pos = new TilePosition(y, x);

                // cyan path overlay ONLY for PATH tiles
                if (tile == Tile.PATH && path.contains(pos))
                {
                    UI.printCyan("· ");
                    continue;
                }

                switch (tile)
                {
                    case WALL -> printWall();
                    case PATH -> printPath();
                    case PLAYER -> printPlayer();
                    case AI -> printAI();
                    case TREASURE -> printTreasure();
                }
            }

            // print right wall
            printWall();
            IO.println();
        }

        // print bottom wall
        for (int x = 0; x < size + 2; x++)
        {
            printWall();
        }
        IO.println();
    }

    private void printWall()
    {
        UI.printGray("▓ ");
    }

    private void printPath()
    {
        UI.printGray("· ");
    }

    private  void printPlayer()
    {
        UI.printGreen("@ ");
    }

    private void printAI()
    {
        UI.printRed("A ");
    }

    private void printTreasure()
    {
        UI.printYellow("$ ");
    }

    private void printScore()
    {
        UI.printlnYellow("Score - You: " + playerTreasuresCollected + " | AI: " + aiTreasuresCollected);
    }

    public TilePosition getAiPosition() {
        return aiPosition;
    }

    public TilePosition getPlayerPosition() {
        return playerPosition;
    }

    public int getPlayerTreasuresCollected() {
        return playerTreasuresCollected;
    }

    public void setPlayerTreasuresCollected(int playerTreasuresCollected) {
        this.playerTreasuresCollected = playerTreasuresCollected;
    }

    public int getAiTreasuresCollected() {
        return aiTreasuresCollected;
    }

    public void setAiTreasuresCollected(int aiTreasuresCollected) {
        this.aiTreasuresCollected = aiTreasuresCollected;
    }


    public int getSize() {
        return size;
    }

    public boolean isMazeCompleted() {
        return mazeCompleted;
    }
}
