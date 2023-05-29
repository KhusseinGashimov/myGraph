public class Main {
    public static void main(String[] args) {
        MyGraph<Integer> graph = new MyGraph<>();

        // Create vertices
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(0,1, 10);
        graph.addEdge(0,2,1);
        graph.addEdge(2,1,2);

        graph.Dijkstra(0);
    }
}