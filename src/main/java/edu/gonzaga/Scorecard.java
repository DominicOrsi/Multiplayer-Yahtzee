package edu.gonzaga;

import java.util.*;

public class Scorecard {
    private final ScoresMap scores;
    // private PossibleScoreCardView possibleScoreCardView;
    private final PossibleScoreCardMap possibleScorecard;
    private int upperBonus;
    private int yahtzeeCount;
    private int lowerBonus;

    public Scorecard() {
        scores = new ScoresMap();
        possibleScorecard = new PossibleScoreCardMap();
        upperBonus = 0;
        lowerBonus = 0;
    }

    /**
    * Returns whether the Scorecard is fully completed.
    *
    * @return True if the Scorecard
    */
    public boolean isFull() {
        return scores.isFull();
    }

    /**
    * Calculates the score for the scorecard based on the given hand and stores within a
    * possibleScorecard
    *
    * @param hand The hand to calculate the score for
    */
    public void score(Hand hand) {
        System.out.println("score");
        ArrayList<Die> dice = new ArrayList<>();
        int sumDice = 0;
        for (Die die : hand) {
            dice.add(die);
            sumDice += die.getSideUp();
        }
        Collections.sort(dice);
        System.out.println("Scoring");

        if (checkForTriple(dice)) possibleScorecard.setScore("3K", sumDice);
        else possibleScorecard.setScore("3K", 0);

        if (checkForQuadruple(dice)) {
            possibleScorecard.setScore("4K", sumDice);
            possibleScorecard.setScore("3K", sumDice);
        } else possibleScorecard.setScore("4K", 0);

        if (checkForYahtzee(dice)) possibleScorecard.setScore("Y", 50);
        else possibleScorecard.setScore("Y", 0);

        if (checkForSmallStraight(dice)) possibleScorecard.setScore("SS", 30);
        else possibleScorecard.setScore("SS", 0);

        if (checkForLargeStraight(dice)) possibleScorecard.setScore("LS", 40);
        else possibleScorecard.setScore("LS", 0);

        if (checkForFullHouse(dice)) {
            possibleScorecard.setScore("FH", 25);
        } else {
            possibleScorecard.setScore("FH", 0);
        }
        possibleScorecard.setScore("C", sumDice);

        // add the plain number scores as sums of the numbers to the possible scorecard
        for (int i = 1; i <= 6; i++) {
            int numOfDice = 0;
            for (Die die : dice) {
                if (die.getSideUp() == i) numOfDice++;
            }
            if (numOfDice > 0) possibleScorecard.setScore(Integer.toString(i), numOfDice * i);
            else possibleScorecard.setScore(Integer.toString(i), 0);
        }
    }

    /**
    * Returns the possibleScoreCard for the Scorecard.
    *
    * @return The possibleScoreCard for the map.
    */
    public PossibleScoreCardMap getPossibleScorecard() {
        return possibleScorecard;
    }

    /** Selects a scoring option from the possibleScorecard and uses the specified ScoreSlot */
    public void chooseScore(String caseName) {
        scores.use(caseName, possibleScorecard.getScore(caseName));
        possibleScorecard.use(caseName);
        if (caseName.equals("Y")
                && possibleScorecard.get("Y").getScore() == 50
                && scores.get("Y").getScore() == 50) {
            yahtzeeCount++;
            if (scores.get("Y").getScore() == 50) lowerBonus += 100;
        }
        if (scores.getUpperScore() >= 63) upperBonus += 35;
        possibleScorecard.reset();
    }

    /**
    * Gets the scoreslot for the specified caseName
    *
    * @param caseName The caseName to get the score for
    * @return The ScoreSlot for the caseName
    */
    public ScoreSlot get(String caseName) {
        return scores.get(caseName);
    }

    /**
    * Checks if the hand contains a num of a kind
    *
    * @param hand The hand to check
    * @param num The number of a kind to check for
    * @return True if the hand contains num of a kind, false otherwise
    */
    private boolean numOfKind(Hand hand, int num) {
        Map<Integer, Integer> numPerDie = new HashMap<>();

        for (int i = 1; i <= 6; ++i) {
            numPerDie.put(i, 0);
        }

        for (Die die : hand) {
            numPerDie.put(die.getSideUp(), numPerDie.get(die.getSideUp()) + 1);
        }

        for (Integer key : numPerDie.keySet()) if (numPerDie.get(key).equals(num)) return true;

        return false;
    }

    /**
    * Checks the hand to see if the number of dice in an ascending order are equal to the length
    * provided.
    *
    * @param hand The hand to check.
    * @param length The length of the straight.
    * @return True if the hand contains a straight of length length.
    */
    private boolean containsStraight(Hand hand, int length) {
        int errors = 0;
        List<Die> dice = new ArrayList<>();
        for (Die die : hand) {
            dice.add(die);
        }
        Collections.sort(dice);
        for (int i = 0; i < dice.size() - 1; i++) {
            if (dice.get(i).getSideUp() + 1 != dice.get(i + 1).getSideUp()) {
                errors++;
            }
        }

        return errors <= hand.getNumDice() - length;
    }

