package com.ritmubi;

public class Ciclo {

	private short 
		emocional,
		fisico,
		intelectual;
	
	public Ciclo(short emocional, short fisico, short intelectual) {
		this.emocional = emocional;
		this.fisico = fisico;
		this.intelectual = intelectual;
	}

	public short getEmocional() {
		return emocional;
	}

	public short getFisico() {
		return fisico;
	}

	public short getIntelectual() {
		return intelectual;
	}
}
