package Maze;

import UserInterface.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maze
{
    private Tile[][] grid;
    private int size = 0;

    public Maze(int size)
    {
        this.size = size;
        grid = initializeMaze(size);
    }

    private Tile[][] initializeMaze(int size)
    {
        grid = new Tile[size][size];

        // initizalize all tiles as WALL
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

    private Tile getTileType(TilePosition tile)
    {
        return grid[tile.row()][tile.col()];
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
        return tile.row() >= 0 && tile.row() < size && tile.col() >= 0 && tile.col() < size;
    }

    public void printMaze()
    {
        for (int x = 0; x < size + 2; x++)
        {
            UI.printGray("▓ ");
        }

        IO.println();

        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                if (x == 0)
                {
                    UI.printGray("▓ ");
                }

                Tile tile = grid[y][x];

                switch (tile)
                {
                    case WALL ->
                            UI.printGray("▓ ");

                    case PATH ->
                            UI.printGray("· ");

                    case PLAYER ->
                            UI.printGreen("@ ");

                    case AI ->
                            UI.printRed("A ");

                    case TREASURE ->
                            UI.printYellow("$ ");
                }
            }
            UI.printGray("▓ ");
            IO.println(); // next row
        }

        for (int x = 0; x < size + 2; x++)
        {
            UI.printGray("▓ ");
        }

        IO.println();
    }

}
