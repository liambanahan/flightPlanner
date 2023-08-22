package flightPlanner.Main;

// personal libraries
import flightPlanner.AirportTransaction.AirportTransaction;
import flightPlanner.AirportTransaction.Airport;
import flightPlanner.GraphDST.WeightedGraph;
import flightPlanner.TransactionProcessSys.TransactionProcessor;

//java libraries
import java.util.ArrayList;

public class FlightPlannerDriver {
    private TransactionProcessor tps = new TransactionProcessor();
    private WeightedGraph airportGraph = new WeightedGraph();
    private ArrayList<String> tripStops = new ArrayList<String>();

    public void displayAirports() {
        System.out.println("\n\nAirports you can travel to and from:\n");
        ArrayList<String> codes = airportGraph.getKeys();
        String text = "";
        for (int i = 0; i < codes.size(); i++) {
            if (i % 10 == 0) {
                text += "\t";
            }
            text += codes.get(i);
            if (i < codes.size() - 1) {
                text += ",";
            }
            if ((i % 10) == 9) {
                text += "\n";
            }  
        }
        System.out.print("\n" + text); //print or printf??
    }

    public void displayCurrentTrip() {
        String text = "";
        text += "Current Trip Stops:\n";
        for (int i = 0; i < tripStops.size() - 1; i++) {
            text += "\t" + i + ". " + tripStops.get(i) + "\n";
        }
        text += "\nCurrent Trip Legs: \n";
        int legNum = 1;
        float tripDistance = 0.0f;
        float legDistance = 0.0f;
        for (int i = 0; i < tripStops.size() - 1; i++) {
            String lastStop;
            String nextStop;

            if (legNum < tripStops.size()) {
                text += "\t" + (i + 1) + ". ";
                lastStop = tripStops.get(legNum - 1);
                nextStop = tripStops.get(legNum);

                // add option for user input for other pathing algorithms here
                ArrayList<String> route = new ArrayList<>();
                airportGraph.findDijkstra(route, lastStop, nextStop);

                if (route.size() < 2) {
                    text += "No Route Found from " + lastStop + " to " + nextStop + ".\n";
                }
                else {
                    for (int j = 0; j < route.size() - 1; j++) {
                        Airport a1 = (Airport)(airportGraph.getNodeData(route.get(i)));
                        Airport a2 = (Airport)(airportGraph.getNodeData(route.get(i + 1)));
                        float distance = Airport.calculateDistance(a1, a2);
                        legDistance += distance;
                        if (i == 0) {   // if first trip
                            text += a1.getId();
                        }
                        text += "-" + a2.getId();
                    }
                    text += " (Leg Distance: " + legDistance + " miles.\n";
                }
                legNum += 1;
                tripDistance += legDistance;
            }
            text += "Total Trip Distance: " + tripDistance + "miles.\n\n";
            System.out.print(text);
        }
    }
}
