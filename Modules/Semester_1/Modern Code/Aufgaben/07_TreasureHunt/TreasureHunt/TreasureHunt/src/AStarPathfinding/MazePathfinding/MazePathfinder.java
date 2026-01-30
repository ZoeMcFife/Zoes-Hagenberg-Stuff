package AStarPathfinding.MazePathfinding;

import AStarPathfinding.Graph;
import AStarPathfinding.RouteFinder;
import Maze.*;

import java.util.List;

public class MazePathfinder
{
    private final Maze maze;
    private final Graph graph = new Graph();
    private final MazeScorer scorer = new MazeScorer();

    public MazePathfinder(Maze maze)
    {
        this.maze = maze;
        createGraphFromMaze(maze);
    }

    public List<TileNode> findPath(TilePosition from, TilePosition to)
    {
        TileNode fromNode = new TileNode(maze.getTileType(from), from);
        TileNode toNode = new TileNode(maze.getTileType(to), to);

        RouteFinder<TileNode> routeFinder = new RouteFinder<>(graph, scorer, scorer);

        return routeFinder.findRoute(toNode, fromNode);
    }

    private void createGraphFromMaze(Maze maze)
    {
        for ( int row = 0; row < maze.getSize(); row++ )
        {
            for (int col = 0; col < maze.getSize(); col++)
            {
                TilePosition position = new TilePosition(row, col);

                TileNode node = new TileNode(maze.getTileType(position), position);

                List<TilePosition> connectedNodes = maze.getTraversableNeighbourTiles(position);

                for (TilePosition connectedPosition : connectedNodes)
                {
                    TileNode connectedNode = new TileNode(maze.getTileType(connectedPosition), connectedPosition);
                    graph.addNode(node, connectedNode);
                }
            }
        }
    }
}
