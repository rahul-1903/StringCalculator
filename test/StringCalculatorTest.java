import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    public void addNumberInEmptyString() {
        assertEquals(0, StringCalculator.Add(""));
    }

    @Test
    public void addIfStringContainsOneNumber() {
        assertEquals(1, StringCalculator.Add("1"));
    }

    @Test void addStringContainingTwoNumbers() {
        assertEquals(3, StringCalculator.Add("1,2"));
    }

    @Test void sumIfStringContainsMoreThanTwoNumbers() {
        assertEquals(6, StringCalculator.Add("1,2,3"));
    }

    @Test
    public void handleNewLinesInInput() {
        assertEquals(6, StringCalculator.Add("1\n2,3"));
    }

    @Test
    public void handleCustomDelimiter() {
        assertEquals(3, StringCalculator.Add("//;\n1;2"));
    }

    @Test
    public void handleSpecialDelimiter() {
        assertEquals(3, StringCalculator.Add("//.\n1.2"));
    }

    @Test
    public void shouldRaiseExceptionOnNegativeNumbers() {
        try {
            StringCalculator.Add("-1,2,3");
            fail("RuntimeException Excepted");
        } catch(RuntimeException e) {
            // exception received
        }
    }

    @Test
    public void exceptionShouldContainNegativeNumber() {
        try {
            StringCalculator.Add("-1,2,3");
            fail("RuntimeException Excepted");
        } catch(RuntimeException e) {
            assertEquals("negatives not allowed: -1", e.getMessage());
        }
    }

    @Test
    public void exceptionShouldContainAllNegativeNumbers() {
        try {
            StringCalculator.Add("-1,-2,-3");
            fail("RuntimeException Excepted");
        } catch(RuntimeException e) {
            assertEquals("negatives not allowed: -1,-2,-3", e.getMessage());
        }
    }

    @Test
    public void IgnoreNumbersGreaterThanThousand() {
        assertEquals(2, StringCalculator.Add("1001,2"));
    }

    @Test
    public void handleDelimiterOfGreaterLength() {
        assertEquals(6, StringCalculator.Add("//[***]\n1***2***3"));
    }

    @Test
    public void handleMultipleDelimiters() {
        assertEquals(6, StringCalculator.Add("//[*][%]\n1*2%3"));
    }

    @Test void handleMultipleDelimiterWithGreaterLength() {
        assertEquals(6, StringCalculator.Add("//[**][%%]\n1**2%%3"));
    }
}