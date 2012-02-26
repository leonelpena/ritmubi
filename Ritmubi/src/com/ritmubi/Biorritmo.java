package com.ritmubi;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Biorritmo {

	public static final short 
			FRECUENCIA_EMOCIONAL 	= 28,
			FRECUENCIA_FISICA	 	= 23,
			FRECUENCIA_INTELECTUAL	= 33;
	
	public static final String 
			EMOCIONAL				= "Emocional",
			FISICO					= "FÌsico",
			INTELECTUAL				= "Intelectual";
	
	private static final long 
			MILISEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;  
	
	/**
	 * Calcula el biorritmo para una fecha puntual
	 * @param nacimiento Fecha de nacimiento
	 * @param fecha Fecha para la cual se desea realizar el calculo
	 * @return
	 */
	public static Ciclo calcular(Date nacimiento, Date fecha) 
					throws FechaException {
		NumberFormat nf = NumberFormat.getInstance(); 
		
		nf.setMaximumFractionDigits(2);
			
		int dias = diasTranscurridos(nacimiento, fecha);
		Ciclo ciclo = new Ciclo(100*calcularEmocional(dias),
							100*calcularFisico(dias),
							100*calcularIntelectual(dias));
		return ciclo;
	}
	
	/**
	 * Calcula el biorritmo para un rango de fechas.
	 * @param nacimiento Fecha de nacimiento
	 * @param inicio Fecha de inicio
	 * @param fin Fecha de t√©rmino
	 * @return
	 */
	public static ArrayList<Ciclo> calcular(Date nacimiento, Date inicio, Date fin) 
					throws FechaException {
		
		int dias_inicial = diasTranscurridos(nacimiento, inicio);
		int dias_rango = diasTranscurridos(inicio, fin);
		Ciclo ciclo = null;
		ArrayList<Ciclo> lista = new ArrayList<Ciclo>(dias_rango);
		Calendar calendario = Calendar.getInstance();
		calendario.set(inicio.getYear(), inicio.getMonth(), inicio.getDate());
		Date fechaCiclo = null;
		
		// Se calcula el biorrimto para cada uno de los dias que estan 
		// dentro del rango
		for(int i=0; i<dias_rango; i++) {
			calendario.add(Calendar.DATE, +1);
			fechaCiclo = new Date();
			fechaCiclo.setDate(calendario.get(Calendar.DATE));
			fechaCiclo.setMonth(calendario.get(Calendar.MONTH));
			fechaCiclo.setYear(calendario.get(Calendar.YEAR));
			ciclo = new Ciclo(
					100*calcularEmocional(dias_inicial+i),
					100*calcularFisico(dias_inicial+i),
					100*calcularIntelectual(dias_inicial+i),
					fechaCiclo
			);
			
			lista.add(ciclo);
		}
		
		return lista;
	}

	private static double calcularEmocional(int dias) {
		return Math.sin(2*Math.PI*dias / FRECUENCIA_EMOCIONAL);
	}
	
	private static double calcularFisico(int dias) {
		return Math.sin(2*Math.PI*dias / FRECUENCIA_FISICA);
	}
	
	private static double calcularIntelectual(int dias) {
		return Math.sin(2*Math.PI*dias / FRECUENCIA_INTELECTUAL);
	}
	
	private static int diasTranscurridos(Date inicio, Date fin) 
				throws FechaException {
		
		if( inicio.after(fin) )
			throw new FechaException("I: "+inicio.getDate()+" "+inicio.getMonth()+
					" "+inicio.getYear()+", F: "+fin.getDate()+" "+fin.getMonth()+
					" "+fin.getYear());

		return (int) ((fin.getTime() - inicio.getTime()) / MILISEGUNDOS_POR_DIA);
	}
}
