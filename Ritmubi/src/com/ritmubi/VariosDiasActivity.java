package com.ritmubi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class VariosDiasActivity extends Activity 
{
	
	private Button botonVolver,botonCalcular;
	private Date fechaNacimiento = new Date();
	private Date fechaInicio = new Date();
	private Date fechaFin =  new Date();
	private DatePicker fechaACalcularPicker;
	ArrayList<Ciclo> listaCiclos;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varios_dias);    
        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonCalcular = (Button) findViewById(R.id.botonCalcular);
        
        botonVolver.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				finish();				
			}
		});
        
        botonCalcular.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Bundle bundle = getIntent().getExtras();
				fechaACalcularPicker =  (DatePicker)findViewById(R.id.selectorFechaInicio);
				// Fecha de Nacimiento
				fechaNacimiento.setDate(bundle.getInt("dayNacimiento"));
				fechaNacimiento.setMonth(bundle.getInt("monthNacimiento"));
				fechaNacimiento.setYear(bundle.getInt("yearNacimiento"));

				// Fecha Inicio Calculo
				fechaInicio.setDate(fechaACalcularPicker.getDayOfMonth());
				fechaInicio.setMonth(fechaACalcularPicker.getMonth());
				fechaInicio.setYear(fechaACalcularPicker.getYear());

				Calendar fechaFinal = Calendar.getInstance();
				fechaFinal.set(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDate());
				fechaFinal.add(Calendar.DATE, +42);

				fechaFin.setDate(fechaFinal.get(Calendar.DATE));
				fechaFin.setMonth(fechaFinal.get(Calendar.MONTH));
				fechaFin.setYear(fechaFinal.get(Calendar.YEAR));
				
				try
				{
					listaCiclos = Biorritmo.calcular(fechaNacimiento, fechaInicio, fechaFin);
				}
				catch(FechaException e) 
				{
					//Toast.makeText(getApplicationContext(), "Fecha Incorrecta",
					Toast.makeText(getApplicationContext(), "N: "+fechaNacimiento.getDate()+
							" "+fechaNacimiento.getMonth()+" "+fechaNacimiento.getYear()+
							", I: "+fechaInicio.getDate()+" "+fechaInicio.getMonth()+
							" "+fechaInicio.getYear()+" , F: "+fechaFinal.get(Calendar.DAY_OF_MONTH)+
							" "+fechaFinal.get(Calendar.MONTH)+" "+fechaFinal.get(Calendar.YEAR),
							Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent = new Intent(VariosDiasActivity.this, GraficoSenoidal.class);
				intent.putExtra("listaCiclos", listaCiclos);
				startActivity(intent);
			}

		});
    }
}
