package com.ritmubi;

import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class UnDiaActivity extends Activity {

	private Button botoncalcular,botonVolver;
	private DatePicker fechaACalcularPicker;
	public Ciclo ciclo;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_dia);
        
        botoncalcular = (Button)findViewById(R.id.botonCalcular);
        botonVolver = (Button) findViewById(R.id.botonVolver);
        fechaACalcularPicker =  (DatePicker)findViewById(R.id.selectorFechaACalcular);
    
        botoncalcular.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v)
			{
			
				Bundle bundle = getIntent().getExtras();
				
				// Fecha de Nacimiento
				Calendar fechaNacimiento = Calendar.getInstance();
				fechaNacimiento.set(bundle.getInt("yearNacimiento"),
						bundle.getInt("monthNacimiento"), bundle.getInt("dayNacimiento"),
						Biorritmo.HORAS, Biorritmo.MINUTOS, Biorritmo.SEGUNDOS);

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
