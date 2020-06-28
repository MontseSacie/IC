package Negocio.Objetos;

import java.lang.Math;
import java.util.ArrayList;

public class Nodo {
	
	private int fila;
	private int columna;
	private double g; //distancia real al inicio -> cambia
	private double h; //distancia estimada a meta -> no cambia
	private Nodo padre;
	private TipoNodo tipo = TipoNodo.LIBRE;
	
	public Nodo(int x, int y){
		this.fila = x;
		this.columna = y;
	}
	
	public int getFila() {
		return this.fila;
	}
	public int getColumna() {
		return this.columna;
	}
	public double getG() {
		return g;
	}
	
	public void setG(double g) {
		this.g = g;
	}
	
	public double getH() {
		return this.h;
	}
	public void setH(double h) {
		this.h = h;
	}
	
	public double costeANodo (Nodo n) {
		return Math.sqrt(Math.pow(n.getFila() - this.fila, 2) +  Math.pow(n.getColumna() - this.columna, 2));
	}
	public double costeF() {
		if(tipo == TipoNodo.PELIGROSO) {
			return (this.h + this.g) + 1;
		}
		return (this.h + this.g);
	}
	
	public Nodo getPadre() {
		return padre;
	}
	
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	
	public TipoNodo getTipoNodo() {
		return tipo;
	}
	
	public void setTipoNodo(TipoNodo t) {
		this.tipo = t;
	}
	
}
