package com.ritmubi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import android.app.Activity;

//Clase que dibuja El Grafico de barras para mostrar el Biorritmo de un dia, utiliza la libreria GraphView 2.0.
public class GraficoBarras extends Activity  {
	
	private TextView emocionalText,
					fisicoText,
					intelectualText,
					fechaText;
	private Button botonnuevocalculo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico_barras);
		
		botonnuevocalculo = (Button)findViewById(R.id.nuevocalculo);
		emocionalText = (TextView) findViewById(R.id.emocionalText);
		fisicoText = (TextView) findViewById(R.id.fisicoText);
		intelectualText = (TextView) findViewById(R.id.intelectualText);
		fechaText = (TextView) findViewById(R.id.fechaBarra);
		
		Bundle bundle = getIntent().getExtras();
		Ciclo ciclo = (Ciclo) bundle.getParcelable("ciclo");
		GraphViewSeries Series = new GraphViewSeries(new GraphViewData[] 
				{new GraphViewData(1, ciclo.getEmocional()),new GraphViewData(2,ciclo.getFisico()),new GraphViewData(3,ciclo.getIntelectual())});
		GraphView graphView;
		graphView = new BarGraphView(
				this // context
				, "Tu Biorritmo Actual" // heading
		);
		
		graphView.addSeries(Series);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
		
		// Se escriben los porcentajes en la vista
		fechaText.setText("Fecha: "+ciclo.getFechaFormateada());
		emocionalText.setText(Biorritmo.EMOCIONAL+": "+
				Integer.toString(ciclo.getEmocional())+"%");
		fisicoText.setText(Biorritmo.FISICO+": "+
				Integer.toString(ciclo.getFisico())+"%");
		intelectualText.setText(Biorritmo.INTELECTUAL+": "+
				Integer.toString(ciclo.getIntelectual())+"%");
		
		//Boton para nuevo cálculo.
		botonnuevocalculo.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v)
			{
				finish();				
			}
		});
	}

}
