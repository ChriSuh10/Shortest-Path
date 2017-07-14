import java.util.List;

//From http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html

public class Graph {
	private final List<Vertex> vertexes;
	private final List<Edge> edges;

	public Graph(List<Vertex> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}



}