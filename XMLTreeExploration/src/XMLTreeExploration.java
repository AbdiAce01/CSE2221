import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {

        out.println("The middle child label is: " + xt.label());

        if (xt.isTag()) {
            out.println("The middle child is a Tag.");
            out.println("The middle child has " + xt.numberOfChildren()
                    + " child/children.");
        } else {
            out.println("The middle child is a Text.");
        }
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

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        /* out.print(xml.toString()); */
        xml.display();

        if (xml.isTag()) {
            out.println("Is Tag.");
            xml.numberOfChildren();
        } else {
            out.println("Is Text.");
        }
        out.println("The label of the root is: " + xml.label());

        XMLTree results = xml.child(0);

        XMLTree channel = xml.child(0).child(0);
        /* Find middle child node */
        int i = (channel.numberOfChildren() / 2) - 1;
        XMLTree xt = channel.child(i);
        printMiddleNode(xt, out);

        XMLTree title = xml.child(0).child(0).child(1);

        XMLTree titleText = xml.child(0).child(0).child(1).child(0);

        out.print(xml.child(0).child(0).child(1).child(0).label());
        /* OR titleText.label() */
        out.println();

        XMLTree astronomy = xml.child(0).child(0).child(10);

        if (astronomy.hasAttribute("sunset")) {
            out.println("True.");
        } else {
            out.println("False.");
        }

        if (astronomy.hasAttribute("midday")) {
            out.print("True.");
        } else {
            out.println("False.");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
