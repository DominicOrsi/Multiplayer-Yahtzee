package edu.gonzaga;

import java.util.HashMap;
import java.util.Map;

/** A map of scoreSlots to keep track of a Yahtzee score. */
public class ScoresMap {
    private final String[] cases;
    protected Map<String, ScoreSlot> scores;

    /**
    * Default constructor for a ScoreSlotMap.
    *
    * <p>Defines cases 1 through 6 and 3K through Chance
    */
    public ScoresMap() {
        scores = new HashMap<>();
        cases = new String[] {"1", "2", "3", "4", "5", "6", "3K", "4K", "SS", "LS", "FH", "Y", "C"};

        for (String string : cases) {
            scores.put(string, new ScoreSlot());
        }
    }

    /**
    * Uses the scoreslot at the specified slotName
    *
    * @param slotName The slot within the map
    * @param points The points to be set
    */
    public void use(String slotName, int points) {
        scores.get(slotName).use(points);
    }

    /**
    * States whether the scoreSlot at the specified slot has been used.
    *
    * @param slotName The slot within the map
    * @return True if the slot has been used. False otherwise.
    */
    public boolean isUsed(String slotName) {
        return scores.get(slotName).isUsed();
    }

    /**
    * Fetches the scoreslot for a specific slotName
    *
    * @param slotName The slot within the map
    * @return The slot within the scorecard.
    */
    public ScoreSlot get(String slotName) {
        return scores.get(slotName);
    }

    /**
    * Fetches the score for a specific ScoreSlot
    *
    * @param slotName The slot within the map
    * @return The slot within the scorecard
    */
    public Integer getScore(String slotName) {
        return scores.get(slotName).getScore();
    }

    /**
    * Fetches the cases for all possible scorings.
    *
    * @return An array of cases.
    */
    public String[] getCases() {
        return cases;
    }

    /**
    * Checks if the current ScoreMap is full.
    *
    * @return True if it is full, false otherwise.
    */
    public boolean isFull() {
        for (String string : cases) {
            if (!scores.get(string).isUsed()) {
                return false;
            }
        }
        return true;
    }

    /**
    * Gets the score of the upper scorecard.
    *
    * @return The score of the upper scorecard.
    */
    public int getUpperScore() {
        int sum = 0;
        for (int i = 1; i <= 6; ++i) {
            sum += scores.get(String.valueOf(i)).getScore();
        }
        return sum;
    }

    /**
    * Gets the score of the lower scorecard.
    *
    * @return The score of the lower scorecard.
    */
    public int getLowerScore() {
        int sum = 0;
        String[] cases = new String[] {"3K", "4K", "SS", "LS", "FH", "Y", "C"};
        for (String s : cases) {
            sum += scores.get(s).getScore();
        }
        return sum;
    }
}
