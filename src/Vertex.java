import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex<V>{
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices; // hashmap contains vertices as keys and weight as values
    public Vertex(V data){ // constructor
        this.data = data;
        adjacentVertices = new HashMap<>();
    }
}