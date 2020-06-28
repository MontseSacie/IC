package Negocio.Objetos;

import java.util.HashMap;

public class Atributo {
	private String nombre;
	private HashMap<String, Integer> positivos; // Valor del atributo - numero de positivos
	private HashMap<String, Integer> valores; // Nombre valor -numero de ejemplos con atributo = valor
	private int numEjemplos;
	private double merito;

	public Atributo(String nombre, Dataset tabla) {
		this.nombre = nombre;
		this.positivos = new HashMap<String, Integer>();
		this.valores = new HashMap<String, Integer>();
		this.numEjemplos = tabla.getN();
		calculaPositivosYNegativos(tabla);
		calculaMerito();
	}

	public void calculaPositivosYNegativos(Dataset tabla) {
		Ejemplo e;
		String valor;
		for (int i = 0; i < tabla.getN(); i++) {
			e = tabla.getEjemploByIndex(i);
			valor = e.getValorAtributo(this.nombre);
			if (this.valores.containsKey(valor)) {
				this.valores.put(valor, this.valores.get(valor) + 1);
				if (e.getValorAtributo(tabla.getNomResultado()).equals("si")) {
					this.positivos.put(valor, this.positivos.get(valor) + 1);
				}
			} else { // primer ejemplo que toma ese valor para el atributo
				this.valores.put(valor, 1);
				if (e.getValorAtributo(tabla.getNomResultado()).equals("si")) {
					this.positivos.put(valor, 1);
				} else {
					this.positivos.put(valor, 0);
				}
			}
		}
	}

	public double infor(double p, double n) {
		if (p == 0) {
			if (n != 0) {
				return -n * (Math.log(n) / Math.log(2));
			} else {
				return 0;
			}
		}
		if (n != 0) {
			return -p * (Math.log(p) / Math.log(2)) - n * (Math.log(n) / Math.log(2));
		}
		return -p * (Math.log(p) / Math.log(2));
	}

	public void calculaMerito() {
		this.merito = 0;
		double r = 0;
		double n = 0;
		for (String valor : this.valores.keySet()) {
			r = this.valores.get(valor).doubleValue() / this.numEjemplos;
			n = this.valores.get(valor).doubleValue() - this.positivos.get(valor).doubleValue();
			this.merito = this.merito
					+ r * infor(this.positivos.get(valor).doubleValue() / this.valores.get(valor).doubleValue(),
							n / this.valores.get(valor).doubleValue());
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getMerito() {
		return merito;
	}

	public void setMerito(double merito) {
		this.merito = merito;
	}

	public HashMap<String, Integer> getPositivos() {
		return positivos;
	}

	public void setPositivos(HashMap<String, Integer> positivos) {
		this.positivos = positivos;
	}

	public HashMap<String, Integer> getValores() {
		return valores;
	}

	public void setValores(HashMap<String, Integer> valores) {
		this.valores = valores;
	}

	public int getNumEjemplos() {
		return numEjemplos;
	}

	public void setNumEjemplos(int numEjemplos) {
		this.numEjemplos = numEjemplos;
	}

}
