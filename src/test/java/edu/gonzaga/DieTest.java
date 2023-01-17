package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DieTest {
    @Test
    void testDefaultConstructor() {
        int expectedNumSides = 6;
        int expectedSideUp = 1;
        boolean expectedLocked = false;
        Die die = new Die();
        int actualNumSides = die.getNumSides();
        int actualSideUp = die.getSideUp();
        boolean actualLocked = die.isLocked();
        System.out.println("DieTest.testDefaultConstructor()");
        System.out.println("--------------------------------");
        System.out.println(
                "Expected numSides: "
                        + expectedNumSides
                        + " || Actual numSides: "
                        + actualNumSides);
        System.out.println(
                "Expected sideUp: " + expectedSideUp + " || Actual sideUp: " + actualSideUp);
        System.out.println(
                "Expected locked: " + expectedLocked + " || Actual locked: " + actualLocked);
        assertEquals(expectedNumSides, die.getNumSides());
        assertEquals(expectedSideUp, die.getSideUp());
        assertEquals(expectedLocked, die.isLocked());
        System.out.println("");
    }

    @Test
    void testParameterizedConstructor() {
        int expectedNumSides = 10;
        int expectedSideUp = 1;
        boolean expectedLocked = false;
        Die die = new Die(expectedNumSides);
        int actualNumSides = die.getNumSides();
        int actualSideUp = die.getSideUp();
        boolean actualLocked = die.isLocked();
        System.out.println("DieTest.testParameterizedConstructor()");
        System.out.println("--------------------------------");
        System.out.println(
                "Expected numSides: "
                        + expectedNumSides
                        + " || Actual numSides: "
                        + actualNumSides);
        System.out.println(
                "Expected sideUp: " + expectedSideUp + " || Actual sideUp: " + actualSideUp);
        System.out.println(
                "Expected locked: " + expectedLocked + " || Actual locked: " + actualLocked);
        assertEquals(expectedNumSides, die.getNumSides());
        assertEquals(expectedSideUp, die.getSideUp());
        assertEquals(expectedLocked, die.isLocked());
        System.out.println("");
    }

    @Test
    void testRoll() {
        System.out.println("DieTest.testRoll()");
        System.out.println("--------------------------------");
        System.out.println("Rolls the dice 100 times with a 6 sided die.");
        Die die = new Die();

        for (int i = 0; i < 100; i++) {
            die.roll();
            assertTrue(die.getNumSides() >= 1 && die.getNumSides() <= 6);
        }

        System.out.println("Rolls the dice 100 times with a 100 sided die.");
        die = new Die(100);

        for (int i = 0; i < 100; i++) {
            die.roll();
            assertTrue(die.getNumSides() >= 1 && die.getNumSides() <= 100);
        }

        System.out.println("");
    }

    @Test
    void testLockedStateRoll() {
        System.out.println("DieTest.testLockedStateRoll()");
        System.out.println("--------------------------------");
        Die die = new Die();
        assertFalse(die.isLocked());
        die.roll();
        assertFalse(die.isLocked());
        die.lock();
        assertTrue(die.isLocked());
        die.roll();
        assertTrue(die.isLocked());
        die.unlock();
        assertFalse(die.isLocked());
        die.roll();
        assertFalse(die.isLocked());
        System.out.println("");
    }

    @Test
    void testToString() {
        Die die = new Die();
        die.setDie(5);
        System.out.println("Expected Die String: 5 || Actual Die String: " + die.toString());
        assertEquals("5", die.toString());
    }
}
