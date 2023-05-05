import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Abdifatah Ashirow
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // TODO - fill in body

        NaturalNumber total = new NaturalNumber2(0);

        /* Searches for each arithmetic operation */
        if (exp.label().equals("times")) {
            NaturalNumber first = evaluate(exp.child(0));
            first.multiply(evaluate(exp.child(1)));
            total.copyFrom(first);
        }
        if (exp.label().equals("plus")) {
            NaturalNumber first = evaluate(exp.child(0));
            first.add(evaluate(exp.child(1)));
            total.copyFrom(first);
        }
        if (exp.label().equals("divide")) {
            NaturalNumber first = evaluate(exp.child(0));
            NaturalNumber second = evaluate(exp.child(1));
            if (second.canConvertToInt() && second.toInt() == 0) {
                /* Divide by zero error */
                components.utilities.Reporter
                        .fatalErrorToConsole("Error: Dividing by zero");
            }
            first.divide(second);
            total.copyFrom(first);
        }
        if (exp.label().equals("minus")) {
            NaturalNumber first = evaluate(exp.child(0));
            NaturalNumber second = evaluate(exp.child(1));
            if (second.compareTo(first) > 0) {
                /* NaturalNumber cannot be negative */
                components.utilities.Reporter.fatalErrorToConsole(
                        "Creating a negative natural number");
            }
            first.subtract(second);
            total.copyFrom(first);
        }
        if (exp.label().equals("number")) {
            return new NaturalNumber2(exp.attributeValue("value"));
        }
        return total;

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}