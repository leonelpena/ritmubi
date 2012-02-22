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
	//private short tipoPresentacion;
	private Button botoncalcular;
	private DatePicker fechacalcular;
	private Date fechan;
	private Date fechaa;
	public Ciclo ciclo;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_dia);
        botoncalcular = (Button)findViewById(R.id.botonCalcular);
        //this.tipoPresentacion = tipoPresentacion;
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "Un Dia, Presentacion: "+ bundle.getString("tipoPresentacion"), Toast.LENGTH_LONG).show();
    
        botoncalcular.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//fechan = new Date();
			fechaa = new Date();
			Bundle bundle = getIntent().getExtras();
			fechan = (Date)bundle.get("fechaNacimiento");
			fechacalcular =  (DatePicker)findViewById(R.id.selectorFechaACalcular);
			fechaa.setDate(fechacalcular.getDayOfMonth());
			fechaa.setMonth(fechacalcular.getMonth());
			fechaa.setYear(fechacalcular.getYear());
			ciclo = Biorritmo.calcular(fechan, fechaa);
			Intent i = new Intent(UnDiaActivity.this, GraficoBarras.class);
			//double []valores;
			//valores = new double [3];
			//valores[0] = ciclo.getEmocional();
			//valores[1] = ciclo.getFisico();
			//valores[2] = ciclo.getIntelectual();
			i.putExtra("ciclo", ciclo);
			//i.putExtra("valores", valores);
			startActivity(i);
			}
        	
        });
        
    }   
}
