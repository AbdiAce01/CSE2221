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
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n;
        int max = 0; /* Initialize a max variable */
        out.print(x + ", ");
        max = x; /* Make max the fist variable in the list */
        while (x > 1) {
            if (x % 2 == 0) {
                x = x / 2;
            } else {
                x = 3 * x + 1;
            }
            out.print(x + ", ");
            if (x > max) { /* Change max repeatedly when needed. */
                max = x;
            }
        }
        out.println();
        out.print("max: " + max);
        out.println();
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
        int n = in.nextInteger();
        while (n < 1) {
            out.println("Enter a integer greater than 1.");
            n = in.nextInteger();
        }

        generateSeries(n, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