    /**
    * Each of these checkfor_____ is used in the lower section scoring checks to see if either
    * special case exists with the given hand
    */
    private boolean checkForTriple(ArrayList<Die> hand) // The various for loops that are bounded by
            { // hand.size are meant to be used for varying amounts
        for (int i = 0;
                i < hand.size() - 2;
                ++i) // of dice in a given hand, so this will work with any amount above or equal to
        // 5 dice
        {
            Die die1 = hand.get(i);
            Die die3 = hand.get(i + 2);
            if (die1.getSideUp().equals(die3.getSideUp())) return true;
        }
        return false;
    }

    private boolean checkForQuadruple(ArrayList<Die> hand) {
        for (int i = 0; i < hand.size() - 3; ++i) {
            Die die1 = hand.get(i);
            Die die4 = hand.get(i + 3);
            if (die1.getSideUp().equals(die4.getSideUp())) return true;
        }
        return false;
    }

    private boolean checkForFullHouse(ArrayList<Die> hand) {
        if (checkForTriple(hand) && !checkForQuadruple(hand)) {
            for (int i = 0; i < hand.size() - 4; ++i) {
                Die die1 = hand.get(i);
                Die die2 = hand.get(i + 1);
                Die die3 = hand.get(i + 2);
                Die die4 = hand.get(i + 3);
                Die die5 = hand.get(i + 4);
                if ((die1.getSideUp().equals(die2.getSideUp()))
                                && !(die1.getSideUp().equals(die3.getSideUp()))
                        || (die4.getSideUp().equals(die5.getSideUp())
                                && !(die3.getSideUp().equals(die5.getSideUp())))) return true;
            }
        }
        return false;
    }

    private boolean checkForSmallStraight(ArrayList<Die> hand) {
        if (checkForTriple(hand) || checkForQuadruple(hand) || checkForFullHouse(hand))
            return false;
        ArrayList<Integer> values = new ArrayList<>();
        for (Die die : hand) {
            if (!values.contains(die.getSideUp())) values.add(die.getSideUp());
        }
        if (values.size() < 4) return false;
        if (!values.contains(3) || !values.contains(4)) return false;

        if (values.contains(1) && values.contains(2)) return true;
        else if (values.contains(2) && values.contains(5)) return true;
        else return values.contains(5) && values.contains(6);
    }

    private boolean checkForLargeStraight(ArrayList<Die> hand) {

        for (int i = 0; i < hand.size() - 4; ++i) {
            Die die1 = hand.get(i);
            Die die5 = hand.get(i + 4);
            if (checkForSmallStraight(hand) && die5.getSideUp() == die1.getSideUp() + 4)
                return true;
        }
        return false;
    }

    private boolean checkForYahtzee(ArrayList<Die> hand) {
        for (int i = 0; i < hand.size() - 4; ++i) {
            Die die1 = hand.get(i);
            Die die5 = hand.get(i + 4);
            if (checkForQuadruple(hand) && die1.getSideUp().equals(die5.getSideUp())) return true;
        }
        return false;
    }

    /**
    * Returns the bonus for the upper section of the Scorecard.
    *
    * @return The bonus for the upper section of the Scorecard.
    */
    public int getUpperBonus() {
        return upperBonus;
    }

    /**
    * Returns the bonus for the lower section of the Scorecard.
    *
    * @return The bonus for the lower section of the Scorecard.
    */
    public int getLowerBonus() {
        return lowerBonus;
    }

    /**
    * Returns the sum of the scores in the Upper Scorecard.
    *
    * @return The sum of the scores in the Upper Scorecard.
    */
    public int getUpperTotal() {
        int upperTotal = 0;
        for (int i = 1; i <= 6; ++i) {
            upperTotal += scores.get(Integer.toString(i)).getScore();
        }

        return upperTotal;
    }

    /**
    * Gets the total score of the Lower Scorecard.
    *
    * @return The total score of the Lower Scorecard.
    */
    public int getLowerTotal() {
        int lowerTotal = 0;
        String[] cases = {"3K", "4K", "SS", "LS", "FH", "Y", "C"};
        for (String str : cases) {
            lowerTotal += scores.get(str).getScore();
        }

        return lowerTotal;
    }

    /**
    * Returns the total score of the Scorecard.
    *
    * @return The total score of the Scorecard.
    */
    public int getTotal() {
        return getUpperTotal() + getLowerTotal() + getUpperBonus() + getLowerBonus();
    }

    /**
    * Gets the possible cases inside of the scorecard
    *
    * @return A list of the possible cases
    */
    public String[] getCases() {
        return scores.getCases();
    }
}
