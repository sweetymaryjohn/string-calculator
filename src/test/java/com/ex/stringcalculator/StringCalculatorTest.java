package com.ex.stringcalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    /**
     * iii.	An empty string should return a sum of 0.
     */
    @Test
    public final void testAddWithEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    /**
     * iv.	numbers can include 0, 1, or 2 integers (e.g. "", "1", "1,2").
     */
    @Test
    public final void testAddWithOneNumber() {
        assertEquals(1, StringCalculator.add("1"));
    }

    /**
     * iv.	numbers can include 0, 1, or 2 integers (e.g. "", "1", "1,2").
     */
    @Test
    public final void testAddWithTwoNumbers() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    /**
     * v.	Add returns the sum of the integers provided in the string numbers.
     */
    @Test
    public final void testAddWithValidInput() {
        assertEquals(7, StringCalculator.add("3,4"));
    }

    /**
     * 2.	Allow the Add method to handle an unknown number of numbers (in the string).
     */
    @Test
    public final void testAddWithUnknownNumberOfNumbers () {
        assertEquals(2+3+6+11+23+49, StringCalculator.add("2,3,6,11,23,49"));
    }

    /**
     * 3.	Allow the Add method to handle new lines between numbers (as well as commas):
     *
     * i.	Example: "1\n2,3" returns 6.
     */
    @Test
    public final void testAddWithNewLineAndComma() {
        assertEquals(6, StringCalculator.add("1\n2,3" ));
    }

    /**
     * 4.	Allow the Add method to handle a different delimiter:
     *
     * ii.	Example: "//;\n1;2" returns 3 (the delimiter is ";").
     */
    @Test
    public final void testAddWithDifferentDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    /**
     * 5.	Calling Add with a negative number will throw an exception
     * "Negatives not allowed: " and then listing all negative numbers
     * that were in the list of numbers.
     *
     * ii.	Example: "-1,2" throws "Negatives not allowed: -1".
     */
    @Test
    public void testAddWithNegativeNumbers() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            StringCalculator.add("-1,2");
        });

        assertNotNull(exception);
        assertEquals("Negatives not allowed: -1", exception.getMessage());
    }

    /**
     * 5.	Calling Add with a negative number will throw an exception
     * "Negatives not allowed: " and then listing all negative numbers
     * that were in the list of numbers.
     *
     * ii.	Example: "2,-4,3,-5" throws "Negatives not allowed: -4,-5".
     */
    @Test
    public void testAddWithMoreNegativeNumbers() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            StringCalculator.add("2,-4,3,-5");
        });

        assertNotNull(exception);
        assertEquals("Negatives not allowed: -4, -5", exception.getMessage());
    }

    /**
     * 6.	Numbers greater than 1000 should be ignored.
     * i.	Example: "1001,2" returns 2.
     */
    @Test
    public final void testAddWithNumbersGreaterThan1000() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }

    /**
     * 7.	Delimiters can be any length, using this syntax: "//[|||]\n1|||2|||3" returns 6.
     *
     */
    @Test
    public final void testAddWithDelimitersOfAnyLength() {
        assertEquals(6, StringCalculator.add("//[|||]\n1|||2|||3"));
    }

    /**
     * 8.	Allow multiple delimiters, using this syntax: "//[|][%]\n1|2%3" returns 6.
     *
     */
    @Test
    public final void testAddWithMultipleDelimiters() {
        assertEquals(6, StringCalculator.add("//[|][%]\n1|2%3"));
    }
}