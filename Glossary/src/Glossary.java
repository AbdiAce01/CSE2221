import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * @author Abdifatah Ashirow
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Generates the pairs of terms and definitions (Strings) in the given
     * {@code file} into the given {@code Map}. Also generates a Queue of all
     * the terms in no particular order into the given {@code Queue}.
     *
     * @param input
     *            the input
     * @param termAndDef
     *            the map that will contain the terms with their associated
     *            definitions
     * @return terms set of terms
     *
     *
     */
    public static Queue<String> getTermsAndDef(SimpleReader input,
            Map<String, String> termAndDef) {

        Queue<String> terms = new Queue1L<>();

        while (!input.atEOS()) {
            String term = input.nextLine();

            StringBuilder definition = new StringBuilder();
            boolean nextTerm = false;

            while (!nextTerm) {
                String line = input.nextLine();
                if (!line.equals("")) {
                    definition.append(line + " ");
                } else {
                    definition.deleteCharAt(definition.length() - 1);
                    nextTerm = true;
                }
            }
            /*
             * * Adds the term and its corresponding definition to {@code*
             * termAndDef}, as well as to {@code terms}.
             */
            termAndDef.add(term, definition.toString());
            terms.enqueue(term);
        }
        terms.sort(String.CASE_INSENSITIVE_ORDER);
        return terms;
    }

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param terms
     *            the Sequence of terms
     * @param feedfile
     *            the output stream
     * @updates {@code out.content}
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML opening tags]}
     * </pre>
     */
    private static void outputIndex(SimpleWriter feedfile,
            Queue<String> terms) {

        feedfile.println("<html>");
        feedfile.println("<head>");
        feedfile.println("<title>" + "Glossary Index" + "</title>");
        feedfile.println("</head>");
        feedfile.println("<body>");
        feedfile.println("<h2>Glossary</h2>");
        feedfile.println("<hr>");
        feedfile.println("<h3>Index</h3>");
        feedfile.println("<ul>");

        /*
         * loops through the list of terms generating each term page and
         * creating a link to the newly created page
         */

        for (int i = 0; terms.length() > i; i++) {
            feedfile.println("<li>");
            feedfile.println("<a href=\"" + terms.front() + ".html\">"
                    + terms.front() + "</a>");
            feedfile.println("</li>");
            terms.rotate(1);
        }

        /*
         * Generates footer html code
         */
        feedfile.println("</ul>");
        feedfile.println("</body>");
        feedfile.println("</html>");

    }

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param terms
     *            the Sequence of terms
     * @param feedFile
     *            the output stream
     * @param termsAndDef
     *            the map that will contain the terms with their associated
     *            definitions
     * @updates {@code out.content}
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML opening tags]}
     * </pre>
     */
    private static void generateTermPage(SimpleWriter feedFile, String terms,
            Map<String, String> termsAndDef) {

        feedFile.println("<html>");
        feedFile.println("<head>");
        feedFile.println("<title>" + terms + "</title>");
        feedFile.println("</head>");
        feedFile.println("<body>");
        /* Set font color */
        feedFile.println("<h2><b><i><font color=\"red\">" + terms
                + "</font></i></b></h2>");

        /*
         *          * Prints to {@code feedFile} the term and its corresponding
         * definition,          * formatted in html style.         
         */

        feedFile.println(
                "<blockquote>" + termsAndDef.value(terms) + "</blockquote>");
        feedFile.println("<hr>");
        feedFile.println("<p>Return to <a href=\"index.html\">index</a></p>");

        /* Closing Tags */
        feedFile.println("</body>");
        feedFile.println("</html>");

    }

    /**
     * Updates given {@code Map} values with html hyperlink tags included next
     * to terms appearing in definitions.
     *
     * @param terms
     *            the given {@code Map}
     * @param termsAndDef
     *            the {@code Map} to be used
     */
    public static void updateDefinition(Queue<String> terms,
            Map<String, String> termsAndDef) {

        Set<Character> separators = new Set1L<>();

        separators.add(',');
        separators.add(' ');
        separators.add('\t');
        separators.add(';');
        separators.add('.');

        /* Check for definition with terms */
        for (int i = 0; i < terms.length(); i++) {
            String term = terms.front();
            for (int q = 0; q < terms.length(); q++) {
                String curTerm = terms.front();
                String definition = termsAndDef.value(term);
                int position = 0;
                while (position < definition.length()) {
                    String curWord = nextWordOrSeparator(definition, position,
                            separators);

                    /*
                     * If a term is found, update definition to contain the html
                     * hyperlinked equivalent, linking to its respective page.
                     */

                    if (curWord.equals(curTerm)) {
                        definition = definition.substring(0, position)
                                + "<a href=\"" + curWord + ".html\">" + curWord
                                + "</a>"
                                + definition.substring(
                                        position + curWord.length(),
                                        definition.length());
                    }
                    position += curWord.length();
                }
                termsAndDef.replaceValue(term, definition);
                terms.rotate(1);
            }

            terms.rotate(1);
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires <pre>
     * {@code 0 <= position < |text|}
     * </pre>
     * @ensures <pre>
     * {@code nextWordOrSeparator =
     *   text[ position .. position + |nextWordOrSeparator| )  and
     * if elements(text[ position .. position + 1 )) intersection separators = {}
     * then
     *   elements(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
     *      intersection separators /= {})
     * else
     *   elements(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    elements(text[ position .. position + |nextWordOrSeparator| + 1 ))
     *      is not subset of separators)}
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int count = 0;
        char returnedPiece = 'a';
        String returned = "";
        if (separators.contains(text.charAt(position))) {
            while (count < text.substring(position, text.length()).length()) {
                returnedPiece = text.charAt(position + count);
                if (separators.contains(text.charAt(position + count))) {
                    returned = returned + returnedPiece;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        } else {
            while (count < text.substring(position, text.length()).length()) {
                returnedPiece = text.charAt(position + count);
                if (!separators.contains(text.charAt(position + count))) {
                    returned = returned + returnedPiece;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        }
        return returned;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Ask for input file
         */
        out.println("Insert name of input file: ");
        String inputFile = in.nextLine();
        /*
         * Ask for output folder name
         */
        out.println("Insert name of output folder: ");
        String Outputfile = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);
        Map<String, String> termsAndDef = new Map1L<>();

        Queue<String> terms = getTermsAndDef(input, termsAndDef);
        updateDefinition(terms, termsAndDef);

        /* Generates title page. */

        SimpleWriter output = new SimpleWriter1L(Outputfile + "\\index.html");
        outputIndex(output, terms);
        /* Generates term page for each term. */
        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            output = new SimpleWriter1L(Outputfile + "\\" + term + ".html");
            generateTermPage(output, term, termsAndDef);
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        input.close();
        output.close();
    }
}