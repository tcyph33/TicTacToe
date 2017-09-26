package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class HardGame extends AppCompatActivity {

    MainActivity mainAct = new MainActivity();
    DifficultyLevel level = new DifficultyLevel();
    Random r = new Random();
    boolean markArr[] = level.getMarked(),
            OMiddle = false,
            firstMark;
    int xVal,
        value,
        count = 0,
        turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onButtonClick(View v)
    {
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
        boolean OWin,
                XWin,
                tie,
                blocked;

        //Set X mark
        level.SetMark_X(bArr, button);

        //Find which button was tapped
        xVal = level.getButton(bArr, button);

        //if it is the first mark being placed, set first mark true so that it can be used later
        //to reduce chances of the "secret"
        if(turnCount == 0) {
            firstMark = true;
            turnCount++;
        }else
        {
            firstMark = false;
            turnCount++;
        }

        //Check if player won
        XWin = level.CheckWinner_X();

        if(XWin)
        {
            Intent intent = new Intent(this, DisplayWinner.class);
            intent.putExtra("winner", 'H');
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
            if(xVal == 0 || xVal == 2 || xVal == 6 || xVal == 8) //Mark placed was in a corner
            {

                value = level.getRandom(4); //for hard use 0-3
                if(value < 3)    //25% chance that they could win
                {
                    if(level.isMarked(4)){ //if middle is already taken

                        if(OMiddle)     //if middle was marked with O
                        {
                            //**When implementing this in Impossible, instead of random, make the same
                            //**Algorithm as PlaceMarkHard but change it to if odd so 1% chance of winning
                            //**is maintained ****delete this note later****
                            //place randomly so more chances it does not go to corners
                            do {
                                value = level.getRandom(9);
                            } while (markArr[value]);

                            level.PlaceMark(bArr, value);
                        }else {
                            PlaceMarkHard(bArr);  //Place so more chances it DOES go to corners
                        }
                    }else{
                        level.PlaceMark(bArr, 4); //Place an O in the middle
                        OMiddle = true;
                    }

                }else
                {
                    //place randomly
                    do {
                         value = level.getRandom(9);
                    } while (markArr[value]);

                    level.PlaceMark(bArr, value);
                }
            }else {

                //If first X was not put in the middle or corners it will get to this code
                //This reduces probability of the secret happens
               if(firstMark && !level.isMarked(4)) {
                   level.PlaceMark(bArr, 4);
               }
                else {
                   PlaceMarkHard(bArr);
               }

            }

        }

    }

    private void PlaceMarkHard(Button [] bArr)
    {

        //implement algorithm here
        value = r.nextInt(9);

        if(!level.isMarked(value)) //if unmarked
        {
            if (value % 2 == 0) //if even
            {
                level.PlaceMark(bArr, value);
                count = 0;
            } else {
                if (count < 1)   //For hard level, try to get an even position 2 times or else on 2nd time just use the number
                {               //.5^2 is about 25% they could win
                    count++;
                    PlaceMarkHard(bArr); //Run function again
                } else {
                    level.PlaceMark(bArr, value);
                    count = 0;
                }
            }
        }else
        {
            //If already marked
            count++;
            PlaceMarkHard(bArr);
        }
    }
}
