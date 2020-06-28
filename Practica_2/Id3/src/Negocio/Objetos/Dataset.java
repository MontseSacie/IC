package Negocio.Objetos;

import java.util.ArrayList;

public class Dataset {
	// va a contener los ejemplos de la subtabla a la que aplicar el algoritmo Id3
	private ArrayList<Ejemplo> ejemplos;
	private ArrayList<String> nomAtributos; // atributos para el entrenamiento
	private String nomResultado; // atributo resultado

	public Dataset(ArrayList<String> listaAtributos, ArrayList<Ejemplo> listaEjemplos) {
		this.ejemplos = listaEjemplos;
		this.nomAtributos = listaAtributos;
		this.nomResultado = listaAtributos.get(listaAtributos.size() - 1);
		this.nomAtributos.remove(listaAtributos.size() - 1);
	}

	public Dataset(Dataset subtabla) {
		this.ejemplos = subtabla.getEjemplos();
		this.nomAtributos = subtabla.getNomAtributos();
		this.nomResultado = subtabla.getNomResultado();
	}

	public ArrayList<String> getNomAtributos() {
		return nomAtributos;
	}

	public void setNomAtributos(ArrayList<String> nomAtributos) {
		this.nomAtributos = nomAtributos;
	}

	public String getNomResultado() {
		return nomResultado;
	}

	public void setNomResultado(String nomResultado) {
		this.nomResultado = nomResultado;
	}

	public ArrayList<Ejemplo> getEjemplos() {
		return ejemplos;
	}

	public void setEjemplos(ArrayList<Ejemplo> ejemplos) {
		this.ejemplos = ejemplos;
	}

	public void addEjemplo(Ejemplo e) {
		this.ejemplos.add(e);
	}

	public void removeEjemplo(Ejemplo e) {
		this.ejemplos.remove(e);
	}

	public void removeEjemploByIndex(int i) {
		ejemplos.remove(i);
	}

	public Ejemplo getEjemploByIndex(int i) {
		return ejemplos.get(i);
	}

	public int getN() {
		// número de ejemplos
		return ejemplos.size();
	}

	// para construir subtabla
	public ArrayList<Ejemplo> ejemplosConValorEnAtributo(String atributo, String valor) {
		ArrayList<Ejemplo> sol = new ArrayList<Ejemplo>();
		for (int i = 0; i < ejemplos.size(); i++) {
			if (ejemplos.get(i).getValorAtributo(atributo).equals(valor)) {
				sol.add(ejemplos.get(i));
			}
		}
		return sol;
	}

	// Para saber cuantos positivos hay o cuantos negativos o dependiendo del data
	// set otro valor para el resultado
	public int getNumEjemplosConResultado(String valorResultado) {
		int cont = 0;
		for (Ejemplo e : this.ejemplos) {
			if (e.getValorAtributo(this.nomResultado).equals(valorResultado)) {
				cont++;
			}
		}
		return cont;
	}
}
