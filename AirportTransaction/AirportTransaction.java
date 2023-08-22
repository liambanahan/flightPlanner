package flightPlanner.AirportTransaction;

import java.util.ArrayList;
import flightPlanner.TransactionProcessSys.Transaction;


public class AirportTransaction implements Transaction {
    private String code;
    ArrayList<String> tripStops;
    
    public AirportTransaction(String code, ArrayList<String> tripStops) {
        this.code = code;
        this.tripStops = tripStops;
    }

    public void doTransaction() {
        tripStops.add(code);
    }

    public void undoTransaction() {
        tripStops.remove(tripStops.size() - 1);
    }

    public String toString() {
        return "Adding Trip";
    }

}
