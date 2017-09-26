package com.example.tyler.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DisplayWinner extends AppCompatActivity {


    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_winner);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        //Get extra
        Bundle bundle = getIntent().getExtras();
        char c = bundle.getChar("winner");
        TextView txtView = (TextView)findViewById(R.id.fullscreen_content);

        if (c == 'C' || c =='G')
        {
            //short time delay to see how game ended in case they didn't realize how they lost
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        //set background color based on winner
        switch (c){
            case 'X': txtView.setText("X's win!! \n \n Begin mocking the loser");
                      txtView.setBackgroundColor(0xFFFF0000);
                      break;
            case 'O': txtView.setText("O's win!! \n \n Begin mocking the loser");
                      txtView.setBackgroundColor(0xFF00FF00);
                      break;
            case 'T': txtView.setText("You are too evenly matched!");
                      txtView.setBackgroundColor(0xFFa1a1a1);
                      break;
            case 'C': txtView.setText("O's Win!! \n \n How could you let your own phone beat you???");
                      txtView.setBackgroundColor(0xFF00FF00);
                      break;
            case 'P': txtView.setText("You Win!! \n \n You can now feel accomplished.");
                      txtView.setBackgroundColor(0xFFFF0000);
                      break;
            case 'H': txtView.setText("You Win!! \n \n I'm proud of you!");
                      txtView.setBackgroundColor(0xFFFF0000);
                      break;
            case 'I': txtView.setText("You Win!! \n \n I now grant you the rank of Master.");
                      txtView.setBackgroundColor(0xFFFF0000);
                      break;
            case 'G': txtView.setText("Go Green Team! \n Green is better than all colors. Remember that!");
                      txtView.setBackgroundColor(0xFF00FF00);
                      break;
        }


    }

    public void onScreenTap(View v)
    {
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }

    @Override

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(000);
    }



    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
    */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }




}
