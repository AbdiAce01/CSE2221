import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber x = new NaturalNumber2(n);
        NaturalNumber ONE = new NaturalNumber2(1);
        NaturalNumber TWO = new NaturalNumber2(2);
        NaturalNumber THREE = new NaturalNumber2(3);
        NaturalNumber temp = new NaturalNumber2();

        NaturalNumber Counter = new NaturalNumber2(1);
        out.print(x + ", ");
        while (x.compareTo(ONE) == 1) {

            temp.copyFrom(x);
            int Reminder = temp.divideBy10();
            if (Reminder % 2 == 0) {
                x.divide(TWO);
            } else {
                x.multiply(THREE);
                x.increment();
            }
            out.print(x + ", ");
            Counter.increment();
        }
        out.println();
        out.print("Size: " + Counter);
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
        /*
         * Ask user to input an integer.
         */
        out.println("Enter a integer.");
        int x = in.nextInteger();
        while (x < 1) {
            out.println("Enter a integer greater than 1.");
            x = in.nextInteger();
        }
        NaturalNumber n = new NaturalNumber2(x);

        generateSeries(n, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
