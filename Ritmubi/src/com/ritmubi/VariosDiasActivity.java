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
import android.widget.DatePicker.OnDateChangedListener;

//Clase que realiza el cálculo para varios dias.
public class VariosDiasActivity extends Activity 
{
	
	private Button botonVolver,botonCalcular;
	private DatePicker fechaACalcularPicker;
	private Calendar fechaNacimiento;
	ArrayList<Ciclo> listaCiclos;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varios_dias);    
        botonVolver = (Button) findViewById(R.id.botonVolver);
        botonCalcular = (Button) findViewById(R.id.botonCalcular);
        fechaACalcularPicker =  (DatePicker)findViewById(R.id.selectorFechaInicio);
        
        Bundle bundle = getIntent().getExtras();

        // Fecha de Nacimiento
		fechaNacimiento = Calendar.getInstance();
		fechaNacimiento.set(bundle.getInt("yearNacimiento"),
				bundle.getInt("monthNacimiento"), bundle.getInt("dayNacimiento"),
				Biorritmo.HORAS, Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);
        
       
		// Se suma un dia para obtener la fecha del dia siguiente
		Calendar siguienteFecha = (Calendar)fechaNacimiento.clone();
		siguienteFecha.add(Calendar.DATE, +1);

		fechaACalcularPicker.init(siguienteFecha.get(Calendar.YEAR),
				siguienteFecha.get(Calendar.MONTH), siguienteFecha.get(Calendar.DATE),
        	new OnDateChangedListener() {
        		public void onDateChanged(DatePicker view, int year, int month, int day) {

        			// Se verifica la fecha seleccionada por el usuario
        			Calendar fechaSeleccionada = Calendar.getInstance();
        			fechaSeleccionada.set(year, month, day, Biorritmo.HORAS,
        					Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);
        			
        			if( fechaSeleccionada.before(fechaNacimiento) )
        				Toast.makeText(getApplicationContext(), "Fecha debe ser posterior al nacimiento",
        						Toast.LENGTH_LONG).show();
        		}
        	}
        );
		
        botonCalcular.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
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
					Toast.makeText(getApplicationContext(), "Fecha Incorrecta", 
							Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent = new Intent(VariosDiasActivity.this, GraficoSenoidal.class);
				intent.putExtra("listaCiclos", listaCiclos);
				startActivity(intent);
			}

		});
        
        //Boton para volver
        botonVolver.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				finish();
			}
		});
    }
}
