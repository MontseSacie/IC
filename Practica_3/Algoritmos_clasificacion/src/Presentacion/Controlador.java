package Presentacion;

import java.util.ArrayList;
import java.util.HashMap;

import Jama.Matrix;
import Negocio.Algoritmos.Bayes;
import Negocio.Algoritmos.Kmeans;
import Negocio.Algoritmos.Lloyd;

public class Controlador {
	private static Controlador instance = null;
	private	String rutaFichero;
	private ArrayList<double[]> datos; //datos
	private ArrayList<String> clasesDatos;
	private ArrayList<String> clases;
	private ArrayList<double[]> centrosIni;
	private ArrayList<double[]> ejemplos;
	private ArrayList<String> clasesEjemplos;
	private Lloyd lloyd;
	private Bayes bayes;
	private Kmeans kmeans;
	
	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
		}

		return instance;
	}
	
	public Controlador() {
		clean();
	}
	
	public Kmeans getKmeans() {
		return kmeans;
	}

	public void setKmeans(Kmeans kmeans) {
		this.kmeans = kmeans;
	}

	public Bayes getBayes() {
		return bayes;
	}

	public void setBayes(Bayes bayes) {
		this.bayes = bayes;
	}

	public Lloyd getLloyd() {
		return lloyd;
	}

	public void setLloyd(Lloyd lloyd) {
		this.lloyd = lloyd;
	}


	public String getRutaFichero() {
		return rutaFichero;
	}


	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
	public void inicializaDatos(ArrayList<double[]> datos, ArrayList<String> clases) {
		this.datos = datos;
		this.clasesDatos = clases;
		this.clases = new ArrayList<String>();
		for(int i = 0; i < clases.size(); i++) {
			if(!this.clases.contains(clases.get(i))) {
				this.clases.add(new String (clases.get(i)));
			}
		}
		this.centrosIni = new ArrayList<double[]>();
		this.centrosIni.add(new double[]{4.6, 3.0, 4.0, 0.0});
		this.centrosIni.add(new double[]{6.8, 3.4, 4.6, 0.7});
	}

	public ArrayList<String> getClasesEjemplos() {
		return clasesEjemplos;
	}

	public void setClasesEjemplos(ArrayList<String> clasesEjemplos) {
		this.clasesEjemplos = clasesEjemplos;
	}

	public ArrayList<String> getClases() {
		return clases;
	}


	public void setClases(ArrayList<String> clases) {
		clases = clases;
	}


	public ArrayList<double[]> getCentrosIni() {
		return centrosIni;
	}


	public void setCentrosIni(ArrayList<double[]> centros) {
		centrosIni = centros;
		lloyd.setCentros(centros);
	}


	public ArrayList<double[]> getEjemplos() {
		return ejemplos;
	}


	public void setEjemplos(ArrayList<double[]> ejemplos) {
		ejemplos = ejemplos;
	}


	public int getNumClases() {
		if(clases == null) {
			return 0;
		}
		return clases.size();
	}
	
	public ArrayList<double[]> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<double[]> datos) {
		this.datos = datos;
	}

	public ArrayList<String> getClasesDatos() {
		return clasesDatos;
	}

	public void setClasesDatos(ArrayList<String> clasesDatos) {
		this.clasesDatos = clasesDatos;
	}
	
	public ArrayList<double[]> getDatosByIndiceClase(int indiceClase) {
		ArrayList<double []> aux =  new ArrayList<double[]>();
		for(int i = 0; i < clasesDatos.size(); i++) {
			if(clasesDatos.get(i).equals(clases.get(indiceClase))) {
				aux.add(datos.get(i));
			}
		}
		return aux;
	}
	
	public void clean() {
		datos = new ArrayList<double[]> ();
		clases = new ArrayList<String>();
		clasesDatos = new ArrayList<String>();
		centrosIni = new ArrayList<double[]> ();
		ejemplos = new ArrayList<double[]> ();
		clasesEjemplos =  new ArrayList<String>();

	}
	
	public void reiniciar() {
		ejemplos = new ArrayList<double[]> ();
		clasesEjemplos =  new ArrayList<String>();
		inicializaAlgoritmos();
	}
	public void addEjemplo(double[] ejemplo) {
		if(ejemplos == null) {
			ejemplos = new ArrayList<double[]>();
		}
		ejemplos.add(ejemplo);
		
	}

	public void inicializaAlgoritmos() {
		if(this.rutaFichero != null) {
			this.kmeans = new Kmeans(this.datos, this.centrosIni);
			this.lloyd = new Lloyd(this.datos,this.centrosIni); 
			this.bayes = new Bayes(this.datos,this.clasesDatos);
		}
	}

	public int getNumMCovarianzas() {
		if(this.bayes == null || this.bayes.getmCovarianzas().isEmpty()) {
			return 0;
		}
		return this.bayes.getmCovarianzas().size();
	}

}
