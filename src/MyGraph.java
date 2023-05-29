import java.util.*;

public class MyGraph<V>{
    private Map<V, Vertex<V>> vertices; // contains the key and the Vertex, the edges are stored in Vertex class

    public MyGraph(){
        vertices = new HashMap<V, Vertex<V>>(); // initializing hashmap
    }

    public void addVertex(V data){ // creating a new vertex with data
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(V sourceKey, V destinationKey, double weight){ // adding the edges by entering keys
        Vertex<V> source = vertices.get(sourceKey);
        Vertex<V> destination = vertices.get(destinationKey);
        source.addAdjacentVertex(destination, weight); // we add the path both ways
        destination.addAdjacentVertex(source, weight);
    }

    public void printGraph(){
        for(Vertex<V> vertex: vertices.values()){ // traversing the values itself of hashmap with vertices
            ArrayList<Vertex<V>> neighbours = (ArrayList<Vertex<V>>) vertex.getNeighbours();
            System.out.print("Current Vertex -> " + vertex.getData() + "  Connected vertices: ");
            for(Vertex<V> neighbour:neighbours){
                System.out.print(neighbour.getData() + " ");
            }
            System.out.println();
        }

    }

    public void removeEdge(V sourceKey, V destinationKey){
        Vertex<V> source = vertices.get(sourceKey);
        Vertex<V> destination = vertices.get(destinationKey);
        source.removeAdjacentVertex(destination);
        destination.removeAdjacentVertex(source);
    }

    public boolean hasEdge(V sourceKey, V destinationKey){
        Vertex<V> source = vertices.get(sourceKey);
        Vertex<V> destination = vertices.get(destinationKey);
        if(source.hasEdge(destination) || destination.hasEdge(source)){ // checking the edge for both
            return true;
        }
        return false;
    }

    public List<Vertex<V>> getNeighbours(V key){ // returning neighbours of the particular vertex
        Vertex<V> vertex = vertices.get(key);
        return vertex.getNeighbours();
    }

    public void BFS(V startKey){
        Vertex<V> start = vertices.get(startKey);
        Map<Vertex<V>, Boolean> visited = new HashMap<>(); // here I save node that I already visited
        Queue<Vertex<V>> q = new LinkedList<>(); // here I add nodes that need to be visited

        q.add(start); // starting node
        while(!q.isEmpty()){ // while my queue is not empty the algo works
            Vertex<V> current = q.poll(); // take the first in the queue
            visited.put(current, true); // mark it as visited
            System.out.print(current.getData() + " ");
            ArrayList<Vertex<V>> neighbours = (ArrayList<Vertex<V>>) current.getNeighbours(); // get all the nodes that are neighbours
            for(Vertex<V> vertex: neighbours){
                if(!visited.containsKey(vertex)){ // if one of neighbour is not visited, yet I add him to the queue
                    q.add(vertex);
                    visited.put(vertex, true);
                }
            }
        }

    }

    public void Dijkstra(V start) {
        // Check if the start vertex exists in the graph
        if (!vertices.containsKey(start)) {
            System.out.println("Start vertex does not exist in the graph.");
            return;
        }

        Vertex<V> startVertex = vertices.get(start);

        // Create a map to store the distances from the start vertex to other vertices
        Map<Vertex<V>, Double> distances = new HashMap<>();
        // Create a map to store the previous vertices in the shortest path
        Map<Vertex<V>, Vertex<V>> previousVertices = new HashMap<>();

        // Initialize all distances to infinity except the start vertex which is 0
        for (Vertex<V> vertex : vertices.values()) {
            if (vertex.equals(startVertex)) {
                distances.put(vertex, 0.0);
            } else {
                distances.put(vertex, Double.POSITIVE_INFINITY);
            }
        }

        // Create a priority queue (min-heap) to store vertices based on their distances
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();

            // Stop if the current vertex distance is infinity (i.e., unreachable)
            if (distances.get(currentVertex) == Double.POSITIVE_INFINITY) {
                break;
            }

            // Process the adjacent vertices
            for (Vertex<V> neighbor : currentVertex.getNeighbours()) {
                double edgeWeight = currentVertex.getEdgeWeight(neighbor);
                double distance = distances.get(currentVertex) + edgeWeight;

                // Update the distance and previous vertex if a shorter path is found
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    previousVertices.put(neighbor, currentVertex);
                    queue.remove(neighbor); // Remove and re-add to update the priority
                    queue.add(neighbor);
                }
            }
        }

        // Print the shortest paths and distances
        for (Vertex<V> vertex : vertices.values()) {
            if (vertex.equals(startVertex)) {
                System.out.println("Shortest path from " + start + " to " + vertex.getData() + ":");
                System.out.println(start + " (Distance: 0)");
            } else if (previousVertices.containsKey(vertex)) {
                List<Vertex<V>> path = new ArrayList<>();
                Vertex<V> current = vertex;
                while (current != null) {
                    path.add(0, current);
                    current = previousVertices.get(current);
                }
                StringBuilder pathString = new StringBuilder();
                for (Vertex<V> v : path) {
                    pathString.append(v.getData()).append(" -> ");
                }
                pathString.delete(pathString.length() - 4, pathString.length()); // Remove the last " -> "
                System.out.println("Shortest path from " + start + " to " + vertex.getData() + ":");
                System.out.println(pathString.toString() + " (Distance: " + distances.get(vertex) + ")");
            } else {
                System.out.println("There is no path from " + start + " to " + vertex.getData());
            }
        }
    }




}