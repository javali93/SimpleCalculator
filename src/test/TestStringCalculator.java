package test;

import main.StringCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chetana
 * @created on: 07, May 2021
 */
public class TestStringCalculator {

    @Test
    void test_emptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void test_singleNumberString() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    void test_singleNumberStringWithDefaultDelimeters() {
        assertEquals(2, StringCalculator.add("2\n"));
    }

    @Test
    void test_twoNumberString() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    void test_multipleNumbersString() {
        assertEquals(16, StringCalculator.add("1,2,13"));
    }

    @Test
    void test_forNegativeNumbers() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,-2,-13")
        );
        assertEquals("Negatives not allowed: -2,-13", e.getMessage());
    }


    @Test
    void test_multipleNumbersStringWithDefaultDelimeters() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    void test_addNumbersUpto1000() {
        assertEquals(1005, StringCalculator.add("2,3,1000"));
    }

    @Test
    void test_ignoreNumbersBiggerThan1000() {
        assertEquals(2, StringCalculator.add("2,1001"));
    }
}
