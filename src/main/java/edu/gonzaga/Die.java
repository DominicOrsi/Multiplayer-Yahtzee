package edu.gonzaga;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/** This class represents a die within a game of Yahtzee. */
public class Die implements Comparable<Die> {
    private boolean locked;
    private Integer sideUp;
    private final int numSides;
    private final PropertyChangeSupport pcs;

    /**
    * Default constuctor for a die.
    *
    * <p>Sets the number of sides to 6 Sets the sideUp to 1. Sets the state to unlocked.
    */
    public Die() {
        numSides = 6;
        sideUp = 1;
        locked = false;
        pcs = new PropertyChangeSupport(this);
    }

    /**
    * Constructor for a die.
    *
    * <p>Sets the number of sides to the parameter. Sets the sideUp to 1. Sets the state to
    * unlocked.
    *
    * @param numSides - number of sides
    */
    public Die(int numSides) {
        this.numSides = numSides;
        this.locked = false;
        this.sideUp = 1;
        pcs = new PropertyChangeSupport(this);
    }

    /** Sets the sideUp to a random number between 1 and the number of sides. */
    public void roll() {
        Random rand = new Random();
        if (!locked) {
            int oldSide = sideUp;
            int newSide = rand.nextInt(numSides) + 1;
            sideUp = newSide;
            pcs.firePropertyChange("newSide", oldSide, newSide);
        }
    }

    /**
    * Returns the current side facing up.
    *
    * @return the current side facing up.
    */
    public Integer getSideUp() {
        return sideUp;
    }

    /**
    * Returns the number of sides.
    *
    * @return the number of sides.
    */
    public int getNumSides() {
        return numSides;
    }

    /** Locks the dice. */
    public void lock() {
        locked = true;
    }

    /** Unlocks the dice. */
    public void unlock() {
        locked = false;
    }

    /** Used to set the dice to a specific value WARNING: FOR TESTING PURPOSES ONLY */
    public void setDie(int value) {
        sideUp = value;
    }

    /**
    * Returns the statoe of the dice.
    *
    * @return True if the dice is locked, false otherwise.
    */
    public boolean isLocked() {
        return locked;
    }

    /**
    * Returns side up of a die as a string
    *
    * @return side up of a die as a string
    */
    public String toString() {
        return String.valueOf(getSideUp());
    }

    /**
    * Compares this die to another die.
    *
    * @param otherDie - the other die to compare to
    * @return -1 if this die is less than other die, 0 if they are equal, 1 if this die is greater
    *     than other die
    */
    public int compareTo(Die otherDie) {
        return this.getSideUp().compareTo(otherDie.getSideUp());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
}
