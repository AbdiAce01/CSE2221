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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {

        if (s.length() >= 8) {
            out.println("Vaild length.");
            containsUpperCaseLetter(s);
            containsLowerCaseLetter(s);
            containsADigit(s);
            containsASpecialCharacter(s);
        } else {
            out.println("Invalid length.");
        }
        out.println();

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean b = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean b = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Checks if the given String contains a digit letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit letter, false otherwise
     */
    private static boolean containsADigit(String s) {
        boolean b = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Checks if the given String contains a special character.
     *
     * @param s
     *            the String to check
     * @return true if s contains a special character, false otherwise
     */
    private static boolean containsASpecialCharacter(String s) {
        boolean b = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '$' || s.charAt(i) == '!' || s.charAt(i) == '@'
                    || s.charAt(i) == '%' || s.charAt(i) == '&'
                    || s.charAt(i) == '*') {
                b = true;
            }
        }
        return b;
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

        out.println("Enter a password at least 8 characters long.");
        String s = in.nextLine();
        checkPassword(s, out);
        while (s.length() < 8) {
            out.println("Enter a password at least 8 characters long.");
            s = in.nextLine();
            checkPassword(s, out);
        }

        containsUpperCaseLetter(s);
        int uppercase = 0;
        int lowercase = 0;
        int digit = 0;
        int special = 0;

        if (containsUpperCaseLetter(s)) {
            out.println("uppercase.");
            uppercase++;
        }
        containsLowerCaseLetter(s);
        if (containsLowerCaseLetter(s)) {
            out.println("lowercase.");
            lowercase++;
        }
        containsADigit(s);
        if (containsADigit(s)) {
            out.println("digit.");
            digit++;
        }
        containsASpecialCharacter(s);
        if (containsASpecialCharacter(s)) {
            out.println("Special.");
            special++;
        }

        while (digit + lowercase + uppercase + special < 3) {
            out.println(
                    "Password does not meet requirements. Enter a new password");
            s = in.nextLine();
            checkPassword(s, out);

            if (containsUpperCaseLetter(s)) {
                out.println("uppercase.");
                uppercase++;
            }
            containsLowerCaseLetter(s);
            if (containsLowerCaseLetter(s)) {
                out.println("lowercase.");
                lowercase++;
            }
            containsADigit(s);
            if (containsADigit(s)) {
                out.println("digit.");
                digit++;
            }
            containsASpecialCharacter(s);
            if (containsASpecialCharacter(s)) {
                out.println("Special.");
                special++;
            }
            special = 0;
            digit = 0;
            uppercase = 0;
            lowercase = 0;
        }
        if (digit + lowercase + uppercase + special >= 3) {
            out.println("Valid password.");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
