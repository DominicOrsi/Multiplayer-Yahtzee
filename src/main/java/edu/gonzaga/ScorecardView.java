package edu.gonzaga;

import java.awt.*;
import javax.swing.*;

public class ScorecardView extends JPanel {
    private final Scorecard scorecard;
    private Player player;
    private JButton upperTotal;
    private JButton upperBonus;
    private JButton lowerTotal;
    private JButton lowerBonus;
    private JButton total;

    /**
    * Constructs a new PlayerScorecardView.
    *
    * @param scorecard The scorecard to display.
    */
    public ScorecardView(Scorecard scorecard) {
        super();
        setLayout(new GridLayout(0, 2));
        this.scorecard = scorecard;
        setupPanel();
        // setVisible(false);
    }

    /** Sets up the panel. */
    private void setupPanel() {
        setBounds(1040, 10, 330, 760);
        add(new JLabel("Player Scorecard", SwingConstants.CENTER));
        add(new JLabel());
        add(new JLabel("Upper Scorecard", SwingConstants.CENTER));
        add(new JLabel());
        setBackground(Color.RED);

        JButton button;

        for (int i = 1; i <= 6; ++i) {
            button = new JButton(String.valueOf(i));
            button.setEnabled(false);
            add(button);
            button = new PlayerScoreSlotButton(scorecard.get(String.valueOf(i)));
            button.setText("-");
            add(button);
        }

        add(new JLabel("Sub Total", SwingConstants.CENTER));
        upperTotal = new JButton("0");
        upperTotal.setEnabled(false);
        add(upperTotal);

        add(new JLabel("Upper Bonus", SwingConstants.CENTER));
        upperBonus = new JButton("0");
        upperBonus.setEnabled(false);
        add(upperBonus);

        add(new JLabel("Lower Scorecard", SwingConstants.CENTER));
        add(new JLabel(""));
        String[] lowerLabels = {
            "3 of a Kind",
            "4 of a Kind",
            "Full House",
            "Small Straight",
            "Large Straight",
            "Yahtzee",
            "Chance"
        };
        String[] lowerAbrev = {"3K", "4K", "FH", "SS", "LS", "Y", "C"};

        for (int i = 0; i < lowerLabels.length; ++i) {
            String caseName = lowerLabels[i];

            button = new JButton(caseName);
            button.setEnabled(false);
            add(button);
            button = new PlayerScoreSlotButton(scorecard.get(lowerAbrev[i]));
            button.setText("-");
            add(button);
        }

        add(new JLabel("Lower Total"));
        lowerTotal = new JButton("0");
        lowerTotal.setEnabled(false);
        add(lowerTotal);

        add(new JLabel("Yahtzee Bonus"));
        lowerBonus = new JButton("0");
        lowerBonus.setEnabled(false);
        add(lowerBonus);

        add(new JLabel("Grand Total"));
        total = new JButton("0");
        total.setEnabled(false);
        add(total);
    }

    public void updateColor(Color color) {
        setBackground(color);
    }

    /**
    * States whether the scorecard has all the boxes filled or not.
    *
    * @return True if the scorecard is filled, false otherwise.
    */
    public boolean isFull() {
        return scorecard.isFull();
    }

    /** Updates the upperBonus, upperTotal, lowerTotal and yahtzeeBonus values.. */
    public void updateMaxScores() {
        upperTotal.setText(String.valueOf(scorecard.getUpperTotal()));
        upperBonus.setText(String.valueOf(scorecard.getUpperBonus()));
        lowerTotal.setText(String.valueOf(scorecard.getLowerTotal()));
        lowerBonus.setText(String.valueOf(scorecard.getLowerBonus()));
        total.setText(String.valueOf(scorecard.getTotal()));
    }

    /**
    * Scores the provided hand.
    *
    * @param hand hand to be scored.
    */
    public void score(Hand hand) {
        scorecard.score(hand);
    }

    /**
    * Creates a possible scorecard from the current scorecard.
    *
    * @param player the controller of the player.
    */
    public PossibleScoreCardView createPossibleScorecard(Player player) {
        return new PossibleScoreCardView(scorecard, player, this);
    }

    public void hideCard() {
        setVisible(false);
    }

    public void revealCard() {
        setVisible(true);
    }
}
