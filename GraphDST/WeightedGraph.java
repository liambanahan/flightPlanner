package flightPlanner.GraphDST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedGraph {
    private Map<String, Object> nodes = new HashMap<String, Object>();
    private Map<String, WeightedEdge> edges = new HashMap<String, WeightedEdge>();

    public WeightedGraph(){} //default constructor only,
    //should be initialized empty

    public ArrayList<String> getKeys(){
        //generate set of keys from hashmap
        Set<String> keySet = nodes.keySet();
        //generate arraylist w the keySet
        ArrayList<String> keys = new ArrayList<String>(keySet);//still need to make sure this works
        return keys;
    }

    public boolean nodeExists(String node) {
        return nodes.containsKey(node);
    }

    public String getEdgeId(String node1, String node2) {
        return node1 + "-" + node2;
    }

    public void addNode(String key, Object val) {
        nodes.put(key, val);
    }

    public Object getNodeData(String id) {
        return nodes.get(id);
    }

    public void addEdge(String node1, String node2, double weight) {
        String edgeId = this.getEdgeId(node1, node2);
        edges.put(edgeId, new WeightedEdge(node1, node2, weight));// anon constr
    }

    //iterate thru hashmap using for-each loop
    public void getNeighbors(ArrayList<String> neighbors, String target) {
        // storing all edge ids in a set
        Set<String> keySet = edges.keySet();
        // passing to arraylist for processing
        ArrayList<String> keyList = new ArrayList<String>(keySet);
        //iterate through the ids, parse, look for neighbors
        for (String id : keyList) {
            int dash = id.indexOf("-");
            String node1 = id.substring(0, dash);
            if (node1.compareTo(target) == 0) { //if first elem of id is target
                String neighbor = id.substring(dash + 1);
                neighbors.add(neighbor);
            }
        }
        
    }

    public boolean areNeighbors(String node1, String node2) {
        ArrayList<String> neighbors = new ArrayList<String>();
        getNeighbors(neighbors, node1); 
        return neighbors.contains(node2);
    }

    public double getNeighborsWeight(String node1, String node2) {
        if (areNeighbors(node1, node2)) {
            String edgeId = getEdgeId(node1, node2);
            return edges.get(edgeId).getWeight();
        }
        return 0.0;
    }

    public void findDijkstra(ArrayList<String> path, String node1, String node2) {
        System.out.println("Finding path from " + node1 + " to " + node2 + ".");
        //if either of the objects dont exist
        if (!nodeExists(node1) || (!nodeExists(node2))) {
            return; // return empty path maybe error msg
        }
        HashMap<String, String> visited = new HashMap<String, String>();
        visited.put(node1, node1);

        while (path.size() > 0) { 
            String last = path.get(path.size() - 1); //latest element of path
            ArrayList<String> neighbors = new ArrayList<String>();
            getNeighbors(neighbors, last);

            int closestIndex = -1;
            double closestDistance = 10000000L;

            for (int i = 0; i < neighbors.size(); i++) {
                String testNeighbor = neighbors.get(i);
                if (testNeighbor.compareTo(node2) == 0) {
                    path.add(testNeighbor);
                    return;
                } // if direct path, return that.

                if (!(visited.containsKey(testNeighbor))) { //if not visited
                    String id = getEdgeId(last, testNeighbor);
                    WeightedEdge edge = edges.get(id);
                    if (edge.getWeight() < closestDistance) {
                        closestIndex = i;
                        closestDistance = edge.getWeight();
                    }
                }
            }

            if (closestIndex >= 0) { //if a closer node has been found
                //add it
                String closestNode = neighbors.get(closestIndex);
                visited.put(closestNode, closestNode);
                path.add(closestNode);
            }

            else if (path.size() > 0) {
                path.remove(path.size() - 1);
            } //if no closer node found, backtrack
        }
    }
    // add bfs and maybe prims or dfs
}
