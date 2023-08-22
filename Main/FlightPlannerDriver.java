package flightPlanner.Main;

// personal libraries
import flightPlanner.AirportTransaction.AirportTransaction;
import flightPlanner.AirportTransaction.Airport;
import flightPlanner.GraphDST.WeightedGraph;
import flightPlanner.TransactionProcessSys.TransactionProcessor;

//java libraries
import java.util.ArrayList;
import java.util.Scanner;

public class FlightPlannerDriver {
    private static TransactionProcessor tps = new TransactionProcessor();
    private static WeightedGraph airportGraph = new WeightedGraph();
    private static ArrayList<String> tripStops = new ArrayList<String>();

    public static void displayAirports() {
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

    public static void displayCurrentTrip() {
        String text = "";
        text += "\nCurrent Trip Stops:\n";
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
        }
        text += "Total Trip Distance: " + tripDistance + "miles.\n\n";
        System.out.print(text);
    }
    
    public static void displayMenu() {
        String text = "\nEnter a Selection:\n";
        text += "S) Add a Stop to your Trip\n"
        + "U) Undo\n" 
        + "R) Redo\n"
        + "E) Empty Trip\n"
        + "Q) Quit\n";
        System.out.print(text);
    }

    public static boolean processUserInput() {
        Scanner stdin = new Scanner(System.in);
        String inp = stdin.nextLine();
        String id;
        
        switch (inp) {
                case "S":
                    System.out.println("\nEnter the Airport ID: ");
                    id = stdin.nextLine();
                    if (airportGraph.nodeExists(id)) {
                        ArrayList<String> neighbors = new ArrayList<String>();
                        airportGraph.getNeighbors(neighbors, id);
                        if (tripStops.size() > 0) {//if not first trip
                            String lastStop = tripStops.get(tripStops.size() - 1);
                            if (lastStop == id) {
                                System.out.println("Duplicate Stop Error - no Stop Added.\n");
                            }
                            else {
                                AirportTransaction transaction = new AirportTransaction(id, tripStops);
                                tps.addTransaction(transaction);
                            }
                        }
                        else { //if first trip, no need to check for duplicate
                            AirportTransaction transaction = new AirportTransaction(id, tripStops);
                            tps.addTransaction(transaction);
                        }
                    }
                    else {
                        System.out.println("Invalid Airport Code Error - no Stop Added");
                    }
                    break;
                case "U":
                    tps.undoTransaction();
                    break;
                case "R":
                    tps.doTransaction();
                    break;
                case "E":
                    tps.clearAllTransactions();
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    stdin.close();// prevent resource leak
                    return true;
                default:
                    System.out.println("Output not recognized. Please try again.\n");
                    break;
            }  //consider changing to enchanced switch statement
        stdin.close();
        return false;
    }

    public static void initEdge(String node1, String node2) {
        Airport a1 = (Airport)airportGraph.getNodeData(node1);
        Airport a2 = (Airport)airportGraph.getNodeData(node2);
        float distance = Airport.calculateDistance(a1, a2);
        airportGraph.addEdge(node1, node2, distance);
        airportGraph.addEdge(node2, node1, distance); //undirected edges
    }

    public static void initAllAirports() {
        //read from JSON... to be implemented tm
    }

    public static void main(String[] args) {
        initAllAirports();
        boolean quit = false;
        while (!quit) {
            displayAirports();
            displayCurrentTrip();
            displayMenu();
            quit = processUserInput();
        }
    }
}
