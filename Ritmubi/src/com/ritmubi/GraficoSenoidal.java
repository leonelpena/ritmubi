package com.ritmubi;

import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class GraficoSenoidal extends Activity
{
	public Ciclo []ciclo = new Ciclo[29];
	private float [] valoresEmocionales = new float[28];
	private float [] valoresIntelectuales = new float[28];
	private float [] valoresFisicos = new float[28];
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafico_senoidal);
        Bundle bundle = getIntent().getExtras();
        
        Integer i;

        valoresEmocionales = bundle.getFloatArray("valoresEmocionales");
        valoresIntelectuales = bundle.getFloatArray("valoresIntelectuales");
        valoresFisicos = bundle.getFloatArray("valoresFisicos");
        
        GraphViewData[] dataEmocional = new GraphViewData[28];
        GraphViewData[] dataIntelectual = new GraphViewData[28];
        GraphViewData[] dataFisica = new GraphViewData[28];
        
        for(i=0;i<28;i++) 
        {
         dataEmocional[i] = new GraphViewData(i,valoresEmocionales[i]);
         dataIntelectual[i] = new GraphViewData(i,valoresIntelectuales[i]);
         dataFisica[i] = new GraphViewData(i,valoresFisicos[i]);
        }
         
        GraphViewSeries ondaEmocional = new GraphViewSeries("Emocional", Color.rgb(200, 50, 00), dataEmocional);
        GraphViewSeries ondaIntelectual = new GraphViewSeries("Intelectual", Color.rgb(90, 250, 00), dataIntelectual);
        GraphViewSeries ondaFisica = new GraphViewSeries("Fisica", Color.rgb(32,50,32), dataFisica);
         
        LineGraphView graphView;
		graphView = new LineGraphView(this, "Biorritmo");
		graphView.addSeries(ondaEmocional);
		graphView.addSeries(ondaIntelectual);
		graphView.addSeries(ondaFisica);
		graphView.setShowLegend(true);
		graphView.setViewPort(2, 40);
		graphView.setScrollable(true);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
	}
}
