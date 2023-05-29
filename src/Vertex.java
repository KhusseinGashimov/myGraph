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
    public void addAdjacentVertex(Vertex<V> destination, double weight){ // to add new connection
        adjacentVertices.put(destination, weight);
    }

    public void removeAdjacentVertex(Vertex<V> destination){
        adjacentVertices.remove(destination);
    }

    public V getData(){
        return data;
    }

    public List<Vertex<V>> getNeighbours(){ // to get all the vertices that are connected to this
        ArrayList<Vertex<V>> neighbours = new ArrayList<>(); // vertices are stores in ArrayList before returning
        for(Vertex<V> vertex: adjacentVertices.keySet()){
            neighbours.add(vertex);
        }
        return neighbours;
    }

    public boolean hasEdge(Vertex<V> destination){
        if(adjacentVertices.containsKey(destination)){
            return true;
        }else{
            return false;
        }
    }



    public double getEdgeWeight(Vertex<V> neighbor) {
        return adjacentVertices.get(neighbor);
    }
}