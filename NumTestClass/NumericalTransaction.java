package flightPlanner.NumTestClass;

import flightPlanner.TransactionProcessSys.Transaction;

public class NumericalTransaction implements Transaction {
    private Numerical num;
    private int modifier;
    
    public NumericalTransaction(Numerical num, int modifier) {
        this.num = num;
        this.modifier = modifier;
    }

    public void doTransaction() {
        num.setNum(num.getNum() + modifier);
    }

    public void undoTransaction() {
        num.setNum(num.getNum() - modifier);
    }

    public String toString() {
        return "Add " + this.modifier;
    }
}