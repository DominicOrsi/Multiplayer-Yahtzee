package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScoreSlotTest {

    @Test
    void testDefaultContructor() {
        int expectedScore = 0;
        boolean expectedUsed = false;
        ScoreSlot scoreSlot = new ScoreSlot();
        System.out.println("ScoreSlot.testDefaultConstructor()");
        System.out.println("----------------------------------");
        System.out.println(
                "Expected score: " + expectedScore + " || Actual score: " + scoreSlot.getScore());
        System.out.println(
                "Expected used: " + expectedUsed + " || Actual used: " + scoreSlot.isUsed());
        assertEquals(expectedScore, scoreSlot.getScore());
        assertEquals(expectedUsed, scoreSlot.isUsed());
        System.out.println();
    }

    @Test
    void testExplicitValueConstructor() {
        int expectedScore = 10;
        boolean expectedUsed = true;
        ScoreSlot scoreSlot = new ScoreSlot(expectedUsed, expectedScore);
        System.out.println("ScoreSlot.testExplicitValueConstructor()");
        System.out.println("----------------------------------------");
        System.out.println(
                "Expected score: " + expectedScore + " || Actual score: " + scoreSlot.getScore());
        System.out.println(
                "Expected used: " + expectedUsed + " || Actual used: " + scoreSlot.isUsed());
        assertEquals(expectedScore, scoreSlot.getScore());
        assertEquals(expectedUsed, scoreSlot.isUsed());
    }

    @Test
    void testUse() {
        int expectedScore = 25;
        boolean expectedUsed = true;
        ScoreSlot scoreSlot = new ScoreSlot();
        System.out.println("ScoreSlot.Use()");
        System.out.println("---------------");
        System.out.println("Score before calling use(): " + scoreSlot.getScore());
        System.out.println("Used before calling use(): " + scoreSlot.isUsed());
        scoreSlot.use(expectedScore);
        System.out.println("use() called on ScoreSlot");
        System.out.println(
                "Expected score: " + expectedScore + " || Actual score: " + scoreSlot.getScore());
        System.out.println(
                "Expected used: " + expectedUsed + " || Actual used: " + scoreSlot.isUsed());
        assertEquals(expectedScore, scoreSlot.getScore());
        assertEquals(expectedUsed, scoreSlot.isUsed());
    }
}
