package com.ritmubi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
//import android.widget.Toast;

public class RitmubiActivity extends Activity {
	
	private Button botonSiguiente;
	private RadioButton 
				botonUnDia,
				botonVariosDias,
				botonTextoYGraficos,
				botonSoloGrafico,
				botonSoloTexto;
	private String tipoPresentacion = Grafico.TEXTO_Y_GRAFICOS;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        botonSiguiente = (Button) findViewById(R.id.botonSiguiente);
        botonUnDia = (RadioButton) findViewById(R.id.botonUnDia);
        botonVariosDias = (RadioButton) findViewById(R.id.botonVariosDias);
        botonTextoYGraficos = (RadioButton) findViewById(R.id.botonTextoYGraficos);
        botonSoloGrafico = (RadioButton) findViewById(R.id.botonSoloGraficos);
        botonSoloTexto = (RadioButton) findViewById(R.id.botonSoloTexto);
        
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
        
        botonTextoYGraficos.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				tipoPresentacion = Grafico.TEXTO_Y_GRAFICOS;
			}
		});
        
        botonSoloGrafico.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				tipoPresentacion = Grafico.SOLO_GRAFICOS;
			}
		});
        
        botonSoloTexto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				tipoPresentacion = Grafico.SOLO_TEXTO;
			}
		});
    }

    private void setActionUnDia() {
    	botonSiguiente.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "Un Dia, "+tipoPresentacion, Toast.LENGTH_LONG).show();
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

    private void lanzadorUnDiaActivity() {
    	// lanzamos el intent de UnDiaActivity
		Intent i = new Intent(this, UnDiaActivity.class);
		i.putExtra("tipoPresentacion", tipoPresentacion);
		startActivity(i);
    }

    private void lanzadorVariosDiasActivity() {
    	// lanzamos el intent de VariosDiasActivity
		Intent i = new Intent(this, VariosDiasActivity.class);
		i.putExtra("tipoPresentacion", tipoPresentacion);
		startActivity(i);
    }
}