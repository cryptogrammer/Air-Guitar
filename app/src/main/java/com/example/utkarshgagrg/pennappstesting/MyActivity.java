package com.example.utkarshgagrg.pennappstesting;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;

import static android.view.View.OnTouchListener;


public class MyActivity extends Activity {

    private Button chordG, chordC, chordD, chordEm, myoButton;
    private MediaPlayer successPlayer;
    private boolean strumG = false;
    private boolean strumC = false;
    private boolean strumD = false;
    private boolean strumEm = false;
    private int intensity = 0;
    private float originalPitch = 0;
    private float newPitch = 0;
    private String direction = "up";


    private Vector3 gravity = new Vector3();
    private Vector3 acceleration = new Vector3();

    private double rawIntensity = 0.0;


    // This code will be returned in onActivityResult() when the enable Bluetooth activity exits.
    private static final int REQUEST_ENABLE_BT = 1;


    // Classes that inherit from AbstractDeviceListener can be used to receive events from Myo devices.
    // If you do not override an event, the default behavior is to do nothing.
    private DeviceListener mListener = new AbstractDeviceListener() {

        private Arm mArm = Arm.UNKNOWN;
        private XDirection mXDirection = XDirection.UNKNOWN;

        // onConnect() is called whenever a Myo has been connected.
        @Override
        public void onConnect(Myo myo, long timestamp) {
            // Set the text color of the text view to cyan when a Myo connects.
            Log.i("Connected!", "CONNECTED");
        }

        // onDisconnect() is called whenever a Myo has been disconnected.
        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            // Set the text color of the text view to red when a Myo disconnects.
            Log.i("NOTNOT Connected!", "NOT CONNECTED");
        }

        // onArmRecognized() is called whenever Myo has recognized a setup gesture after someone has put it on their
        // arm. This lets Myo know which arm it's on and which way it's facing.
        @Override
        public void onArmRecognized(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
            mArm = arm;
            mXDirection = xDirection;
        }

        // onArmLost() is called whenever Myo has detected that it was moved from a stable position on a person's arm after
        // it recognized the arm. Typically this happens when someone takes Myo off of their arm, but it can also happen
        // when Myo is moved around on the arm.
        @Override
        public void onArmLost(Myo myo, long timestamp) {
            mArm = Arm.UNKNOWN;
            mXDirection = XDirection.UNKNOWN;
        }

        // onOrientationData() is called whenever a Myo provides its current orientation,
        // represented as a quaternion.
        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            // Calculate Euler angles (roll, pitch, and yaw) from the quaternion.
            float roll = (float) Math.toDegrees(Quaternion.roll(rotation));
            newPitch = (float) Math.toDegrees(Quaternion.pitch(rotation));
            float yaw = (float) Math.toDegrees(Quaternion.yaw(rotation));
            checkPitch();


            // Adjust roll and pitch for the orientation of the Myo on the arm.
            if (mXDirection == XDirection.TOWARD_ELBOW) {
                roll *= -1;
                newPitch *= -1;
            }

