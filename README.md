# Final Full Yahtzee Game Team Project

Final Team project for team: Java The Hutt

Team members: Cameron Williamson, Conner Diede, Dominic Orsi, Ryan Gavino

## Description

This is a simple Java Swing implementation of a game of Yahtzee with multiplayer.

### Gameplay Images

<p align="center"><img src="/images/home-screen.png" alt="home screen" width="70%"> <img src="/images/dice-select-screen.png" alt="dice selection screen" width="90%">  <img src="/images/game-over-screen.png" alt="game over screen" width="80%"> </p>

## Rules of Yahtzee

Source: The Yahtzee rulebook

### Objective

Roll dice for scoring combinations and get the highest total score.

### Game Summary

On each turn, roll the dice up to 3 times to get the highest scoring combination for one of 13 categories. After you finish rolling, you must place a score or a zero in one of the 13 category boxes on your score card. The game ends when all players have filled in their 13 boxes. Scores are totaled including any bonus points. The player with the highest total wins.

### How to Play

Each player takes a score card. To decide who goes first, each player in turn rolls all 5 dice. The player with the highest total goes first. Play then passes to the left.

### Taking a Turn

On your turn, you may roll the dice up to 3 times, although you may stop and score after your first or second roll. To roll the dice, place them in the dice cup, shake them up, and roll them out. **First roll:** Roll all 5 dice and set any "keepers" aside. You can stop to score now or roll again. **Second roll**: Reroll any or all dice you want from the previous roll.

### Scoring

When finished rolling, decide which box to fill in on your score card. For each game, there is a column of 13 boxes. You must fill in a box on each turn; if you can't (or don't want to) enter a score, you must enter a zero. Fill in each box only once in any order depending on the best scoring option. The score card is divided into an Upper Section and a Lower Section. Scoring combinations for each section are explained below.

| Upper Section | What to Score        |
| ------------- | -------------------- |
| Aces (Ones)   | Total of Aces only   |
| Twos          | Total of Twos only   |
| Threes        | Total of Threes only |
| Fours         | Total of Fours only  |
| Fives         | Total of Fives only  |
| Sixes         | Total of Sixes only  |

The Lower Section is scored very differently from the upper section. This is more explained with the next table.

| Lower Section             | What to Score       |
| ------------------------- | ------------------- |
| 3 of a Kind               | Total of all 5 dice |
| 4 of a Kind               | Total of all 5 dice |
| Full House                | 25 points           |
| Small Straight            | 30 points           |
| Large Straight            | 40 points           |
| YAHTZEE<br/>(5 of a Kind) | 50 points           |
| Chance                    | Total of all 5 dice |

Three of a kind, four of a kind, full house, and the straights are pretty straightforward. The Yahtzee rules are the more confusing rules within the scorecard. The first Yahtzee you enter in the Yahtzee box is worth 50 points. For each additional Yahtzee you roll, you can earn a bonus. The Yahtzee bonus gives you a 100-point bonus.
