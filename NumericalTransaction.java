package flightPlanner;

public class NumericalTransaction implements Transaction {
    private Numerical num;
    private int modifier;
    
    public NumericalTransaction(Numerical num, int modifier) {
        this.num = num;
        this.modifier = modifier;
    }

    public void doTransaction() {
        //
    }

    public void undoTransaction() {
        //
    }

    public String toString() {
        return null;
    }
}