            // Next, we apply a rotation to the text view using the roll, pitch, and yaw.
//            mTextView.setRotation(roll);
//            mTextView.setRotationX(newPitch);
//            mTextView.setRotationY(yaw);
        }


        public void checkPitch() {
            float difference = newPitch - originalPitch;
            if (Math.abs(difference) > 10) {
                System.out.println("CHANGE");
                originalPitch = newPitch;
                Log.i("INTENSITY:", rawIntensity+"");

                //if(rawIntensity == Double.NaN) intensity = -1;

                Log.i("CHANGE: INTENSITY", intensity + "");

                if (difference < 0) {
                    // down to up
                    String newDirection = "up";
                    Log.i("CHANGE: UP","");
                    if (!direction.equals(newDirection)) {
                        direction = newDirection;
                        strumMethod();
                    }

                } else {
                    // up to down
                    String newDirection = "down";
                    Log.i("CHANGE: DOWN","");
                    if (!direction.equals(newDirection)) {
                        direction = newDirection;
                        strumMethodUp();
                        return ;
                    }
                }
            }
        }

        public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
            double alpha = 0.8;

            gravity = new Vector3(alpha * gravity.x() + (1-alpha) * accel.x(),
                    alpha * gravity.y() + (1-alpha) * accel.y(),
                    alpha * gravity.z() + (1-alpha) * accel.z());

            acceleration = new Vector3(accel.x() - gravity.x(),
                    accel.y() - gravity.y(),
                    accel.z() - gravity.z());

            rawIntensity = Math.sqrt(acceleration.x() * acceleration.x() +
                    acceleration.y() + acceleration.y() +
                    acceleration.z() + acceleration.z());

        }

        // onPose() is called whenever a Myo provides a new pose.
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
                    Log.i("UNKNOWN","");
                    break;
                case REST:
                    int restTextId = R.string.hello_world;
                    switch (mArm) {
                        case LEFT:
                            restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            restTextId = R.string.arm_right;
                            break;
                    }
                    Log.i("UNKNOWN","");
                    break;
                case FIST:
                    Log.i("UNKNOWN","");
                    break;
                case WAVE_IN:
                    Log.i("UNKNOWN","");
                    break;
                case WAVE_OUT:
                    Log.i("UNKNOWN","");
                    break;
                case FINGERS_SPREAD:
                    Log.i("UNKNOWN","");
                    break;
                case THUMB_TO_PINKY:
                    Log.i("UNKNOWN","");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        chordG = (Button) findViewById(R.id.chordG);
        chordG.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chordG.setBackgroundResource(R.drawable.bluechord);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        strumG = true;
                        return strumG;
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        strumG = false;
                        chordG.setBackgroundResource(R.drawable.unpressedbluechord);
                        return false;
                }
                return false;
            }
        });

        chordC = (Button) findViewById(R.id.chordC);
        chordC.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chordC.setBackgroundResource(R.drawable.redchord);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumC = true;
                    return strumC;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumC = false;
                    chordC.setBackgroundResource(R.drawable.unpressedredchord);
                    return false;
                }
                return false;
            }
        });

        chordD = (Button) findViewById(R.id.chordD);
        chordD.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chordD.setBackgroundResource(R.drawable.yellowchord);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumD = true;
                    return strumD;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumD = false;
                    chordD.setBackgroundResource(R.drawable.unpressedyellowchord);
                    return false;
                }
                return false;
            }
        });

        chordEm = (Button) findViewById(R.id.chordEm);
        chordEm.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chordEm.setBackgroundResource(R.drawable.greenchord);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    strumEm = true;
                    return strumEm;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    strumEm = false;
                    chordEm.setBackgroundResource(R.drawable.unpressedgreenchord);
                    return false;
                }
                return false;
            }
        });

        hub.addListener(mListener);
    }

    private void strumMethod(){

        if (strumG) {
            downPlayG();
        }
        else if (strumC) {
            downPlayC();
        }
        else if (strumD) {
            downPlayD();
        }
        else if (strumEm) {
            downPlayEm();
        }
    }

    private void strumMethodUp(){

        if (strumG) {
            upPlayG();
        }
        else if (strumC) {
            upPlayC();
        }
        else if (strumD) {
            upPlayD();
        }
        else if (strumEm) {
            upPlayEm();
        }
    }


    private void downPlayG() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.g_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.g_down_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void upPlayG() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.g_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.g_up_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void downPlayC() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.c_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.c_down_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void upPlayC() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.c_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.c_up_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void downPlayD() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.d_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.d_down_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void upPlayD() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.d_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.d_up_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void downPlayEm() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.em_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.em_down_low);
        }
        successPlayer.setLooping(false);
        successPlayer.start();

        successPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                successPlayer.release();
            }
        });

    }

    private void upPlayEm() {
        successPlayer = new MediaPlayer();
        if(intensity==0) {
            successPlayer = MediaPlayer.create(this, R.raw.em_short);
        }
        else if (intensity==1) {
            successPlayer = MediaPlayer.create(this, R.raw.em_up_low);
        }
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
    protected void onResume() {
        super.onResume();

        // If Bluetooth is not enabled, request to turn it on.
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We don't want any callbacks when the Activity is gone, so unregister the listener.
        Hub.getInstance().removeListener(mListener);

        if (isFinishing()) {
            // The Activity is finishing, so shutdown the Hub. This will disconnect from the Myo.
            Hub.getInstance().shutdown();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth, so exit.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.action_scan == id) {
            onScanActionSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }
}
