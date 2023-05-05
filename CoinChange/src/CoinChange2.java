import java.util.Arrays;

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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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
        int coins[] = new int[6]; // Coin count

        int D[] = new int[6];
        /*
         * Coin denominations
         *
         */
        D[0] = 100; //Dollar
        D[1] = 50; //HalfDollar
        D[2] = 25; // Quarter
        D[3] = 10; //Dimes
        D[4] = 5; //Nickles
        D[5] = 1; //Pennies

        while (n > 0) {
            int i = 0;
            if (n >= D[i]) {
                coins[i] = n / D[i]; // Assigns number of coins
                n = n - coins[i] * D[i]; // Assigns cents left
                i++;
            } else {
                i++;
            }
        }

        out.println("Change(Greatest coin to Least)");
        out.print(coins);

        out.println(Arrays.toString(coins));

        in.close();
        out.close();
    }

}
