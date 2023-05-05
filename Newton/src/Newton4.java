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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param e
     *            epsilon value for relative error
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {
        double s = x;
        if (s != 0) {
            while ((Math.abs(s * s - x) / x) > e * e) {
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

        out.println(
                "Enter a positive number greater than zero or negitive to quit: ");
        double num = in.nextDouble();
        while (num >= 0) {
            out.println("Enter epsilion value for relative error.");
            /* Enter epsilon value to calculate square-root. */
            double e = in.nextDouble();
            /* Method call */
            out.println("Square-root is: " + sqrt(num, e));
            out.println(
                    "Enter a positive number greater than zero or negitive to quit: ");
            num = in.nextDouble();
        }
        out.println("Quit.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
