package edu.hw11;

import edu.hw11.task3.Task3;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    public void creatingMethodFib() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Object clazz = Task3.returnClass();
        long result = (long) clazz.getClass().getMethod("fib", int.class).invoke(clazz, 10);
        assertEquals(55, result);
    }
}
