import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IncludingNodes {
	private List<Vertex> all;
	private List<Edge> edges;
	private List<LinkedList<Vertex>> paths;

	public IncludingNodes(List<Vertex> all, List<Edge> edges) {
		this.all = all;
		this.edges = edges;
		paths = new ArrayList<LinkedList<Vertex>>();
	}

	public LinkedList<Vertex> shortInclude(Vertex start, List<Vertex> targets) {
		if (targets.size() == 0) {
			LinkedList<Vertex> ret = new LinkedList<Vertex>();
			ret.add(start);
			return ret;
		}

		ArrayList<LinkedList<Vertex>> paths = new ArrayList<LinkedList<Vertex>>();
		for (int i = 0; i < targets.size(); i++) {
			for (int j = i + 1; j < targets.size(); i++) {
				Vertex t1 = targets.get(i);
				Vertex t2 = targets.get(j);
				
				Dijkstra d = new Dijkstra(new Graph(all, edges));
				d.Shortest(t1, t2);
				paths.add(d.shortestPath(t2));
			}
		}
	}

	public LinkedList<Vertex> sortPermutations(List<LinkedList<Vertex>> paths, int numNodes) {
		//implement this
	}
}
