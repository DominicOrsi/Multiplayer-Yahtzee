package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    void testDefaultConstructor() {
        Hand newHand = new Hand();
        System.out.println("Expected hand size: 6 || Actual hand size: " + newHand.getNumDice());
        assertEquals(5, newHand.getNumDice());
        for (int i = 0; i < newHand.getNumDice(); ++i)
            assertEquals(1, newHand.getDie(i).getSideUp());
        System.out.println("All expected dice exist.");
    }

    @Test
    void testParameterizedConstructor() {
        Hand newHand = new Hand(10);
        System.out.println("Expected hand size: 10 || Actual hand size: " + newHand.getNumDice());
        assertEquals(10, newHand.getNumDice());
        for (int i = 0; i < newHand.getNumDice(); ++i)
            assertEquals(1, newHand.getDie(i).getSideUp());
        System.out.println("All expected dice exist.");
    }

    @Test
    void testRolling() {
        Hand newHand = new Hand();
        int handSize = newHand.getNumDice();
        System.out.println("Rolling 6 dice now...");
        newHand.roll();
        for (int i = 0; i < handSize; ++i) {
            System.out.println("Die #" + i + ": " + newHand.getDie(i).getSideUp());
            assertTrue(newHand.getDie(i).getSideUp() >= 1 && newHand.getDie(i).getSideUp() <= 6);
        }
    }

    @Test
    void testHandReset() {
        Hand newHand = new Hand();
        int handSize = newHand.getNumDice();
        System.out.println("Setting all dice in hand to 6...");
        for (int i = 0; i < handSize; ++i) {
            newHand.getDie(i).setDie(6);
            assertEquals(6, newHand.getDie(i).getSideUp());
        }
        System.out.println("Resetting hand...");
        newHand.resetHand();
        for (int i = 0; i < newHand.getNumDice(); ++i)
            assertEquals(1, newHand.getDie(i).getSideUp());
        System.out.println("Hand has successfully been reset");
    }

    @Test
    void testHandIteration() {
        Hand newHand = new Hand();
        int count = 0;
        assertEquals(5, newHand.getNumDice());
        for (Die die : newHand) {
            assertEquals(1, die.getSideUp());
            ++count;
        }
        System.out.println(
                "Expected iteration size: "
                        + newHand.getNumDice()
                        + " || Actual iteration size: "
                        + count);
        assertEquals(count, newHand.getNumDice());
    }

    @Test
    void testToString() {
        Hand newHand = new Hand();
        int handSize = newHand.getNumDice();
        for (int i = 0; i < handSize; ++i) {
            newHand.getDie(i).setDie(4);
        }
        System.out.println(
                "Expected hand string: \"[4, 4, 4, 4, 4, 4]\" || Actual hand string: "
                        + newHand.toString());
        assertEquals("[4, 4, 4, 4, 4]", newHand.toString());
    }
}
