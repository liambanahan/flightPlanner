package flightPlanner;

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

    public Object getValue(String key) {
        return nodes.get(key);
    }

    public void addEdge(String node1, String node2, double weight) {
        String edgeId = this.getEdgeId(node1, node2);
        edges.put(edgeId, new WeightedEdge(node1, node2, weight));// anon constr
    }

    
}
