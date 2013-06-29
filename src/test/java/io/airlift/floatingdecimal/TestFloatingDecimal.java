package io.airlift.floatingdecimal;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestFloatingDecimal
{
    @BeforeSuite
    public void verifyPatchInstalled()
    {
        assertTrue(FloatingDecimal.isPatchInstalled(), "FloatingDecimal patch is not installed. Can't run tests.");
    }

    @Test
    public void testWellKnownValues()
            throws Exception
    {
        assertParseDouble(Double.MIN_VALUE);
        assertParseDouble(Double.MAX_VALUE);
        assertParseDouble(-Double.MIN_VALUE);
        assertParseDouble(-Double.MAX_VALUE);

        assertParseDouble(Double.NaN);
        assertParseDouble(Double.NEGATIVE_INFINITY);
        assertParseDouble(Double.POSITIVE_INFINITY);

        assertParseDouble(0.0);
        assertParseDouble(1.0);
        assertParseDouble(-1.0);
    }

    @Test
    public void testAllExponents()
            throws Exception
    {
        for (int i = Double.MIN_EXPONENT; i <= Double.MAX_EXPONENT; i++) {
            assertParseDouble(Math.pow(2, i));
        }
    }

    private static void assertParseDouble(double value)
    {
        assertEquals(Double.parseDouble(Double.toString(value)), value, "Error parsing: " + value);
    }
}
