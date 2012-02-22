package com.ritmubi;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import android.app.Activity;

public class GraficoBarras extends Activity  {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico_barras);
		//double []valores;
		//valores = new double [3];
		Bundle bundle = getIntent().getExtras();
		Ciclo ciclo = (Ciclo) bundle.getSerializable("ciclo");
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
		
	}

}
