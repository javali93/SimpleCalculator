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
    void test_nullString() {
        assertEquals(0, StringCalculator.add(null));
    }

    @Test
    void test_singleNumberString() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    void test_singleNumberStringWithDefaultDelimiters() {
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
    void test_multipleNumbersStringWithDefaultDelimiters() {
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

    @Test
    public void test_throwIllegalArgumentExceptionWhenStringHasSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,2, 13"));
    }

    @Test
    public void test_throwIllegalArgumentExceptionWhenStringHasExtraComma() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,,2,13"));
    }

    @Test
    public void test_throwIllegalArgumentExceptionWhenStringHasAlphabet() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,a,13"));
    }

    @Test
    public void test_throwIllegalArgumentExceptionWhenStringHasASpecialCharacter() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,*,13"));
    }

    @Test
    void test_stringWithSingleCharCustomDelimiter() {
        assertEquals(6, StringCalculator.add("//;\n1;2;3"));
    }

    @Test
    void test_stringWithMultipleRepeatCharCustomDelimiter() {
        assertEquals(6, StringCalculator.add("//[%%%]\n1%%%2%%%3"));
    }

    @Test
    void test_stringWithMultipleNonRepeatCharCustomDelimiter() {
        assertEquals(10, StringCalculator.add("//[abc]\n1abc2abc3abc4"));
    }

    @Test
    void test_stringWithMultipleCharStarDelimiter() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    void test_stringWithCustomDelimiterAndNegativeNumber() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("//[%*%]\n1%*%-2%*%-3")
        );
        assertEquals("Negatives not allowed: -2,-3", e.getMessage());
    }

    @Test
    public void test_stringHasASpecialCharacterAndCustomDelimiter() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("//[%*%]1%*%@%*%3"));
    }

    @Test
    void test_stringWithCustomAndDefaultDelimiters() {
        assertEquals(15, StringCalculator.add("//[%%%]\n1%%%2,3%%%4,5"));
    }

    @Test
    void test_stringWithCustomDelimiterWithOutSquareBrackets() {
        assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("//;;\n1;;2;;3"));
    }

    @Test
    void test_stringWithMultipleCustomDelimiterSingleChar() {
        assertEquals(6, StringCalculator.add("//[%][*]\n1*2%3"));
    }

    @Test
    void test_stringWithMultipleCustomDelimiterMultiChar() {
        assertEquals(6, StringCalculator.add("//[%%%][abc]\n1%%%2abc3"));
    }
}
