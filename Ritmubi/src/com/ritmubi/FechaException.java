package com.ritmubi;

//public class FechaException extends Exception {
public class FechaException extends RuntimeException {

	private static final long serialVersionUID = -8209033899029761537L;

	public FechaException() {
		super("Fecha termino menor o igual que fecha inicio");
	}
	
	public FechaException(String msg) {
		super("Fecha incorrecta: "+msg);
	}
}
