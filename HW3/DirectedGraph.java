import java.util.*;

public class DirectedGraph<V extends Comparable<V>> extends CommonGraph<V> implements IGraph<V> {
    private final SortedMap<V, SortedSet<V>> vertices;

    public DirectedGraph() {
        this.vertices = new TreeMap<>();
    }

    public void addVertex(V v) {
        addVertexHelper(v, vertices);
    }

    public void addEdge(V u, V v) {
        if(!vertices.containsKey(u)) {
            addVertex(u);
        }
        if(!vertices.containsKey(v)) {
            addVertex(v);
        }
        vertices.get(u).add(v);
    }

    public boolean containsVertex(V v) {
        return containsVertexHelper(v, vertices);
    }

    public boolean containsEdge(V u, V v) {
        return containsEdgeHelper(u, v, vertices);
    }

    public Set<V> removeVertex(V v) {
        return removeVertexHelper(v, vertices);
    }

    public boolean removeEdge(V u, V v) {
        if(vertices.get(u) == null) {
            return false;
        }
        if(vertices.get(u).contains(v)) {
            vertices.get(u).remove(v);
            return true;
        }
        return false;
    }

    public String getGraphType() {
        return getGraphTypeHelper(vertices);
    }

    public int numOfVertices() {
        return numOfVerticesHelper(vertices);
    }

    public int numOfEdges() {
        return numOfEdgesHelper(vertices);
    }

    @Override
    public String toString() {
        String str = "DirectedGraph:\t";
        str = toStringHelper(vertices, str);
        if(str.length() > 15) {
            StringBuilder myName = new StringBuilder(str);
            myName.deleteCharAt(str.length()-1);
            str = String.valueOf(myName);
        }
        return str;
    }
}
