package AStarPathfinding;

public interface Scorer<Node extends GraphNode>
{
    double computeCost(Node from, Node to);
}
