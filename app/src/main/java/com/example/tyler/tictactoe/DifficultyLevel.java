package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class DifficultyLevel extends AppCompatActivity {

    Random random = new Random();
    boolean marked[] = {false, false, false, false, false, false, false, false, false};
    boolean X_marked[] = {false, false, false, false, false, false, false, false, false};
    boolean O_marked[] = {false, false, false, false, false, false, false, false, false};
    int turnCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onEasyClick(View v)
    {
       Intent intent = new Intent(this, EasyGame.class);
       startActivity(intent);
    }

    public void onHardClick(View v)
    {
       Intent intent = new Intent(this, HardGame.class);
       startActivity(intent);
    }

    public void onImpossibleClick(View v)
    {
       Intent intent = new Intent(this, ImpossibleGame.class);
       startActivity(intent);
    }

    //This checks if there are two Os in a row and places a third O to win
    protected boolean TwoInARow_O(Button [] bArr)
    {
        int val;

        //Check if two Os in a row and then find val to get 3 in a row
        for(int i = 0; i < O_marked.length; i++)
        {
            if (!O_marked[i]) //if first index isn't marked don't bother finding second index
                continue;

            for(int j = i + 1; j < O_marked.length; j++)
            {
                if(O_marked[j]) //both marked and not the same
                {
                    //if one of index is middle
                    if(j == 4)
                    {
                        val = (j + j) - i;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }

                        continue;
                    }

                    //if other index is middle
                    if(i == 4)
                    {
                        val = (i + i) - j;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }

                        continue;
                    }

                    //If they are next to each other vertically
                    if(j - i == 3)
                    {
                        if(i < 3)
                        {
                            val = j + 3;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }else
                        {
                            val = i - 3;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }

                        continue;
                    }

                    //next to each other horizontally
                    if (j - i == 1 && j != 3 && j != 6)
                    {
                        if (j == 1 || j == 7) {
                            val = j + 1;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        } else {
                            val = i - 1;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }

                        continue;
                    }

                    //If they are opposite of each other
                    if ((i+j)%2 == 0)
                    {
                        val = (i + j) / 2;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    //This function finds if there are two Xs in a row and blocks it with an O
    protected boolean TwoInARow_X(Button [] bArr)
    {
        int val;

        //Check if two Xs in a row and then find val to get 3 in a row
        for(int i = 0; i < X_marked.length; i++)
        {
            if (!X_marked[i]) //if first index isn't marked don't bother finding second index
                continue;

            for(int j = i + 1; j < X_marked.length; j++)
            {
                if(X_marked[j]) //both marked and not the same
                {
                    //if one of index is middle
                    if(j == 4)
                    {
                        val = (j + j) - i;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }

                        continue;
                    }

                    //if other index is middle
                    if(i == 4)
                    {
                        val = (i + i) - j;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }

                        continue;
                    }

                    //If they are next to each other vertically
                    if(j - i == 3)
                    {
                        if(i < 3)
                        {
                            val = j + 3;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }else
                        {
                            val = i - 3;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }

                        continue;
                    }

                    //next to each other horizontally
                    if (j - i == 1 && j != 3 && j != 6)
                    {
                        if (j == 1 || j == 7) {
                            val = j + 1;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        } else {
                            val = i - 1;
                            //Mark value if not yet taken
                            if (!marked[val]) {
                                PlaceMark(bArr, val);
                                return true;
                            }
                        }

                        continue;
                    }

                    //If they are opposite of each other
                    if ((i+j)%2 == 0)
                    {
                        val = (i + j) / 2;
                        //Mark value if not yet taken
                        if (!marked[val]) {
                            PlaceMark(bArr, val);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    //Marks the button the user tapped with an X
    protected void SetMark_X(Button[] bArr, Button button)
    {
        //Change tapped button to Red
        button.setBackgroundColor(Color.RED);
        button.setText("X");
        button.setEnabled(false); //Do not allow to tap again

        //set button as marked
        for(int i = 0; i < bArr.length; i++)
        {
            if(bArr[i].getId() == button.getId() && !marked[i])
            {
                marked[i] = true;
                X_marked[i] = true;
            }
        }

        turnCount++;
    }

    //Places an O
    protected void PlaceMark(Button [] bArr, int val)
    {
        //mark button as green O
        bArr[val].setBackgroundColor(Color.GREEN);
        bArr[val].setText("O");
        bArr[val].setEnabled(false);
        marked[val] = true;
        O_marked[val] = true;
        turnCount++;
    }

    //Checks if user's X placement has won the game
    protected boolean CheckWinner_X()
    {
        //Checks if one of the 8 ways to win are marked by the user
        return (X_marked[0] && X_marked[1] && X_marked[2])
                || (X_marked[3] && X_marked[4] && X_marked[5])
                || (X_marked[6] && X_marked[7] && X_marked[8])
                || (X_marked[0] && X_marked[3] && X_marked[6])
                || (X_marked[1] && X_marked[4] && X_marked[7])
                || (X_marked[2] && X_marked[5] && X_marked[8])
                || (X_marked[0] && X_marked[4] && X_marked[8])
                || (X_marked[2] && X_marked[4] && X_marked[6]);
    }

    //Returns true if all buttons are marked
    protected boolean isTie()
    {
        return turnCount == 9;
    }

    //find which button was pressed
    protected int getButton(Button [] bArr, Button b)
    {
        int val = 9999;

        for(int i = 0; i < bArr.length; i++)
        {
            if(bArr[i].getId() == b.getId())
                val = i;
        }

        return val;
    }

    //Returns marked array
    protected boolean [] getMarked()
    {
        return marked;
    }

    //Returns true if a position is marked
    protected boolean isMarked(int x) { return marked[x]; }

    //Returns random number
    protected int getRandom(int x)
    {
        return random.nextInt(x);
    }


}
