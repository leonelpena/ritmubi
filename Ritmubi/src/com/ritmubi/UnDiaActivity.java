package com.ritmubi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class UnDiaActivity extends Activity {
	//private short tipoPresentacion;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_dia);
        //this.tipoPresentacion = tipoPresentacion;
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "Un Dia, Presentacion: "+
        		bundle.getString("tipoPresentacion"), Toast.LENGTH_LONG).show();
    }
}
