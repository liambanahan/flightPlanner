package flightPlanner;

//data type for testing TP
public class Numerical {
    private int num = 0;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void andMask(int mask) {
        num = num & mask;
    }

    public void orMask(int mask) {
        num = num | mask;
    }
}
