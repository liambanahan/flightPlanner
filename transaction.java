package flightPlanner;

public interface Transaction {
    public void doTransaction();
    public void undoTransaction();
    public String toString();
}
