package com.ritmubi;

import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;

public class UnDiaActivity extends Activity {

	private Button botoncalcular,botonVolver;
	private DatePicker fechaACalcularPicker;
	private Calendar fechaNacimiento;
	public Ciclo ciclo;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_dia);
        
        botoncalcular = (Button)findViewById(R.id.botonCalcular);
        botonVolver = (Button) findViewById(R.id.botonVolver);
        fechaACalcularPicker =  (DatePicker)findViewById(R.id.selectorFechaACalcular);
        
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
    
        botoncalcular.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v)
			{

				// Fecha a Calcular
				Calendar fechaACalcular = Calendar.getInstance();
				fechaACalcular.set(fechaACalcularPicker.getYear(),
						fechaACalcularPicker.getMonth(), fechaACalcularPicker.getDayOfMonth(),
						Biorritmo.HORAS, Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);

				try 
				{
					ciclo = Biorritmo.calcular(fechaNacimiento.getTime(),
								fechaACalcular.getTime());
				} 
				catch(FechaException e) 
				{
					Toast.makeText(getApplicationContext(), "Fecha Incorrecta", 
							Toast.LENGTH_LONG).show();
					return;
				}
				
				Toast.makeText(getApplicationContext(), ""+Biorritmo.diasTranscurridos(
						fechaNacimiento.getTime(), fechaACalcular.getTime()),
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(UnDiaActivity.this, GraficoBarras.class);
				i.putExtra("ciclo", ciclo);
				startActivity(i);
			}
        });
        
        botonVolver.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				// Cerramos la activity
				finish();				
			}
		});
        
    }
}
