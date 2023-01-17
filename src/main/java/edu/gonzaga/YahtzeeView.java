package edu.gonzaga;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class YahtzeeView implements PropertyChangeListener {

    ArrayList<Player> players;
    private Player currentPlayer;
    private final Player lastPlayer;
    private JFrame YahtzeeWindow;
    private JPanel handPanel;
    private ArrayList<DieButton> dice;
    private ArrayList<JTextField> scoreTrackers;
    private JButton rollButton;
    private JButton quitButton;
    private int rollCounter;
    private int currentPlayerOrder;

    public YahtzeeView(ArrayList<Player> newPlayers) {
        players = newPlayers;
        for (Player player : players) player.addPropertyChangeListener(this);
        currentPlayer = players.get(0);
        lastPlayer = players.get(players.size() - 1);
        currentPlayerOrder = 0;
        createWindow();
        for (Player player : players) player.addScoreCard(YahtzeeWindow);
    }

    private void createPlayers() {
        int numPlayers = 1;
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; ++i) players.add(new Player());
    }

    private void createWindow() {
        YahtzeeWindow = new JFrame();
        YahtzeeWindow.setBounds(0, 0, 1400, 800);
        YahtzeeWindow.setLocationRelativeTo(null);
        YahtzeeWindow.setVisible(true);
        YahtzeeWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        YahtzeeWindow.setLayout(null);
        YahtzeeWindow.getContentPane().setBackground(Color.DARK_GRAY);
        setUpButtons();
    }

    private void setUpButtons() {
        rollButton = new JButton();
        rollButton.setText("Roll! (" + (3 - rollCounter) + " rolls left!)");
        rollButton.setBounds(190, 630, 200, 30);
        rollButton.setVisible(true);
        rollButton.addActionListener(
                e -> {
                    for (DieButton die : dice) die.allowLocking();
                    currentPlayer.rollHand();
                    currentPlayer.calculateScores();
                    System.out.println(currentPlayer.getHand());
                    ++rollCounter;
                    if (maxRolls()) {
                        rollButton.setEnabled(false);
                        currentPlayer.setSelectScoreMode();
                    }
                    rollButton.setText("Roll! (" + (3 - rollCounter) + " rolls left!)");
                });
        YahtzeeWindow.add(rollButton);
        // Quit button
        quitButton = new JButton("QUIT");
        quitButton.setBounds(530, 700, 100, 40);
        quitButton.setVisible(true);
        quitButton.addActionListener(
                e -> {
                    JFrame quitWindow = new JFrame("Quit Yahtzee");
                    quitWindow.setLocation(525, 325);
                    quitWindow.setSize(500, 200);
                    quitWindow.setVisible(true);
                    JPanel quitPanel = new JPanel();
                    quitPanel.setLayout(null);
                    quitPanel.setBackground(Color.DARK_GRAY);
                    JTextArea quitMessage = new JTextArea("Are you sure you want to quit Yahtzee?");
                    quitMessage.setFont(new Font("Arial", Font.PLAIN, 25));
                    quitMessage.setForeground(Color.WHITE);
                    quitMessage.setBackground(Color.DARK_GRAY);
                    quitMessage.setBounds(30, 40, 425, 30);
                    quitMessage.setEditable(false);
                    JButton yesButton = new JButton("YES");
                    yesButton.setBackground(Color.WHITE);
                    yesButton.setBounds(250, 110, 100, 40);
                    yesButton.addActionListener(
                            f -> {
                                quitWindow.dispose();
                                YahtzeeWindow.dispose();
                                System.exit(0);
                            });
                    JButton cancelButton = new JButton("CANCEL");
                    cancelButton.setBackground(Color.WHITE);
                    cancelButton.setBounds(370, 110, 100, 40);
                    cancelButton.addActionListener(
                            g -> {
                                quitWindow.dispose();
                            });
                    quitPanel.add(quitMessage);
                    quitPanel.add(yesButton);
                    quitPanel.add(cancelButton);
                    quitWindow.add(quitPanel);
                });
        YahtzeeWindow.add(quitButton);

        handPanel = new JPanel();
        dice = new ArrayList<>();
        handPanel.setBounds(30, 110, 130, 560);
        handPanel.setLayout(null);
        // handPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        handPanel.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < 5; ++i) {
            DieButton newDie = null;
            try {
                newDie = new DieButton(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newDie.attachDie(currentPlayer.getHand().getDie(i));
            newDie.setVisible(true);
            dice.add(newDie);
            handPanel.add(newDie);
        }
        handPanel.setVisible(true);
        YahtzeeWindow.add(handPanel);
        addScoreBoardTotals();
        YahtzeeWindow.revalidate();
        YahtzeeWindow.repaint();
    }

    private void addScoreBoardTotals() {
        scoreTrackers = new ArrayList<>();
        for (int i = 0; i < players.size(); ++i) {
            JTextField scoreTracker = new JTextField();
            scoreTracker.setBounds(45 + (i * 150), 20, 100, 30);
            scoreTracker.setText(players.get(i).getName() + ": " + 0);
            scoreTracker.setBackground(players.get(i).getColor());
            scoreTracker.setEditable(false);
            scoreTracker.setVisible(true);
            scoreTrackers.add(scoreTracker);
            YahtzeeWindow.add(scoreTracker);
        }
    }

    private boolean maxRolls() {
        return rollCounter > 2;
    }

    private void gameEndScreen() {
        String finalMainString = checkPlayers(players.size());
        if (currentPlayer.scoreCardFull() != -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "<html>" + finalMainString + "</html>",
                    "Game Over",
                    JOptionPane.DEFAULT_OPTION);
        }
    }

    private String checkPlayers(int playerCount) {
        ArrayList<Integer> playerScores = new ArrayList<>();

        String mainEndGameString = "";
        String mainHtmlString = "<h1><center><strong>GAME OVER</strong></h1>";
        String winnerString = "";

        if (currentPlayer.scoreCardFull() != -1) {
            for (int i = 0; i < playerCount; i++) {
                playerScores.add(players.get(i).scoreCardFull());
            }
            Collections.sort(playerScores);
            Collections.reverse(playerScores);
            winnerString =
                    "<h2><center>Winner</h2><p><center>"
                            + players.get(0).getName()
                            + " with a score of "
                            + playerScores.get(0)
                            + "</p>";
        }

        mainEndGameString = mainHtmlString + winnerString;

        return mainEndGameString;
    }

    public void shiftPlayers() {
        scoreTrackers
                .get(currentPlayerOrder)
                .setText(currentPlayer.getName() + ": " + currentPlayer.totalScore());
        if (currentPlayerOrder == players.size() - 1) currentPlayerOrder = 0;
        else currentPlayerOrder++;
        currentPlayer.hideScorecard(YahtzeeWindow);
        currentPlayer = players.get(currentPlayerOrder);
        currentPlayer.showScorecard(YahtzeeWindow);
        rollCounter = 0;
        rollButton.setEnabled(true);
        currentPlayer.resetHand();
        rollButton.setText("Roll! (" + (3 - rollCounter) + " rolls left!)");
        for (int i = 0; i < 5; i++) {
            DieButton changeDie = dice.get(i);
            changeDie.updateDie(currentPlayer.getHand().getDie(i));
            changeDie.reset();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName());
        if ("chooseScore".equals(evt.getPropertyName())) {
            if (currentPlayer.getName() == lastPlayer.getName()) {
                boolean gameOver = (currentPlayer.scoreCardFull() > 0);
                if (gameOver) System.out.println("Game Over!");
                gameEndScreen();
            }
            System.out.println("Shifting players...");
            shiftPlayers();
        }
    }
}
