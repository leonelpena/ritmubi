package com.ritmubi;

import java.util.ArrayList;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.GraphViewSeries;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GraficoSenoidal extends Activity
{
	private TextView fechaView,
					emocionalView,
					intelectualView,
					fisicoView;
	private ArrayList<Ciclo> listaCiclos;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafico_senoidal);
        Bundle bundle = getIntent().getExtras();

        listaCiclos = bundle.getParcelableArrayList("listaCiclos");

        GraphViewData[] dataEmocional = new GraphViewData[listaCiclos.size()];
        GraphViewData[] dataIntelectual = new GraphViewData[listaCiclos.size()];
        GraphViewData[] dataFisica = new GraphViewData[listaCiclos.size()];

        Ciclo ciclo = null;
        for(int i=0; i<listaCiclos.size(); i++) 
        {
        	ciclo = listaCiclos.get(i);
        	dataEmocional[i] = new GraphViewData(i, ciclo.getEmocional());
        	dataIntelectual[i] = new GraphViewData(i, ciclo.getIntelectual());
        	dataFisica[i] = new GraphViewData(i, ciclo.getFisico());
        }
         
        GraphViewSeries ondaEmocional = new GraphViewSeries("Emocional", Color.rgb(200, 50, 00),
        		dataEmocional);
        GraphViewSeries ondaIntelectual = new GraphViewSeries("Intelectual", Color.rgb(90, 250, 00),
        		dataIntelectual);
        GraphViewSeries ondaFisica = new GraphViewSeries("Fisica", Color.rgb(0,0,200), dataFisica);
         
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
		
		// Informacion para el primer dia del rango de inicio
		fechaView = (TextView) findViewById(R.id.fecha_1);
		emocionalView = (TextView) findViewById(R.id.emocional_1);
		intelectualView = (TextView) findViewById(R.id.intelectual_1);
		fisicoView = (TextView) findViewById(R.id.fisico_1);
		
		ciclo = listaCiclos.get(0);
		fechaView.setText("Fecha: "+ciclo.getFecha().getDate()+"/"+
				(ciclo.getFecha().getMonth()+1) + "/"+ciclo.getFecha().getYear() );
		emocionalView.setText(Biorritmo.EMOCIONAL+": "+ciclo.getEmocional()+"%");
		intelectualView.setText(Biorritmo.INTELECTUAL+": "+ciclo.getIntelectual()+"%");
		fisicoView.setText(Biorritmo.FISICO+": "+ciclo.getFisico()+"%");
		
		// Informacion para el segundo dia del rango de inicio
		fechaView = (TextView) findViewById(R.id.fecha_2);
		emocionalView = (TextView) findViewById(R.id.emocional_2);
		intelectualView = (TextView) findViewById(R.id.intelectual_2);
		fisicoView = (TextView) findViewById(R.id.fisico_2);
		
		ciclo = listaCiclos.get(1);
		fechaView.setText("Fecha: "+ciclo.getFecha().getDate()+"/"+
				(ciclo.getFecha().getMonth()+1) + "/"+ciclo.getFecha().getYear() );
		emocionalView.setText(Biorritmo.EMOCIONAL+": "+ciclo.getEmocional()+"%");
		intelectualView.setText(Biorritmo.INTELECTUAL+": "+ciclo.getIntelectual()+"%");
		fisicoView.setText(Biorritmo.FISICO+": "+ciclo.getFisico()+"%");
		
		// Informacion para el segundo dia del rango de inicio
		fechaView = (TextView) findViewById(R.id.fecha_3);
		emocionalView = (TextView) findViewById(R.id.emocional_3);
		intelectualView = (TextView) findViewById(R.id.intelectual_3);
		fisicoView = (TextView) findViewById(R.id.fisico_3);
		
		ciclo = listaCiclos.get(2);
		fechaView.setText("Fecha: "+ciclo.getFecha().getDate()+"/"+
				(ciclo.getFecha().getMonth()+1) + "/"+ciclo.getFecha().getYear() );
		emocionalView.setText(Biorritmo.EMOCIONAL+": "+ciclo.getEmocional()+"%");
		intelectualView.setText(Biorritmo.INTELECTUAL+": "+ciclo.getIntelectual()+"%");
		fisicoView.setText(Biorritmo.FISICO+": "+ciclo.getFisico()+"%");
	}
}
