package edu.gonzaga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class StartGameView {
    public JFrame window;
    private JPanel parentPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;
    private JPanel inputPanel;
    private JTextPane yahtzeeText;
    private JTextPane authorsText;
    private JTextPane messageText;
    private JTextField playerOne;
    private JTextField playerTwo;
    private JTextField playerThree;
    private JTextField playerFour;
    private JButton startButton;
    private JButton dummyButton;
    private final Color playerOneColor;
    private final Color playerTwoColor;
    private final Color playerThreeColor;
    private final Color playerFourColor;
    private final ArrayList<Player> players;

    /** Constructor builds the StartGame GUI, so it is ready to be displayed */
    public StartGameView() {
        players = new ArrayList<>();
        playerOneColor = Color.decode("#B4EDD2");
        playerTwoColor = Color.decode("#A0CFD3");
        playerThreeColor = Color.decode("#8D94BA");
        playerFourColor = Color.decode("#FBC4AB");
        setWindow();
        setPanels();
        setTextPanes();
        setTextFields();
        setStartButton();
        centerPanel.add(yahtzeeText);
        centerPanel.add(authorsText);
        centerPanel.add(inputPanel);
        centerPanel.add(messageText);
        centerPanel.add(buttonPanel);
        parentPanel.add(centerPanel, BorderLayout.CENTER);
        JTextPane spacePane = new JTextPane();
        spacePane.setPreferredSize(new Dimension(100, 125));
        spacePane.setBackground(Color.DARK_GRAY);
        parentPanel.add(spacePane, BorderLayout.NORTH);
        parentPanel.add(spacePane, BorderLayout.SOUTH);
        window.add(parentPanel);
    }

    /** Sets the main Frame */
    private void setWindow() {
        window = new JFrame("CPSC 224 - Yahtzee");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
    }

    /**
    * Sets the panels (One with a Border Layout and another with a Box Layout inside the Border
    * Layout's center)
    */
    private void setPanels() {
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout(0, 50));
        parentPanel.setBackground(Color.DARK_GRAY);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBackground(Color.DARK_GRAY);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(Color.DARK_GRAY);
    }

    /** Sets the StartGame GUI's text */
    private void setTextPanes() {
        // Used for text alignment
        StyledDocument doc;
        SimpleAttributeSet center;

        yahtzeeText = new JTextPane();
        yahtzeeText.setText("YAHTZEE");
        yahtzeeText.setMargin(new Insets(100, 0, 0, 0));
        doc = yahtzeeText.getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        yahtzeeText.setFont(new Font("Arial", Font.BOLD, 80));
        yahtzeeText.setForeground(Color.WHITE);
        yahtzeeText.setBackground(Color.DARK_GRAY);
        yahtzeeText.setEditable(false);

        authorsText = new JTextPane();
        authorsText.setText("Cameron Williamson, Ryan Gavino, Dominic Orsi, Connor Deide");
        authorsText.setMargin(new Insets(0, 0, 10, 0));
        doc = authorsText.getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        authorsText.setFont(new Font("Arial", Font.PLAIN, 20));
        authorsText.setForeground(Color.WHITE);
        authorsText.setBackground(Color.DARK_GRAY);
        authorsText.setEditable(false);

        messageText = new JTextPane();
        messageText.setMargin(new Insets(20, 0, 10, 0));
        doc = messageText.getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        messageText.setFont(new Font("Arial", Font.PLAIN, 20));
        messageText.setForeground(Color.RED);
        messageText.setBackground(Color.DARK_GRAY);
        messageText.setEditable(false);
    }

    /** Sets the input boxes to get players */
    private void setTextFields() {
        JTextArea playerOneText = new JTextArea("Player 1:");
        playerOneText.setFont(new Font("Arial", Font.PLAIN, 16));
        playerOneText.setForeground(Color.WHITE);
        playerOneText.setBackground(Color.DARK_GRAY);
        playerOneText.setEditable(false);
        playerOne = new JTextField();
        playerOne.setPreferredSize(new Dimension(100, 20));
        playerOne.setForeground(Color.DARK_GRAY);
        playerOne.setBackground(playerOneColor);
        inputPanel.add(playerOneText);
        inputPanel.add(playerOne);

        JTextArea playerTwoText = new JTextArea("Player 2:");
        playerTwoText.setFont(new Font("Arial", Font.PLAIN, 16));
        playerTwoText.setForeground(Color.WHITE);
        playerTwoText.setBackground(Color.DARK_GRAY);
        playerTwoText.setEditable(false);
        playerTwo = new JTextField();
        playerTwo.setPreferredSize(new Dimension(100, 20));
        playerTwo.setForeground(Color.DARK_GRAY);
        playerTwo.setBackground(playerTwoColor);
        inputPanel.add(playerTwoText);
        inputPanel.add(playerTwo);

        JTextArea playerThreeText = new JTextArea("Player 3:");
        playerThreeText.setFont(new Font("Arial", Font.PLAIN, 16));
        playerThreeText.setForeground(Color.WHITE);
        playerThreeText.setBackground(Color.DARK_GRAY);
        playerThreeText.setEditable(false);
        playerThree = new JTextField();
        playerThree.setPreferredSize(new Dimension(100, 20));
        playerThree.setForeground(Color.DARK_GRAY);
        playerThree.setBackground(playerThreeColor);
        inputPanel.add(playerThreeText);
        inputPanel.add(playerThree);

        JTextArea playerFourText = new JTextArea("Player 4:");
        playerFourText.setFont(new Font("Arial", Font.PLAIN, 16));
        playerFourText.setForeground(Color.WHITE);
        playerFourText.setBackground(Color.DARK_GRAY);
        playerFourText.setEditable(false);
        playerFour = new JTextField();
        playerFour.setPreferredSize(new Dimension(100, 20));
        playerFour.setForeground(Color.DARK_GRAY);
        playerFour.setBackground(playerFourColor);
        inputPanel.add(playerFourText);
        inputPanel.add(playerFour);
    }

    /** Sets the Start Game button and assigns an Action Listener */
    private void setStartButton() {
        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.setForeground(Color.DARK_GRAY);
        startButton.setBackground(Color.WHITE);
        startButton.setVisible(true);
        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Create the game GUI
                        if (checkTextFields()) {
                            getPlayers();
                            YahtzeeView yahtzeeView = new YahtzeeView(players);

                            // Collapse this GUI
                            window.dispose();
                        } else {
                            messageText.setText("- There must be at least one player -");
                        }
                    }
                });
        dummyButton = new JButton();
        dummyButton.setVisible(false);
        dummyButton.setPreferredSize(new Dimension(100, 40));
        dummyButton.setFocusable(false);
        dummyButton.setBackground(Color.DARK_GRAY);

        buttonPanel.add(startButton);
        buttonPanel.add(dummyButton);
    }

    /** Method gets the number of players and adds them to the Yahtzee classes players ArrayList */
    private void getPlayers() {
        if (!playerOne.getText().isEmpty())
            players.add(new Player(playerOne.getText(), playerOneColor));
        if (!playerTwo.getText().isEmpty())
            players.add(new Player(playerTwo.getText(), playerTwoColor));
        if (!playerThree.getText().isEmpty())
            players.add(new Player(playerThree.getText(), playerThreeColor));
        if (!playerFour.getText().isEmpty())
            players.add(new Player(playerFour.getText(), playerFourColor));
    }

    /** Method checks that at least one player's name has been typed */
    private boolean checkTextFields() {
        if (playerOne.getText().isEmpty()) {
            if (playerTwo.getText().isEmpty()) {
                if (playerThree.getText().isEmpty()) {
                    return !playerFour.getText().isEmpty();
                }
            }
        }
        return true;
    }
}
