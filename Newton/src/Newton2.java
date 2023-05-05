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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double s = x;
        final double error = 0.0001;
        if (s != 0) {
            while ((Math.abs(s * s - x) / x) > error * error) {
                s = (s + x / s) / 2;
            }
        } else {
            s = 0;
        }
        return s;
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

        String mychar;

        /*
         * Do-while loop to ask repeatedly to calculate new square root.
         */
        do {
            out.println(
                    "Enter 'y' to calculate square root or any other key to exit");
            mychar = in.nextLine();

            if (mychar.equals("y")) {
                out.println("Enter a positive number greater than zero: ");
                double num = in.nextDouble();
                if (num >= 0) {
                    /* Method call */
                    out.println("Square-root is: " + sqrt(num));
                } else {
                    out.println("Invalid number.");
                }
            }
        } while (mychar.equals("y"));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
