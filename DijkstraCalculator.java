import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Node;

public class DijkstraCalculator {

	public ArrayList<GraphNode> shortestPath(GraphNode source, GraphNode destination) {
		ArrayList<GraphNode> shortestPathNodes = new ArrayList<GraphNode>();

		ArrayList<GraphNode> settledNodes = new ArrayList<GraphNode>();
		ArrayList<GraphNode> unsettledNodes = new ArrayList<GraphNode>();

		source.setDistanceToSource(0);
		GraphNode nodeToCheck = source;
		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			// Searches through starting nodes adjacent nodes and assigns the shortest path
			// to the source and the previous assigned node
			if (nodeToCheck.getDirections().size() > 0) {
				for (int i = 0; i < nodeToCheck.getDirections().size(); i++) {
					nodeToCheck.getDirection(i).getTargetNode().setPreviousNode(nodeToCheck);

					for (GraphNode tempNode = nodeToCheck.getDirection(i)
							.getTargetNode(); tempNode != source; tempNode = tempNode.getPreviousNode()) {
						nodeToCheck.getDirection(i).getTargetNode()
								.setDistanceToSource(nodeToCheck.getDirection(i).getTargetNode().getDistanceToSource()
										+ tempNode.getPreviousNode().getDirection(i).getEdgeWeight());
					}
					// Adds any newly encountered node to the array list of unsettled nodes
					if (!unsettledNodes.contains(nodeToCheck.getDirection(i).getTargetNode())) {
						unsettledNodes.add(nodeToCheck.getDirection(i).getTargetNode());
					}

				}
			}
			unsettledNodes.remove(nodeToCheck);
			settledNodes.add(nodeToCheck);
			nodeToCheck = quickestNodeSoFar(unsettledNodes);
		}

		// work backwards to store the shortest-path nodes in their array
		GraphNode tempNode = destination;
		while (tempNode != source) {
			shortestPathNodes.add(tempNode);
			tempNode = tempNode.getPreviousNode();
		}

		return shortestPathNodes;
	}

	private GraphNode quickestNodeSoFar(ArrayList<GraphNode> unsettledNodes) {
		GraphNode tempNode = unsettledNodes.get(0);
		if (unsettledNodes.size() > 0) {
			for (int i = 1; i < unsettledNodes.size(); i++) {
				if (unsettledNodes.get(i).getDistanceToSource() < tempNode.getDistanceToSource()) {
					tempNode = unsettledNodes.get(i);
				}
			}
		}
		return tempNode;
	}
}
