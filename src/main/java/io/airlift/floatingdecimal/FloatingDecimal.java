package io.airlift.floatingdecimal;

public class FloatingDecimal
{
    public static boolean isPatchInstalled()
    {
        try {
            sun.misc.FloatingDecimal.class.getDeclaredField("MARKER");
        }
        catch (NoSuchFieldException e) {
            return false;
        }

        return true;
    }
}
