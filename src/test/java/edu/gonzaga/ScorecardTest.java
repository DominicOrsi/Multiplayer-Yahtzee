package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScorecardTest {
    @Test
    void testYahtzee() {
        Hand hand = new Hand();
        System.out.println("Testing all 1s. Expect Yahtzee but not 4 of a kind or 3 of a kind.");
        Scorecard scorecard = new Scorecard();
        scorecard.score(hand);
        PossibleScoreCardMap possibleScorecard = scorecard.getPossibleScorecard();
        for (String key : possibleScorecard.getCases()) {
            if (key.equals("Y")) {
                System.out.println("Yahtzee: " + possibleScorecard.getScore(key));
                assertEquals(50, possibleScorecard.getScore(key));
            } else if (key.equals("4K")) {
                System.out.println("4K: " + possibleScorecard.getScore(key));
                assertEquals(5, possibleScorecard.getScore(key));
            } else if (key.equals("3K")) {
                System.out.println("3K: " + possibleScorecard.getScore(key));
                assertEquals(5, possibleScorecard.getScore(key));
            } else if (key.equals("C")) {
                System.out.println("C: " + possibleScorecard.getScore(key));
                assertEquals(5, possibleScorecard.getScore(key));
            } else if (key.equals("1")) {
                assertEquals(5, possibleScorecard.getScore(key));
            } else {
                assertEquals(0, possibleScorecard.getScore(key));
            }
        }
    }

    @Test
    void test4OfAKind() {
        Hand hand = new Hand();
        hand.getDie(0).setDie(6);
        hand.getDie(1).setDie(6);
        hand.getDie(2).setDie(6);
        hand.getDie(3).setDie(6);
        int sumDice = 25;

        System.out.println("Testing with four 6s. Expect 4 of a kind and 3 of a kind.");
        Scorecard scorecard = new Scorecard();
        scorecard.score(hand);
        PossibleScoreCardMap possibleScorecard = scorecard.getPossibleScorecard();
        System.out.println(possibleScorecard);
        for (String key : possibleScorecard.getCases()) {
            switch (key) {
                case "4K":
                    System.out.println("4K: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "3K":
                    System.out.println("3K: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "C":
                    System.out.println("C: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "6":
                    assertEquals(4 * 6, possibleScorecard.getScore(key));
                    break;
                case "1":
                    assertEquals(1, possibleScorecard.getScore(key));
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    void test3OfAKind() {
        Hand hand = new Hand();
        hand.getDie(0).setDie(6);
        hand.getDie(1).setDie(6);
        hand.getDie(2).setDie(6);
        hand.getDie(3).setDie(5);
        hand.getDie(4).setDie(3);
        System.out.println(hand);
        int sumDice = 26;

        System.out.println(
                "Testing with three 6s. Expect 3 of a kind but not Yahtzee or 4 of a kind.");
        Scorecard scorecard = new Scorecard();
        scorecard.score(hand);
        PossibleScoreCardMap possibleScorecard = scorecard.getPossibleScorecard();
        for (String key : possibleScorecard.getCases()) {
            switch (key) {
                case "3K":
                    System.out.println("3K: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "C":
                    System.out.println("C: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "6":
                    assertEquals(3 * 6, possibleScorecard.getScore(key));
                    break;
                case "5":
                    assertEquals(5, possibleScorecard.getScore(key));
                    break;
                case "3":
                    assertEquals(3, possibleScorecard.getScore(key));
                    break;
                default:
                    System.out.println("Default case " + key);
                    assertEquals(0, possibleScorecard.getScore(key));
                    break;
            }
        }
    }

    @Test
    void testFullHouse() {
        Hand hand = new Hand();
        hand.getDie(0).setDie(6);
        hand.getDie(1).setDie(6);
        hand.getDie(2).setDie(6);
        hand.getDie(3).setDie(5);
        hand.getDie(4).setDie(5);
        int sumDice = 28;

        System.out.println("Testing with three 6s and two 5s. Expect full house.");
        Scorecard scorecard = new Scorecard();
        scorecard.score(hand);
        PossibleScoreCardMap possibleScorecard = scorecard.getPossibleScorecard();
        for (String key : possibleScorecard.getCases()) {
            switch (key) {
                case "FH":
                    System.out.println("FH: " + possibleScorecard.getScore(key));
                    assertEquals(25, possibleScorecard.getScore(key));
                    break;
                case "3K":
                    System.out.println("3K: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "C":
                    System.out.println("C: " + possibleScorecard.getScore(key));
                    assertEquals(sumDice, possibleScorecard.getScore(key));
                    break;
                case "6":
                    assertEquals(3 * 6, possibleScorecard.getScore(key));
                    break;
                case "5":
                    assertEquals(10, possibleScorecard.getScore(key));
                    break;
                default:
                    System.out.println("Default case " + key);
                    assertEquals(0, possibleScorecard.getScore(key));
                    break;
            }
        }
    }
}
