package edu.gonzaga;

import java.util.ArrayList;
import java.util.Iterator;

/** This class represents a players hand of dice to use in the game of Yahtzee. */
public class Hand implements Iterable<Die> {
    private final ArrayList<Die> dice;
    private final int numDice;

    /**
    * Default constructor for a hand.
    *
    * <p>Creates an ArrayList of dice that is filled with 6 separate dice.
    */
    public Hand() {
        dice = new ArrayList<>();
        numDice = 5;
        for (int i = 0; i < numDice; ++i) dice.add(new Die());
    }

    public Hand(int numDice) {
        dice = new ArrayList<>();
        this.numDice = numDice;
        for (int i = 0; i < this.numDice; ++i) dice.add(new Die());
    }

    /**
    * Returns the die at the given index.
    *
    * @return the die at the given index.
    */
    public Die getDie(int index) {
        return dice.get(index);
    }

    /** Rolls all the dice in the current hand */
    public void roll() {
        for (Die currentDie :
                dice) // Since the Die class already provides a check for the locked status of a
            // die,
            currentDie.roll(); // this function can be simplified
    }

    /** Resets all the dice in the current hand */
    public void resetHand() {
        for (int i = 0; i < numDice; ++i) dice.set(i, new Die());
    }

    /**
    * Returns the number of dice in the current hand.
    *
    * @return the number of dice in the current hand.
    */
    public int getNumDice() {
        return numDice;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    /**
    * Returns hand as a string
    *
    * @return hand as a string
    */
    public String toString() {
        String s = "";
        for (int i = 0; i < dice.size(); i++) {
            // System.out.print(dice.get(i).getSideUp());
            s += dice.get(i).getSideUp();
            if (i < dice.size() - 1) {
                s += ", ";
            }
        }
        return "[" + s + "]";
    }

    /** Returns the sum of all the dice in the current hand. */
    public int getSum() {
        int sum = 0;
        for (Die currentDie : dice) sum += currentDie.getSideUp();
        return sum;
    }

    /** Sets all dice in the current hand to the 1 */
    public void reset() {
        for (Die currentDie : dice) currentDie.setDie(1);
    }

    /**
    * Allows the hand to be iterated through easily.
    *
    * @return the hand's iterator.
    */
    @Override
    public Iterator<Die> iterator() {
        return dice.iterator();
    }
}
