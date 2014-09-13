package com.example.utkarshgagrg.pennappstesting;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnTouchListener;


public class MyActivity extends Activity {

    private Button soundButton, myoButton;
    private MediaPlayer successPlayer;
    private boolean strum = false;
    private GestureDetector gd;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        myoButton = (Button) findViewById(R.id.myo);
        myoButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strum = true;
                    return strum;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strum = false;
                    return strum;
                }
                return false;
            }
        });
        GestureDetector.SimpleOnGestureListener sogl =
                new GestureDetector.SimpleOnGestureListener() {

                    public boolean onDown(MotionEvent event) {
                        return true;
                    }

                    public void onLongPress(MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_MOVE){
                            playSuccess();
                        }
                    }
                };
        // OK. it works. Need to interchange the functionality of the
        // 2 buttons. make the chord a boolean and and the strum play sound.
        gd = new GestureDetector(context, sogl);
        soundButton = (Button) findViewById(R.id.soundButton);
        soundButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(strum) {
                    playSuccess();
                    return true;
                }
                return false;
            }
        });
//            public boolean onTouch(View v, MotionEvent m) {
//                Log.i("TEST", m.getAction() + "");
//                if (gd.onTouchEvent(m) && strum) {
//                    playSuccess();
//                    return true;
//                }
//                return false;
//            }
//
//        });
    }


    private void playSuccess() {
        successPlayer = new MediaPlayer();
        successPlayer = MediaPlayer.create(this, R.raw.playsoundtest);
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
