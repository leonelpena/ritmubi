package com.ritmubi;

import java.util.ArrayList;
import java.util.Calendar;
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
				Calendar fechaNacimiento = Calendar.getInstance();
				fechaNacimiento.set(bundle.getInt("yearNacimiento"),
						bundle.getInt("monthNacimiento"), bundle.getInt("dayNacimiento"),
						Biorritmo.HORAS, Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);

				// Fecha Inicio Calculo
				Calendar fechaInicio = Calendar.getInstance();
				fechaInicio.set(fechaACalcularPicker.getYear(),
						fechaACalcularPicker.getMonth(), fechaACalcularPicker.getDayOfMonth(),
						Biorritmo.HORAS, Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);

				Calendar fechaFinal = (Calendar) fechaInicio.clone();
				// Se aumenta la fecha en 42 dias para que una vez dibujada la función seno
				// se pueda ver correctamente
				fechaFinal.add(Calendar.DATE, +42);
				
				try
				{
					listaCiclos = Biorritmo.calcular(fechaNacimiento.getTime(),
							fechaInicio.getTime(), fechaFinal.getTime());
				}
				catch(FechaException e) 
				{
					Toast.makeText(getApplicationContext(), "N: "+fechaNacimiento.get(
							Calendar.DATE)+"-"+fechaNacimiento.get(Calendar.MONTH)+"-"+
							fechaNacimiento.get(Calendar.YEAR)+", I: "+fechaInicio.get(
							Calendar.DATE)+"-"+fechaInicio.get(Calendar.MONTH)+"-"+
							fechaInicio.get(Calendar.YEAR)+", F: "+fechaFinal.get(
							Calendar.DATE)+"-"+fechaFinal.get(Calendar.MONTH)+"-"+
							fechaFinal.get(Calendar.YEAR), Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent = new Intent(VariosDiasActivity.this, GraficoSenoidal.class);
				intent.putExtra("listaCiclos", listaCiclos);
				startActivity(intent);
			}

		});
    }
}
