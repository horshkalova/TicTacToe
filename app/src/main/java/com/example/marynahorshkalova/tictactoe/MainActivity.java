package com.example.marynahorshkalova.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // cross is player 0 (starts), null is player 1

    int nullCount = 0;
    int crossCount = 0;

    int activePlayer = 0;

    boolean gameIsActive = true;

    // state of game, 2 means unplayed cell
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // Tags of ImageView combinations which are winning positions

    int[][] winningPositions = {{0, 1, 2,}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        // *** give each ImageView tag from "0" to "8" in xml --> android:tag="0"
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // if this cell wasn't played
        if (gameState[tappedCounter] == 2 && gameIsActive) {

            // change it's state from array of int gameState
            // from "2" to 0 or 1 (according to a player's number)
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            // check whose turn it is - 0 cross, 1 - null
            if (activePlayer == 0) {

                // set black cross image
                counter.setImageResource(R.drawable.crossblack);

                // change player
                activePlayer = 1;

            } else {

                // set black null image
                counter.setImageResource(R.drawable.nullblack);

                // change player
                activePlayer = 0;
            }

            // ImageView drops on a grid
            counter.animate().translationYBy(1000f).setDuration(300);
        }

        // for each array {winningPosition} within int[][]
        for (int[] winningPosition : winningPositions) {

            //if all of three cells of winning position are filled by the same player
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {

                String winner = "Cross";


                if (gameState[winningPosition[0]] == 1) {

                    winner = "Null";

                }

                // Someone has won!

                gameIsActive = false;

                TextView whoWon = findViewById(R.id.whoWon);

                whoWon.setText(winner + " has won!");

                LinearLayout layout = findViewById(R.id.playAgainLayout);

                layout.setVisibility(View.VISIBLE);

                if (winner == "Cross") {

                    crossCount++;
                }
                if (winner == "Null") {

                    nullCount++;
                }

            } else {

                boolean gameOver = true;

                for (int counterState : gameState) {

                    if (counterState == 2) {

                        gameOver = false;
                    }

                }

                if (gameOver) {

                    TextView whoWon = findViewById(R.id.whoWon);

                    whoWon.setText("It's a draw");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                }


            }
        }
    }

    public void playAgain(View view) {

        LinearLayout layout = findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

        gameIsActive = true;

        TextView counterWhoWins = findViewById(R.id.counterWhoWins);

        counterWhoWins.setText("Cross " + crossCount + " : " + nullCount + " Null");



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView counterWhoWins = findViewById(R.id.counterWhoWins);

        counterWhoWins.setText("Cross " + crossCount + " : " + nullCount + " Null");




    }
}
