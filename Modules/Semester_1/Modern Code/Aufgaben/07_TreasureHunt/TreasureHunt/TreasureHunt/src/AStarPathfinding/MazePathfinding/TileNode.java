package AStarPathfinding.MazePathfinding;

import AStarPathfinding.GraphNode;
import Maze.Tile;
import Maze.TilePosition;

public class TileNode implements GraphNode
{
    private final String id;
    private final Tile tile;
    private final TilePosition position;

    public TileNode(Tile tile, TilePosition position)
    {
        this.tile = tile;
        this.position = position;
        this.id = position.row() + "," + position.col();
    }

    @Override
    public String getId()
    {
        return id;
    }

    public Tile getTile() {
        return tile;
    }

    public TilePosition getPosition() {
        return position;
    }
}
