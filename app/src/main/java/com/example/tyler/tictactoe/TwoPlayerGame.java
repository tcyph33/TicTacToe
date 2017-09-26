package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TwoPlayerGame extends AppCompatActivity {

    int count = 0;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onButtonClick(View v)
    {
        boolean player1_turn = true;
        Button button = (Button)findViewById(v.getId());    //Get id of button tapped

        //Check to see who's turn it is
        player1_turn = whichTurn(player1_turn);

        //Set button to either X or O
        if(player1_turn == true)
        {
            v.setBackgroundColor(Color.RED);
            button.setText("X");
        }
        else
        {
            v.setBackgroundColor(Color.GREEN);
            button.setText("O");
        }

        button.setEnabled(false);   //Do not allow to tap again

        //Check to see if there is a winner
        CheckWinner();

        //if there is a winner display a message that dismisses when screen is tapped
        //then reset the game by enabling the buttons and resetting the text, bgcolor, and count
    }

    private boolean whichTurn(boolean f)
    {
        count++;

        if(count % 2 == 1) //if the count is odd it is player 1's turn to place an X
        {
            f = true;
        }
        else
        {
            f = false;
        }

        return f;
    }

    private void CheckWinner()
    {
        Button b1 = (Button)findViewById(R.id.button);
        Button b2 = (Button)findViewById(R.id.button2);
        Button b3 = (Button)findViewById(R.id.button3);
        Button b4 = (Button)findViewById(R.id.button4);
        Button b5 = (Button)findViewById(R.id.button5);
        Button b6 = (Button)findViewById(R.id.button6);
        Button b7 = (Button)findViewById(R.id.button7);
        Button b8 = (Button)findViewById(R.id.button8);
        Button b9 = (Button)findViewById(R.id.button9);
        Button bArr[] = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        CharSequence t1 = b1.getText();
        CharSequence t2 = b2.getText();
        CharSequence t3 = b3.getText();
        CharSequence t4 = b4.getText();
        CharSequence t5 = b5.getText();
        CharSequence t6 = b6.getText();
        CharSequence t7 = b7.getText();
        CharSequence t8 = b8.getText();
        CharSequence t9 = b9.getText();
        boolean gameEnd = false;
        Intent intent = new Intent(this, DisplayWinner.class);

        //Check if X has 3 in a row
        if ((t1 == "X" && t2 == "X" && t3 == "X")
                || (t4 == "X" && t5 == "X" && t6 == "X")
                || (t7 == "X" && t8 == "X" && t9 == "X")
                || (t1 == "X" && t4 == "X" && t7 == "X")
                || (t2 == "X" && t5 == "X" && t8 == "X")
                || (t3 == "X" && t6 == "X" && t9 == "X")
                || (t1 == "X" && t5 == "X" && t9 == "X")
                || (t3 == "X" && t5 == "X" && t7 == "X"))
        {
            //display X is a winner stuff
            intent.putExtra("winner", 'X');
            startActivity(intent);
            gameEnd = true;
        }

        //Check if O has 3 in a row
        if ((t1 == "O" && t2 == "O" && t3 == "O")
                || (t4 == "O" && t5 == "O" && t6 == "O")
                || (t7 == "O" && t8 == "O" && t9 == "O")
                || (t1 == "O" && t4 == "O" && t7 == "O")
                || (t2 == "O" && t5 == "O" && t8 == "O")
                || (t3 == "O" && t6 == "O" && t9 == "O")
                || (t1 == "O" && t5 == "O" && t9 == "O")
                || (t3 == "O" && t5 == "O" && t7 == "O"))
        {
            //display O is a winner stuff
            intent.putExtra("winner", 'O');
            startActivity(intent);
            gameEnd = true;
        }



        //Check if it is a tie (all text has an X or O)
        if(count == 9 && gameEnd != true)
        {
            //display that they are too evenly matched
            intent.putExtra("winner", 'T');
            startActivity(intent);
            gameEnd = true;
        }

        //If game has ended
        if(gameEnd == true)
        {
            mainActivity.endGame(bArr);
        }

    }

}
