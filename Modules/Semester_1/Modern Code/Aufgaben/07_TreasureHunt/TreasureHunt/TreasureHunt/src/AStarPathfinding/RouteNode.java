package AStarPathfinding;

public class RouteNode<Node extends GraphNode> implements Comparable<RouteNode>
{
    private final Node current;
    private Node previous;
    private double routeScore;
    private double estimatedScore;

    public RouteNode(Node current, Node previous, double routeScore, double estimatedScore)
    {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    public RouteNode(Node current)
    {
        this(current, null, Double.MAX_VALUE, Double.MAX_VALUE);
    }


    @Override
    public int compareTo(RouteNode other)
    {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }

    public Node getCurrent() {
        return current;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public double getEstimatedScore()
    {
        return estimatedScore;
    }

    public void setEstimatedScore(double estimatedScore)
    {
        this.estimatedScore = estimatedScore;
    }
}

