import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, HashMap<String, Double>> graphMap = new HashMap<>();
        graphMap.put("START", new HashMap<String, Double>() {
            {
                put("A", 5.);
                put("B", 2.);
            }
        });
        graphMap.put("A", new HashMap<String, Double>() {
            {
                put("C", 4.);
                put("D", 2.);
            }
        });
        graphMap.put("B", new HashMap<String, Double>() {
            {
                put("D", 7.);
            }
        });
        graphMap.put("C", new HashMap<String, Double>() {
            {
                put("END", 3.);
                put("D", 6.);
            }
        });
        graphMap.put("D", new HashMap<String, Double>() {
            {
                put("END", 1.);
            }
        });
        graphMap.put("END", new HashMap<String, Double>());

        WeightedGraph myGraph = new WeightedGraph(graphMap);
        System.out.println("");
        System.out.println("The shortest path from vertex 'START' to vertex 'END' is: " + myGraph.getShortestPathTo("END"));
        System.out.println("The shortest distance from vertex 'START' to vertex 'END' is: " + myGraph.getDistanceTo("END"));
        System.out.println();
    }
}
