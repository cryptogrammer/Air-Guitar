package com.example.utkarshgagrg.pennappstesting;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnTouchListener;


public class MyActivity extends Activity {

    private Button chordG, chordC, chordD, chordEm, myoButton;
    private MediaPlayer successPlayer;
    private boolean strumG = false;
    private boolean strumC = false;
    private boolean strumD = false;
    private boolean strumEm = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        chordG = (Button) findViewById(R.id.chordG);
        chordG.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumG = true;
                    return strumG;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumG = false;
                    return strumG;
                }
                return false;
            }
        });

        chordC = (Button) findViewById(R.id.chordC);
        chordC.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumC = true;
                    return strumC;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumC = false;
                    return strumC;
                }
                return false;
            }
        });

        chordD = (Button) findViewById(R.id.chordD);
        chordD.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumD = true;
                    return strumD;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumD = false;
                    return strumD;
                }
                return false;
            }
        });

        chordEm = (Button) findViewById(R.id.chordEm);
        chordEm.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumEm = true;
                    return strumEm;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumEm = false;
                    return strumEm;
                }
                return false;
            }
        });

        // OK. it works. Need to interchange the functionality of the
        // 2 buttons. make the chord a boolean and and the strum play sound.
        myoButton = (Button) findViewById(R.id.myo);
        myoButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (strumG) {
                    playG();
                    return true;
                }
                if (strumC) {
                    playC();
                    return true;
                }
                if (strumD) {
                    playD();
                    return true;
                }
                if (strumEm) {
                    playEm();
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


    private void playG() {
        successPlayer = new MediaPlayer();
        successPlayer = MediaPlayer.create(this, R.raw.g);
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void playC() {
        successPlayer = new MediaPlayer();
        successPlayer = MediaPlayer.create(this, R.raw.c);
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void playD() {
        successPlayer = new MediaPlayer();
        successPlayer = MediaPlayer.create(this, R.raw.d);
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void playEm() {
        successPlayer = new MediaPlayer();
        successPlayer = MediaPlayer.create(this, R.raw.em);
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
