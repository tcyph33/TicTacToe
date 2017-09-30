package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    /* Button Layout For The Game Is As Follows

    *    1    2    3
    *    4    5    6
    *    7    8    9
    *
    *  Button Layout In Terms Of Array Indices Is As Follows
    *
    *   [0]  [1]  [2]
    *   [3]  [4]  [5]
    *   [6]  [7]  [8]
    *
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void on1PlayerClick(View v)
    {
        Intent intent = new Intent(this, DifficultyLevel.class);
        startActivity(intent);
    }

    public void on2PlayerClick(View v)
    {
        Intent intent = new Intent(this, TwoPlayerGame.class);
        startActivity(intent);
    }

    public void onRGClick(View v)
    {
        Intent intent = new Intent(this, RedVsGreen.class);
        startActivity(intent);
    }

    protected void endGame(Button[] bArr)
    {
        //If users use the UI back button they cannot press any more buttons
        for(Button b : bArr)
        {
            b.setEnabled(false);
        }

    }

}


