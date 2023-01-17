package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testDefaultConstructor() {
        String expectedName = "";
        int expectedHandSize = 5;
        Player player = new Player();
        System.out.println("Player.testDefualtConstructor()");
        System.out.println("-------------------------------");
        System.out.println(
                "Expected Name: " + expectedName + " || Actual Name: " + player.getName());
        System.out.println("Expected Hand Size: 6 || Player Hand: " + player.getHand().toString());
        System.out.println("Player Scorecard: " + player.getScorecard().toString());
        assertEquals(expectedName, player.getName());
        assertNotNull(player.getHand());
        assertEquals(expectedHandSize, player.getHand().getNumDice());
        assertNotNull(player.getScorecard());
    }

    @Test
    void testExplicitValueConstructor() {
        String expectedName = "John";
        int expectedHandSize = 5;
        Player player = new Player("John");
        System.out.println("Player.testExplicitValueConstructor()");
        System.out.println("-------------------------------------");
        System.out.println(
                "Expected Name: " + expectedName + " || Actual Name: " + player.getName());
        System.out.println("Expected Hand Size: 6 || Player Hand: " + player.getHand().toString());
        System.out.println("Player Scorecard: " + player.getScorecard().toString());
        assertEquals(expectedName, player.getName());
        assertNotNull(player.getHand());
        assertEquals(expectedHandSize, player.getHand().getNumDice());
        assertNotNull(player.getScorecard());
    }
}
