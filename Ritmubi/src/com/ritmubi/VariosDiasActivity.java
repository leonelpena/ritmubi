package com.ritmubi;

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
	public Ciclo []ciclo = new Ciclo[28];
	private float [] valoresEmocionales = new float[28];
	private float [] valoresIntelectuales = new float[28];
	private float [] valoresFisicos = new float[28];
	private Integer i=0;
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
				
				//Fecha Final Calculo
				fechaFin = CalcularFechaFinal(fechaInicio);
				try 
				{
					ciclo = Biorritmo.calcular(fechaNacimiento, fechaInicio, fechaFin);
				}
				catch(FechaException e) 
				{
					Toast.makeText(getApplicationContext(), "Fecha Incorrecta", 
							Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent = new Intent(VariosDiasActivity.this, GraficoSenoidal.class);
				
				for(i = 0;i<28;i++)	
				{
				 valoresEmocionales[i] = ciclo[i].getEmocional();
				 valoresIntelectuales[i] = ciclo[i].getIntelectual();
				 valoresFisicos[i] = ciclo[i].getFisico();
				}
				
				intent.putExtra("valoresEmocionales", valoresEmocionales);
				intent.putExtra("valoresIntelectuales", valoresIntelectuales);
				intent.putExtra("valoresFisicos", valoresFisicos);
				startActivity(intent);
				
			}

			private Date CalcularFechaFinal(Date fechaInicio) {
				//calculamos la fecha final
				int mes;
				Date fechaFin = new Date();
				mes = fechaInicio.getMonth();
				if(mes==11) 
				{	
					mes = 0;
				}
				else 
				{
					mes = mes +1;
				}
				fechaFin = fechaInicio;
				fechaFin.setMonth(mes);
				return fechaFin;
			}
		});
    }
}
