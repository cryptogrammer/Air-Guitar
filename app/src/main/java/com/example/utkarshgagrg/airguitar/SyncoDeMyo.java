package com.example.utkarshgagrg.airguitar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SyncoDeMyo extends Activity {

    Button syncoDeMayoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synco_de_myo);

        syncoDeMayoBtn = (Button)findViewById(R.id.syncoDeMyoButton);
    }

    public void onSyncoClick(View view) {
        System.gc();
        Intent intent = new Intent(this, AirGuitarActivity.class);
        startActivity(intent);
        finish();
    }
}
