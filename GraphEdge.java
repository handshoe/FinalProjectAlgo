
public class GraphEdge {
	private GraphNode targetNode;
	private double edgeWeight;

	public GraphEdge(GraphNode targetNode, double edgeWeight) {
		this.targetNode = targetNode;
		this.edgeWeight = edgeWeight;
	}

	public double getEdgeWeight() {
		return edgeWeight;
	}

	public GraphNode getTargetNode() {
		return targetNode;
	}
}
