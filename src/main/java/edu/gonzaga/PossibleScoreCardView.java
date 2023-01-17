package edu.gonzaga;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
* A view that displays the possible scores for the current dice roll.
*
* @author Cameron S. Williamson
*/
public class PossibleScoreCardView extends JPanel {
    private final PossibleScoreCardMap possibleScorecardMap;
    private final List<JButton> buttons;
    private final Scorecard scorecard;
    private final Player player;
    private final ScorecardView scorecardView;

    /**
    * Constructs a new PossibleScorecardView
    *
    * @param scorecard the scorecard to display
    * @param player The controller of the current player's state.
    */
    public PossibleScoreCardView(Scorecard scorecard, Player player, ScorecardView scorecardView) {
        super();
        setLayout(new GridLayout(0, 2));
        this.scorecard = scorecard;
        this.player = player;
        this.possibleScorecardMap = scorecard.getPossibleScorecard();
        buttons = new ArrayList<>();
        setupPanel();
        this.scorecardView = scorecardView;
        // setupPanel(player);
    }

    /** Sets up the panel. */
    private void setupPanel() {
        setBounds(650, 10, 330, 760);
        add(new JLabel("Possible Scorecard"));
        add(new JLabel());
        add(new JLabel("Upper Scorecard"));
        add(new JLabel());
        setBackground(Color.RED);

        JButton button;

        for (int i = 1; i <= 6; ++i) {
            button = new JButton(String.valueOf(i));
            button.setEnabled(false);
            add(button);
            button = new PossibleScoreSlotButton(possibleScorecardMap.get(String.valueOf(i)));
            final int pos = i;
            button.addActionListener(
                    e -> {
                        scorecard.chooseScore(String.valueOf(pos));
                        scorecardView.updateMaxScores();
                        reset();
                        player.stopSelectScoreMode();
                    });
            add(button);
            buttons.add(button);
        }

        add(new JLabel("Lower Scorecard"));
        add(new JLabel());

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
            button = new PossibleScoreSlotButton(possibleScorecardMap.get(lowerAbrev[i]));
            final int pos = i;
            button.addActionListener(
                    e -> {
                        scorecard.chooseScore(lowerAbrev[pos]);
                        scorecardView.updateMaxScores();
                        reset();
                        player.stopSelectScoreMode();
                    });
            add(button);
            buttons.add(button);
        }
    }

    public void updateColor() {
        setBackground(player.getColor());
    }

    public void hideCard() {
        setVisible(false);
    }

    public void revealCard() {
        setVisible(true);
    }

    public void reset() {
        for (JButton button : buttons) button.setText(Integer.toString(0));
    }

    /** Turn on chooseScore mode which allows the possibleScorecard to be clicked. */
    public void chooseScore() {
        for (JButton button : buttons) {
            PossibleScoreSlotButton pssb = (PossibleScoreSlotButton) button;
            if (pssb.notUsed()) pssb.setEnabled(true);
            System.out.println("not used");
        }
        System.out.println("chose score");
    }

    /** Disable chooseScore mode. */
    public void stopChoosingScore() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
