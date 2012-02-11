package com.ritmubi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class VariosDiasActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varios_dias);
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "Varios Dias, Presentacion: "+
        		bundle.getString("tipoPresentacion"), Toast.LENGTH_LONG).show();
    }
}
