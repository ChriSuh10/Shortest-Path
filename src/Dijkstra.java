import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
//http://stackoverflow.com/questions/222413/find-the-shortest-path-in-a-graph-which-visits-certain-nodes

public class Dijkstra {
	private final List<Vertex> vertices;
	private final List<Edge> edges;
	private Set<Vertex> settled;
	private Set<Vertex> unSettled;
	private Map<Vertex, Vertex> previous;
	private Map<Vertex, Integer> distance;

	public Dijkstra(Graph graph) {
		this.vertices = new ArrayList<Vertex>(graph.getVertices());
		this.edges = new ArrayList<Edge>(graph.getEdges());
		unSettled = new HashSet<Vertex>();
		settled = new HashSet<Vertex>();
		previous = new HashMap<Vertex, Vertex>();
		distance = new HashMap<Vertex, Integer>();
	}

	public void Shortest (Vertex start, Vertex end) {
		unSettled.addAll(vertices);
		for (Vertex v: vertices)
			distance.put(v, Integer.MAX_VALUE);
		distance.put(start, new Integer(0));

		while(unSettled.size() != 0) {
			Vertex evaluation = ShortestNode(unSettled);
			EvaluateNeightbors(evaluation);
			settled.add(evaluation);
			unSettled.remove(evaluation);
		}
	}

	public Vertex ShortestNode(Set<Vertex> unSettled) {
		Integer min = Integer.MAX_VALUE;
		Vertex shortest = null;
		for (Vertex v: unSettled)
			if (distance.get(v) < min)
				shortest = v;
		return shortest;
	}

	public void EvaluateNeightbors(Vertex evaluate) {
		for (Edge e: edges) {
			Vertex destNode = null;
			if (e.getSource().equals(evaluate) && unSettled.contains(e.getDestination())) {
				destNode = e.getDestination();
				Integer dist = e.getWeight() + distance.get(evaluate);
				if (distance.get(destNode) > dist) {
					distance.put(destNode, dist);
					previous.put(destNode, evaluate);
				}
			}
		}
	}
	
	public LinkedList<Vertex> shortestPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		if (previous.get(step) == null)
			return null;
		path.add(step);
		
		while (previous.get(step) != null) {
			step = previous.get(step);
			path.add(step);
		}
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args) {
		Vertex a = new Vertex("a", "a");
		Vertex b = new Vertex("b", "b");
		Vertex c = new Vertex("c", "c");
		Vertex d = new Vertex("d", "d");
		Edge ab = new Edge("ab",a,b,3);
		Edge bc = new Edge("bc",b,c,7);
		Edge cd = new Edge("cd",c,d,1);
		Edge ac = new Edge("ac",a,c,5);
		ArrayList<Vertex> v = new ArrayList<Vertex>();
		ArrayList<Edge> e = new ArrayList<Edge>();
		v.add(a);
		v.add(b);
		v.add(c);
		v.add(d);

		e.add(ab);
		e.add(bc);
		e.add(cd);
		e.add(ac);
		Graph graph = new Graph(v,e);

		Dijkstra dijk = new Dijkstra(graph);
		dijk.Shortest(a,c);
		LinkedList<Vertex> shortestPath = dijk.shortestPath(d);
		System.out.println(shortestPath);
	}
}
