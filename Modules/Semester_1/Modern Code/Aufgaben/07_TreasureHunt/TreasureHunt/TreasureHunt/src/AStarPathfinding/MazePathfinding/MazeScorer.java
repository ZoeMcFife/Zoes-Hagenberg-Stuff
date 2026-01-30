package AStarPathfinding.MazePathfinding;

import AStarPathfinding.Scorer;
import Maze.Maze;

public class MazeScorer implements Scorer<TileNode>
{
    @Override
    public double computeCost(TileNode from, TileNode to)
    {
        return Maze.getManhattanDistance(to.getPosition(), from.getPosition());
    }
}
