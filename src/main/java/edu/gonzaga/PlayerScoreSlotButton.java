package edu.gonzaga;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
* A button that displays the score of a score slot.
*
* @author Cameron S. Williamson
*/
public class PlayerScoreSlotButton extends JButton implements PropertyChangeListener {
    private ScoreSlot scoreSlot;

    /** Constructs a new PlayerScoreSlotButton. */
    public PlayerScoreSlotButton() {
        setEnabled(false);
    }

    /**
    * Constructs a new PlayerScoreSlotButton with the given score slot.
    *
    * @param scoreSlot The slot for the this object to link to
    */
    public PlayerScoreSlotButton(ScoreSlot scoreSlot) {
        this();
        linkScoreSlot(scoreSlot);
    }

    /**
    * Links this button to the given score slot.
    *
    * @param scoreSlot The slot to link to
    */
    public void linkScoreSlot(ScoreSlot scoreSlot) {
        this.scoreSlot = scoreSlot;
        System.out.println();
        setText(String.valueOf(scoreSlot.getScore()));
        scoreSlot.addPropertyChangeListener(this);
    }

    /**
    * Checks if the current score slot has been used yet.
    *
    * @return true if the current score slot has not been used, false otherwise
    */
    public boolean notUsed() {
        return !scoreSlot.isUsed();
    }

    /**
    * Listens for a change in a score slot, and if so the current text is updated to the new score.
    */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propName = e.getPropertyName();
        if ("score".equals(propName)) {
            setText(((Integer) e.getNewValue()).toString());
            System.out.println("Score changed to " + e.getNewValue());
        }
    }
}
