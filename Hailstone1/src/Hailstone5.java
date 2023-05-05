import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone5() {
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
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.println("Enter a positive integer.");
        String s = in.nextLine();
        if (FormatChecker.canParseInt(s)) {
            int n = Integer.parseInt(s);
        }

        int n = Integer.parseInt(s);
        if (n > 1) {

        } else {
            out.println("Invalid.");
        }
        return n;

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

        getPositiveInteger(in, out);

        generateSeries(n, out);

        out.println("Ener 'y' to generate another series");
        String s = in.nextLine();
        while (s.equals("y")) {
            out.println();
            out.println("Enter a integer.");
            n = in.nextInteger();
            generateSeries(n, out);
            out.println("Ener 'y' to generate another series");
            s = in.nextLine();
        }
        out.println("Quit.");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
