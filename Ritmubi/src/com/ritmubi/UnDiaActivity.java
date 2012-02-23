package com.ritmubi;

import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class UnDiaActivity extends Activity {

	private Button botoncalcular,
					botonVolver;
	private DatePicker fechaACalcularPicker;
	private Date fechaNacimiento;
	private Date fechaACalcular;
	public Ciclo ciclo;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_dia);
        
        fechaACalcular = new Date();
        botoncalcular = (Button)findViewById(R.id.botonCalcular);
        botonVolver = (Button) findViewById(R.id.botonVolver);
        fechaACalcularPicker =  (DatePicker)findViewById(R.id.selectorFechaACalcular);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "D: "+ 
        		bundle.getInt("dayNacimiento")+", M: "+ bundle.getInt("monthNacimiento") +
        		", Y: "+bundle.getInt("yearNacimiento"), Toast.LENGTH_LONG).show();
    
        botoncalcular.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			
				Bundle bundle = getIntent().getExtras();
				
				// Fecha de Nacimiento
				fechaNacimiento = new Date();
				fechaNacimiento.setDate(bundle.getInt("dayNacimiento"));
				fechaNacimiento.setMonth(bundle.getInt("monthNacimiento"));
				fechaNacimiento.setYear(bundle.getInt("yearNacimiento"));

				// Fecha a Calcular
				fechaACalcular.setDate(fechaACalcularPicker.getDayOfMonth());
				fechaACalcular.setMonth(fechaACalcularPicker.getMonth());
				fechaACalcular.setYear(fechaACalcularPicker.getYear());
				
				Toast.makeText(getApplicationContext(), "D: "+ 
		        		fechaACalcular.getDate()+", M: "+ fechaACalcular.getMonth() +
		        		", Y: "+fechaACalcular.getYear(), Toast.LENGTH_LONG).show();

				try {
					ciclo = Biorritmo.calcular(fechaNacimiento, fechaACalcular);
				} catch(FechaException e) {
					Toast.makeText(getApplicationContext(), "Fecha Incorrecta", 
							Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent i = new Intent(UnDiaActivity.this, GraficoBarras.class);
				i.putExtra("ciclo", ciclo);
				startActivity(i);
			}
        });
        
        botonVolver.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cerramos la activity
				finish();				
			}
		});
        
    }   
}
