import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WeightedGraph {
    HashMap<String, HashMap<String, Double>> graphMap;
    HashMap<String, String> parents = new HashMap<>();
    HashMap<String, Double> distance = new HashMap<>();

    HashMap<String, Boolean> processed = new HashMap<>();

    WeightedGraph(HashMap<String, HashMap<String, Double>> graphMap) {
        this.graphMap = graphMap;

        for (String key : graphMap.keySet()) {
            distance.put(key, Double.POSITIVE_INFINITY);
        }
        distance.put("START", 0.);

        updateVertexStats();
    }

    private void updateVertexStats() {
        String vertex = getShortestVertex();
        while (vertex != "") {
            System.out.println("Processing vertex '" + vertex + "'");

            HashMap<String, Double> distanceToChildren = graphMap.get(vertex);
            processed.put(vertex, true);

            for (String childVertex : distanceToChildren.keySet()) {
                double parentDistance = distance.get(vertex);
                double newChildDistance = parentDistance + distanceToChildren.get(childVertex);

                double currentChildDistance = distance.get(childVertex);

                if (newChildDistance < currentChildDistance) {
                    parents.put(childVertex, vertex);
                    distance.put(childVertex, newChildDistance);

                    System.out.println("\tUpdated vertex '" + childVertex + "' shortest distance:\n\t\tfrom: "
                            + currentChildDistance + "\n\t\tto: " + newChildDistance + "\n\t\this new parent now is: "
                            + vertex);
                }
            }
            vertex = getShortestVertex();
        }
    }

    public double getDistanceTo(String vertex) {
        return distance.get(vertex);
    }

    public String getShortestPathTo(String vertex) {
        ArrayList<String> path = new ArrayList<>();
        
        String parent = parents.get(vertex);
        path.add(vertex);
        while (parent != "START") {
            path.add(parent);
            parent = parents.get(parent);
        }
        path.add("START");

        Collections.reverse(path);
        return path.toString();
    }

    private String getShortestVertex() {
        String shortestVertex = "";
        double shortestValue = Double.POSITIVE_INFINITY;

        for (String key : distance.keySet()) {
            if (distance.get(key) > shortestValue || processed.containsKey(key))
                continue;

            shortestValue = distance.get(key);
            shortestVertex = key;
        }

        return shortestVertex;
    }
}