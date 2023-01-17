package edu.gonzaga;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.*;

public class Player {
    private final String name;
    private final Hand hand;
    private final Scorecard scorecard;
    private final Color color;
    private final PossibleScoreCardView possibleScoreCardView;
    private final ScorecardView scorecardView;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*DVC sets the Player's name to blank and instantiates a hand, scorecard, and possibleScoreCard */
    public Player() {
        name = "";
        hand = new Hand();
        scorecard = new Scorecard();
        color = Color.RED;
        scorecardView = new ScorecardView(scorecard);
        possibleScoreCardView = scorecardView.createPossibleScorecard(this);
        scorecardView.setVisible(true);
        possibleScoreCardView.setVisible(true);
    }

    /**
    * EVC sets the Player's name to the given param and instantiates a hand, scorecard, and
    * possibleScoreCard
    */
    public Player(String name) {
        this.name = name;
        hand = new Hand();
        scorecard = new Scorecard();
        color = Color.RED;
        scorecardView = new ScorecardView(scorecard);
        possibleScoreCardView = scorecardView.createPossibleScorecard(this);
        scorecardView.setVisible(true);
        possibleScoreCardView.setVisible(true);
    }

    public Player(String name, Color color) {
        this.name = name;
        hand = new Hand();
        scorecard = new Scorecard();
        this.color = color;
        scorecardView = new ScorecardView(scorecard);
        scorecardView.updateColor(color);
        possibleScoreCardView = scorecardView.createPossibleScorecard(this);
        possibleScoreCardView.updateColor();
        scorecardView.setVisible(true);
        possibleScoreCardView.setVisible(true);
    }

    /**
    * Getter for the Player's name
    *
    * @return name
    */
    public String getName() {
        return name;
    }

    /**
    * Getter for the Player's hand
    *
    * @return hand
    */
    public Hand getHand() {
        return hand;
    }

    public Color getColor() {
        return color;
    }

    /**
    * Getter for the Player's scorecard
    *
    * @return scorecard
    */
    public Scorecard getScorecard() {
        return scorecard;
    }

    /**
    * Determines whether all of the ScoreSlots of the ScoreCard have been used or not
    *
    * @return true/false
    */
    public int scoreCardFull() {
        if (scorecard.isFull()) {
            return scorecard.getTotal();
        }
        return -1;
    }

    public int totalScore() {
        return scorecard.getTotal();
    }

    /** Rolls the Player's hand. */
    public void rollHand() {
        hand.roll();
    }

    /** Calculates scores and updates the PossibleScoreCardView */
    public void calculateScores() {
        scorecard.score(hand);
        System.out.println("calculating scores");
    }

    /** Makes it such that the PossibleScoreCardView scores can be chosen. */
    public void setSelectScoreMode() {
        possibleScoreCardView.chooseScore();
    }

    /** Makes it such that the PossibleScoreCardView scores can not be chosen. */
    public void stopSelectScoreMode() {
        possibleScoreCardView.stopChoosingScore();
        pcs.firePropertyChange("chooseScore", true, false);
    }

    public void addScoreCard(JFrame yahtzeeWindow) {
        System.out.println("Adding new scorecard");
        yahtzeeWindow.add(scorecardView);
        yahtzeeWindow.add(possibleScoreCardView);
    }

    /**
    * Shows the player's scorecard on the Yahtzee window.
    *
    * @param yahtzeeWindow The game window.
    */
    public void showScorecard(JFrame yahtzeeWindow) {
        scorecardView.revealCard();
        possibleScoreCardView.revealCard();
    }

    /**
    * Removes the player's scorecard from the Yahtzee window.
    *
    * @param yahtzeeWindow The game window.
    */
    public void hideScorecard(JFrame yahtzeeWindow) {
        scorecardView.hideCard();
        possibleScoreCardView.hideCard();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void resetHand() {
        hand.reset();
    }

    /** Returns a string of the players attributes */
    @Override
    public String toString() {
        return "Player name: " + name + "\n Hand: " + hand + "\n Scorecard: " + scorecard;
    }
}
