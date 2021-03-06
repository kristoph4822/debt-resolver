import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args){

        if (args.length == 1)
            throw new IllegalArgumentException("Wrong arguments.\n 1st argument is text file, 2nd is choice of algorithm (1 or 2).");

        DebtCalculator dc = null;

        // GUI version
        if (args.length == 0){
            JFrame jf = new JFrame("Debt Resolver");
            jf.setContentPane(new gui().mainPanel);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.pack();
            jf.setVisible(true);
        }

        // Command line version
        else {
            try {
                dc = new DebtCalculator(FileReader.readPersonsFromFile(args[0]));
            } catch (IOException e) {
                System.out.println("Unable to open given file.");
                System.exit(-1);
            }

            dc.resolveDebt(Integer.parseInt(args[1]));

            NumberFormat f = new DecimalFormat("#0.00");
            System.out.println("Sum: " + f.format(dc.sum));
            System.out.println("Amount each person (with ratio 1) has to pay: " + f.format(dc.x));
            System.out.println("----------------------------");

            System.out.println(dc.printDebts());
        }
    }


}
