package Negocio.Algoritmos;

import java.util.ArrayList;

public class Lloyd{
	private double tolerancia = Math.pow(10, -10);
	private final int numMaxIter = 10;
	private double rAprendizaje = 0.1;
	private ArrayList<double []> puntos;
	private ArrayList<double []> centros;
	private ArrayList<double []> centrosAnt;
	
	public Lloyd(ArrayList<double []> _puntos,ArrayList<double[]> _c) {
		this.puntos = _puntos;
		this.centros = _c;
	}
	
	public void execute() {
		int numIter = 0;
		do {
			numIter++;
			this.centrosAnt = this.centros; //ojito que no sea por referencia
			int actualiza = 0;
			for(int i = 0; i < this.puntos.size(); i++) {
				actualiza = competicion(this.puntos.get(i));
				actualizaCentro(actualiza, i);
			}
			
		}while(!fin() && numIter <= numMaxIter);
	}
	
	private boolean fin() {
		for(int i = 0; i < this.centros.size(); i++) {
			if(distancia(this.centros.get(i), this.centrosAnt.get(i)) >= tolerancia) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<double[]> getCentros() {
		return centros;
	}

	public void setCentros(ArrayList<double[]> centros) {
		this.centros = new ArrayList<double[]> ();
		for(int i = 0; i < centros.size(); i++) {
			double [] aux = new double [centros.get(i).length];
			for(int j = 0; j < centros.get(i).length; j++) {
				aux[j] = centros.get(i)[j];
			}
 			this.centros.add(aux);
		}
		this.centrosAnt =  new ArrayList<double[]>();
	}
	public double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}

	public double getrAprendizaje() {
		return rAprendizaje;
	}

	public void setrAprendizaje(double rAprendizaje) {
		this.rAprendizaje = rAprendizaje;
	}


	private int competicion(double[] punto) {
		int indiceMejor = 0;
		double menorDist = distancia(punto, this.centros.get(0));
		double dist;
		for(int j = 1; j < centros.size(); j++) {
			dist = distancia(punto,centros.get(j));
			if(dist < menorDist) {
				menorDist = dist;
				indiceMejor = j;
			}
		}
		return indiceMejor;
	}
	
	private void actualizaCentro(int iCentro, int iPunto) {
		for(int j = 0; j < this.centros.get(iCentro).length; j++) {
			this.centros.get(iCentro)[j] = this.centros.get(iCentro)[j] + this.rAprendizaje * (this.puntos.get(iPunto)[j] - this.centros.get(iCentro)[j]);
		}
	}
	
	private double distancia(double [] punto, double [] centro) {
		double distancia = 0;
		for(int i = 0; i < punto.length; i++) {
			distancia += Math.pow(punto[i] - centro[i], 2);
		}
		 distancia = Math.sqrt(distancia);
		 return distancia;
	}
	
	public int clasificarNuevo(double[] punto) {
		return competicion(punto);
		
	}

	public ArrayList<double[]> getCentrosAnt() {
		return centrosAnt;
	}

	public void setCentrosAnt(ArrayList<double[]> centrosAnt) {
		this.centrosAnt = centrosAnt;
	}
	
	
}
