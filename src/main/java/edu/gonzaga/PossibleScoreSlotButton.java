package edu.gonzaga;

import java.beans.PropertyChangeEvent;

/**
* This class is a button that represents a possible score slot.
*
* @author Cameron S. Williamson
*/
public class PossibleScoreSlotButton extends PlayerScoreSlotButton {
    private boolean locked;

    /**
    * Constructs a new PossibleScoreSlotButton.
    *
    * @param scoreSlot The scoreslot for it to be linked to.
    */
    public PossibleScoreSlotButton(ScoreSlot scoreSlot) {
        super(scoreSlot);
        locked = false;
    }

    /**
    * Listens for changes within a score slot.
    *
    * @param e the event that occurred.
    */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propName = e.getPropertyName();
        if ("used".equals(propName)) {
            locked = true;
        } else if ("score".equals(propName)) {
            setText(((Integer) e.getNewValue()).toString());
            System.out.println("PossibleScoreSlotButton: " + getText());
            System.out.println(e.getNewValue());
        }
    }

    /**
    * States whether the button is locked.
    *
    * @return True if locked; false otherwise.
    */
    public boolean isLocked() {
        return locked;
    }
}
