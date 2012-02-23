package com.ritmubi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
//import android.widget.Toast;

public class RitmubiActivity extends Activity {
	
	private Button botonSiguiente;
	private RadioButton 
				botonUnDia,
				botonVariosDias;

	//private Date fechaNacimiento;
	private DatePicker fechaNacimientoPicker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //fechaNacimiento = new Date();
        botonSiguiente = (Button) findViewById(R.id.botonSiguiente);
        botonUnDia = (RadioButton) findViewById(R.id.botonUnDia);
        botonVariosDias = (RadioButton) findViewById(R.id.botonVariosDias);
        fechaNacimientoPicker = (DatePicker) findViewById(R.id.selectorFechaNacimiento);
        
        // Accion por defecto
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setActionUnDia();
			}
		});

        botonUnDia.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setActionUnDia();
			}
		});
        
        botonVariosDias.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setActionVariosDias();
			}
		});
    }

    private void setActionUnDia() {
    	botonSiguiente.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Un Dia, "+tipoPresentacion, Toast.LENGTH_LONG).show();
				//fechaNacimiento.setDate(fechaNacimientoPicker.getDayOfMonth());
				//fechaNacimiento.setMonth(fechaNacimientoPicker.getMonth());
				//fechaNacimiento.setYear(fechaNacimientoPicker.getYear());
				//Toast.makeText(getApplicationContext(), "Fecha: "+fechaNacimiento,
					//	Toast.LENGTH_LONG).show();
				//lanzadorUnDiaActivity(fechaNacimiento);
				lanzadorUnDiaActivity();
			}
		});
    }

    private void setActionVariosDias() {
    	botonSiguiente.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Varios Dias, "+tipoPresentacion, Toast.LENGTH_LONG).show();
				lanzadorVariosDiasActivity();
			}
		});
    }

    //private void lanzadorUnDiaActivity(Date nacimiento) {
    private void lanzadorUnDiaActivity() {
    	// lanzamos el intent de UnDiaActivity
		Intent i = new Intent(this, UnDiaActivity.class);
		//fechaNacimiento.setDate(fechaNacimientoPicker.getDayOfMonth());
		//fechaNacimiento.setMonth(fechaNacimientoPicker.getMonth());
		//fechaNacimiento.setYear(fechaNacimientoPicker.getYear());
		//i.putExtra("fechaNacimiento", fechaNacimiento);
		i.putExtra("dayNacimiento", fechaNacimientoPicker.getDayOfMonth());
		i.putExtra("monthNacimiento", fechaNacimientoPicker.getMonth());
		i.putExtra("yearNacimiento", fechaNacimientoPicker.getYear());
		startActivity(i);
    }

    private void lanzadorVariosDiasActivity() {
    	// lanzamos el intent de VariosDiasActivity
		Intent i = new Intent(this, VariosDiasActivity.class);
		startActivity(i);
    }
}