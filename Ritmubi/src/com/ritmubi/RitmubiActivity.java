package com.ritmubi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
//import android.widget.Toast;

public class RitmubiActivity extends Activity 
{
	
	private Button botonSiguiente;
	private RadioButton botonUnDia,botonVariosDias;
	private DatePicker fechaNacimientoPicker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        botonSiguiente = (Button) findViewById(R.id.botonSiguiente);
        botonUnDia = (RadioButton) findViewById(R.id.botonUnDia);
        botonVariosDias = (RadioButton) findViewById(R.id.botonVariosDias);
        fechaNacimientoPicker = (DatePicker) findViewById(R.id.selectorFechaNacimiento);
        
        // Accion por defecto (Calcular un solo día)
        botonSiguiente.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				//setActionUnDia();
				lanzadorUnDiaActivity();
			}
		});
        //Seleccionamos calcular un solo día
        botonUnDia.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				setActionUnDia();
			}
		});
        //Seleccionamos calcular varios días
        botonVariosDias.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				setActionVariosDias();
			}
		});
    }
    
    private void setActionUnDia() 
    {
    	botonSiguiente.setOnClickListener(new View.OnClickListener() 
    	{
			public void onClick(View v) {
				lanzadorUnDiaActivity();
			}
		});
    }

    private void setActionVariosDias() 
    {
    	botonSiguiente.setOnClickListener(new View.OnClickListener() 
    	{
			public void onClick(View v) 
			{
				lanzadorVariosDiasActivity();
			}
		});
    }

    //Lanzador calculo de un día
    private void lanzadorUnDiaActivity() 
    {
    	//intent de UnDiaActivity
		Intent i = new Intent(this, UnDiaActivity.class);
		i.putExtra("dayNacimiento", fechaNacimientoPicker.getDayOfMonth());
		i.putExtra("monthNacimiento", fechaNacimientoPicker.getMonth());
		i.putExtra("yearNacimiento", fechaNacimientoPicker.getYear());
		startActivity(i);
    }
    //Lanzador calculo de varios días
    private void lanzadorVariosDiasActivity() 
    {
    	//intent de VariosDiasActivity
		Intent i = new Intent(this, VariosDiasActivity.class);
		i.putExtra("dayNacimiento", fechaNacimientoPicker.getDayOfMonth());
		i.putExtra("monthNacimiento", fechaNacimientoPicker.getMonth());
		i.putExtra("yearNacimiento", fechaNacimientoPicker.getYear());
		startActivity(i);
    }
}