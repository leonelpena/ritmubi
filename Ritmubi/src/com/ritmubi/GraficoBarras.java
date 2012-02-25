package com.ritmubi;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import android.app.Activity;

public class GraficoBarras extends Activity  {
	
	private TextView emocionalText,
					fisicoText,
					intelectualText;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico_barras);

		emocionalText = (TextView) findViewById(R.id.emocionalText);
		fisicoText = (TextView) findViewById(R.id.fisicoText);
		intelectualText = (TextView) findViewById(R.id.intelectualText);
		
		Bundle bundle = getIntent().getExtras();
		//Ciclo ciclo = (Ciclo) bundle.getSerializable("ciclo");
		Ciclo2 ciclo = (Ciclo2) bundle.getParcelable("ciclo2");
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
		emocionalText.setText(Biorritmo.EMOCIONAL+": "+
				Float.toString(ciclo.getEmocional())+"%");
		fisicoText.setText(Biorritmo.FISICO+": "+
				Float.toString(ciclo.getFisico())+"%");
		intelectualText.setText(Biorritmo.INTELECTUAL+": "+
				Float.toString(ciclo.getIntelectual())+"%");
	}

}
