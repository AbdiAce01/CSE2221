import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /**
     * Routine test of combination.
     */
    @Test
    public void testCombination() {
        String str1 = "Hello W";
        String str2 = "World";
        int overlap = 1;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Hello World", result);
    }

    @Test
    public void testCombination2() {
        String str1 = "removethis";
        String str2 = "thisshouldberemoved";
        int overlap = 4;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("removethisshouldberemoved", result);
    }

    /**
     * Routine test of addToSetAvoidingSubstrings.
     */
    @Test
    public void testaddToSetAvoidingSubstrings() {
        Set<String> strset = new Set1L<String>();
        strset.add("Yo Dawg!");
        strset.add("wassup dude?");
        strset.add("Nm jc.");
        Set<String> strset2 = new Set1L<String>();
        strset2.add("Yo Dawg!");
        strset2.add("wassup dude?");
        strset2.add("Nm jc.");
        String str = "dude";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertEquals(strset, strset2);
    }

    @Test
    public void testaddToSetAvoidingSubstrings2() {
        Set<String> strset = new Set1L<String>();
        strset.add("Yo Dawg!");
        strset.add("wassup dude?");
        strset.add("Nm jc.");
        Set<String> strset2 = new Set1L<String>();
        strset2.add("Yo Dawg!");
        strset2.add("wassup dude?");
        strset2.add("Nm jc.");
        String str = "wassup";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertEquals(strset, strset2);
    }

    @Test
    public void printWithLineSeparatorsTest1() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "This ~ is ~ how ~ we ~ do ~ it";

        StringReassembly.printWithLineSeparators(test1, out);

        out.close();
    }

    @Test
    public void printWithLineSeparatorsTest2() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Hi, nice to meet you I'm ~ Abdi";

        StringReassembly.printWithLineSeparators(test1, out);

        out.close();
    }

}
