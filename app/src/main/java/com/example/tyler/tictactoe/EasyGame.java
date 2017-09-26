package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class EasyGame extends AppCompatActivity {

    MainActivity mainAct = new MainActivity();
    DifficultyLevel level = new DifficultyLevel();
    boolean markArr[] = level.getMarked();
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onButtonClick(View v) {
        Button button = (Button) findViewById(v.getId());    //Get id of button tapped
        Button bArr[] = {(Button) findViewById(R.id.button),
                (Button) findViewById(R.id.button2),
                (Button) findViewById(R.id.button3),
                (Button) findViewById(R.id.button4),
                (Button) findViewById(R.id.button5),
                (Button) findViewById(R.id.button6),
                (Button) findViewById(R.id.button7),
                (Button) findViewById(R.id.button8),
                (Button) findViewById(R.id.button9)};
        int value;
        boolean OWin,
                XWin,
                tie,
                blocked;

        //Set X mark
        level.SetMark_X(bArr, button);


        //Check if player won
        XWin = level.CheckWinner_X();

        if(XWin)
        {
            Intent intent = new Intent(this, DisplayWinner.class);
            intent.putExtra("winner", 'P');
            startActivity(intent);
            mainAct.endGame(bArr); //Game has ended
            return;
        }

        //Check to see if all places are marked (9 spots so it will end on an X placement)
        tie = level.isTie();

        if(tie)
        {
            Intent intent = new Intent(this, DisplayWinner.class);
            intent.putExtra("winner", 'T');
            startActivity(intent);
            mainAct.endGame(bArr); //Game has ended
            return;
        }


        //Check if two Os in a row and place one in the third spot if it is available
        OWin = level.TwoInARow_O(bArr);

        if (OWin) {
            //display O is a winner stuff
            Intent intent = new Intent(this, DisplayWinner.class);
            intent.putExtra("winner", 'C');
            startActivity(intent);
            mainAct.endGame(bArr); //Game has ended
            return;
        }


        //Check if two X's in a row and block if there are
        blocked = level.TwoInARow_X(bArr);

        if (!blocked) //Did not find two x's in a row/place an O
        {
            //Generate numbers until it finds one that is not yet marked
            do {
                value = level.getRandom(9);
            } while (markArr[value]);

            level.PlaceMark(bArr, value);
        } else {
            //If blocked a win, display a toast first time
            if (count == 0) {
                Toast.makeText(getApplicationContext(), "You didn't think 'EASY' meant it would be that easy did you?",
                        Toast.LENGTH_SHORT).show();
                count++;
            }
        }

    }
    


}
