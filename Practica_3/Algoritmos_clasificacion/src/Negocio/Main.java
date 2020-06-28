package Negocio;

import java.util.ArrayList;
import java.util.HashMap;

import Jama.Matrix;
import Negocio.Algoritmos.Bayes;
import Negocio.Algoritmos.Kmeans;
import Negocio.Algoritmos.Lloyd;
import Presentacion.Auxiliar;
import Presentacion.Controlador;
import Presentacion.VistaPrincipal;

public class Main {
	public static void main(String[] a) {
		try{
			VistaPrincipal.getInstance();
		
		
		//Hoja 2 de ejercicios -> para comprobar funcionamiento
		
		// Ejercicio 1, Hoja 2
		ArrayList<double []> puntos = new ArrayList<double []>();
		puntos.add(new double[] {3,1});
		puntos.add(new double[] {2,2});
		puntos.add(new double[] {1,0});
		puntos.add(new double[] {6,7});
		puntos.add(new double[] {7,5});
		puntos.add(new double[] {8,6});
		
		ArrayList<String> clases = new ArrayList<String>();//clase de cada punto
		clases.add("Clase 1");
		clases.add("Clase 1");
		clases.add("Clase 1");
		clases.add("Clase 2");
		clases.add("Clase 2");
		clases.add("Clase 2");
		
		ArrayList<double []> centros = new ArrayList<double[]>();
		centros.add(new double[] {2,1});
		centros.add(new double[] {7,6});
		
		Bayes b = new Bayes(puntos,clases);
		b.execute();
		System.out.println("########################################## \n");
		System.out.println("Algoritmo de Bayes -  Ejercicio 1, Hoja 2");
		System.out.println("..........................................");
		System.out.println("Centros obtenidos:");
		System.out.println(Auxiliar.centros2String(b.getCentros()));
		System.out.println("Matrices de Covarianza:");
		HashMap<String, Matrix> mCov = b.getmCovarianzas();
		for(String c: mCov.keySet()) {
			System.out.println("Clase: " + c + "\n");
			System.out.println(Auxiliar.matrix2String(mCov.get(c)));
		}
		
		//Ejercicio 2 Hoja 2
		Kmeans k_medias = new Kmeans(puntos,centros);
		k_medias.execute();
		ArrayList<double[]> sol2 = k_medias.getCentros();
		System.out.println("########################################## \n");
		System.out.println("Algoritmo k_means borroso - Ejercicio 2, Hoja 2");
		System.out.println("..........................................");
		System.out.println("Centros obtenidos:");
		System.out.println(Auxiliar.centros2String(sol2));
		System.out.println("Matriz de grados de pertenencia U:");
		System.out.println(Auxiliar.u2String(k_medias.getU()));
		
		//Ejercicio 3 Hoja 2
		
		Lloyd l = new Lloyd(puntos,centros);
		l.execute();
		ArrayList<double[]> sol = l.getCentros();
		System.out.println("########################################## \n");
		System.out.println("Algoritmo de Lloyd - Ejercicio 3, Hoja 2");
		System.out.println("..........................................");
		System.out.println("Centros obtenidos:");
		System.out.println(Auxiliar.centros2String(sol));
		}catch(Exception e) {
			System.out.println("Fichero con formato incorrecto");
			Controlador.getInstance().clean();
		}
	}
}
