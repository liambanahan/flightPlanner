package flightPlanner;

import java.util.ArrayList;

public class TransactionProcessor {
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private int numTransactions; // counter
    private int mostRecentTransaction; //index of last transaction
    private boolean performingDo;
    private boolean performingUndo;

    //only default constructior required bc should not be constructed w parameters

    public TransactionProcessor(){
        this.numTransactions = 0;
        this.mostRecentTransaction = -1;
        this.performingDo = false;
        this.performingUndo = false;
    }

    private void popTopTransaction() {
        this.transactions.remove(this.mostRecentTransaction);
        this.numTransactions--;
    }
    
    public boolean isPerformingDo() {
        return this.performingDo;
    }

    public boolean isPerfomingUndo() {
        return this.performingUndo;
    }

    public boolean hasTransactionToRedo() {
        return this.mostRecentTransaction + 1 < this.numTransactions;
        // if if pointer is gteq num trans. at least one transaction has been undone
    }

    public boolean hasTransactionToUndo() {
        return this.mostRecentTransaction >= 0;
    } // if pointer geq 0, at least one transaction can be undone

    public int getSize() {
        return (int)this.transactions.size();
    }

    public int getRedoSize() {
        return (int)this.getSize() - this.mostRecentTransaction - 1;
    }

}