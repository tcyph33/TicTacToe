package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class ImpossibleGame extends AppCompatActivity {

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
        setContentView(R.layout.activity_impossible_game);
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
            intent.putExtra("winner", 'I');
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

                value = level.getRandom(100); //For Impossible use 0-99
                if(value < 99)  //1% chance of being given a chance to win
                {
                    if(level.isMarked(4)){ //if middle is already taken

                        if(OMiddle)     //if middle was marked with O
                        {

                            //place so that it is still a 1% chance that it is placed in a corner
                            //being placed in a corner here allows player to win and we don't want that here
                            CornerPlaced(bArr);
                        }else {
                            PlaceMarkImpossible(bArr);  //Place so more chances it DOES go to corners
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
                    PlaceMarkImpossible(bArr);
                }

            }

        }

    }

    private void PlaceMarkImpossible(Button [] bArr)
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
                if (count < 5)   //For Impossible level, try to get an even position 6 times or else on 6th time just use the number
                {               //.5^6 is about 1% chance
                    count++;
                    PlaceMarkImpossible(bArr); //Run function again
                } else {
                    level.PlaceMark(bArr, value);
                    count = 0;
                }
            }
        }else
        {
            //If already marked
            count++;
            PlaceMarkImpossible(bArr);
        }
    }

    //Used in case that they placed a corner, O was put in middle, and they placed in opposite corner
    //This will make it so that it is very likely to place an O that is NOT one of the last 2 corners
    private void CornerPlaced(Button [] bArr)
    {

        //implement algorithm here
        value = r.nextInt(9);

        if(!level.isMarked(value)) //if unmarked
        {
            if (value % 2 == 1) //if odd
            {
                level.PlaceMark(bArr, value);
                count = 0;
            } else {
                if (count < 3)   //For Impossible level, try to get an odd position 4 times or else on 4th time just use the number
                                //33% probability that a corner is picked so .33^4 = 1% chance it is in corner
                {
                    count++;
                    CornerPlaced(bArr); //Run function again
                } else {
                    level.PlaceMark(bArr, value);
                    count = 0;
                }
            }
        }else
        {
            //If already marked
            count++;
            CornerPlaced(bArr);
        }
    }
}
