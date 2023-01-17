/* (C)2021 */
package edu.gonzaga;

/*
*  This is the main class for the Yahtzee project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

/** Main program class for launching Yahtzee program. */
public class Yahtzee {

    public static void main(String[] args) {
        StartGameView startGameView = new StartGameView();
        startGameView.window.setVisible(true);
    }
}
