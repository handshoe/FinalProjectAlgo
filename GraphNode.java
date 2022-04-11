import java.util.*;

public class GraphNode {
	private String nodeName;
	private GraphNode previousNode;
	private ArrayList<GraphEdge> directedEdges = new ArrayList<GraphEdge>();

	private double distanceToSource = Integer.MAX_VALUE;

	public GraphNode(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setDistanceToSource(double distanceToSource) {
		this.distanceToSource = distanceToSource;
	}

	public double getDistanceToSource() {
		return distanceToSource;
	}

	public void addNewDirection(GraphNode targetNode, double edgeWeight) {
		GraphEdge ge = new GraphEdge(targetNode, edgeWeight);
		directedEdges.add(ge);
	}

	public GraphEdge getDirection(int index) {
		return directedEdges.get(index);
	}

	public ArrayList<GraphEdge> getDirections() {
		return directedEdges;
	}

	public void setPreviousNode(GraphNode previousNode) {
		this.previousNode = previousNode;
	}

	public GraphNode getPreviousNode() {
		return previousNode;
	}

}
