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
}