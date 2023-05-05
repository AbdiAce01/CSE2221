import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Abdifatah Ashirow
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Enter a positive real number greater than zero.");
        String s = in.nextLine();
        char[] ch = new char[s.length()];
        int i = 0;
        while (i < s.length()) {
            ch[i] = s.charAt(i);
            while (Character.isLetter(ch[i])) {
                out.println("Enter a positive real number greater than zero.");
                s = in.nextLine();
            }
            i++;
        }
        FormatChecker.canParseDouble(s);
        double u = Double.parseDouble(s);
        while (u <= 0) {
            out.println("Enter a positive real number greater than zero.");
            s = in.nextLine();
            if (FormatChecker.canParseDouble(s)) {
                u = Double.parseDouble(s);
            }
        }
        return u;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */

    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String s = in.nextLine();
        char[] ch = new char[s.length()];
        int i = 0;
        while (i < s.length()) {
            ch[i] = s.charAt(i);
            while (Character.isLetter(ch[i])) {
                out.println("Enter a positive real number greater than 1.");
                s = in.nextLine();
            }
            i++;
        }
        FormatChecker.canParseDouble(s);
        double u = Double.parseDouble(s);

        while (u <= 1) {
            out.println(
                    "Invalid. Enter a positive real number greater than 1.");
            s = in.nextLine();
            if (FormatChecker.canParseDouble(s)) {
                u = Double.parseDouble(s);
            }
        }
        return u;

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

        /* Enter relative error less than 1% */
        double u = getPositiveDouble(in, out);
        out.println(
                "Enter 4 positive real number greater than 1 that have personal "
                        + "meaning to you.");
        out.println();

        out.println("Enter a positive real numer greater than 1 for w.");
        double w = getPositiveDoubleNotOne(in, out);

        out.println("Enter a positive real numer greater than 1 for x.");
        double x = getPositiveDoubleNotOne(in, out);

        out.println("Enter a positive real numer greater than 1 for y.");
        double y = getPositiveDoubleNotOne(in, out);

        out.println("Enter a positive real numer greater than 1 for z.");
        double z = getPositiveDoubleNotOne(in, out);

        double[] mylist = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };

        double e = 0.0, estimate = 0.0, a = 0.0, b = 0.0, c = 0.0, d = 0.0;

        int i = 0;
        while (i < mylist.length) {
            int j = 0;
            while (j < mylist.length) {
                int k = 0;
                while (k < mylist.length) {
                    int t = 0;
                    while (t < mylist.length) {
                        estimate = (Math.pow(w, mylist[i]))
                                * (Math.pow(x, mylist[j]))
                                * (Math.pow(y, mylist[k]))
                                * (Math.pow(z, mylist[t]));
                        e = (estimate - u) / u;
                        if (Math.abs(e) < 0.001) {
                            a = mylist[i];
                            b = mylist[j];
                            c = mylist[k];
                            d = mylist[t];
                        }
                        t++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        /* Print out approximations */
        out.println(a);
        out.println(b);
        out.println(c);
        out.println(d);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
