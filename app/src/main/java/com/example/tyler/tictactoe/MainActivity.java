package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


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
        for(int i = 0; i < bArr.length; i++)
        {
            bArr[i].setEnabled(false);
        }

    }

}


