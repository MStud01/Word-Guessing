package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DSL;
import model.DeterminedString;

public class TestDSL {
    private DSL testDSLinstance;
    private DeterminedString testDS1;
    private DeterminedString testDS2;
    private DeterminedString testDS3;

    @BeforeEach
    void runBefore() {
        testDSLinstance = DSL.getInstance();
        testDS1 = new DeterminedString();
        testDS2 = new DeterminedString("abc", false);
        testDS3 = new DeterminedString("test", true);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("test");
    }

    @Test
    void testAddDSOnce() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("test");

        testDSLinstance.addDS(testDS3);

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getDS("test"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS3));
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSMultipleDifferent() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("test");
        testGetDSDoesNotExist("");

        testDSLinstance.addDS(testDS3);
        testDSLinstance.addDS(testDS1);

        assertEquals(2, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getDS("test"));
        TestDeterminedString.testEquals(testDS1, testDSLinstance.getDS(""));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS3));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS1));
        TestDeterminedString.testEquals(testDS1, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSMultipleSame() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("test");

        testDSLinstance.addDS(testDS3);
        testDSLinstance.addDS(testDS3);

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getDS("test"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS3));
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getRecentDS());

    }

    @Test
    void testAddDSWithStringOnlyOnce() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");

        testDSLinstance.addDS("abc");

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSWithStringOnlyMultipleDifferent() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");
        testGetDSDoesNotExist("");

        testDSLinstance.addDS("");
        testDSLinstance.addDS("abc");

        assertEquals(2, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        TestDeterminedString.testEquals(testDS1, testDSLinstance.getDS(""));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS1));
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSWithStringOnlyMultipleSame() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");

        testDSLinstance.addDS("abc");
        testDSLinstance.addDS("abc");
        testDSLinstance.addDS("abc");

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSWithStringAndStatusOnce() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");

        testDSLinstance.addDS("abc", true);

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSWithStringAndStatusMultipleDifferent() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");
        testGetDSDoesNotExist("test");

        testDSLinstance.addDS("abc", true);
        testDSLinstance.addDS("test", false);

        assertEquals(2, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getDS("test"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS3));
        TestDeterminedString.testEquals(testDS3, testDSLinstance.getRecentDS());
    }

    @Test
    void testAddDSWithStringAndStatusMultipleSame() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");

        testDSLinstance.addDS("abc", true);
        testDSLinstance.addDS("abc", false);
        testDSLinstance.addDS("abc", true);
        testDSLinstance.addDS("abc", false);

        assertEquals(1, testDSLinstance.getDeterminedStrings().size());
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getDS("abc"));
        assertTrue(testDSLinstance.getDeterminedStrings().contains(testDS2));
        TestDeterminedString.testEquals(testDS2, testDSLinstance.getRecentDS());
    }

    @Test
    void testGetRecentDSDoesNotExist() {
        assertNull(testDSLinstance.getRecentDS());
    }

    @Test
    void testGetDSDoesNotExist(String string) {
        assertNull(testDSLinstance.getDS(string));
    }

    // TODO: to be tested when implementing robustness
    // @Test
    // void testGetDSStatusDoesNotExist() {

    // }

    @Test
    void testGetDSStatusExistsFalse() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("abc");
        
        testDSLinstance.addDS(testDS2);

        assertFalse(testDSLinstance.getDSstatus("abc"));
    }

    @Test
    void testGetDSStatusExistsTrue() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        testGetDSDoesNotExist("test");
        
        testDSLinstance.addDS(testDS3);

        assertTrue(testDSLinstance.getDSstatus("test"));
    }
}
