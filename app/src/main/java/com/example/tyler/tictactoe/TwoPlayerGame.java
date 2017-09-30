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
        boolean player1_turn;
        Button button = (Button)findViewById(v.getId());    //Get id of button tapped

        //Check to see who's turn it is
        player1_turn = whichTurn();

        //Set button to either X or O
        if(player1_turn)
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

    }

    private boolean whichTurn()
    {
        count++;

        //if the count is odd it is player 1's turn to place an X so it returns true
        return count % 2 == 1;
    }

    private void CheckWinner()
    {
        Button bArr[] = {
                (Button)findViewById(R.id.button),
                (Button)findViewById(R.id.button2),
                (Button)findViewById(R.id.button3),
                (Button)findViewById(R.id.button4),
                (Button)findViewById(R.id.button5),
                (Button)findViewById(R.id.button6),
                (Button)findViewById(R.id.button7),
                (Button)findViewById(R.id.button8),
                (Button)findViewById(R.id.button9)};
        CharSequence txtArr[] = {
                bArr[0].getText(),
                bArr[1].getText(),
                bArr[2].getText(),
                bArr[3].getText(),
                bArr[4].getText(),
                bArr[5].getText(),
                bArr[6].getText(),
                bArr[7].getText(),
                bArr[8].getText()};
        boolean gameEnd = false;
        Intent intent = new Intent(this, DisplayWinner.class);

        //Check if X has 3 in a row
        if ((txtArr[0] == "X" && txtArr[1] == "X" && txtArr[2] == "X")
                || (txtArr[3] == "X" && txtArr[4] == "X" && txtArr[5] == "X")
                || (txtArr[6] == "X" && txtArr[7] == "X" && txtArr[8] == "X")
                || (txtArr[0] == "X" && txtArr[3] == "X" && txtArr[6] == "X")
                || (txtArr[1] == "X" && txtArr[4] == "X" && txtArr[7] == "X")
                || (txtArr[2] == "X" && txtArr[5] == "X" && txtArr[8] == "X")
                || (txtArr[0] == "X" && txtArr[4] == "X" && txtArr[8] == "X")
                || (txtArr[2] == "X" && txtArr[4] == "X" && txtArr[6] == "X"))
        {
            //display X is a winner stuff
            intent.putExtra("winner", 'X');
            startActivity(intent);
            gameEnd = true;
        }

        //Check if O has 3 in a row
        if ((txtArr[0] == "O" && txtArr[1] == "O" && txtArr[2] == "O")
                || (txtArr[3] == "O" && txtArr[4] == "O" && txtArr[5] == "O")
                || (txtArr[6] == "O" && txtArr[7] == "O" && txtArr[8] == "O")
                || (txtArr[0] == "O" && txtArr[3] == "O" && txtArr[6] == "O")
                || (txtArr[1] == "O" && txtArr[4] == "O" && txtArr[7] == "O")
                || (txtArr[2] == "O" && txtArr[5] == "O" && txtArr[8] == "O")
                || (txtArr[0] == "O" && txtArr[4] == "O" && txtArr[8] == "O")
                || (txtArr[2] == "O" && txtArr[4] == "O" && txtArr[6] == "O"))
        {
            //display O is a winner stuff
            intent.putExtra("winner", 'O');
            startActivity(intent);
            gameEnd = true;
        }



        //Check if it is a tie (all text has an X or O)
        if(count == 9 && !gameEnd)
        {
            //display that they are too evenly matched
            intent.putExtra("winner", 'T');
            startActivity(intent);
            gameEnd = true;
        }

        //If game has ended
        if(gameEnd)
        {
            mainActivity.endGame(bArr);
        }

    }

}
