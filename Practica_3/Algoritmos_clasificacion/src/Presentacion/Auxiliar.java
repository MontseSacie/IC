package Presentacion;

import java.util.ArrayList;

import Jama.Matrix;

public class Auxiliar {
	private static Auxiliar instance = null;
	
	public static Auxiliar getInstance() {
		if (instance == null) {
			instance = new Auxiliar();
		}
		return instance;
	}
	
	public static String ejemplos2String(ArrayList<double[]> ejemplos) {
		String sol = "";
		int pos = 0;
		for(double[] ej: ejemplos){
			for(int i = 0; i < ej.length; i++){
				if(i == 0)
					sol += (pos+1) + ".) "+ ej[i];
				else
					sol += ", " + ej[i];
			}
			sol += "\n";
			pos++;
		}
		
		return sol;
		
	}
	public static String datos2String(ArrayList<double[]> datos){
		String sol = "";
		for(int i = 0; i < datos.size(); i++){
			for(int j = 0; j < datos.get(i).length; j++){
				if(j == 0)
					sol += datos.get(i)[j];
				else
					sol += ", " + datos.get(i)[j];			
			}
			sol += "\n";
		}	
		
		return sol;
	}

	public static String clases2String(ArrayList<String> clases) {
		String s = "";
		int i = 0;
		for(String c: clases) {
			s += (i+1)+ ".) " + c + "\n";
			i++;
		}
		return s;
	}

	public static String centros2String(ArrayList<double[]> centros) {
		String text = "";
		for(int i = 0; i < centros.size(); i++) {
			text += (i+1) + ". [";
			for(int j = 0; j < centros.get(i).length; j++) {
				text += roundDecimals(centros.get(i)[j],2);
				if(j != centros.get(i).length - 1) {
					text += ", ";
				}
			}
			text += "] " + "\n";
		}
		return text;
	}
	
	public static String matrix2String(Matrix m) {
		String s = "";
		s += "---Matriz---------" + "\n";
		for(int i = 0; i < m.getRowDimension(); i++) {
			for (int j = 0; j <m.getColumnDimension(); j++) {
				s += roundDecimals(m.get(i, j),3);
				if (j != m.getColumnDimension() - 1) {
					s += "  ";
				}
			}
			s += "\n";
		}
		s += "------------------" + "\n";
		return s;
	}
	 public static double roundDecimals(double valorInicial, int numeroDecimales) {
	        double parteEntera, resultado;
	        resultado = valorInicial;
	        resultado =  resultado *  Math.pow(10, numeroDecimales);
	        resultado = Math.floor(resultado);
	        resultado = resultado / Math.pow(10, numeroDecimales);
	        //parteEntera = Math.floor(resultado);
	        //resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        //resultado=Math.round(resultado);
	        //resultado = Math.floor(resultado);
	        //resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        return resultado;
	    }

	public static String u2String(double[][] u) {
		// TODO Auto-generated method stub
		String s = "";
		s += "---Matriz---------" + "\n";
		for(int i = 0; i < u.length; i++) {
			for (int j = 0; j <u[i].length; j++) {
				s += roundDecimals(u[i][j],3);
				if (j != u[i].length - 1) {
					s += "  ";
				}
			}
			s += "\n";
		}
		s += "------------------" + "\n";
		return s;
	}

	public static String centrosIni2String(ArrayList<double[]> centros) {
		String text = "";
		for(int i = 0; i < centros.size(); i++) {
			text += (i+1) + ". [";
			for(int j = 0; j < centros.get(i).length; j++) {
				text += centros.get(i)[j];
				if(j != centros.get(i).length - 1) {
					text += ", ";
				}
			}
			text += "] " + "\n";
		}
		return text;
	}
	
	
}
