package flightPlanner;

import java.util.HashMap;

public class TransactionProcessor {
    private HashMap<String, transaction> transactions = new HashMap<String, transaction>();
    private int numTransactions;
    private int mostRecentTransactions;
    private boolean performingDo;
    private boolean performingUndo;
    public static void main(String[] args) {
        // idt ill need this
    }

    private void popTopTransaction() {
        // implement later   
    }
}