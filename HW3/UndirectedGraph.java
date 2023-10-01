import java.util.*;

public class UndirectedGraph <V extends Comparable<V>> extends CommonGraph<V> implements IGraph<V> {
    private final SortedMap<V, SortedSet<V>> vertices;

    public UndirectedGraph() {
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
        vertices.get(v).add(u);
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
        if(vertices.get(u) == null || vertices.get(v) == null) {
            return false;
        }

        if(vertices.get(u).contains(v)) {
            vertices.get(u).remove(v);
            vertices.get(v).remove(u);
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
        String str = "UndirectedGraph:\t";
        str = toStringHelper(vertices, str);
        if(str.length() > 17) {
            StringBuilder myName = new StringBuilder(str);
            myName.deleteCharAt(str.length()-1);
            str = String.valueOf(myName);
        }
        return str;
    }
}

