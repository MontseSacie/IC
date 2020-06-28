package Negocio.Objetos;

import java.util.ArrayList;

public class Nodo {
	private Atributo atributoMejor; // nombre del atributo más informativo
	private String valor; // distinto de null si se trata de un nodo hoja
	private String valorPadre;
	private Dataset subtabla; // subtabla para aplicar de nuevo id3
	private ArrayList<Atributo> atributos; // atributos para el cálculo del mérito
	private ArrayList<Nodo> hijos; // nodos a los que lleva cada rama del atributo mejor
	private boolean isHoja = false;

	public Nodo(String valor, Dataset subtabla) {
		this.subtabla = subtabla;
		this.hijos = new ArrayList<Nodo>();
		if (valor != null) {
			this.valor = valor;
			this.isHoja = true;
		}
		this.atributos = new ArrayList<Atributo>();
		inicializaAtributos();

	}

	private void inicializaAtributos() {
		ArrayList<String> nombres = this.subtabla.getNomAtributos();
		for (int i = 0; i < nombres.size(); i++) {
			Atributo a = new Atributo(nombres.get(i), this.subtabla);
			this.atributos.add(a);
		}
	}

	public void calculaAtributoMasInformativo() {
		atributoMejor = this.atributos.get(0);
		for (int i = 1; i < this.atributos.size(); i++) {
			if (this.atributoMejor.getMerito() > this.atributos.get(i).getMerito()) {
				this.atributoMejor = this.atributos.get(i);
			}
		}
	}

	public void calcularNodosHijos() {
		for (String v : this.atributoMejor.getValores().keySet()) {
			Dataset nuevo = new Dataset(this.subtabla); // creo una tabla nueva por copia
			// dejo solo ejemplos con el valor v (una rama)
			nuevo.setEjemplos(nuevo.ejemplosConValorEnAtributo(this.atributoMejor.getNombre(), v));
			Nodo n = new Nodo(null, nuevo);
			n.setValorPadre(v);
			n.getAtributos().remove(this.atributoMejor);
			n.getSubtabla().setNomAtributos(n.getNombresAtributos());
			this.hijos.add(n);
		}
		for (Nodo n : this.hijos) {
			n.removeAtributo(this.atributoMejor.getNombre());
		}
	}

	private void removeAtributo(String nombre) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.atributos.size(); i++) {
			if (this.atributos.get(i).getNombre() == nombre) {
				this.atributos.remove(i);
			}
		}
		for (Ejemplo e : this.subtabla.getEjemplos()) {
			e.eliminarAtributo(nombre);
		}
	}

	public String getValorPadre() {
		return valorPadre;
	}

	public void setValorPadre(String valorPadre) {
		this.valorPadre = valorPadre;
	}

	public void setAtributoMejor(Atributo atributoMejor) {
		this.atributoMejor = atributoMejor;
	}

	public Atributo getAtributoMejor() {
		return atributoMejor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.isHoja = true;
		this.valor = valor;
	}

	public Dataset getSubtabla() {
		return subtabla;
	}

	public void setSubtabla(Dataset subtabla) {
		this.subtabla = subtabla;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}

	public boolean isHoja() {
		return isHoja;
	}

	public void setHoja(boolean isHoja) {
		this.isHoja = isHoja;
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<Atributo> atributos) {
		this.atributos = atributos;
	}

	public ArrayList<String> getNombresAtributos() {
		ArrayList<String> sol = new ArrayList<String>();
		for (Atributo a : this.atributos) {
			sol.add(a.getNombre());
		}
		return sol;
	}

}
