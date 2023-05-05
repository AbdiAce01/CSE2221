import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Abdifatah Ashirow
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Test Default Constructor. *BOUDNARY*
     */
    @Test
    public final void testDefaultConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test Constructor. *ROUTINE*
     */
    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("C", "S", "E", "2231");
        Map<String, String> sExpected = this.createFromArgsRef("C", "S", "E",
                "2231");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test Add Empty. *BOUNDARY*
     */

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> s2 = this.createFromArgsRef("green", "pink");
        /*
         * Call method under test
         */
        s.add("green", "pink");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s2);
    }

    /**
     * Test for add non-Empty. *ROUTINE*
     */
    @Test
    public final void testAddNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue");
        Map<String, String> s2 = this.createFromArgsRef("red", "blue", "green",
                "pink");
        /*
         * Call method under test
         */
        s.add("green", "pink");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s2);
    }

    /**
     * Test for add non-Empty. *Challenge* Add a similar Key with same value to
     * Map
     */
    @Test
    public final void testAddChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "pink");
        Map<String, String> s2 = this.createFromArgsRef("red", "blue", "green",
                "pink", "Green", "pink");
        /*
         * Call method under test
         */
        s.add("Green", "pink");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s2);
    }

    /**
     * Test Remove Empty. *BOUNDARY*
     */
    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "orange");
        Map<String, String> sExpected = this.createFromArgsTest();
        /*
         * Call method under test
         */
        s.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test Remove. *ROUTINE
     */
    @Test
    public final void testRemove() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "black");
        Map<String, String> sExpected = this.createFromArgsRef("green",
                "black");
        /*
         * Call method under test
         */
        s.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test Remove. *ROUTINE Remove a key from a long Map.
     */
    @Test
    public final void testRemoveChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "black", "brown", "yellow", "white", "orange", "violet", "pink",
                "silver", "gold", "crimson", "teal", "bronze", "aqua");
        Map<String, String> sExpected = this.createFromArgsRef("red", "blue",
                "green", "black", "brown", "yellow", "white", "orange",
                "silver", "gold", "crimson", "teal", "bronze", "aqua");
        /*
         * Call method under test
         */
        s.remove("violet");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for Remove-any. *Boundary*
     */
    @Test
    public final void testRemoveAnyEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("", "");
        Map<String, String> sExpected = this.createFromArgsTest("", "");
        /*
         * Call method under test
         */
        Map.Pair<String, String> temp = s.removeAny();
        String tempkey = temp.key();
        sExpected.remove(tempkey);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for Remove-any. *ROUTINE*
     */
    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "pink");
        Map<String, String> sExpected = this.createFromArgsTest("red", "blue",
                "green", "pink");
        /*
         * Call method under test
         */
        Map.Pair<String, String> temp = s.removeAny();
        String tempkey = temp.key();
        sExpected.remove(tempkey);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for Remove-any. *Challenge* Remove-any on a large map
     */
    @Test
    public final void testRemoveAnyChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "black", "brown", "yellow", "white", "orange", "violet", "pink",
                "silver", "gold", "crimson", "teal", "bronze", "aqua");
        Map<String, String> sExpected = this.createFromArgsTest("red", "blue",
                "green", "black", "brown", "yellow", "white", "orange",
                "violet", "pink", "silver", "gold", "crimson", "teal", "bronze",
                "aqua");
        /*
         * Call method under test
         */
        Map.Pair<String, String> temp = s.removeAny();
        String tempkey = temp.key();
        sExpected.remove(tempkey);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for Value. *Boundary
     */
    @Test
    public final void testValueEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("", "");
        /*
         * Call method under test
         */
        String x = s.value("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("", x);
    }

    /**
     * Test for Value. *ROUTINE
     */
    @Test
    public final void testValue() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("green", "blue", "red",
                "pink");
        /*
         * Call method under test
         */
        String x = s.value("green");
        String x2 = s.value("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("blue", x);
        assertEquals("pink", x2);
    }

    /**
     * Test for Value. *Challenge Using method Integer.toString() as a value
     */
    @Test
    public final void testValueChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("green",
                Integer.toString(0), "red", "red");
        /*
         * Call method under test
         */
        String x = s.value("green");
        String x2 = s.value("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("0", x);
        assertEquals("red", x2);
    }

    /**
     * Test has Key *Boundary.
     */
    @Test
    public final void testHasKeyEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        /*
         * Call method under test
         */
        boolean x = s.hasKey("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, x);
    }

    /**
     * Test has Key *ROUTINE.
     */
    @Test
    public final void testHasKeyTrue() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("green", "blue", "red",
                "pink");
        /*
         * Call method under test
         */
        boolean x = s.hasKey("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, x);
    }

    /**
     * Test has key. *ROUTINE*
     */
    @Test
    public final void testHasKeyFalse() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("green", "blue", "red",
                "pink");
        /*
         * Call method under test
         */
        boolean x = s.hasKey("purple");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, x);
    }

    /**
     * Test has key. *Challenge* Using Integer.toString() as a key
     */
    @Test
    public final void testHasKeyChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest(Integer.toString(0),
                "blue", "red", "pink");
        /*
         * Call method under test
         */
        boolean x = s.hasKey("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, x);
    }

    /**
     * Test Size. *ROUTINE
     */
    @Test
    public final void testSizenonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("blue", "green");
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, i);
    }

    /**
     * Test Size Empty. *BOUNDARY*
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
    }

    /**
     * Test Size Empty. *Challenge* Test on a large map
     */
    @Test
    public final void testSizeEmptyChallenge() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest("red", "blue", "green",
                "black", "brown", "yellow", "white", "orange", "violet", "pink",
                "silver", "gold", "crimson", "teal", "bronze", "aqua");
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(8, i);
    }

}
