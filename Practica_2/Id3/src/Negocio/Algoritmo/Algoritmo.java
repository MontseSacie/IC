package Negocio.Algoritmo;

import java.util.ArrayList;
import Negocio.Objetos.Dataset;
import Negocio.Objetos.Ejemplo;
import Negocio.Objetos.Nodo;

public class Algoritmo {

	private ArrayList<ArrayList<String>> reglas;

	public Algoritmo() {
		this.reglas = new ArrayList<ArrayList<String>>();
	}

	public Nodo iniciarAlgoritmo(ArrayList<Ejemplo> listaEjemplos, ArrayList<String> listaAtributos) {
		Dataset datos = new Dataset(listaAtributos, listaEjemplos);
		Nodo raiz = new Nodo(null, datos);
		return execute(raiz);
	}

	public Nodo execute(Nodo raiz) {
		if (raiz.getSubtabla().getEjemplos().isEmpty() == true) {
			return null;
		}

		// si todos los ejemplos son + o todos -
		String resultado = raiz.getSubtabla().getEjemplos().get(0)
				.getValorAtributo(raiz.getSubtabla().getNomResultado());
		int cont = raiz.getSubtabla().getNumEjemplosConResultado(resultado);
		if (cont == raiz.getSubtabla().getEjemplos().size()) {
			raiz.setValor(resultado);
			return raiz;
		}

		if (raiz.getSubtabla().getNomAtributos().isEmpty()) {
			throw new NullPointerException("La lista de atributos está vacía");
		} else {
			raiz.calculaAtributoMasInformativo();
			raiz.calcularNodosHijos();
			for (Nodo r : raiz.getHijos()) {
				r = execute(r);
			}
		}
		return raiz;
	}

	public ArrayList<ArrayList<String>> calcularReglas(Nodo arbolSolucion) {
		reglas = new ArrayList<ArrayList<String>>();

		ArrayList<String> r = new ArrayList<String>();
		if (arbolSolucion.getHijos().isEmpty()) {
			if (arbolSolucion.isHoja()) {
				String a = arbolSolucion.getAtributoMejor().getNombre();
				if (arbolSolucion.getValor().equals("si")) {
					r.add("Siempre");
					r.add(a + " = " + "sí");
				} else {
					r.add("Siempre");
					r.add(a + " = " + "no");
				}
				reglas.add(r);
			}
		} else {
			for (int i = 0; i < arbolSolucion.getHijos().size(); i++) {
				recorridoEnProfundidad(arbolSolucion.getHijos().get(i), new ArrayList<String>(r),
						arbolSolucion.getAtributoMejor().getNombre());

			}
		}
		return reglas;
	}

	private void recorridoEnProfundidad(Nodo raiz, ArrayList<String> r, String nombrePadre) {
		if (raiz.getHijos().isEmpty()) {
			if (raiz.isHoja()) {
				r.add(nombrePadre + " = " + raiz.getValorPadre());
				r.add(raiz.getSubtabla().getNomResultado() + " = " + raiz.getValor());
				reglas.add(r);
			}
		} else {
			r.add(nombrePadre + " = " + raiz.getValorPadre());
			for (int i = 0; i < raiz.getHijos().size(); i++)
				recorridoEnProfundidad(raiz.getHijos().get(i), new ArrayList<String>(r),
						raiz.getAtributoMejor().getNombre());
		}
	}

	public String comprobarEjemplo(ArrayList<String> e, Nodo arbol) {
		if (arbol.isHoja()) {
			return arbol.getValor();
		} else {
			for (Nodo n : arbol.getHijos()) {
				if (e.contains(n.getValorPadre())) {
					return comprobarEjemplo(e, n);
				}
			}
		}
		return "";
	}
}
