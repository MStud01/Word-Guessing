package test.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DeterminedString;


public class TestDeterminedString {
    private DeterminedString testDS1;
    private DeterminedString testDS2;
    private DeterminedString testDS3;

    @BeforeEach
    void runBefore() {
        testDS1 = new DeterminedString();
        testDS2 = new DeterminedString("abc", false);
        testDS3 = new DeterminedString("test", true);
    }

    @Test
    void testConstructor() {
        assertEquals("", testDS1.getString());
        assertTrue(testDS1.isNotWord());
        assertFalse(testDS1.isWord());

        assertEquals("abc", testDS2.getString());
        assertTrue(testDS2.isNotWord());
        assertFalse(testDS2.isWord());

        assertEquals("test", testDS3.getString());
        assertTrue(testDS3.isWord());
        assertFalse(testDS3.isNotWord());
    }

    @Test
    void testSetStatusUnchangedFalse() {
        assertTrue(testDS1.isNotWord());
        assertFalse(testDS1.isWord());

        testDS1.setStatus(false);
        
        assertTrue(testDS1.isNotWord());
        assertFalse(testDS1.isWord());
    }

    @Test
    void testSetStatusUnchangedTrue() {
        assertTrue(testDS3.isWord());
        assertFalse(testDS3.isNotWord());

        testDS3.setStatus(true);
        
        assertTrue(testDS3.isWord());
        assertFalse(testDS3.isNotWord());
    }

    @Test
    void testSetStatusChangedtoTrue() {
        assertTrue(testDS2.isNotWord());
        assertFalse(testDS2.isWord());

        testDS2.setStatus(true);
        
        assertTrue(testDS2.isWord());
        assertFalse(testDS2.isNotWord());
    }

    @Test
    void testSetStatusChangedtoFalse() {
        assertTrue(testDS3.isWord());
        assertFalse(testDS3.isNotWord());

        testDS3.setStatus(false);
        
        assertTrue(testDS3.isNotWord());
        assertFalse(testDS3.isWord());
    }

    @Test
    void testEquals() {
        DeterminedString testDS4 = new DeterminedString("abc", true);
        
        assertTrue(testDS4.equals(testDS2));

        assertEquals(testDS4.getString(), testDS2.getString());
        assertFalse(testDS4.isWord() == testDS2.isWord());
        assertFalse(testDS4.isNotWord() == testDS2.isNotWord());

        testSetStatusChangedtoTrue();

        assertEquals(testDS4.getString(), testDS2.getString());
        assertTrue(testDS4.isWord() == testDS2.isWord());
        assertTrue(testDS4.isNotWord() == testDS2.isNotWord());
        
        assertTrue(testDS4.equals(testDS2));

        // TODO: add full coverage for equals() method
    }
}
