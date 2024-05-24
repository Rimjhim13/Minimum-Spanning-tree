import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;





public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (Edge e : adj[v]) {
                sb.append(e).append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
