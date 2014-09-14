package com.example.utkarshgagrg.pennappstesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.utkarshgagrg.pennappstesting.R;

public class SyncoDeMyo extends Activity {

    Button syncoDeMayoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synco_de_myo);

        syncoDeMayoBtn = (Button)findViewById(R.id.syncoDeMyoButton);
    }

    public void onSyncoClick(View view) {
        Intent intent = new Intent(this, AirGuitarActivity.class);
        startActivity(intent);
    }
}
