package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.ArithmeticUtilsRedefine;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void redefineMethodSum() throws InstantiationException, IllegalAccessException {
        var modifiedClass = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ArithmeticUtilsRedefine.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        var instance = (ArithmeticUtils) modifiedClass.newInstance();
        assertEquals(12, instance.sum(4, 3));
    }

}

