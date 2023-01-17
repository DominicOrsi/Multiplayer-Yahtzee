package edu.gonzaga;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ScoreSlot {
    private boolean used;
    private int score;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
    * Adds a property change listener to the listener list.
    *
    * @param listener the property change listener to be added
    */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
    * Removes a property change listener from the listener list.
    *
    * @param listener the property change listener to be removed
    */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /** DVC Sets used to false and score to zero */
    public ScoreSlot() {
        used = false;
        score = 0;
    }

    /**
    * EVC Sets a ScoreSlots private member vars to the passed in params
    *
    * @param used
    * @param score
    */
    public ScoreSlot(boolean used, int score) {
        this.used = used;
        this.score = score;
    }

    /**
    * Sets the score to the passed in param and sets used to true
    *
    * @param score
    */
    public void use(int score) {
        setScore(score);
        used = true;
        pcs.firePropertyChange("used", false, true);
    }

    /**
    * Getter for private memeber var: used
    *
    * @return used
    */
    public boolean isUsed() {
        return used;
    }

    /**
    * Getter for private memeber var: score
    *
    * @return score
    */
    public int getScore() {
        return score;
    }

    /**
    * Sets the score to the specified param but does not set used to true.
    *
    * @param score The score to set to.
    */
    public void setScore(int score) {
        pcs.firePropertyChange("score", this.score, score);
        this.score = score;
    }

    /** Resets the score slot to its default state if it is unused. */
    public void reset() {
        if (!used) {
            score = 0;
        }
    }
}
