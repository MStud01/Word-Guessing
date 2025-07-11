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
        testEquals(testDS2, new DeterminedString("abc", true));
    }

    @Test
    static void testEquals(DeterminedString DS1, DeterminedString DS2) {
        assertTrue(DS1.equals(DS1));
        assertFalse(DS1.equals(null));
        assertFalse(DS1.equals(DS1.getString()));
        assertFalse(DS1.equals(new DeterminedString(DS1.getString() + "a", DS1.isWord())));

        assertTrue(DS1.equals(DS2));

        if (DS1 == DS2) {
            assertEquals(DS1.getString(), DS2.getString());
            assertTrue(DS1.isWord() == DS2.isWord());
            assertTrue(DS1.isNotWord() == DS2.isNotWord());

            DS1.setStatus(DS1.isNotWord());

            assertEquals(DS1.getString(), DS2.getString());
            assertTrue(DS1.isWord() == DS2.isWord());
            assertTrue(DS1.isNotWord() == DS2.isNotWord());
        } else if (DS1.isWord() == DS2.isWord()) {
            assertEquals(DS1.getString(), DS2.getString());
            assertTrue(DS1.isWord() == DS2.isWord());
            assertTrue(DS1.isNotWord() == DS2.isNotWord());

            DS1.setStatus(DS1.isNotWord());

            assertEquals(DS1.getString(), DS2.getString());
            assertFalse(DS1.isWord() == DS2.isWord());
            assertFalse(DS1.isNotWord() == DS2.isNotWord());
        } else {
            assertEquals(DS1.getString(), DS2.getString());
            assertFalse(DS1.isWord() == DS2.isWord());
            assertFalse(DS1.isNotWord() == DS2.isNotWord());

            DS1.setStatus(DS1.isNotWord());

            assertEquals(DS1.getString(), DS2.getString());
            assertTrue(DS1.isWord() == DS2.isWord());
            assertTrue(DS1.isNotWord() == DS2.isNotWord());
        }

        assertTrue(DS1.equals(DS2));
    }
}
