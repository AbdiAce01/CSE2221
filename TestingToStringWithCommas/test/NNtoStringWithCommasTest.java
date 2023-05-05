import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas3.toStringWithCommas(n);
    }

    /*
     * Routine
     */
    @Test
    public void Test11902() {
        NaturalNumber n = new NaturalNumber2(11902);
        String nExpected = ("11,902");

        String nString = redirectToMethodUnderTest(n);

        assertEquals(nExpected, nString);
    }

    /*
     * Routine
     */
    @Test
    public void Test119027() {
        NaturalNumber n = new NaturalNumber2(119027);
        String nExpected = ("119,027");

        String nString = redirectToMethodUnderTest(n);

        assertEquals(nExpected, nString);
    }

    /*
     * Boundary
     */
    @Test
    public void Test1011() {
        NaturalNumber n = new NaturalNumber2(1011);
        String nExpected = ("1,011");

        String nString = redirectToMethodUnderTest(n);

        assertEquals(nExpected, nString);
    }

    /*
     * Challenging
     */
    @Test
    public void Test479360269() {
        NaturalNumber n = new NaturalNumber2(479360269);
        String nExpected = ("479,360,269");

        String nString = redirectToMethodUnderTest(n);

        assertEquals(nExpected, nString);
    }

    /*
     * Boundary
     */
    @Test
    public void Test392() {
        NaturalNumber n = new NaturalNumber2(392);
        String nExpected = ("392");

        String nString = redirectToMethodUnderTest(n);

        assertEquals(nExpected, nString);
    }

}
