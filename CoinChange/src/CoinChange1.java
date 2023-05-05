import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Abdifatah Ashirow
 *
 */
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Enter the number of cents.");
        int n = in.nextInteger();
        int quarter = 0;
        int dimes = 0;
        int nickles = 0;
        int penny = 0;
        int halfdollar = 0;
        int dollar = 0;

        if (n >= 100) {
            dollar = n / 100;
            n = n - dollar * 100;
        }
        if (n >= 50) {
            halfdollar = n / 50;
            n = n - halfdollar * 50;
        }
        if (n >= 25) {
            quarter = n / 25;
            n = n - quarter * 25;
        }
        if (n >= 10) {
            dimes = n / 10;
            n = n - dimes * 10;
        }
        if (n >= 5) {
            nickles = n / 5;
            n = n - nickles * 5;
        }
        if (n > 0) {
            penny = n / 1;
            n = n - 1;
        }
        out.println("Enter vaild number of cents.");

        out.print(dollar + "  " + halfdollar + "  " + quarter + "  " + dimes
                + "  " + nickles + "  " + penny);

        in.close();
        out.close();
    }

}
