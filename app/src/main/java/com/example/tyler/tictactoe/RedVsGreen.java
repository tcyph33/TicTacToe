package com.example.tyler.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RedVsGreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_vs_green);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    final int notSet = 9999;
    int count = 0,
        mark1 = notSet,
        mark2 = notSet,
        greenMark = notSet;


    public void onRGButtonClick(View v)
    {
        Button button = (Button)findViewById(v.getId());    //Get id of button tapped
        Button b1 = (Button)findViewById(R.id.RGbutton);
        Button b2 = (Button)findViewById(R.id.RGbutton2);
        Button b3 = (Button)findViewById(R.id.RGbutton3);
        Button b4 = (Button)findViewById(R.id.RGbutton4);
        Button b5 = (Button)findViewById(R.id.RGbutton5);
        Button b6 = (Button)findViewById(R.id.RGbutton6);
        Button b7 = (Button)findViewById(R.id.RGbutton7);
        Button b8 = (Button)findViewById(R.id.RGbutton8);
        Button b9 = (Button)findViewById(R.id.RGbutton9);
        Button bArr[] = {b1, b2, b3, b4, b5, b6, b7, b8, b9};



        //Change button to Red
        button.setBackgroundColor(Color.RED);
        button.setText("R");
        button.setEnabled(false); //Do not allow to tap again

        //set button as marked. If its the third time, skip this as all buttons will be set to Green soon
        if(count != 2) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i].getId() == button.getId()) {
                    if (count == 0) {
                        mark1 = i;
                    } else {
                        mark2 = i;
                    }
                }
            }
        }


        //place green mark
        setGreenMark(bArr);


    }

    private void setGreenMark(Button [] bArr)
    {
        Random rand = new Random();
        int val;
        MainActivity mainActivity = new MainActivity();

        if(count != 2)
        {

            //Generate numbers until it finds one that is not yet marked
            do {
                val = rand.nextInt(9);
            }while(val == mark1 || val == mark2 || val == greenMark);

            //mark button as green
            bArr[val].setBackgroundColor(Color.GREEN);
            bArr[val].setText("G");
            bArr[val].setEnabled(false);
            greenMark = val;
            count++;
        }else
        {
            //Mark all buttons Green
            for(int i = 0; i < bArr.length; i++)
            {
                bArr[i].setBackgroundColor(Color.GREEN);
                bArr[i].setText("G");
            }

            //call activity that green has won
            Intent intent = new Intent(this, DisplayWinner.class);
            intent.putExtra("winner", 'G');
            startActivity(intent);

            //reset game
            mainActivity.endGame(bArr);
            count = 0;
        }
    }


}

