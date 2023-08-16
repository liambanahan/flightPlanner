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

    public void andMask(int masking) {
        num = num & masking;
    }

    public void orMask(int masking) {
        num = num | masking;
    }
}
