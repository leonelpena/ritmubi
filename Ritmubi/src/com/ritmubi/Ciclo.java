package com.ritmubi;

import java.io.Serializable;

public class Ciclo implements Serializable {

	private static final long serialVersionUID = 1L;
	private float 
		emocional,
		fisico,
		intelectual;

	/**
	 * Crea una nueva instancia de la clase Ciclo.
	 * Un Objeto de tipo ciclo contiene los estados emocional, fisico e intelectual  
	 * @param emocional
	 * @param fisico
	 * @param intelectual
	 */
	public Ciclo(double emocional, double fisico, double intelectual) {
		this.emocional = (float) emocional;
		this.fisico = (float) fisico;
		this.intelectual = (float) intelectual;
	}

	public float getEmocional() {
		return emocional;
	}

	public float getFisico() {
		return fisico;
	}

	public float getIntelectual() {
		return intelectual;
	}
}
