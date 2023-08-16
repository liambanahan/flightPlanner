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

    public void doTransaction() {
        if (this.hasTransactionToRedo()) {
            this.performingDo = true;
            Transaction transaction = this.transactions.get(mostRecentTransaction);
            transaction.doTransaction();
            this.mostRecentTransaction++;
            this.performingDo = false;
        }
    }

    public void addTransaction(Transaction transaction) {
        while(this.hasTransactionToRedo()) {
            this.popTopTransaction();
        } // this deletes old transactions for if we are branching
        this.numTransactions++;
        this.transactions.add(transaction);
        this.doTransaction();
    }

    public void undoTransaction() {
        if (this.hasTransactionToUndo()) {
            this.performingDo = true;
            Transaction transaction = this.transactions.get(mostRecentTransaction);
            transaction.undoTransaction();
            this.mostRecentTransaction--;
            this.performingUndo = false;
        }
    }

    public void clearAllTransactions() {
        this.transactions.clear();
        this.mostRecentTransaction = -1;
    }

    public String appendStack(String inp, ArrayList<Transaction> transactions) {
        for (Transaction i : transactions) {
            inp += i;
        }
        return inp;
    }

    @Override
    public String toString() {
        return "\nNumber of Transactions: " + this.numTransactions +
        "\nCurrent Stack Index: " + this.mostRecentTransaction + 
        "\nCurrent Transacton Stack:\n" + appendStack(null, transactions) + "\n\n";
    }
}