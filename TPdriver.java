package flightPlanner;

import java.util.Scanner;

public class TPdriver {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        TransactionProcessor tp = new TransactionProcessor();
        Numerical num = new Numerical();
        boolean quit = false;
        while (!quit) {
            System.out.println("\nCurrent Transaction Process: " + tp.toString());
            System.out.println("Num is " + num.getNum());
            System.out.println("\nEnter a selection:" +
            "\nA - Add a Transaction" + 
            "\nU) - Undo a Transaction" + 
            "\nR) - Redo a Transaction" +
            "\nC) - Clear All Transactions" +
            "\nN) - Reset Num and Transactions" + 
            "\nQ) - Quit\n--------------------\n");
            String inp = stdin.nextLine();
            switch (inp) {
                case "A":
                    System.out.println("Enter an amount to add");
                    int modifier = stdin.nextInt();
                    NumericalTransaction transaction = new NumericalTransaction(num, modifier);
                    tp.addTransaction(transaction);
                    break;
                case "U":
                    tp.undoTransaction();
                    break;
                case "R":
                    tp.doTransaction();
                    break;
                case "C":
                    tp.clearAllTransactions();
                    break;
                case "N":
                    tp.clearAllTransactions();
                    num.setNum(0);
                    break;
                case "Q":
                    quit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Output not recognized. Please try again.\n");
                    break;
            }
        }
        stdin.close();
    }    
}
