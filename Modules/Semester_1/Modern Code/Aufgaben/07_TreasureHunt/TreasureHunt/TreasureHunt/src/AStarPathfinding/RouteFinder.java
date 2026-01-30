package AStarPathfinding;

import java.util.*;

public class RouteFinder<Node extends GraphNode>
{
    private final Graph<Node> graph;
    private final Scorer<Node> nextNodeScorer;
    private final Scorer<Node> targetNodeScorer;

    public RouteFinder(Graph<Node> graph, Scorer<Node> nextNodeScorer, Scorer<Node> targetNodeScorer)
    {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetNodeScorer = targetNodeScorer;
    }

    public List<Node> findRoute(Node to, Node from)
    {
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<Node, RouteNode<Node>> allNodes = new HashMap<>();

        RouteNode<Node> start = new RouteNode<Node>(from, null, 0d, targetNodeScorer.computeCost(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        while (!openSet.isEmpty())
        {
            RouteNode<Node> next = openSet.poll();
            if (next.getCurrent().equals(to))
            {
                List<Node> route = new ArrayList<>();
                RouteNode<Node> current = next;
                do
                {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                }
                while (current != null);

                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection ->
            {
                RouteNode<Node> nextNode = allNodes.getOrDefault(connection, new RouteNode<Node>(connection));
                allNodes.put(connection, nextNode);

                double newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);

                if (newScore < nextNode.getRouteScore())
                {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetNodeScorer.computeCost(connection, to));
                    openSet.add(nextNode);
                }
            });

            return Collections.emptyList();
            //throw new IllegalStateException("No route found");
        }

        return Collections.emptyList();
    }
}
