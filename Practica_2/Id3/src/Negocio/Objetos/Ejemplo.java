package Negocio.Objetos;

import java.util.ArrayList;
import java.util.HashMap;

public class Ejemplo {
	HashMap<String, String> atributos; // incluye la variable resultado junto a su valor

	public Ejemplo(ArrayList<String> ats, ArrayList<String> valores) {
		this.atributos = new HashMap<String, String>();
		for (int i = 0; i < valores.size(); i++) {
			this.atributos.put(ats.get(i), valores.get(i));
		}
	}

	public HashMap<String, String> getEjemplo() {
		return atributos;
	}

	public void setEjemplo(HashMap<String, String> ejemplo) {
		this.atributos = ejemplo;
	}

	public void addAtributo(String nombre, String valor) {
		atributos.put(nombre, valor);
	}

	public String getValorAtributo(String nombre) {
		return atributos.get(nombre);
	}

	public void eliminarAtributo(String nombre) {
		this.atributos.remove(nombre);
	}

}
