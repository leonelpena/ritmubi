package com.ritmubi;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;

public class Ciclo implements Parcelable {

	private Date fecha;
	private float 
		emocional,
		fisico,
		intelectual;

	public Ciclo() {
		this.fecha = new Date();
	}
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
		this.fecha = new Date();
	}
	
	public Ciclo(double emocional, double fisico, double intelectual, Date fecha) {
		this.emocional = (float) emocional;
		this.fisico = (float) fisico;
		this.intelectual = (float) intelectual;
		this.fecha = fecha;
	}
	
	public Ciclo(Parcel in) {
		readFromParcel(in);
	}
	
	public int getEmocional() {
		return (int) emocional;
	}
	
	public int getFisico() {
		return (int) fisico;
	}
	
	public int getIntelectual() {
		return (int) intelectual;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public String getFechaFormateada() {
		return (fecha.getDate()+" / "+(fecha.getMonth()+1)+" / "+fecha.getYear());
	}
	
	public int describeContents() {
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

	public static final Parcelable.Creator<Ciclo> CREATOR =
			new Parcelable.Creator<Ciclo>() {
		public Ciclo createFromParcel(Parcel in) {
			return new Ciclo(in);
	    }
	 
	    public Ciclo[] newArray(int size) {
	        return new Ciclo[size];
	    }
	};
}
