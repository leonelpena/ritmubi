package com.ritmubi;

import java.util.Date;

public class Biorritmo {

	public static final short 
			FRECUENCIA_EMOCIONAL 	= 28,
			FRECUENCIA_FISICA	 	= 23,
			FRECUENCIA_INTELECTUAL	= 33;
	
	private static final long 
			MILISEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;  
	
	/**
	 * Calcula el biorritmo para una fecha puntual
	 * @param nacimiento Fecha de nacimiento
	 * @param fecha Fecha para la cual se desea realizar el calculo
	 * @return
	 */
	public static Ciclo calcular(Date nacimiento, Date fecha) {
		int dias = diasTranscurridos(nacimiento, fecha);
		Ciclo ciclo = new Ciclo(
				calcularEmocional(dias),
				calcularFisico(dias),
				calcularIntelectual(dias)
		);
		
		return ciclo;		
	}
	
	/**
	 * Calcula el biorritmo para un rango de fechas.
	 * @param nacimiento Fecha de nacimiento
	 * @param inicio Fecha de inicio
	 * @param fin Fecha de término
	 * @return
	 */
	public static Ciclo calcular(Date nacimiento, Date inicio, Date fin) {
		
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
	
	private static int diasTranscurridos(Date inicio, Date fin) {
		if( inicio.compareTo(fin) <= 0 )
			throw new IllegalArgumentException("Fecha termino menor o igual que fecha inicio");
			
		return (int) ((inicio.getTime() - fin.getTime()) / MILISEGUNDOS_POR_DIA);
	}
}
