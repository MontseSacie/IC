package Negocio.Auxiliar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Negocio.Objetos.Ejemplo;

public final class LecturaDatos {

	public static ArrayList<String> readAtributos(String nomFile) {
		if (nomFile == null || nomFile.equals("")) {
			throw new NullPointerException();
		}
		File file = new File(nomFile);
		String[] atributos = null;
		ArrayList<String> listaAtributos = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				atributos = line.split(",");
			}

			for (String a : atributos) {
				listaAtributos.add(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return listaAtributos;
	}

	public static ArrayList<Ejemplo> readEjemplos(String nomFile, ArrayList<String> atributos) {
		if (nomFile == null || nomFile.equals("")) {
			throw new NullPointerException();
		}
		File file = new File(nomFile);
		ArrayList<Ejemplo> listaEjemplos = new ArrayList<Ejemplo>();
		ArrayList<String> listaValores = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] linea = line.split(",");
				for (String a : linea) {
					listaValores.add(a);
				}
				Ejemplo e = new Ejemplo(atributos, listaValores);
				listaEjemplos.add(e);
				listaValores.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return listaEjemplos;
	}

	public static ArrayList<String> leerNuevoEjemplo(String s) {
		String[] valoresAtributos = s.split(",");
		ArrayList<String> ejemplo = new ArrayList<String>();
		for (String a : valoresAtributos) {
			ejemplo.add(a);
		}
		return ejemplo;
	}
}
