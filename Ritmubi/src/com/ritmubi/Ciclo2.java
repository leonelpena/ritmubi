package com.ritmubi;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;

public class Ciclo2 implements Parcelable {

	private Date fecha;
	private float 
		emocional,
		fisico,
		intelectual;

	public Ciclo2() {
		this.fecha = new Date();
	}
	/**
	 * Crea una nueva instancia de la clase Ciclo.
	 * Un Objeto de tipo ciclo contiene los estados emocional, fisico e intelectual  
	 * @param emocional
	 * @param fisico
	 * @param intelectual
	 */
	public Ciclo2(double emocional, double fisico, double intelectual) {
		this.emocional = (float) emocional;
		this.fisico = (float) fisico;
		this.intelectual = (float) intelectual;
		this.fecha = new Date();
	}
	
	public Ciclo2(double emocional, double fisico, double intelectual, Date fecha) {
		this.emocional = (float) emocional;
		this.fisico = (float) fisico;
		this.intelectual = (float) intelectual;
		this.fecha = fecha;
	}
	
	public Ciclo2(Ciclo ciclo) {
		this.emocional = ciclo.getEmocional();
		this.fisico = ciclo.getFisico();
		this.intelectual = ciclo.getIntelectual();
		this.fecha = new Date();
	}
	
	public Ciclo2(Parcel in) {
		readFromParcel(in);
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
	
	public Date getFecha() {
		return fecha;
	}
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(emocional);
		dest.writeFloat(fisico);
		dest.writeFloat(intelectual);
		dest.writeString(fecha.toString());
	}
	
	private void readFromParcel(Parcel in) {
		emocional = in.readFloat();
		fisico = in.readFloat();
		intelectual = in.readFloat();
		fecha = new Date(in.readString());
	}

	public static final Parcelable.Creator<Ciclo2> CREATOR =
			new Parcelable.Creator<Ciclo2>() {
		public Ciclo2 createFromParcel(Parcel in) {
			return new Ciclo2(in);
	    }
	 
	    public Ciclo2[] newArray(int size) {
	        return new Ciclo2[size];
	    }
	};
}
