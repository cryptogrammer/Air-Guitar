package com.example.utkarshgagrg.pennappstesting;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnTouchListener;


public class MyActivity extends Activity {

    private Button chordG, chordC, chordD, chordEm, myoButton;
    private MediaPlayer successPlayer;
    private boolean[] strumG = {false,false,false};
    private boolean[] strumC = {false,false,false};
    private boolean[] strumD = {false,false,false};
    private boolean[] strumEm = {false,false,false};
    private int intensity =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        chordG = (Button) findViewById(R.id.chordG);
        chordG.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(intensity==0) {
                        strumG[0] = true;
                        return strumG[0];
                    }
                    else if(intensity==1) {
                        strumG[1] = true;
                        return strumG[1];
                    }
                    else if(intensity==2) {
                        strumG[2] = true;
                        return strumG[2];
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumG[0] = false;
                    strumG[1] = false;
                    strumG[2] = false;
                    return false;
                }
                return false;
            }
        });

        chordC = (Button) findViewById(R.id.chordC);
        chordC.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(intensity==0) {
                        strumC[0] = true;
                        return strumC[0];
                    }
                    else if(intensity==1) {
                        strumC[1] = true;
                        return strumC[1];
                    }
                    else if(intensity==2) {
                        strumC[2] = true;
                        return strumC[2];
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumC[0] = false;
                    strumC[1] = false;
                    strumC[2] = false;
                    return false;
                }
                return false;
            }
        });

        chordD = (Button) findViewById(R.id.chordD);
        chordD.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(intensity==0) {
                        strumD[0] = true;
                        return strumD[0];
                    }
                    else if(intensity==1) {
                        strumD[1] = true;
                        return strumD[1];
                    }
                    else if(intensity==2) {
                        strumD[2] = true;
                        return strumD[2];
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumD[0] = false;
                    strumD[1] = false;
                    strumD[2] = false;
                    return false;
                }
                return false;
            }
        });

        chordEm = (Button) findViewById(R.id.chordEm);
        chordEm.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(intensity==0) {
                        strumEm[0] = true;
                        return strumEm[0];
                    }
                    else if(intensity==1) {
                        strumEm[1] = true;
                        return strumEm[1];
                    }
                    else if(intensity==2) {
                        strumEm[2] = true;
                        return strumEm[2];
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumEm[0] = false;
                    strumEm[1] = false;
                    strumEm[2] = false;
                    return false;
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
                if (strumG[0]) {
                    playG();
                    return true;
                }
                else if (strumG[1]) {
                    playG();
                    return true;
                }
                else if (strumG[2]) {
                    playG();
                    return true;
                }
                else if (strumC[0]) {
                    playC();
                    return true;
                }
                else if (strumC[1]) {
                    playC();
                    return true;
                }
                else if (strumC[2]) {
                    playC();
                    return true;
                }
                else if (strumD[0]) {
                    playD();
                    return true;
                }
                else if (strumD[1]) {
                    playD();
                    return true;
                }
                else if (strumD[2]) {
                    playD();
                    return true;
                }
                else if (strumEm[0]) {
                    playEm();
                    return true;
                }
                else if (strumEm[1]) {
                    playEm();
                    return true;
                }
                else if (strumEm[2]) {
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
        successPlayer = MediaPlayer.create(this, R.raw.g_low);
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
        successPlayer = MediaPlayer.create(this, R.raw.c_low);
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
        successPlayer = MediaPlayer.create(this, R.raw.d_low);
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
        successPlayer = MediaPlayer.create(this, R.raw.em_low);
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
