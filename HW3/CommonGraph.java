import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class CommonGraph<V extends Comparable<V>> implements IGraph<V> {

    public void addVertexHelper(V v, SortedMap<V, SortedSet<V>> vertices) {
        if(vertices.get(v) == null) {
            SortedSet<V> set = new TreeSet<>();
            vertices.put(v, set);
        }
    }

    public boolean containsVertexHelper(V v, SortedMap<V, SortedSet<V>> vertices) {
        if(vertices.get(v) == null) {
            return false;
        }
        return vertices.containsKey(v);
    }

    public boolean containsEdgeHelper(V u, V v, SortedMap<V, SortedSet<V>> vertices) {
        if(vertices.get(u) == null) {
            return false;
        }
        return vertices.get(u).contains(v);
    }

    public Set<V> removeVertexHelper(V v, SortedMap<V, SortedSet<V>> vertices) {
        if(containsVertex(v)) {
            SortedSet<V> vSet = vertices.get(v);
            vertices.remove(v);
            return vSet;
        }
        return null;
    }

    public String getGraphTypeHelper(SortedMap<V, SortedSet<V>> vertices) {
        if(vertices.size() > 1) {
            for(V key : vertices.keySet()) {
                for(V value : vertices.get(key)) {
                    if(vertices.get(value).contains(key)) {
                        return "Undirected";
                    }
                }
            }
        }
        return "Directed";
    }

    public int numOfVerticesHelper(SortedMap<V, SortedSet<V>> vertices) {
        return vertices.size();
    }

    public int numOfEdgesHelper(SortedMap<V, SortedSet<V>> vertices) {
        int counter = 0;
        for(V key : vertices.keySet()) {
            counter += vertices.get(key).size();
        }
        return counter;
    }

    public String toStringHelper(SortedMap<V, SortedSet<V>> vertices, String str) {
        int count;
        for(V key : vertices.keySet()) {
            count = 0;
            str += key + ":{";
            for (V value : vertices.get(key)) {
                str += value;
                count += 1;
                if(count < vertices.get(key).size()) {
                    str += ",";
                }
            }
            str += "} ";
        }
        return str;
    }
}
