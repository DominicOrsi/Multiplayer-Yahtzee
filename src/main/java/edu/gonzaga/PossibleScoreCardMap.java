package edu.gonzaga;

/** This class is used to map the possible scores of a hand and store them */
public class PossibleScoreCardMap extends ScoresMap {
    public PossibleScoreCardMap() {
        super();
    }

    /**
    * Sets the state of a scoreslot to used Please note the "points" parameter is depricated within
    * this method. It will always set the score to 0
    *
    * @param slotName the name of the slot to set
    * @param points the points to set the slot to
    */
    @Override
    public void use(String slotName, int points) {
        use(slotName);
    }

    /**
    * Sets the state of a scoreslot to used
    *
    * @param slotName the name of the slot to set
    * @param points the points to set the slot to
    */
    public void use(String slotName) {
        scores.get(slotName).use(0);
    }

    /**
    * Sets the score of a specific slot to a specific value
    *
    * @param slotName the name of the slot to set
    * @param points the points to set the slot to
    */
    public void setScore(String slotName, int points) {
        scores.get(slotName).setScore(points);
        System.out.println("Set " + slotName + " to " + points);
    }

    /** Resets all of the unused slots to 0 points. */
    public void reset() {
        for (String key : scores.keySet()) {
            scores.get(key).reset();
        }
    }

    /**
    * Returns a representation of the possible score card
    *
    * @return a string representation of the possible score card
    */
    @Override
    public String toString() {
        String retStr = "";
        retStr += "Possible Score Card:\n";
        for (String key : scores.keySet()) {
            retStr += key + ": " + scores.get(key).getScore() + "\n";
        }
        retStr += "--------------------\n";
        return retStr;
    }
}
