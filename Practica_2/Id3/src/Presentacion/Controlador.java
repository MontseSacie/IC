package Presentacion;

import java.util.ArrayList;

import Negocio.Algoritmo.Algoritmo;
import Negocio.Objetos.Atributo;
import Negocio.Objetos.Nodo;

public class Controlador {
	private static Controlador instance = null;
	private Nodo arbol;
	private ArrayList<ArrayList<String>> reglas;
	private Algoritmo algoritmo;

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
		}

		return instance;
	}

	public ArrayList<ArrayList<String>> getReglas() {
		return reglas;
	}

	public void setReglas(ArrayList<ArrayList<String>> reglas) {
		this.reglas = reglas;
	}

	public Nodo getArbol() {
		return arbol;
	}

	public void setArbol(Nodo raizArbol) {
		this.arbol = raizArbol;
	}

	public void pintarArbolyReglas() {
		// TODO Auto-generated method stub
		VistaPrincipal.getInstance().pintar(arbol, reglas);

	}

	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}

	public void escribeMeritos(Nodo nodo, int it,String padre) {
		if (nodo.isHoja() == false) {
			System.out.println("Iteración " + it);
			if (it != 0) {
				System.out.println("Rama ->" + padre + " = " + nodo.getValorPadre());
			}
			System.out.println("----------------");
			String s = "";
			for (Atributo a : nodo.getAtributos()) {
				if (a.getNombre() == nodo.getAtributoMejor().getNombre()) {
					s = a.getNombre() + " = " + a.getMerito() + " (menor mérito)";
				} else {
					s = a.getNombre() + " = " + a.getMerito();
				}
				System.out.println(s);
				
			}
			System.out.println("");
			System.out.println("################");
			for (Nodo hijo : nodo.getHijos()) {
				escribeMeritos(hijo, it + 1,nodo.getAtributoMejor().getNombre());
			}
		}
	}

	public String comprobarEjemplo(ArrayList<String> ejemplo, Nodo raiz) {
		algoritmo = VistaPrincipal.getInstance().getA();
		return algoritmo.comprobarEjemplo(ejemplo,raiz);
	}

	public void setAlgoritmo(Algoritmo a) {
		this.algoritmo = a;
		
	}

}
