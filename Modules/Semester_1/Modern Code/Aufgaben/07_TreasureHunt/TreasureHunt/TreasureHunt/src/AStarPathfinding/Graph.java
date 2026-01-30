package AStarPathfinding;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<Node extends GraphNode>
{
    private final Set<Node> nodes = new HashSet<>();
    private final Map<String, Set<String>> connections = new HashMap<>();

    public void addNode(Node node, Node... connectedNodes)
    {
        nodes.add(node);
        Set<String> connectedNodeIds = Arrays.stream(connectedNodes)
                .map(GraphNode::getId)
                .collect(Collectors.toSet());
        connections.put(node.getId(), connectedNodeIds);
    }

    public Node getNode(String id)
    {
        return nodes.stream()
                .filter(node -> node.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Node with ID " + id + " not found."));
    }

    public Set<Node> getConnections(Node node)
    {
        return connections.get(node.getId()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }
}
