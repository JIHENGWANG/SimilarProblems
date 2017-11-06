package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class graphProblems {
	static class GraphNode {
	    public int key;
	     public List<GraphNode> neighbors;
	     public GraphNode(int key) {
	       this.key = key;
	       this.neighbors = new ArrayList<GraphNode>();
	     }
	     public void addNeibor(GraphNode node){
	    	 neighbors.add(node);
	     }
	}
	
	/*
	 * reverse a graph
	 */
	public List<GraphNode> reverse (List<GraphNode> list){
		Map<GraphNode, GraphNode> map = new HashMap<> ();
		for(GraphNode node : list){
			map.put(node,  new GraphNode(node.key));
		}
		for(GraphNode node : list){
			for(GraphNode nei : node.neighbors){
				map.get(nei).addNeibor(map.get(node));
			}
		}
		List<GraphNode> sol = new ArrayList<GraphNode>();
		for(Map.Entry<GraphNode, GraphNode> node : map.entrySet()){
			sol.add(node.getValue());
		}
		return sol;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
