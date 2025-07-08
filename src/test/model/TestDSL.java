package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.DSL;

public class TestDSL {
    private DSL testDSLinstance;

    @BeforeEach
    void runBefore() {
        testDSLinstance = DSL.getInstance();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDSLinstance.getDeterminedStrings().size());
        testGetRecentDSDoesNotExist();
        assertNull(testDSLinstance.getDS("test"));
    }

    @Test
    void testAddDS() {
        // Once


        // Multiple different DS



        // Multiple same DS
    }

    @Test
    void testAddDSWithStringOnly() {
        // Once


        // Multiple different DS



        // Multiple same DS
    }

    @Test
    void testAddDSWithStringAndStatus() {
        // Once


        // Multiple different DS



        // Multiple same DS
    }

    @Test
    void testGetRecentDSDoesNotExist() {

    }

    @Test
    void testGetRecentDSOneExists() {
        
    }

    @Test
    void testGetRecentDSMultipleExists() {
        
    }

    @Test
    void testGetDSDoesNotExist() {

    }

    @Test
    void testGetDSExists() {
        
    }

    // TODO: to be tested when implementing robustness
    // @Test 
    // void testGetDSStatusDoesNotExist() {
        
    // }

    @Test
    void testGetDSStatusExists() {
        
    }

    @Test
    void testSingletonInstance() {
        assertEquals(testDSLinstance, DSL.getInstance());

        // changes occur to testDSLinstance

        assertEquals(testDSLinstance, DSL.getInstance());

        // more changes occur

        // repeat assertions
    }
}
