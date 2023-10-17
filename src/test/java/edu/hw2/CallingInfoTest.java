package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallingInfoTest {
    @Test
    public void testCallingInfo() {
        CallingInfo callingInfo = Task4.callingInfo();

        assertEquals("edu.hw2.CallingInfoTest", callingInfo.className());
        assertEquals("testCallingInfo", callingInfo.methodName());
    }
}
