import java.util.*;

public class MyGraph<V>{
    private Map<V, Vertex<V>> vertices; // contains the key and the Vertex, the edges are stored in Vertex class

    public MyGraph(){
        vertices = new HashMap<V, Vertex<V>>(); // initializing hashmap
    }
}