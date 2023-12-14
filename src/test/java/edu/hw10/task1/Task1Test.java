package edu.hw10.task1;

import edu.hw10.task1.classes.MyClass;
import edu.hw10.task1.classes.MyRecord;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    RandomObjectGenerator rog = new RandomObjectGenerator();

    @RepeatedTest(10)
    void testRecordClassWithAllTypesOfArgs() {
        var myRecordInstance = rog.nextObject(MyRecord.class);
        assertInstanceOf(MyRecord.class, myRecordInstance);
        assertEquals(11, myRecordInstance.intValue());
        assertTrue(myRecordInstance.doubleValue() < 1);
        assertNotNull(myRecordInstance.string());
    }

    @RepeatedTest(10)
    void testPOJOClassViaConstructor() {
        var myClassInstance = rog.nextObject(MyClass.class);
        assertInstanceOf(MyClass.class, myClassInstance);
        assertTrue(myClassInstance.getIntValue() >= 10 && myClassInstance.getIntValue() < 13);
        assertTrue(myClassInstance.getDoubleValue() < 1000);
    }

    @RepeatedTest(10)
    void testPOJOClassViaFactoryMethod() {
        var myClassInstance = rog.nextObject(MyClass.class, "create");
        assertInstanceOf(MyClass.class, myClassInstance);
        assertTrue(myClassInstance.getIntValue() >= 10 && myClassInstance.getIntValue() < 13);
        assertTrue(myClassInstance.getDoubleValue() < 1000);
    }
}
